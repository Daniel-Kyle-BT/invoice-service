package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.company.dry_cleaners.invoicing.audit.AuditableEntity;

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

    @Column(name = "codigo", nullable = false , length = 8)
    private String codigo;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private BigDecimal total;
    
    @Column(nullable = false)
    private String estado;

    @OneToMany(
        mappedBy = "boleta",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
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
}