package com.PeaceOfMind.MicroMarket.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.entity.Category;
import com.PeaceOfMind.MicroMarket.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category crear(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> listar() {
        return categoryRepository.findAll();
    }

    public Category buscarPorId(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public Category actualizar(Long id, Category nueva) {
        Category categoria = buscarPorId(id);

        categoria.setNombre(nueva.getNombre());
        categoria.setDescripcion(nueva.getDescripcion());

        return categoryRepository.save(categoria);
    }

    public void eliminar(Long id) {
        categoryRepository.deleteById(id);
    }
}