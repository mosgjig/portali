package com.fifoo.demo.service;

import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.exception.CategoryFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();
    CategoryDto create(CategoryDto categoryDto) throws CategoryFoundException;
    void delete(Long id) throws CategoryNotFoundException;
    CategoryDto update(Long id, CategoryDto categoryDto) throws CategoryNotFoundException;
}
