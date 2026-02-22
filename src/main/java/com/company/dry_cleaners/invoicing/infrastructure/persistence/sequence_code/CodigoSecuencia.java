package com.company.dry_cleaners.invoicing.infrastructure.persistence.sequence_code;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "codigo_secuencia")
@Getter @Setter
public class CodigoSecuencia {

    @Id
    @Column(length = 10)
    private String prefijo;

    @Column(name = "ultimo_valor", nullable = false)
    private Long ultimoValor;
}
