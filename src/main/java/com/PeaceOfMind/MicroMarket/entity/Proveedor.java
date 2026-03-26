package com.PeaceOfMind.MicroMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SQLDelete(sql = "UPDATE Proveedores SET deleted = true WHERE id_proveedor = ?")
@SQLRestriction("deleted = false")

@Entity
@Table(name = "Proveedores")
@Data
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 100)
    private String nit;

    @Column(length = 20)
    private String telefono;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 200)
    private String direccion;

    @Column(nullable = false)
    private boolean deleted = false;
}