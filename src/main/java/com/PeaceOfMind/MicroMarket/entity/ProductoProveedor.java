package com.PeaceOfMind.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "Productos_Proveedores")
@SQLDelete(sql = "UPDATE Productos_Proveedores SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")

@Data
public class ProductoProveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;

    @Column(nullable = false)
    private boolean deleted = false;
}