package com.example.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.model.Author;
import com.example.graphql.model.Book;
import com.example.graphql.repository.AuthorRepository;
import com.example.graphql.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *	Mutation class implements GraphQLMutationResolver interface
 *	@author Dipanjan Paul
 */

@Component
public class Mutation implements GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Autowired
    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    /**
     * createAuthor method saves the author details
     * @param name
     * @param age
     * @return the author object
     */
    public Author createAuthor(String name, Integer age) {
        return authorRepository.save(new Author(name, age));
    }

    /**
     * createTutorial method saves the Book Details
     * @param title
     * @param description
     * @param authorId
     * @return the tutorial object
     */
    public Book createBook(String title, String description, Long authorId) {
        return bookRepository.save(new Book(title, description, new Author(authorId)));
    }

    /**
     * deleteBook method deletes the book with particular id
     * @param id
     * @return boolean true
     */
    public boolean deleteBook(Long id) {
        bookRepository.deleteById(id);
        return true;
    }

    /**
     * updateBook method updates the parameter of particular Book
     * @param id
     * @param title
     * @param description
     * @param authorId
     * @return return the Book object
     * @throws NotFoundException
     */
    public Book updateBook(Long id, String title, String description, Long authorId) throws NotFoundException {
        Optional<Book> optBook = bookRepository.findById(id);
        if (optBook.isPresent()) {
            Book tutorial = optBook.get();
            if (title != null)
                tutorial.setTitle(title);
            if (description != null)
                tutorial.setDescription(description);
            if (authorId != null)
                tutorial.setAuthor(authorRepository.findById(authorId).orElseThrow());
            return bookRepository.save(tutorial);
        }
        throw new NotFoundException("Not found Tutorial to update!");
    }
}