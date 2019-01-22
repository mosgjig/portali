package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.ArticleConverter;
import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.exception.ArticleNotFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.model.Category;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.repository.ArticleRepository;
import com.fifoo.demo.repository.CategoryRepository;
import com.fifoo.demo.repository.TagRepository;
import com.fifoo.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ArticleConverter articleConverter;

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public ArticleDto create(ArticleDto articleDto) throws CategoryNotFoundException{
        Article article = ArticleConverter.toEntity(articleDto);
        Optional<Category> category = categoryRepository.findByName(articleDto.getCategory());
        if(category.isPresent()) {
            article.setCategory(category.get());
        }
        else {
            throw new CategoryNotFoundException("Category doesn't exist.");
        }
        List <Tag> tagDb = tagRepository.findAll();
        List<Tag> tagDto = articleDto.getTag().stream().map(e -> new Tag(e)).collect(Collectors.toList());
        tagDb = tagDb.stream().filter(e-> tagDto.contains(e)).collect(Collectors.toList());
        tagDto.removeAll(tagDb);
        article.getTags().addAll(tagDb);
        article.getTags().addAll(tagDto);
        article = articleRepository.save(article);

        return ArticleConverter.toDto(article);
    }
    @Override
    public void delete(Long id) throws ArticleNotFoundException {
        Optional <Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            articleRepository.delete(optionalArticle.get());
        }
        else{
            throw new ArticleNotFoundException("You cannot delete an article that doesn't exist!");
        }
    }
    @Override
    public ArticleDto update(Long id, ArticleDto articleDto) throws ArticleNotFoundException, CategoryNotFoundException{
        if(!id.equals(articleDto.getId())){

        }
        Article foundArticle = null;
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()) {
            foundArticle = optionalArticle.get();
        }
        else {
            throw new ArticleNotFoundException("You cannot update an article that doesn't exist!");
        }
        Optional<Category> category = categoryRepository.findByName(articleDto.getCategory());
        if(category.isPresent()) {
            foundArticle.setCategory(category.get());
        }
        else {
            throw new CategoryNotFoundException("Category doesn't exist.");
        }

        foundArticle.setContent(articleDto.getContent());
        foundArticle.setTitle(articleDto.getTitle());
        foundArticle.setDate(articleDto.getDate());

        foundArticle.getTags().clear();
        List <Tag> tagDb = tagRepository.findAll();
        List<Tag> tagDto = articleDto.getTag().stream().map(e -> new Tag(e)).collect(Collectors.toList());
        tagDb = tagDb.stream().filter(e-> tagDto.contains(e)).collect(Collectors.toList());
        tagDto.removeAll(tagDb);
        foundArticle.getTags().addAll(tagDb);
        foundArticle.getTags().addAll(tagDto);
        foundArticle = articleRepository.save(foundArticle);

        return articleConverter.toDto(foundArticle);
    }

    @Override
    public List<ArticleDto> findByDate(Date date){
        List <Article> articleList = articleRepository.findAll();
        List <Article> finalList = articleList.stream().filter(o-> o.getDate().getDate()==date.getDate() &&
                o.getDate().getMonth()==date.getMonth() && o.getDate().getYear() == date.getYear()
        ).collect(Collectors.toList());
        return ArticleConverter.toDtoList((finalList));
    }

    public List<ArticleDto> findByTags(List<String> tags){
        List<Article> articles = articleRepository.findAll();
        List <Article> finalList = articles.stream().filter(a->{
            return a.getTags().stream().anyMatch(p -> tags.contains(p.getTitle()));
        }).collect(Collectors.toList());
        return ArticleConverter.toDtoList(finalList);
    }

    @Override
    public ArticleDto findById(Long id) throws ArticleNotFoundException{
        Optional<Article> optionalArticle = articleRepository.findById(id);
        ArticleDto returned = null;
        if (optionalArticle.isPresent()) {
            returned = ArticleConverter.toDto(optionalArticle.get());
            return returned;
        } else {
            throw new ArticleNotFoundException("This article doesn't exist!");
        }
    }

    public List<ArticleDto> findByCategory(List<String> categories){
        List<Article> articleList = articleRepository.findAll();
        List<Article> finalList = articleList.stream().filter(o-> categories.contains(o.getCategory().getName())).collect(Collectors.toList());
        return ArticleConverter.toDtoList(finalList);
    }
}

