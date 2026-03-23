package com.PeaceOfMind.MicroMarket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVentaId(Long ventaId);

}