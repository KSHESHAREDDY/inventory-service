package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.model.Book;
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

    private boolean validateBook(Book book) {
        return StringUtils.isNotBlank(book.getName()) && StringUtils.isNotBlank(book.getAuthor());
    }
}
