package com.mycompany.inventery_service.service.impl;

import com.mycompany.inventery_service.dto.ApiResponse;
import com.mycompany.inventery_service.dto.AuthorDto;
import com.mycompany.inventery_service.entity.Author;
import com.mycompany.inventery_service.repository.AuthorRepository;
import com.mycompany.inventery_service.service.AuthorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Override
    public ApiResponse searchAuthor(String name) {
        ApiResponse response = new ApiResponse();
        if(StringUtils.isBlank(name)) {
            response.setMessage("Author name is required");
            response.setStatusCode(800L);
        } else {
            Author author = authorRepository.findByName(name);
            if(author == null) {
                response.setMessage("Author not found");
                response.setStatusCode(404L);
                return response;
            }
            AuthorDto authorDto = convertToAuthorDto(authorRepository.findByName(name));
            response.setAuthors(List.of(authorDto));
            response.setMessage("Author found successfully");
            response.setStatusCode(200L);
        }
        return response;
    }

    private AuthorDto convertToAuthorDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setName(author.getName());
        return authorDto;
    }
}
