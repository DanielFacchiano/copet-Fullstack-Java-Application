package com.wolves.copet.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wolves.copet.dao.LocationDaoImpl;
import com.wolves.copet.dao.PetDaoImplementation;
import com.wolves.copet.dao.UserDaoImpl;
import com.wolves.copet.dto.Breed;
import com.wolves.copet.dto.Location;
import com.wolves.copet.dto.Pet;
import com.wolves.copet.dto.User;

@Component
public class CopetServiceImpl {

	@Autowired
	private LocationDaoImpl locationDao;
	@Autowired
	private UserDaoImpl userDao;
	@Autowired
	private PetDaoImplementation petDao;

	public CopetServiceImpl(LocationDaoImpl locationDao, UserDaoImpl userDao, PetDaoImplementation petDao) {
		this.locationDao = locationDao;
		this.userDao = userDao;
		this.petDao = petDao;
	}

	public List<Pet> getAllPets() {
		return petDao.getAllPets();
	}

	public Pet getPetById(Integer id) {
		return petDao.getPet(id);
	}

	public Pet createNewPet(Pet newPet, List<Integer> breedId) throws EntryDataInfoValidationException {
		if (newPet.getAge() < 0) {
			throw new EntryDataInfoValidationException("Invalid pet age.");
		}
		if (!newPet.getSpecies().toLowerCase().equals("cat") && !newPet.getSpecies().toLowerCase().equals("dog")) {
			throw new EntryDataInfoValidationException("Invalid pet.\nOnly cats or dogs are allowed.");
		}
		String validName = "^[a-zA-Z0-9.\\s]+$";
		if (!newPet.getPetName().matches(validName)) {
			throw new EntryDataInfoValidationException("Invalid pet name.");
		}
		Pet madePet = petDao.createPet(newPet);
		petDao.linkPetToBreeds(madePet.getId(), breedId);
		return madePet;
	}

	public boolean updatePet(Pet updateInfo, List<Integer> breedIds) {
		List<Breed> breedList = petDao.getBreedsForPet(updateInfo.getId());
		List<Integer> intBreeds = new ArrayList<>();
		if (breedIds.size() > 0) {
			for (Breed breed : breedList) {
				intBreeds.add(breed.getId());
			}
			if (updateInfo.getAdopterId() == 0) {
				updateInfo.setAdopterId(null);
			}
		}
		petDao.deleteBreedLink(updateInfo.getId(), intBreeds);
		petDao.linkPetToBreeds(updateInfo.getId(), breedIds);
		return petDao.updatePet(updateInfo);
	}

	public boolean deletePet(Integer id) {
		List<Breed> breedList = petDao.getBreedsForPet(id);
		List<Integer> intBreeds = new ArrayList<>();
		for (Breed breed : breedList) {
			intBreeds.add(breed.getId());
		}
		petDao.deleteBreedLink(id, intBreeds);
		return petDao.deletePet(id);
	}

	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

	public Location getLocationById(Integer id) {
		return locationDao.getLocation(id);
	}

	public Location updateLocationDetails(Location locationDetails) throws EntryDataInfoValidationException {
		// Get all the potentially updatable data out of the object
		String updateName = locationDetails.getLocationName();
		String updateContact = locationDetails.getContactInfo();
		String updateDesc = locationDetails.getLocationDescription();
		// Pull the id that is going to be updated
		Integer locationId = locationDetails.getId();
		if (locationDao.getLocation(locationId) != null) {
			if (updateName != null) {
				// Check to make sure the location name is alphanumeric
				String validName = "^[a-zA-Z0-9,\\s]+$";
				if (updateName.matches(validName)) {
					locationDao.updateLocation(locationId, "locationName", updateName);
				} else {
					throw new EntryDataInfoValidationException("Error, Invalid name entered.");
				}
			}

			if (updateContact != null) {
				locationDao.updateLocation(locationId, "contactInfo", updateContact);
			}
			if (updateDesc != null) {
				locationDao.updateLocation(locationId, "locationDescription", updateDesc);
			}
		}

		return locationDao.getLocation(locationId);

	}

	public List<Pet> getAllPetsForUser(Integer id) {
		return petDao.getPetsByUser(id);
	}

	public User login(String user, String password) throws NoSuchAlgorithmException {

		System.out.println(password.hashCode());

		return userDao.getUserByName(user, String.valueOf(password.hashCode()));
	}

	public List<Pet> getPetsByUser(Integer userId) {
		return petDao.getPetsByUser(userId);
	}

	public boolean updateUserInfo(User updateInfo) {

		User old = userDao.getUser(updateInfo.getId());
		updateInfo.setAdmin(old.getAdmin());
		updateInfo.setHashedPw(old.getHashedPw());
		return userDao.updateUser(updateInfo);
	}
}
