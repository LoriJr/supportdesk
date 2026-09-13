package com.viratech.supportdesk.service;

import com.viratech.supportdesk.domain.Category;
import com.viratech.supportdesk.dto.CategoryRequest;
import com.viratech.supportdesk.dto.CategoryResponse;
import com.viratech.supportdesk.mapper.CategoryMapper;
import com.viratech.supportdesk.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService service;

    @Test
    @DisplayName("Deve salvar categoria com sucesso")
    public void shouldSaveCategoryWithSuccess(){

        Category category = new Category(1L, "Hardware");
        CategoryRequest request = new CategoryRequest("Hardware");
        CategoryResponse response = new CategoryResponse(1L, "Hardware");

        when(mapper.toEntity(request)).thenReturn(category);
        when(mapper.toDto(category)).thenReturn(response);
        when(repository.save(category)).thenReturn(category);

        CategoryResponse result = service.saveCategory(request);

        assertEquals("Hardware", result.name());

        verify(repository).save(category);
    }
}
