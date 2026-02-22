package com.company.dry_cleaners.invoicing.soap.endpoint;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.company.dry_cleaners.invoicing.application.mapper.BoletaSoapMapper;
import com.company.dry_cleaners.invoicing.application.usecase.CrearBoletaCommand;
import com.company.dry_cleaners.invoicing.application.usecase.ListarBoletaQueryService;
import com.company.dry_cleaners.invoicing.application.usecase.ObtenerBoletaQueryService;
import com.company.dry_cleaners.invoicing.application.usecase.RegistrarBoletaService;
import com.company.dry_cleaners.invoicing.infrastructure.persistence.recibo.entity.BoletaEntity;
import com.company.dry_cleaners.invoicing.soap.request.CrearBoletaRequest;
import com.company.dry_cleaners.invoicing.soap.request.ObtenerBoletaRequest;
import com.company.dry_cleaners.invoicing.soap.response.BoletaResponse;
import com.company.dry_cleaners.invoicing.soap.response.BoletaResumenResponse;
import com.company.dry_cleaners.invoicing.soap.response.CrearBoletaResponse;
import com.company.dry_cleaners.invoicing.soap.response.ListarBoletasResponse;

import lombok.RequiredArgsConstructor;

@Endpoint
@RequiredArgsConstructor
public class BoletaEndpoint {

    private static final String NAMESPACE = "http://ms.boleta.soap";

    private final RegistrarBoletaService service;
    private final ObtenerBoletaQueryService obtener;
    private final ListarBoletaQueryService listar;
    private final BoletaSoapMapper mapper;
    
    // ===============================
    // OBTENER
    // ===============================

    @PayloadRoot(namespace = NAMESPACE, localPart = "ObtenerBoletaRequest")
    @ResponsePayload
    public BoletaResponse obtener(
            @RequestPayload ObtenerBoletaRequest request) {

        BoletaEntity entity = obtener.ejecutar(request.getId());

        return mapper.toResponse(entity);
    }

    // ===============================
    // LISTAR
    // ===============================

    @PayloadRoot(namespace = NAMESPACE, localPart = "ListarBoletasRequest")
    @ResponsePayload
    public ListarBoletasResponse listar() {
        List<BoletaEntity> boletas = listar.ejecutar();

        List<BoletaResumenResponse> response =
                boletas.stream()
                       .map(this::mapResumen)
                       .toList();

        ListarBoletasResponse res = new ListarBoletasResponse();
        res.setBoletas(response);

        return res;
    }
    
    
    

    @PayloadRoot(namespace = NAMESPACE, localPart = "CrearBoletaRequest")
    @ResponsePayload
    public CrearBoletaResponse crear(
            @RequestPayload CrearBoletaRequest request) {

        CrearBoletaCommand command = map(request);

        BoletaEntity boleta = service.ejecutar(command);

        return new CrearBoletaResponse(
                boleta.getId(),
                boleta.getCodigo()
        );
    }

    private CrearBoletaCommand map(CrearBoletaRequest request) {

        CrearBoletaCommand cmd = new CrearBoletaCommand();
        cmd.setIdCliente(request.getIdCliente());

        List<CrearBoletaCommand.DetalleCommand> detalles =
                request.getDetalles().stream().map(d -> {

                    CrearBoletaCommand.DetalleCommand det =
                            new CrearBoletaCommand.DetalleCommand();

                    det.setIdProducto(d.getIdProducto());
                    det.setNombre(d.getNombre());
                    det.setCantidad(d.getCantidad());
                    det.setPrecio(d.getPrecio());

                    return det;
                }).toList();

        cmd.setDetalles(detalles);

        return cmd;
    }
    
    private BoletaResumenResponse mapResumen(BoletaEntity b) {
        return new BoletaResumenResponse(
                b.getId(),
                b.getCodigo(),
                b.getIdCliente(),
                b.getEstado(),
                b.getTotal()
        );
    }
}