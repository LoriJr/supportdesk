package com.viratech.supportdesk.controller;

import com.viratech.supportdesk.dto.CategoryRequest;
import com.viratech.supportdesk.dto.CategoryResponse;
import com.viratech.supportdesk.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request){
        CategoryResponse response = service.saveCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
