package com.company.dry_cleaners.invoicing.soap.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(
        name = "CrearBoletaRequest",
        namespace = "http://ms.boleta.soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class CrearBoletaRequest {
	
	@XmlElement(name = "idCliente", namespace = "http://ms.boleta.soap")
    private Long idCliente;

	@XmlElement(
	        name = "detalles",
	        namespace = "http://ms.boleta.soap"
	    )
    private List<DetalleRequest> detalles;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetalleRequest {

    	@XmlElement(namespace = "http://ms.boleta.soap")
        private Long idProducto;

        @XmlElement(namespace = "http://ms.boleta.soap")
        private String nombre;

        @XmlElement(namespace = "http://ms.boleta.soap")
        private Integer cantidad;

        @XmlElement(namespace = "http://ms.boleta.soap")
        private BigDecimal precio;
    }
}
