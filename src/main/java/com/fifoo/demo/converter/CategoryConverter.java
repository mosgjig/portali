package com.fifoo.demo.converter;

import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public static CategoryDto toDto(Category category){

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());

        return categoryDto;
    }

    public static Category toCategory(CategoryDto categoryDto){

        Category category = new Category();
        category.setName(categoryDto.getName());

        return category;
    }
}
