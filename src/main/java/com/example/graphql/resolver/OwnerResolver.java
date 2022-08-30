package com.example.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.graphql.model.Car;
import com.example.graphql.model.Owner;
import com.example.graphql.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OwnerResolver class implements the GraphQLResolver of type Book
 */
@Component
public class OwnerResolver implements GraphQLResolver<Owner> {
    @Autowired
    private CarRepository carRepository;

    /**
     * getAuthor methods find the author details from the repository
     * @param owner
     * @return author object
     */
    public Car getOwner(Owner owner) {
        return carRepository.findById(owner.getCar().getId()).orElseThrow(null);
    }
}
