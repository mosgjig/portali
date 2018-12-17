package com.fifoo.demo.service;

import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getAll();

    TagDto create( TagDto createTag);

    TagDto delete(long id);

}
