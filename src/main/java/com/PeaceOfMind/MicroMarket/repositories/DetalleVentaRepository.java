package com.PeaceOfMind.MicroMarket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PeaceOfMind.MicroMarket.entity.DetalleVenta;

import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVentaId(Long ventaId);

}