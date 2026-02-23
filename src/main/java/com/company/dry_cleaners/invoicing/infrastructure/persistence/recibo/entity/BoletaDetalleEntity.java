package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity;

import java.math.BigDecimal;

import com.company.dry_cleaners.invoicing.soap.type.DetalleBoletaRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "boleta_detalles")
public class BoletaDetalleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_producto", nullable = false)
	private Long idProducto;

	private String nombre;

	private Integer cantidad;

	private BigDecimal precio;

	private BigDecimal subtotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boleta_id")
	private BoletaEntity boleta;
	
	public void actualizarDesde(DetalleBoletaRequest dto) {

	    this.idProducto = dto.getIdProducto();
	    this.nombre = dto.getNombre();
	    this.cantidad = dto.getCantidad();
	    this.precio = dto.getPrecio();

	    this.subtotal = precio.multiply(
	            BigDecimal.valueOf(cantidad)
	    );
	}
}