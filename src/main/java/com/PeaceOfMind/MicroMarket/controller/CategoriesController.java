package com.PeaceOfMind.MicroMarket.controller;

import java.util.List;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import com.PeaceOfMind.MicroMarket.dtos.CategoryDTO;
import com.PeaceOfMind.MicroMarket.dtos.ProductDTO;
import com.PeaceOfMind.MicroMarket.services.CategoriesServices;

@RestController
@RequestMapping("/categorias")
public class CategoriesController {

    @Autowired
    private CategoriesServices categoriesServices;

    @GetMapping
    public List<CategoryDTO> getAllCategorias() {
        return categoriesServices.getAllCategories();
    }

    @GetMapping("/{idCategoria}")
    public CategoryDTO getCategorias(@PathVariable Long idCategoria) {
        return categoriesServices.getCategory(idCategoria);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategoria(@RequestBody CategoryDTO categoriesDTO) {

        CategoryDTO categoriesDTO1 = categoriesServices.saveCategory(categoriesDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoriesDTO1.getId())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, uri.toString())
                .body(categoriesDTO1);
    }

    @PutMapping
    public CategoryDTO updateCategoria(@RequestBody CategoryDTO categoriesDTO) {
        return categoriesServices.updateCategory(categoriesDTO);
    }

    @DeleteMapping("/{idCategoria}")
    public CategoryDTO deleteCategoria(@PathVariable Long idCategoria) {
        return categoriesServices.deleteCategory(idCategoria);
    }

    @GetMapping("/{idCategoria}/productos")
    public List<ProductDTO> getProductos(@PathVariable Long idCategoria) {
        return categoriesServices.getProductos(idCategoria);
    }

}
