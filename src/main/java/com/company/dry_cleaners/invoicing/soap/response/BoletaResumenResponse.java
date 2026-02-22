package com.company.dry_cleaners.invoicing.soap.response;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoletaResumenResponse {

    private Long id;
    private String codigo;
    private Long idCliente;
    private String estado;
    private BigDecimal total;
}
