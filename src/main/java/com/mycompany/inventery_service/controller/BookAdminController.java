package com.mycompany.inventery_service.controller;

import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.entity.Book;
import com.mycompany.inventery_service.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/admin"}, name = "Book Admin Controller", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@Tag(name = "Book Admin Controller", 
     description = "APIs for managing the bookstore inventory. Provides endpoints for adding, updating, and checking the status of books in the system.")
public class BookAdminController {

    @Autowired
    BookService bookService;

    @Operation(
        summary = "Check service status",
        description = "Returns a simple string to verify the admin service is live and operational.",
        operationId = "getAdminStatus"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Service is operational",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string", example = "Admin Dashboard Service is up and running!"))
    )
    @GetMapping("/status")
    public String getAdminStatus() {
        return "Admin Dashboard Service is up and running!";
    }

    @Operation(
        summary = "Add a single book to inventory",
        description = "Adds a new book to the inventory with the provided details. The book name and author are mandatory fields.",
        operationId = "addBook"
    )
    @RequestBody(
        description = "Book object containing book details to be added",
        required = true,
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = Book.class)
        )
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book added successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string"))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid book data - name and author are required",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string"))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    @PostMapping(value = "/add-book", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addBook(@org.springframework.web.bind.annotation.RequestBody Book book) {
        return bookService.addBook(book);
    }

    @Operation(
        summary = "Add multiple books to inventory",
        description = "Bulk operation to add multiple books to the inventory in a single request. Each book must have valid name and author fields.",
        operationId = "addMultipleBooks"
    )
    @RequestBody(
        description = "List of book objects to be added",
        required = true,
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(
                type = "array",
                implementation = Book.class
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Books added successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string", example = "Added books successfully."))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid book data - name and author are required for all books",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string"))
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error during bulk operation",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    @PostMapping(value = "/add-book/bulk", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addMultipleBooks(@org.springframework.web.bind.annotation.RequestBody List<Book> books) {
        return bookService.addMultipleBooks(books);
    }

    @Operation(
        summary = "Update an existing book",
        description = "Updates an existing book record in the inventory with new details.",
        operationId = "updateBook"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Book updated successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string"))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Book not found",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    @PutMapping(value = "/update-book", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateBook() {
        // Placeholder for updating a book in the inventory
        return "Update Book API - To be implemented";
    }

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }
}
