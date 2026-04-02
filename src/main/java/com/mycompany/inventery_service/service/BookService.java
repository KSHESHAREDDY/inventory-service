package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.entity.Book;

import java.util.List;

public interface BookService {
    public ApiResponse addBook(BookDto book);
    public String addMultipleBooks(List<BookDto> books);
    public List<BookDto> getBooks();

    List<BookDto> searchBooks(BookDto bookDto);
}
