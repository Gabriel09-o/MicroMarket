package com.PeaceOfMind.MicroMarket.dtos;

import java.util.List;

public class VentaDTO {

    private Long id;
    private Long empleadoId;
    private Double subtotal;
    private Double iva;
    private Double total;
    private List<DetalleVentaDTO> detalles;

    public VentaDTO() {}

    public VentaDTO(Long id, Long empleadoId, Double subtotal,
                    Double iva, Double total,
                    List<DetalleVentaDTO> detalles) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.detalles = detalles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(Long empleadoId) { this.empleadoId = empleadoId; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public Double getIva() { return iva; }
    public void setIva(Double iva) { this.iva = iva; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public List<DetalleVentaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDTO> detalles) { this.detalles = detalles; }
}