package com.wolves.copet.dto;

import java.util.List;
import java.util.Objects;

public class Pet {
	private int id;
	private String petName;
	private String species;
	private String petDescription;
	private int age;
	private Integer adopterId;
	private int locationId;
	private Location location;
	private List<Breed> BreedList;

	public int getId() {
		return id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Breed> getBreedList() {
		return BreedList;
	}

	public void setBreedList(List<Breed> breedList) {
		BreedList = breedList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getPetDescription() {
		return petDescription;
	}

	public void setPetDescription(String petDescription) {
		this.petDescription = petDescription;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getAdopterId() {
		return adopterId;
	}

	public void setAdopterId(Integer adopterId) {
		this.adopterId = adopterId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(adopterId, age, id, locationId, petDescription, petName, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return adopterId == other.adopterId && age == other.age && id == other.id && locationId == other.locationId
				&& Objects.equals(petDescription, other.petDescription) && Objects.equals(petName, other.petName)
				&& Objects.equals(species, other.species);
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", petName=" + petName + ", species=" + species + ", petDescription=" + petDescription
				+ ", age=" + age + ", adopterId=" + adopterId + ", locationId=" + locationId + ", location=" + location
				+ ", BreedList=" + BreedList + "]";
	}
}
