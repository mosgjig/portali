package com.fifoo.demo.service;

import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.exception.ArticleNotFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.model.Tag;

import java.util.Date;
import java.util.List;

public interface ArticleService {

    List<Article> getAll();
    void create(ArticleDto articleDto) throws CategoryNotFoundException;
    void delete(Long id) throws ArticleNotFoundException;
    ArticleDto update(Long id, ArticleDto article) throws ArticleNotFoundException, CategoryNotFoundException;
    Article findById(Long id) throws ArticleNotFoundException;
    List<Article> findByDate(Date date);
    List<Article> findByTags(List<Tag> tagList);
    List<Article> findByCategory(List<Category> categoryList);
}
