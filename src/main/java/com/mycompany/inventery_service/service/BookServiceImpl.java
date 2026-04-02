package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.entity.Book;
import com.mycompany.inventery_service.repository.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public ApiResponse addBook(BookDto bookDto) {
        ApiResponse response = new ApiResponse();
        if(validateBook(bookDto)) {
            this.constructBookAndSave(bookDto);
            response.setMessage("Add Book API - To be implemented");
        } else {
            response.setMessage("Book name and author are required fields.");
        }
        return response;
    }

    public String addMultipleBooks(List<BookDto> books) {
        for(BookDto bookDto : books) {
            if(validateBook(bookDto)) {
                this.constructBookAndSave(bookDto);
            } else {
                return "Book name and author are required fields.";
            }
        }
        return "Added books successfully.";
    }

    @Override
    public List<BookDto> getBooks() {
        return convertBooksToBookDtos(bookRepository.findAll());
    }

    @Override
    public List<BookDto> searchBooks(BookDto bookDto) {
        // 1. Map DTO to Entity (Values the user typed in)
        Book filterEntity = new Book();
        filterEntity.setName(bookDto.getName());
        filterEntity.setAuthor(bookDto.getAuthor());
        filterEntity.setPublisherName(bookDto.getPublisherName());
        // Note: primitives like double/int default to 0.0,
        // so we handle them in the matcher or use wrapper classes (Double/Integer)

        // 2. Configure the Matcher
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues() // Ignore fields that are null
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // LIKE %value%
                .withIgnoreCase(); // Case-insensitive search

        // 3. Create the Example object
        Example<Book> example = Example.of(filterEntity, matcher);

        // 4. Execute and map back to DTO
        return convertBooksToBookDtos(bookRepository.findAll(example));
    }

    private boolean validateBook(BookDto bookDto) {
        return StringUtils.isNotBlank(bookDto.getName()) && StringUtils.isNotBlank(bookDto.getAuthor());
    }

    private void constructBookAndSave(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setStock(bookDto.getStock());
        book.setDescription(bookDto.getDescription());
        book.setPublisherName(bookDto.getPublisherName());
        bookRepository.save(book);
    }

    private List<BookDto> convertBooksToBookDtos(List<Book> books) {
        return books.stream().map(book -> {
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

}
