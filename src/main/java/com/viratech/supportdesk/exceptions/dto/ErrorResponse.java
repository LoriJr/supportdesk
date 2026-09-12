package com.viratech.supportdesk.exceptions.dto;

public record ErrorResponse(
        Integer status,
        String message
) {}
