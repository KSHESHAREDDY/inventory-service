package com.mycompany.inventery_service.repository;

import com.mycompany.inventery_service.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    public List<Book> findAll();
    public List<Book> findByNameContaining(String name);
    List<Book> findByNameContainingAndPriceGreaterThanAndPriceLessThan(String query, Long priceFrom, Long priceTo);
    List<Book> findAll(Example<Book> example);
}
