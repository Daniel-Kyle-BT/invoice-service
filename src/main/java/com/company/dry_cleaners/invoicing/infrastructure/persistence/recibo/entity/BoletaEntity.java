package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.company.dry_cleaners.invoicing.audit.AuditableEntity;
import com.company.dry_cleaners.invoicing.soap.type.DetalleBoletaRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boletas")
@SQLDelete(sql = "UPDATE boletas SET deleted_at = NOW() WHERE id = ? AND version = ?")
@SQLRestriction("deleted_at IS NULL")
public class BoletaEntity extends AuditableEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "codigo", nullable = false, length = 8)
	private String codigo;

	@Column(name = "id_cliente", nullable = false)
	private Long idCliente;

	@Column(nullable = false)
	private LocalDateTime fecha;

	@Column(nullable = false)
	private BigDecimal total;

	@Column(nullable = false)
	private String estado;

	@OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BoletaDetalleEntity> detalles = new ArrayList<>();

	@Version
	private Long version;

	public void addDetalle(BoletaDetalleEntity detalle) {
		detalle.setBoleta(this);
		this.detalles.add(detalle);
	}

	@PrePersist
	void prePersist() {
		if (estado == null) {
			estado = "PENDIENTE";
		}
	}

	public void cambiarEstado(String nuevoEstado) {
		if (this.estado.equals("ANULADA")) {
			throw new IllegalStateException("No se puede modificar una boleta anulada");
		}
		this.estado = nuevoEstado;
	}

	public void actualizarDetalles(List<DetalleBoletaRequest> requestDetalles) {
		Map<Long, BoletaDetalleEntity> actuales = this.detalles.stream()
				.collect(Collectors.toMap(BoletaDetalleEntity::getId, d -> d));

		List<BoletaDetalleEntity> nuevaLista = new ArrayList<>();

		for (DetalleBoletaRequest dto : requestDetalles) {

			BoletaDetalleEntity detalle;
			// ===== UPDATE =====
			if (dto.getIdDetalle() != null) {
				detalle = actuales.remove(dto.getIdDetalle());
				if (detalle == null) {
					throw new IllegalStateException("Detalle no pertenece a la boleta");
				}
			}
			// ===== CREATE =====
			else {
				detalle = new BoletaDetalleEntity();
				detalle.setBoleta(this);
			}
			detalle.actualizarDesde(dto);
			nuevaLista.add(detalle);
		}
		// orphanRemoval elimina los sobrantes
		this.detalles.clear();
		this.detalles.addAll(nuevaLista);

		recalcularTotal();
	}

	private void recalcularTotal() {
		this.total = this.detalles.stream().map(BoletaDetalleEntity::getSubtotal).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}
}