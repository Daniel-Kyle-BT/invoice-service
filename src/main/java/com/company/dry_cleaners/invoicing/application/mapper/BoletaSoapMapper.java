package com.company.dry_cleaners.invoicing.application.mapper;

import org.springframework.stereotype.Component;

import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.soap.response.BoletaResponse;

@Component
public class BoletaSoapMapper {

    public BoletaResponse toResponse(BoletaEntity entity) {

        BoletaResponse res = new BoletaResponse();

        res.setId(entity.getId());
        res.setCodigo(entity.getCodigo());
        res.setIdCliente(entity.getIdCliente());
        res.setFecha(entity.getFecha());
        res.setTotal(entity.getTotal());

        res.setDetalles(
            entity.getDetalles().stream().map(d -> {
                BoletaResponse.DetalleResponse det =
                        new BoletaResponse.DetalleResponse();

                det.setIdProducto(d.getIdProducto());
                det.setNombre(d.getNombre());
                det.setCantidad(d.getCantidad());
                det.setPrecio(d.getPrecio());
                det.setSubtotal(d.getSubtotal());

                return det;
            }).toList()
        );

        return res;
    }
}
