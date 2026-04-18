package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookAdminRequestDto;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/admin"}, name = "Book Admin Controller")
public class BookAdminController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/add-book")
    public ApiResponse addBook(@RequestBody BookAdminRequestDto bookAdminRequestDto) {
        BookDto bookDto = bookAdminRequestDto.getBookDto();
        return bookService.addBook(bookDto);
    }

    @PostMapping(value = "/add-book/bulk")
    public ApiResponse addMultipleBooks(@RequestBody BookAdminRequestDto bookAdminRequestDto) {
        List<BookDto> booksDto = bookAdminRequestDto.getBooksDto();
        return bookService.addMultipleBooks(booksDto);
    }

    @PostMapping("/books")
    public ApiResponse getBooks(@RequestBody BookAdminRequestDto bookAdminRequestDto) {
        return bookService.getBooks(bookAdminRequestDto);
    }

    @PostMapping(value = "/search")
    public ApiResponse searchBooks(@RequestBody BookAdminRequestDto bookAdminRequestDto) {
        BookDto bookDto = bookAdminRequestDto.getBookDto();
        return bookService.searchBooks(bookDto);
    }
}
