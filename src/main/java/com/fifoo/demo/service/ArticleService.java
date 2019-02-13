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

    public List<Article> getAll();
    public ArticleDto create(ArticleDto articleDto) throws CategoryNotFoundException;
    public void delete(Long id) throws ArticleNotFoundException;
    public ArticleDto update(Long id, ArticleDto article) throws ArticleNotFoundException, CategoryNotFoundException;
    public ArticleDto findById(Long id) throws ArticleNotFoundException;
    public List<ArticleDto> findByDate(Date date);
    public List<ArticleDto> findByTags(List<String> tags);
    public List<ArticleDto> findByCategory(List<String> categories);
}
