package com.fifoo.demo.service;



import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto delete(long id);

    CategoryDto update(long id, CategoryDto categoryDto);
}
