package com.company.dry_cleaners.invoicing.audit.jpa;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // Fase 1: sistema sin auth real
        return Optional.of("external-user");

        // Fase 2 (JWT):
        // return Optional.of(SecurityContextHolder.getContext()
        //        .getAuthentication()
        //        .getName());
    }
}