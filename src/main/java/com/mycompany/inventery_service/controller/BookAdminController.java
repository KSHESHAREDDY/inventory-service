package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/admin"}, name = "Book Admin Controller", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Tag(name = "Book Admin Controller", 
     description = "APIs for managing the bookstore inventory. Provides endpoints for adding, updating, and checking the status of books in the system.")
public class BookAdminController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/add-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PostMapping(value = "/add-book/bulk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addMultipleBooks(@RequestBody List<BookDto> booksDto) {
        return bookService.addMultipleBooks(booksDto);
    }

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> searchBooks(@RequestBody BookDto bookDto) {
        return bookService.searchBooks(bookDto);
    }
}
