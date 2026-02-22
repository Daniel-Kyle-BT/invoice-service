package com.company.dry_cleaners.invoicing.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository.BoletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListarBoletaQueryService {

    private final BoletaRepository repository;

    public List<BoletaEntity> ejecutar() {
        return repository.findSingleAll();
    }
}
