package com.fifoo.demo.service.impl;

import com.fifoo.demo.converter.TagToDto;
import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;
import com.fifoo.demo.repository.TagRepository;
import com.fifoo.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagToDto tagToDto;
    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagToDto tagToDto){
        this.tagRepository = tagRepository;
        this.tagToDto = tagToDto;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public TagDto create(TagDto tagDto) {
        tagRepository.save(TagToDto.toTag(tagDto));
        return tagDto;
    }
    @Override
    public void delete(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
