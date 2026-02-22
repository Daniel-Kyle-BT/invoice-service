package com.company.dry_cleaners.invoicing.infrastructure.persistence.adapter;

import org.springframework.stereotype.Service;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.sequence_code.CodigoSecuencia;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.sequence_code.CodigoSecuenciaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CodigoGeneratorAdapter implements CodigoGenerator {

    private final CodigoSecuenciaRepository repository;

    @Transactional
    public String generar(String prefijo) {

        CodigoSecuencia secuencia = repository.findByIdForUpdate(prefijo)
            .orElseThrow(() -> new IllegalStateException("Prefijo no configurado"));

        long next = secuencia.getUltimoValor() + 1;
        secuencia.setUltimoValor(next);

        return prefijo + String.format("%05d", next);
    }
}
