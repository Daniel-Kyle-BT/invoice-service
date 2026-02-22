package com.company.dry_cleaners.invoicing.soap.response;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(
        name = "ListarBoletasResponse",
        namespace = "http://ms.boleta.soap")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ListarBoletasResponse {

    @XmlElement(name = "boletas")
    private List<BoletaResumenResponse> boletas;
}
