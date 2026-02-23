package com.company.dry_cleaners.invoicing.soap.type;

import java.math.BigDecimal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class DetalleBoletaRequest {
	
	private Long idDetalle;
	
    private Long idProducto;
    private String nombre;
    private Integer cantidad;
    private BigDecimal precio;
}