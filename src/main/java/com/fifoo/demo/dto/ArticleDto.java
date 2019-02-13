package com.fifoo.demo.dto;

import java.util.Date;
import java.util.List;

public class ArticleDto {

    private Long id;

    private String title;

    private String content;

    private Date date;

    private String category;

    private List<String> tags;

    public Long getId()  {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTag() {
        return tags;
    }

    public void setTag(List<String> tag) {
        this.tags = tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
