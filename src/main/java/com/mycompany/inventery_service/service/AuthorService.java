package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.AuthorDto;
import com.mycompany.inventery_service.entity.Author;

public interface AuthorService {
    ApiResponse searchAuthor(String name);
    ApiResponse addAuthor(AuthorDto authorDto);
}
