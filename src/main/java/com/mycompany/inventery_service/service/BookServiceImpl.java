package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.entity.Book;
import com.mycompany.inventery_service.repository.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public BookServiceImpl(){}

    public String addBook(Book book) {
        if(validateBook(book)) {
            bookRepository.save(book);
            return "Add Book API - To be implemented";
        } else {
            return "Book name and author are required fields.";
        }
    }

    public String addMultipleBooks(List<Book> books) {
        for(Book book : books) {
            if(validateBook(book)) {
                bookRepository.save(book);
            } else {
                return "Book name and author are required fields.";
            }
        }
        return "Added books successfully.";
    }

    @Override
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(book -> {
            BookDto bookDto = new BookDto();
            bookDto.setName(book.getName());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setPrice(book.getPrice());
            bookDto.setStock(book.getStock());
            bookDto.setDescription(book.getDescription());
            bookDto.setPublisherName(book.getPublisherName());
            return bookDto;
        }).toList();
    }

    private boolean validateBook(Book book) {
        return StringUtils.isNotBlank(book.getName()) && StringUtils.isNotBlank(book.getAuthor());
    }
}
