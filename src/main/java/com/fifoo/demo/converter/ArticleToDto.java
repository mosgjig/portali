package com.fifoo.demo.converter;

import com.fifoo.demo.dto.ArticleDto;
import com.fifoo.demo.model.Article;
import com.fifoo.demo.model.Tag;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleToDto {

    public static ArticleDto toDto(Article article){

//        ArticleDto articleDto = new DozerBeanMapper().map(article, ArticleDto.class);
//        return articleDto;
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        articleDto.setDate(article.getDate());
        articleDto.setCategory(article.getCategory().getName());
        List<String> stringTagat = article.getTags().stream()
                .map(Tag::getTitle).collect(Collectors.toList());
        articleDto.setTag(stringTagat);

        return articleDto;
    }
//
    public static Article toArticle(ArticleDto articleDto){

//           Article article = new DozerBeanMapper().map(articleDto, Article.class);
//           return article;
            Article article = new Article();
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
            article.setDate(articleDto.getDate());

            return article;
    }
}

