package com.company.dry_cleaners.invoicing.application.usecase;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearBoletaCommand {

    private Long idCliente;
    private List<DetalleCommand> detalles;

    @Getter
    @Setter
    public static class DetalleCommand {
        private Long idProducto;
        private String nombre;
        private Integer cantidad;
        private BigDecimal precio;
    }
}