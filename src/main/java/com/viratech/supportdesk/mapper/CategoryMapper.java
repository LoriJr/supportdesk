package com.viratech.supportdesk.mapper;

import com.viratech.supportdesk.domain.Category;
import com.viratech.supportdesk.dto.CategoryRequest;
import com.viratech.supportdesk.dto.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequest request){
        return Category.builder()
                .name(request.name())
                .build();
    }

    public CategoryResponse toDto(Category entity){
        return new CategoryResponse(
                entity.getId(),
                entity.getName()
        );
    }
}
