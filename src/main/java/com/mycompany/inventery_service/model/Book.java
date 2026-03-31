package com.mycompany.inventery_service.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "books")
@Schema(description = "Book entity representing a book in the inventory")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the book", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
//    use different strategy for other table
    private Long id;
    
    @Schema(description = "Title of the book", example = "The Great Gatsby", minLength = 1, maxLength = 255)
    private String name;
    
    @Schema(description = "Author of the book", example = "F. Scott Fitzgerald", minLength = 1, maxLength = 255)
    private String author;
    
    @Schema(description = "Price of the book in USD", example = "9.99", minimum = "0")
    private double price;
    
    @Schema(description = "Number of copies available in stock", example = "50", minimum = "0")
    private int stock;
    
    @Schema(description = "Detailed description of the book", example = "A classic American novel", maxLength = 1000)
    private String description;

    public Book() {
    }

    public Book(String name, String author, double price, int stock, String description) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
