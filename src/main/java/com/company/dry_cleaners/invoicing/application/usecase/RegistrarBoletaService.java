package com.company.dry_cleaners.invoicing.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.adapter.CodigoGenerator;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaDetalleEntity;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.repository.BoletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrarBoletaService {

    private final BoletaRepository repository;
    private final CodigoGenerator codigoGenerator;

    public BoletaEntity ejecutar(CrearBoletaCommand command) {

        BoletaEntity boleta = new BoletaEntity();

        boleta.setCodigo(codigoGenerator.generar("BOL"));
        boleta.setIdCliente(command.getIdCliente());
        boleta.setFecha(LocalDateTime.now());

        List<BoletaDetalleEntity> detalles = command.getDetalles()
                .stream()
                .map(d -> crearDetalle(d, boleta))
                .toList();

        boleta.setDetalles(detalles);

        // calcular total
        BigDecimal total = detalles.stream()
                .map(BoletaDetalleEntity::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        boleta.setTotal(total);

        return repository.save(boleta);
    }

    private BoletaDetalleEntity crearDetalle(
            CrearBoletaCommand.DetalleCommand d,
            BoletaEntity boleta) {

        BoletaDetalleEntity det = new BoletaDetalleEntity();

        det.setBoleta(boleta);
        det.setIdProducto(d.getIdProducto());
        det.setNombre(d.getNombre());
        det.setCantidad(d.getCantidad());
        det.setPrecio(d.getPrecio());
        
        det.setSubtotal(
                d.getPrecio().multiply(
                    BigDecimal.valueOf(d.getCantidad())
                )
            );

        return det;
    }
    
    
    
}
