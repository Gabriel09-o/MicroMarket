package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PeaceOfMind.MicroMarket.entity.Category;
import com.PeaceOfMind.MicroMarket.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category crear(@RequestBody Category category) {
        return categoryService.crear(category);
    }

    @GetMapping
    public List<Category> listar() {
        return categoryService.listar();
    }

    @GetMapping("/{id}")
    public Category buscar(@PathVariable Long id) {
        return categoryService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Category actualizar(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.actualizar(id, category);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        categoryService.eliminar(id);
    }
}