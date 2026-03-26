package com.PeaceOfMind.MicroMarket.dtos;

import java.util.List;

import lombok.Data;

@Data
public class VentaDTO {

    private Long id;
    private Long empleadoId;
    private double subtotal;
    private double iva;
    private double total;
    private List<DetalleVentaDTO> detalles;

    public VentaDTO() {
    }

    public VentaDTO(Long id, Long empleadoId, double subtotal,
            double iva, double total,
            List<DetalleVentaDTO> detalles) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.detalles = detalles;
    }

}