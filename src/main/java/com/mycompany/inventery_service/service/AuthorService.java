package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.ApiResponse;

public interface AuthorService {
    ApiResponse searchAuthor(String name);
}
