package com.fifoo.demo.controller;

import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService){
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> getAll(){
        return tagService.getAll();
    }

    @PostMapping
    public TagDto create(@RequestBody TagDto createTag){
        return tagService.create(createTag);
    }

    @DeleteMapping("/{id}")
    public TagDto delete(@PathVariable("id") long tagId){
        return tagService.delete(tagId);
    }

}
