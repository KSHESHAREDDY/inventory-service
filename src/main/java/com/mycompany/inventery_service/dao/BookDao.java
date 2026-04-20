package com.mycompany.inventery_service.dao;

import com.mycompany.inventery_service.dto.BookDto;

import java.util.Optional;

public interface BookDao {
    public Optional<BookDto> findBookWithAuthor(Long id);
}
