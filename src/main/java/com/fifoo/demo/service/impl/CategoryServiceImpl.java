package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.CategoryToDto;
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

    private  final CategoryRepository categoryRepository;
    private  final ArticleRepository articleRepository;

    @Autowired
    private final CategoryToDto categoryToDto;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleRepository articleRepository,CategoryToDto categoryToDto){
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
        this.categoryToDto = categoryToDto;
    }
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());

        if(optionalCategory.isPresent()){
            throw new CategoryFoundException("This category already exists");
        }
        else{
          categoryRepository.save(CategoryToDto.toCategory(categoryDto));
        }
        return categoryDto;
    }

    @Override
    public CategoryDto delete(long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

            if(optionalCategory.isPresent()){
            Category newCategory = optionalCategory.get();
            categoryRepository.delete(newCategory);
        }
        else{
            throw new CategoryNotFoundException("You can not delete a Category that  doesnt exist.");
        }
        return null;
    }

    @Override
    public CategoryDto update(long id, CategoryDto categoryDto) {
        Category newCategory = null;
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            newCategory = optionalCategory.get();
        }
        else{
            throw new CategoryNotFoundException("You cannot update a Category that doesnt exist.");
        }
         newCategory.setName(categoryDto.getName());

            categoryRepository.save(newCategory);
            return null;
    }
}
