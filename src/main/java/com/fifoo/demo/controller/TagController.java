package com.fifoo.demo.controller;

import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fifoo.demo.controller.constant.WebDefinition.*;

@RestController
@RequestMapping(TAGS)
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAll(){
        return tagService.getAll();
    }

    @PostMapping
    public TagDto create(@RequestBody TagDto createTag){
        return tagService.create(createTag);
    }

    @DeleteMapping(SLASH + ID)
    public void delete(@PathVariable("id") Long tagId){
            tagService.delete(tagId);
    }

}
