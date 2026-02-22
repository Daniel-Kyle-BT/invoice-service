package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository;

import java.util.List;
import java.util.Optional;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.base.BaseRepository;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;

public interface BoletaRepository extends BaseRepository<BoletaEntity, Long> {

	Optional<BoletaEntity> findByCodigo(String codigo);

	boolean existsByCodigo(String codigo);

	Optional<BoletaEntity> findWithDetalles(Long id);

	List<BoletaEntity> findSingleAll();
}
