package com.mycompany.inventery_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    
    private double price;
    
    private int stock;
    
    private String description;

    private String isbn;

    @Column(name = "publisher_name")
    private String publisherName;

    public Book() {
    }

    public Book(String name, Author author, double price, int stock, String description) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}
