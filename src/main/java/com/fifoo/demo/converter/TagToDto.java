package com.fifoo.demo.converter;

import com.fifoo.demo.dto.TagDto;
import com.fifoo.demo.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagToDto {

    public static TagDto toDto (Tag tag){

        TagDto tagDto = new TagDto();
        tagDto.setTitle(tag.getTitle());

        return tagDto;
    }

    public static Tag toTag(TagDto tagDto){

        Tag tag = new Tag();
        tag.setTitle(tagDto.getTitle());

        return tag;
    }
}
