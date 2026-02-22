package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.base.BaseRepositoryImpl;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;

@Repository
public class BoletaRepositoryImpl extends BaseRepositoryImpl<BoletaEntity, Long, BoletaJpaRepository>
		implements BoletaRepository {

	private final BoletaJpaRepository jpa;

	public BoletaRepositoryImpl(BoletaJpaRepository jpa) {
		super(jpa);
		this.jpa = jpa;
	}

	@Override
	public List<BoletaEntity> findSingleAll() {
		return jpa.findAll();
	}
	
	@Override
	public Optional<BoletaEntity> findByCodigo(String codigo) {
		return jpa.findByCodigo(codigo);
	}

	@Override
	public Optional<BoletaEntity> findWithDetalles(Long id) {
	    return jpa.findWithDetalles(id);
	}
	
	@Override
	public boolean existsByCodigo(String codigo) {
		return jpa.existsByCodigo(codigo);
	}
}