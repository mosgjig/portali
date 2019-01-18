package com.fifoo.demo.controller;

import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.exception.CategoryFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fifoo.demo.controller.constant.WebDefinition.*;

@RestController
@RequestMapping(CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto)throws CategoryFoundException {
        return categoryService.create(categoryDto);
    }

    @DeleteMapping(SLASH + ID)
    public void create(@PathVariable("id") long id)throws CategoryNotFoundException {
            categoryService.delete(id);
    }

    @PutMapping(SLASH + ID)
    public CategoryDto update(@PathVariable("id") long id ,@RequestBody CategoryDto categoryDto) throws  CategoryNotFoundException{
        return categoryService.update(id,categoryDto);
    }

}
