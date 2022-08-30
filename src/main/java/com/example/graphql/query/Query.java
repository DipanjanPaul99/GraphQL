package com.example.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.graphql.model.Car;
import com.example.graphql.model.Owner;
import com.example.graphql.repository.CarRepository;
import com.example.graphql.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Query class implements the GraphQLQueryResolver interface
 * @author Dipanjan Paul
 */
@Component
public class Query implements GraphQLQueryResolver {

    private final CarRepository carRepository;

    private final OwnerRepository ownerRepository;

    @Autowired
    public Query(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    /**
     * findAllAuthor calls the findAll method from repository
     * @return iterator of type Author
     */
    public Iterable<Car> findAllCars() {
        return carRepository.findAll();
    }

    /**
     * findAllTutorials calls the findAll method from repository
     * @return iterator of type Book
     */
    public Iterable<Owner> findAllOwner() {
        return ownerRepository.findAll();
    }

    /**
     * countAuthors call the count from repository
     * @return long
     */
    public long countCars() {
        return carRepository.count();
    }

    /**
     * countTutorial calls the count from repository
     * @return long
     */
    public long countOwners() {
        return ownerRepository.count();
    }
}