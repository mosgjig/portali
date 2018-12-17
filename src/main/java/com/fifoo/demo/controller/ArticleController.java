package com.fifoo.demo.controller;

import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAll(){
        return articleService.getAll();
    }

    @PostMapping
    public void create(@RequestBody ArticleDto articleDto)
    {
        articleService.create(articleDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        articleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody ArticleDto article){

        articleService.update(id,article);
    }

    @GetMapping("/from")
    public List <Article> getByDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        return articleService.findByDate(date);
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable("id") Long id){

        return articleService.findById(id);
    }

    @GetMapping("/getByTags")
    public List<Article> getByTags(@RequestBody List<Tag> lista){
        return articleService.findByTags(lista);
    }
}
