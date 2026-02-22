package com.company.dry_cleaners.invoicing.infrastructure.persistence.sequence_code;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.LockModeType;

@Repository
public interface CodigoSecuenciaRepository extends JpaRepository<CodigoSecuencia, String> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select s from CodigoSecuencia s where s.prefijo = :prefijo")
	Optional<CodigoSecuencia> findByIdForUpdate(@Param("prefijo") String prefijo);
}
