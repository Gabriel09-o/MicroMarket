package com.PeaceOfMind.MicroMarket.dtos;

import org.springframework.stereotype.Component;
import com.PeaceOfMind.MicroMarket.entity.Category;

@Component
public class CategoryMapper {

    public CategoryDTO toDTO(Category category) {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNombre(category.getNombre());
        categoryDTO.setDescripcion(category.getDescripcion());

        return categoryDTO;
    }

    public Category getCategory(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setNombre(categoryDTO.getNombre());
        category.setDescripcion(categoryDTO.getDescripcion());

        return category;
    }
}