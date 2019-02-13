package com.fifoo.demo.service.impl.config;

import com.fifoo.demo.model.Category;
import com.fifoo.demo.dto.CategoryDto;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

@Stateless
public class DozerMapperFactory {

    @Produces
    public Mapper getMapper() {
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Category.class, CategoryDto.class);
            }
        };

        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(builder);

        return mapper;
    }
}
