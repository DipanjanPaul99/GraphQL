package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BookResolver class implements the GraphQLResolver of type Book
 */
@Component
public class BookResolver implements GraphQLResolver<Book> {
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * getAuthor methods find the author details from the repository
     * @param book
     * @return author object
     */
    public Author getAuthor(Book book) {
        return authorRepository.findById(book.getAuthor().getId()).orElseThrow(null);
    }
}
