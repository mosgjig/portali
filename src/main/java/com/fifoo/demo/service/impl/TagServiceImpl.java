package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.TagConverter;
import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.repository.TagRepository;
import com.fifoo.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TagConverter tagConverter;

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public TagDto create(TagDto tagDto) {
        tagRepository.save(TagConverter.toTag(tagDto));
        return tagDto;
    }
    @Override
    public void delete(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
