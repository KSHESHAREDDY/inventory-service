package com.mycompany.inventery_service.repository;

import com.mycompany.inventery_service.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, CrudRepository<Book, Long> {
    public Page<Book> findAll(Pageable pageable);
    public List<Book> findByNameContaining(String name);
    List<Book> findByNameContainingAndPriceGreaterThanAndPriceLessThan(String query, Long priceFrom, Long priceTo);
}
