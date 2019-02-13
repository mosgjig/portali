package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.CategoryConverter;
import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.exception.CategoryFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.repository.CategoryRepository;
import com.fifoo.demo.service.CategoryService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.dozer.Mapper;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    private Mapper mapper;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) throws CategoryFoundException{
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());
        if(optionalCategory.isPresent()){
            throw new CategoryFoundException("This category already exists");
        }
        else{
            Category category = categoryRepository.save(CategoryConverter.toCategory(categoryDto));
            return toDto(category);
        }
    }

    public CategoryDto toDto(Category category){
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException{
        Optional<Category> optionalCategory = categoryRepository.findById(id);
            if(optionalCategory.isPresent()){
            categoryRepository.delete(optionalCategory.get());
        }
        else {
                throw new CategoryNotFoundException("You can not delete a Category that  doesnt exist.");
            }
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto)throws CategoryNotFoundException{
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
          Category foundCategory = optionalCategory.get();
            foundCategory.setName(categoryDto.getName());

            foundCategory = categoryRepository.save(foundCategory);
            return categoryConverter.toDto(foundCategory);
        }
        else{
            throw new CategoryNotFoundException("You cannot update a Category that doesnt exist.");
        }
    }
}
