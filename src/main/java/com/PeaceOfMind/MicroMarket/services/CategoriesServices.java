package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.entity.Category;
import com.PeaceOfMind.MicroMarket.entity.Product;
import com.PeaceOfMind.MicroMarket.dtos.CategoryDTO;
import com.PeaceOfMind.MicroMarket.dtos.CategoryMapper;
import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.repositories.CategoryRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductRepository;

@Service
public class CategoriesServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductsMapper productsMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    public CategoryDTO getCategory(Long idCategoria) {

        Optional<Category> category = categoryRepository.findById(idCategoria);

        if (category.isEmpty()) {
            throw new NotFoundException("category", idCategoria.toString());
        }

        return categoryMapper.toDTO(category.get());
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

        Category category = categoryMapper.getCategory(categoryDTO);

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDTO.getId());

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("category NO ENCONTRADA", categoryDTO.getId().toString());
        }

        Category category = categoryOptional.get();
        category.setNombre(categoryDTO.getNombre());
        category.setDescripcion(categoryDTO.getDescripcion());

        category = categoryRepository.save(category);

        return categoryMapper.toDTO(category);
    }

    public CategoryDTO deleteCategory(Long idCategoria) {
        Optional<Category> categoryOptional = categoryRepository.findById(idCategoria);

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("category", idCategoria.toString());
        }

        Category category = categoryOptional.get();
        categoryRepository.delete(category);

        return categoryMapper.toDTO(category);
    }

    public List<ProductDTO> getProductos(Long idCategoria) {
        Optional<Category> optionalCategory = categoryRepository.findById(idCategoria);

        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("category NO ENCONTRADA", idCategoria.toString());
        }

        Category category = optionalCategory.get();

        Product example = new Product();
        Category categoryExample = new Category();
        categoryExample.setId(category.getId());
        example.setCategoria(categoryExample);

        List<Product> productos = productRepository.findAll(Example.of(example));

        if (productos.isEmpty()) {
            throw new NotFoundException("Productos no encontrados", null);
        }
        return productos
                .stream()
                .map(producto -> productsMapper.toDTO(producto, false))
                .collect(Collectors.toList());
    }
}