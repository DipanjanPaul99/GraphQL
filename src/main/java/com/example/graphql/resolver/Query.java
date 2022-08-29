package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Query class implements the GraphQLQueryResolver interface
 * @author Dipanjan Paul
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * findAllAuthor calls the findAll method from repository
     * @return iterator of type Author
     */
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * findAllTutorials calls the findAll method from repository
     * @return iterator of type Book
     */
    public Iterable<Book> findAllTutorials() {
        return bookRepository.findAll();
    }

    /**
     * countAuthors call the count from repository
     * @return long
     */
    public long countAuthors() {
        return authorRepository.count();
    }

    /**
     * countTutorial calls the count from repository
     * @return long
     */
    public long countTutorials() {
        return bookRepository.count();
    }
}