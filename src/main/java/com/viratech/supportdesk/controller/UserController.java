package com.viratech.supportdesk.controller;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.dto.UserRequest;
import com.viratech.supportdesk.dto.UserResponse;
import com.viratech.supportdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request){
        UserResponse response = service.saveUser(request);
        return ResponseEntity.ok(response);
    }
}
