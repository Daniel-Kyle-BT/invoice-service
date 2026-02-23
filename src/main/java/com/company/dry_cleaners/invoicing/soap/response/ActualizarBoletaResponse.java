package com.company.dry_cleaners.invoicing.soap.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(
        name = "ActualizarBoletaResponse",
        namespace = "http://ms.boleta.soap"
)
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ActualizarBoletaResponse {

    private String mensaje;
}