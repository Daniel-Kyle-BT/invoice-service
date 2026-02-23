package com.company.dry_cleaners.invoicing.soap.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(
        name = "ObtenerBoletaResponse",
        namespace = "http://ms.boleta.soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class BoletaResponse {

    private Long id;
    private String codigo;
    private Long idCliente;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal total;

    @XmlElement(name = "detalles")
    private List<DetalleResponse> detalles;

    @Getter
    @Setter
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DetalleResponse {
    	private Long idBoleta;
        private Long idProducto;
        private String nombre;
        private Integer cantidad;
        private BigDecimal precio;
        private BigDecimal subtotal;
    }
}