package com.fifoo.demo.service;

import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.model.Tag;

import java.util.Date;
import java.util.List;

public interface ArticleService {

    List<Article> getAll();

    void create(ArticleDto articleDto);

    ArticleDto delete(long id);

    void update(long id, ArticleDto article);

    Article findById(Long id);

    List<Article> findByDate(Date date);

    List<Article> findByTags(List<Tag> tagList);

    List<Article> findByCategory(List<Category> categoryList);
}
