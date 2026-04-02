package com.mycompany.inventery_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ApiResponse {
    private Long statusCode;
    private String message;
    private List<BookDto> books;
}
