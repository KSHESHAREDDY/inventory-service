package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.AuthorDto;
import com.mycompany.inventery_service.entity.Author;
import com.mycompany.inventery_service.service.AuthorService;
import com.mycompany.inventery_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping (path = {"/api/admin"}, name = "Author  Controller")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping(value = "/search/author")
    public ApiResponse getBooksByAuthor(@RequestParam String name) {
        return authorService.searchAuthor(name);
    }

    @PostMapping(value = "/author/add")
    public ApiResponse addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    @GetMapping(value = "/author/{id}")
    public ApiResponse getAuthorById(@PathVariable Long id) {
        return authorService.findBookByAuthor(id);
    }

}
