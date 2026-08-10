package com.viratech.supportdesk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(

        @NotBlank(message = "user must not be null!")
        String name,

        @NotBlank(message = "email must not be null!")
        @Email
        String email
) {
}
