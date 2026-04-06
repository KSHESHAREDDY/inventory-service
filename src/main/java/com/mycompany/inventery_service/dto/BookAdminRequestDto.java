package com.mycompany.inventery_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAdminRequestDto {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortDir;
    private BookDto bookDto;
    private List<BookDto> booksDto;
}
