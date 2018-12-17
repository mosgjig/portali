package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.ArticleToDto;
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


    private final ArticleRepository articleRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ArticleToDto articleToDto;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository, CategoryRepository categoryRepository,ArticleToDto articleToDto) {
        this.articleRepository = articleRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.articleToDto = articleToDto;
    } 

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    //test
    public void create(ArticleDto articleDto) {

        Article article = ArticleToDto.toArticle(articleDto);
        Optional<Category> category = categoryRepository.findByName(articleDto.getCategory());
        if(category.isPresent()) {
            article.setCategory(category.get());
        } else {
            throw new CategoryNotFoundException("Category doesn't exist.");
        }

        List <Tag> tagDb = tagRepository.findAll();

        tagDb = tagDb.stream().filter(e-> articleDto.getTag().contains(e.getTitle())).collect(Collectors.toList());
        List<Tag> tagDto = articleDto.getTag().stream().map(e ->
        {
            Tag asd = new Tag();
            asd.setTitle(e);
            return asd;
        }).collect(Collectors.toList());
        tagDto.removeAll(tagDb);
        article.getTags().addAll(tagDb);
        article.getTags().addAll(tagDto);

//
//        int tagsSize = articleDto.getTag().size();
//        for(int i = 0; i < tagsSize; i++) {
//            String tagName = articleDto.getTag().get(i);
//            Optional<Tag> tag = tagRepository.findByTitle(tagName);
//            if (tag.isPresent()) {
//                article.getTags().add(tag.get());
//            } else {
//                Tag newTag = new Tag();
//                newTag.setTitle(tagName);
//                article.getTags().add(newTag);
//            }
//        }
        articleRepository.save(article);
    }
//        articleRepository.save(article);
//        List<String> tags =  article.getTags().stream().map(Tag::getTitle).collect(Collectors.toList());
//        List<Tag> existingTags = tagRepository.findByTitleIn(tags);
//        article.getTags().addAll(existingTags);

    @Override
    public ArticleDto delete(long id) {
        Optional <Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article articleNew = optionalArticle.get();
            articleRepository.delete(articleNew);
        }
        else{
            throw new ArticleNotFoundException("You cannot delete an article that doesn't exist!");
        }
        return null;
    }

    public void update(long id,ArticleDto article){
        if(id != article.getId()) {

        }
        Article articleDb = null;
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isPresent()){
            articleDb = optionalArticle.get();
        }else{throw new ArticleNotFoundException("You cannot update an article that doesn't exist!");}

        Optional<Category> category = categoryRepository.findByName(article.getCategory());
        if(category.isPresent())
            articleDb.setCategory(category.get());

        else
            throw new CategoryNotFoundException("Category doesn't exist.");

        articleDb.setContent(article.getContent());
        articleDb.setTitle(article.getTitle());
        articleDb.setDate(article.getDate());

        ArrayList<String> tagat = new ArrayList<>(article.getTag());
        articleDb.getTags().clear();
        for(int i = 0; i < tagat.size(); i++) {
            Optional<Tag> tag = tagRepository.findByTitle(tagat.get(i));
            if (tag.isPresent()) {
                articleDb.getTags().add(tag.get());
            } else {
                Tag create = new Tag();
                create.setTitle(tagat.get(i));
                articleDb.getTags().add(create);
            }
        }
        articleRepository.save(articleDb);
    }

    @Override
    public List<Article> findByDate(Date date){
        List <Article> articleList = articleRepository.findAll();
        return articleList.stream().filter(o-> o.getDate().getDate()==date.getDate() &&
                o.getDate().getMonth()==date.getMonth() && o.getDate().getYear() == date.getYear()
        ).collect(Collectors.toList());
    }

    public List<Article> findByTags(List<Tag> tagList){
        List<Article> listaEartikujve = articleRepository.findAll();
        List<String> tagStringLista = tagList.stream().map(Tag::getTitle).collect(Collectors.toList());
        return listaEartikujve.stream().filter(a->{
            return a.getTags().stream().anyMatch(p -> tagStringLista.contains(p.getTitle()));
        }).collect(Collectors.toList());
    }

    @Override
    public Article findById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            return optionalArticle.get();
        } else {
            throw new ArticleNotFoundException("This article doesn't exist!");
        }
    }

    public List <Article> findByCategory(List <Category> categoryList){
        List<Article> articleList = articleRepository.findAll();
        List<String>  categoryStringList = categoryList.stream().map(Category::getName).collect(Collectors.toList());
        return articleList.stream().filter(o-> categoryList.contains(o.getCategory().getName())).collect(Collectors.toList());
    }
}

