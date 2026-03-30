package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.model.Book;

import java.util.List;

public interface BookService {
    public String addBook(Book book);
    public String addMultipleBooks(List<Book> books);
}
