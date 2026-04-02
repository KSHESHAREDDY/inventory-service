package com.mycompany.inventery_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDto {
    private String name;
    private String author;
    private double price;
    private int stock;
    private String description;
    private String publisherName;

    public BookDto() {
    }
}
