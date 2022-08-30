package com.example.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.graphql.model.Car;
import com.example.graphql.model.Owner;
import com.example.graphql.repository.CarRepository;
import com.example.graphql.repository.OwnerRepository;
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

    private final CarRepository carRepository;

    private final OwnerRepository ownerRepository;

    @Autowired
    public Mutation(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    /**
     * createAuthor method saves the author details
     * @param name
     * @param age
     * @return the author object
     */
    public Car createCar(String name, Integer age) {
        return carRepository.save(new Car(name, age));
    }

    /**
     * createTutorial method saves the Book Details
     * @param name
     * @param occupation
     * @param carId
     * @return the tutorial object
     */
    public Owner createOwner(String name, String occupation, Long carId) {
        return ownerRepository.save(new Owner(name, occupation, carRepository.findById(carId).orElseThrow()));
    }

    /**
     * deleteBook method deletes the book with particular id
     * @param id
     * @return boolean true
     */
    public boolean deleteOwner(Long id) {
        ownerRepository.deleteById(id);
        return true;
    }

    /**
     * updateBook method updates the parameter of particular Book
     * @param id
     * @param name
     * @param occupation
     * @param carId
     * @return return the Book object
     * @throws NotFoundException
     */
    public Owner updateOwner(Long id, String name, String occupation, Long carId) throws NotFoundException {
        Optional<Owner> optOwner = ownerRepository.findById(id);
        if (optOwner.isPresent()) {
            Owner tutorial = optOwner.get();
            if (name != null)
                tutorial.setName(name);
            if (occupation != null)
                tutorial.setOccupation(occupation);
            if (carId != null)
                tutorial.setCar(carRepository.findById(carId).orElseThrow());
            return ownerRepository.save(tutorial);
        }
        throw new NotFoundException("Not found Tutorial to update!");
    }
}