package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.CategoryConverter;
import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.exception.CategoryFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.repository.ArticleRepository;
import com.fifoo.demo.repository.CategoryRepository;
import com.fifoo.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private ArticleRepository articleRepository;
    private CategoryConverter categoryConverter;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) throws CategoryFoundException{
        Category category = null;
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryFoundException("This category already exists");
        }
        else{
         category = categoryRepository.save(CategoryConverter.toCategory(categoryDto));
        }
        return CategoryConverter.toDto(category);
    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException{
        Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isPresent()){
            Category newCategory = optionalCategory.get();
            categoryRepository.delete(newCategory);
        }
        else {
                throw new CategoryNotFoundException("You can not delete a Category that  doesnt exist.");
            }
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto)throws CategoryNotFoundException{
        Category foundCategory = null;
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            foundCategory = optionalCategory.get();
        }
        else{
            throw new CategoryNotFoundException("You cannot update a Category that doesnt exist.");
        }
         foundCategory.setName(categoryDto.getName());

            Category category = categoryRepository.save(foundCategory);
            return CategoryConverter.toDto(category);
    }
}
