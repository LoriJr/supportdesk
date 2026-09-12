package com.viratech.supportdesk.builders;

import com.viratech.supportdesk.domain.User;
import com.viratech.supportdesk.enums.Role;

import java.time.LocalDateTime;

public class UserBuilder {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
    private UserBuilder(){}
    
    public static UserBuilder aUser(){
        UserBuilder userBuilder = new UserBuilder();
        initValues(userBuilder);
        return userBuilder;
    }

    private static void initValues(UserBuilder userBuilder) {
        userBuilder.id = 1L;
        userBuilder.name = "Usuario Valido";
        userBuilder.email = "email@gmail.com";
        userBuilder.role = Role.EMPLOYEE;
        userBuilder.createdAt = LocalDateTime.now();
    }

    public UserBuilder withId(Long param){
        id = param;
        return this;
    }

    public UserBuilder withName(String param){
        name = param;
        return this;
    }

    public UserBuilder withEmail(String param){
        email = param;
        return this;
    }

    public UserBuilder withRole(Role param){
        role = param;
        return this;
    }

    public UserBuilder withCreatedAt(LocalDateTime param){
        createdAt = param;
        return this;
    }

    public User now(){
        return new User(id, name, email, role, createdAt);
    }
}
