package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.Category;
import com.viratech.supportdesk.dto.CategoryRequest;
import com.viratech.supportdesk.dto.CategoryResponse;
import com.viratech.supportdesk.exceptions.InvalidParameterException;
import com.viratech.supportdesk.mapper.CategoryMapper;
import com.viratech.supportdesk.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    private final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final String className = CategoryService.class.getSimpleName();

    public CategoryResponse saveCategory(CategoryRequest request){
        log.info("[{}] [saveCategory]", className);

        if (request.name() == null){
            throw new InvalidParameterException("Category name null, try again.");
        }

        Category category = mapper.toEntity(request);

        Category saveCategory = repository.save(category);

        return mapper.toDto(saveCategory);
    }
}
