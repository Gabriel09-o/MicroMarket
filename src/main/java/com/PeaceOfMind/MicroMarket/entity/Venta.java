package com.PeaceOfMind.MicroMarket.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE Ventas SET deleted = true WHERE id_venta = ?")
@SQLRestriction("deleted = false")

@Entity
@Table(name = "Ventas")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double iva;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double total;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    @Column(nullable = false)
    private Boolean deleted = false;
}