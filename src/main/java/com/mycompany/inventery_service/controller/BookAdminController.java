package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.model.Book;
import com.mycompany.inventery_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
@Tag(name = "Book Admin Controller", description = "APIs for managing the bookstore inventory")
public class BookAdminController {

    @Autowired
    BookService bookService;

    @Operation(summary = "Check service status", description = "Returns a simple string to verify the admin service is live.")
    @GetMapping("/status")
    public String getAdminStatus() {
        return "Admin Dashboard Service is up and running!";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/add-book/bulk")
    public String addMultipleBooks(@RequestBody List<Book> books) {
        return bookService.addMultipleBooks(books);
    }

    @PutMapping("/update-book")
    public String updateBook() {
        // Placeholder for updating a book in the inventory
        return "Update Book API - To be implemented";
    }
}
