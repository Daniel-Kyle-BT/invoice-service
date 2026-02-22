package com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;

public interface BoletaJpaRepository extends JpaRepository<BoletaEntity, Long> {

	Optional<BoletaEntity> findByCodigo(String codigo);

	boolean existsByCodigo(String codigo);

	@Query("""
			    SELECT b
			    FROM BoletaEntity b
			    LEFT JOIN FETCH b.detalles
			    WHERE b.id = :id
			""")
	Optional<BoletaEntity> findWithDetalles(Long id);
}
