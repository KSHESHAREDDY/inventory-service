package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/admin"}, name = "Book Admin Controller")
public class BookAdminController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/add-book")
    public ApiResponse addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PostMapping(value = "/add-book/bulk")
    public ApiResponse addMultipleBooks(@RequestBody List<BookDto> booksDto) {
        return bookService.addMultipleBooks(booksDto);
    }

    @GetMapping("/books")
    public ApiResponse getBooks() {
        return bookService.getBooks();
    }

    @PostMapping(value = "/search")
    public ApiResponse searchBooks(@RequestBody BookDto bookDto) {
        return bookService.searchBooks(bookDto);
    }
}
