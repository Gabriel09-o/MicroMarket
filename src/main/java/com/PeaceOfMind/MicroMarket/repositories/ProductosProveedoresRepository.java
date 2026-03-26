package com.PeaceOfMind.MicroMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PeaceOfMind.MicroMarket.entity.ProductoProveedor;
import java.util.List;

@Repository
public interface ProductosProveedoresRepository extends JpaRepository<ProductoProveedor, Long> {

    List<ProductoProveedor> findByProveedorId(Long id);
    }
