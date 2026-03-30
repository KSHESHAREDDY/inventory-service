package com.mycompany.inventery_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    use different strategy for other table
    private Long id;
    private String name;
    private String author;
    private double price;
    private int stock;
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
