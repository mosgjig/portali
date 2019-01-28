package com.fifoo.demo.controller;

import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.exception.ArticleNotFoundException;
import com.fifoo.demo.exception.CategoryNotFoundException;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.fifoo.demo.controller.constant.WebDefinition.*;

@RestController
@RequestMapping(ARTICLES)
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAll(){
        return articleService.getAll();
    }

    @PostMapping
    public ArticleDto create(@RequestBody ArticleDto articleDto) throws CategoryNotFoundException
    {
       return articleService.create(articleDto);
    }

    @DeleteMapping(SLASH + ID)
    public void delete(@PathVariable("id") Long id)throws ArticleNotFoundException {
        articleService.delete(id);
    }

    @PutMapping(SLASH + ID)
    public void update(@PathVariable("id") Long id, @RequestBody ArticleDto article) throws ArticleNotFoundException,CategoryNotFoundException{
        articleService.update(id,article);
    }

    @GetMapping(FROM)
    public List <ArticleDto> getByDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date){
        return articleService.findByDate(date);
    }

    @GetMapping(SLASH + ID)
    public ArticleDto getArticle(@PathVariable("id") Long id) throws ArticleNotFoundException{
        return articleService.findById(id);
    }

    @GetMapping(GETBYTAGS)
    public List<ArticleDto> getByTags(@RequestBody List<String> tags) {
        return articleService.findByTags(tags);
    }

    @GetMapping(GETBYCATEGORY)
    public List<ArticleDto> getByCategory(@RequestBody List<String> categoryList){
        return articleService.findByCategory(categoryList);
    }
}
