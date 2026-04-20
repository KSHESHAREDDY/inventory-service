package com.mycompany.inventery_service.dao.impl;

import com.mycompany.inventery_service.dao.BookDao;
import com.mycompany.inventery_service.dto.BookDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<BookDto> findBookWithAuthor(Long id) {
        String sql = """
    SELECT 
        b.name, 
        b.author_id, 
        b.price, 
        b.stock, 
        b.description, 
        b.publisher_name
    FROM books b
    JOIN authors a ON b.author_id = a.id
    WHERE a.id = ?
    """;

        return jdbcTemplate.query(sql, ps -> ps.setLong(1, id), rs -> {
            if (rs.next()) {
                return Optional.of(new BookDto(
                        rs.getString("name"),
                        rs.getLong("author_id"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("description"),
                        rs.getString("publisher_name")
                ));
            }
            return Optional.empty();
        });
    }
}
