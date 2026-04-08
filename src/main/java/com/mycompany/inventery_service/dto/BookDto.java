package com.mycompany.inventery_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String name;
    private Long authorId;
    private Double price;
    private Integer stock;
    private String description;
    private String publisherName;
}
