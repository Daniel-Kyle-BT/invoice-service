package com.company.dry_cleaners.invoicing.soap.request;

import java.util.List;

import com.company.dry_cleaners.invoicing.soap.type.DetalleBoletaRequest;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@XmlRootElement(
        name = "ActualizarBoletaRequest",
        namespace = "http://ms.boleta.soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ActualizarBoletaRequest {

    private Long idBoleta;  
    private Long idCliente;

    @XmlElement(name = "detalles")
    private List<DetalleBoletaRequest> detalles;
}
