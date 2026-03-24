package com.PeaceOfMind.MicroMarket.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.PeaceOfMind.MicroMarket.dtos.CategoriesDTO;
import com.PeaceOfMind.MicroMarket.dtos.CategoriesMapper;
import com.PeaceOfMind.MicroMarket.dtos.ProductsDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductsMapper;
import com.PeaceOfMind.MicroMarket.exceptions.NotFoundException;
import com.PeaceOfMind.MicroMarket.models.Categories;
import com.PeaceOfMind.MicroMarket.models.Products;
import com.PeaceOfMind.MicroMarket.repositories.CategoriesRepository;
import com.PeaceOfMind.MicroMarket.repositories.ProductsRepository;

@Service
public class CategoriesServices {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsMapper productsMapper;

    public List<CategoriesDTO> getAllCategories() {
        return categoriesRepository
                .findAll()
                .stream()
                .map(category -> categoriesMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    public CategoriesDTO getCategory(Long idCategoria) {
        Optional<Categories> category = categoriesRepository.findById(idCategoria);

        if (category.isEmpty()) {
            throw new NotFoundException("category", idCategoria.toString());
        }

        return categoriesMapper.toDTO(category.get());
    }

    public CategoriesDTO saveCategory(CategoriesDTO categoryDTO) {
        Categories category = categoriesMapper.getCategory(categoryDTO);
        return categoriesMapper.toDTO(categoriesRepository.save(category));
    }

    public CategoriesDTO updateCategory(CategoriesDTO categoryDTO) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(categoryDTO.getId());

        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("category NO ENCONTRADA", categoryDTO.getId().toString());
        }
        
        Categories category = categoryOptional.get();
        category.setNombre(categoryDTO.getNombre());
        category.setDescripcion(categoryDTO.getDescripcion());

        category = categoriesRepository.save(category);

        return categoriesMapper.toDTO(category);
    }

    public CategoriesDTO deleteCategory(Long idCategoria) {
        Optional<Categories> categoryOptional = categoriesRepository.findById(idCategoria);
        
        if (categoryOptional.isEmpty()) {
            throw new NotFoundException("category", idCategoria.toString());
        }
        
        Categories category = categoryOptional.get();
        categoriesRepository.delete(category);

        return categoriesMapper.toDTO(category);
    }

    public List<ProductsDTO> getProductos(Long idCategoria) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(idCategoria);

        if (optionalCategory.isEmpty()) {
            throw new NotFoundException("category NO ENCONTRADA", idCategoria.toString());
        }

        Categories category = optionalCategory.get();

        Products example = new Products();
        Categories categoryExample = new Categories();
        categoryExample.setId(category.getId());
        example.setCategory(categoryExample); 

        List<Products> productos = productsRepository.findAll(Example.of(example));

        return productos
                .stream()
                .map(producto -> productsMapper.toDTO(producto, false))
                .collect(Collectors.toList());
    }
}