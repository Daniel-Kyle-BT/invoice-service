	package com.company.dry_cleaners.invoicing.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository.BoletaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ObtenerBoletaQueryService {

    private final BoletaRepository repository;

    public BoletaEntity ejecutar(Long id) {
        return repository.findWithDetalles(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Boleta no encontrada"));
    }
}