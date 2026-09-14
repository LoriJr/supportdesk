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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    @DisplayName("Deve listar todas as categorias")
    public void shouldShowAllCategories(){

        List<Category> listCategory = List.of(
                new Category(1L, "Hardware"),
                new Category(2L, "Software")
        );

        List<CategoryResponse> response = List.of(
                new CategoryResponse(1L, "Hardware"),
                new CategoryResponse(2L, "Software"));

        when(mapper.toResponseList(listCategory)).thenReturn(response);
        when(repository.findAll()).thenReturn(listCategory);

        List<CategoryResponse> result = service.listAllCategories();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

}
