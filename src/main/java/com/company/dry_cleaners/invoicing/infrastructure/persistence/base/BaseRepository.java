package com.company.dry_cleaners.invoicing.infrastructure.persistence.base;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseRepository<E, ID> {

	E save(E entity);

    Optional<E> findById(ID id);
    
    boolean existsById(ID id);
    
    Page<E> findAll(Pageable pageable);

    void delete(E entity);

}
