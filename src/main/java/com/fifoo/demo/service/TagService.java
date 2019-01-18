package com.fifoo.demo.service;

import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;

import java.util.List;

public interface TagService {

    public List<Tag> getAll();
    public TagDto create(TagDto tagDto);
    public void delete(Long id);

}
