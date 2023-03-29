package com.wolves.copet.dao;

import java.util.List;

import com.wolves.copet.dto.Pet;

public interface PetDao {

	public Pet getPet(int id);

	public List<Pet> getAllPets();

	public Pet createPet(Pet newPet);

	public boolean deletePet(int petId);

	public boolean updatePet(Pet pet);

	List<Pet> getPetsByUser(int userId);

	public void linkPetToBreeds(int petId, List<Integer> breedIds);

	public void deleteBreedLink(int petId, List<Integer> breedIds);
}
