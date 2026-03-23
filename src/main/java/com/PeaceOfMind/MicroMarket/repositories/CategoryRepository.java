package com.PeaceOfMind.MicroMarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PeaceOfMind.MicroMarket.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}