package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (path = {"/api/admin"}, name = "Author  Controller")
public class AuthorController {
    @Autowired
    private BookService bookService;
    @GetMapping(value = "/search/by-author")
    public ApiResponse getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }
}
