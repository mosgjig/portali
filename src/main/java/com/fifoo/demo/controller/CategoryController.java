package com.fifoo.demo.controller;

import com.fifoo.demo.dto.CategoryDto;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping
    public CategoryDto create(@RequestBody CategoryDto categoryDto){
        return categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public CategoryDto create(@PathVariable("id") long id){
        return categoryService.delete(id);
    }

    @PutMapping("{/id}")
    public CategoryDto update(@PathVariable("id") long id ,@RequestBody CategoryDto categoryDto){
        return categoryService.update(id,categoryDto);
    }

}
