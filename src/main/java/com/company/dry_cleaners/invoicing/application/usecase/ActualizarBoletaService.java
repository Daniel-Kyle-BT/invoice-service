package com.company.dry_cleaners.invoicing.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository.BoletaRepository;
import com.company.dry_cleaners.invoicing.soap.request.ActualizarBoletaRequest;
import com.company.dry_cleaners.invoicing.soap.request.CambiarEstadoBoletaRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActualizarBoletaService {

	private final BoletaRepository repository;

	@Transactional
	public void ejecutar(ActualizarBoletaRequest request) {

		BoletaEntity boleta = repository.findById(request.getIdBoleta()).orElseThrow();
		
		boleta.setIdCliente(request.getIdCliente());
		boleta.actualizarDetalles(request.getDetalles());
	}

	@Transactional
	public void cambiarEstado(CambiarEstadoBoletaRequest request) {
		
		BoletaEntity boleta = repository.findById(request.getIdBoleta()).orElseThrow();
		
		boleta.cambiarEstado(request.getEstado());
	}
}
