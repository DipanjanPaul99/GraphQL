package com.example.graphql.model;

import javax.persistence.*;

/**
 *	This Entity class represents the Book data where basic data information are stored(such as name, occupation, car)
 *	@author Dipanjan Paul
 */
@Entity
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "occupation")
	private String occupation;

	@ManyToOne
	@JoinColumn(name = "car_id", nullable = false)
	private Car car;

	public Owner() {
	}

	public Owner(String name, String occupation, Car car) {
		this.name = name;
		this.occupation = occupation;
		this.car = car;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", title=" + name + ", occupation=" + occupation + ", car=" + car + "]";
	}

}
