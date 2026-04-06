package com.mycompany.inventery_service.service;

import com.mycompany.inventery_service.constants.MessageConstants;
import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.BookAdminRequestDto;
import com.mycompany.inventery_service.dto.BookDto;
import com.mycompany.inventery_service.entity.Book;
import com.mycompany.inventery_service.repository.BookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    public ApiResponse addBook(BookDto bookDto) {
        ApiResponse response = new ApiResponse();
        if(validateBook(bookDto)) {
            this.constructBookAndSave(bookDto);
            response.setMessage(MessageConstants.BOOK_ADDED_SUCCESSFULLY);
            response.setStatusCode(200L);
        } else {
            response.setMessage(MessageConstants.BOOK_AND_AUTHOR_REQUIRED);
            response.setStatusCode(800L);
        }
        return response;
    }

    public ApiResponse addMultipleBooks(List<BookDto> books) {
        ApiResponse response = new ApiResponse();
        for(BookDto bookDto : books) {
            if(validateBook(bookDto)) {
                this.constructBookAndSave(bookDto);
            } else {
                response.setMessage(MessageConstants.BOOK_AND_AUTHOR_REQUIRED);
                response.setStatusCode(800L);
                return response;
            }
        }
        response.setMessage(MessageConstants.BOOKS_ADDED_SUCCESSFULLY);
        response.setStatusCode(200L);
        return response;
    }

    @Override
    public ApiResponse getBooks(BookAdminRequestDto bookAdminRequestDto) {
//        Sort Object;
        Sort sort = bookAdminRequestDto.getSortDir().equalsIgnoreCase("asc")
                ? Sort.by(bookAdminRequestDto.getSortBy()).ascending()
                : Sort.by(bookAdminRequestDto.getSortBy()).descending();

//        Pageable Object
        Pageable pageable = PageRequest.of(bookAdminRequestDto.getPage(), bookAdminRequestDto.getSize(), sort);

        List<BookDto> booksDto = convertBooksToBookDtos(bookRepository.findAll(pageable)
                .stream()
                .toList());
        ApiResponse response = new ApiResponse();
        response.setBooks(booksDto);
        response.setStatusCode(200L);
        response.setMessage(MessageConstants.BOOKS_RETRIEVED_SUCCESSFULLY);
        return response;
    }

    @Override
    public ApiResponse searchBooks(BookDto bookDto) {
        List<Book> books = bookRepository.findAll();
        Predicate<Book> nameFilter = (Book book) -> book.getName().toUpperCase().contains(bookDto.getName().toUpperCase());
        Predicate<Book> priceFilter = (Book book) -> book.getPrice() > bookDto.getPrice();
        ApiResponse response = new ApiResponse();
        List<BookDto> booksDto = convertBooksToBookDtos(books.stream()
                .filter(nameFilter)
                .filter(priceFilter)
                .toList());
        response.setBooks(booksDto);
        response.setStatusCode(200L);
        response.setMessage(MessageConstants.BOOKS_RETRIEVED_SUCCESSFULLY);
        return response;
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
            bookDto.setName(book.getName().toUpperCase());
            bookDto.setAuthor(book.getAuthor());
            bookDto.setPrice(book.getPrice());
            bookDto.setStock(book.getStock());
            bookDto.setDescription(book.getDescription());
            bookDto.setPublisherName(book.getPublisherName());
            return bookDto;
        }).toList();
    }

}
