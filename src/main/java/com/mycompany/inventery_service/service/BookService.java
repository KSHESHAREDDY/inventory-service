package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookDto;

import java.util.List;

public interface BookService {
    public ApiResponse addBook(BookDto book);
    public ApiResponse addMultipleBooks(List<BookDto> books);
    public ApiResponse getBooks();
    public ApiResponse searchBooks(BookDto bookDto);
}
