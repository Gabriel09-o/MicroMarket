package com.PeaceOfMind.MicroMarket.dtos;

import java.math.BigDecimal;
import java.util.List;

public class VentaDTO {

    private Long id;
    private Long empleadoId;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal total;
    private List<DetalleVentaDTO> detalles;

    public VentaDTO() {}

    public VentaDTO(Long id, Long empleadoId, BigDecimal subtotal,
                    BigDecimal iva, BigDecimal total,
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

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getIva() { return iva; }
    public void setIva(BigDecimal iva) { this.iva = iva; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public List<DetalleVentaDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaDTO> detalles) { this.detalles = detalles; }
}