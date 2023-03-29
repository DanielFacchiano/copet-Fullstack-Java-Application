package com.wolves.copet.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wolves.copet.CopetApplication;
import com.wolves.copet.dto.Pet;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CopetApplication.class)
class PetDaoImplementationTest {

	@Autowired
	PetDao petDao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void createAndDeletePet() {
		Pet doggo = new Pet();
		doggo.setPetName("Doggo");
		doggo.setPetDescription("Goes woof");
		doggo.setAge(12);
		doggo.setSpecies("Dog");
		doggo.setLocationId(1);
		Pet res = petDao.createPet(doggo);
		boolean result = petDao.deletePet(doggo.getId());
		Pet fromDao = petDao.getPet(doggo.getId());
		assertNull(fromDao);
	}

	@Test
	void testUpdateAndGetPet() {
		Pet doggo = new Pet();
		doggo.setPetName("Doggo");
		doggo.setPetDescription("Goes woof");
		doggo.setAge(12);
		doggo.setSpecies("Dog");
		doggo.setLocationId(1);
		doggo.setAdopterId(null);
		Pet res = petDao.createPet(doggo);

		Pet fido = petDao.getPet(doggo.getId());
		fido.setAdopterId(null);
		Pet fido2 = petDao.getPet(doggo.getId());
		fido2.setAdopterId(null);
		assertTrue(fido.equals(fido2));
		fido.setAge(3);
		fido.setPetName("bob");

		petDao.updatePet(fido);
		fido = petDao.getPet(fido.getId());
		assertFalse(fido.equals(fido2));
		boolean result = petDao.deletePet(fido.getId());
		Pet fromDao = petDao.getPet(fido.getId());
		assertNull(fromDao);

	}

	@Test
	void getAllPets() {
		Pet doggo = new Pet();
		doggo.setPetName("Doggo");
		doggo.setPetDescription("Goes woof");
		doggo.setAge(12);
		doggo.setSpecies("Dog");
		doggo.setLocationId(1);
		Pet res = petDao.createPet(doggo);
		List petList = petDao.getAllPets();
		assertTrue(petList.size() > 0);
		boolean result = petDao.deletePet(doggo.getId());
		Pet fromDao = petDao.getPet(doggo.getId());
		assertNull(fromDao);
	}

	@Test
	void testAddBreedToPet() {
		Pet doggo = new Pet();
		doggo.setPetName("Doggo");
		doggo.setPetDescription("Goes woof");
		doggo.setAge(12);
		doggo.setSpecies("Dog");
		doggo.setLocationId(1);
		Pet res = petDao.createPet(doggo);

		List<Integer> breedList = Arrays.asList(1, 2);
		petDao.linkPetToBreeds(doggo.getId(), breedList);
		Pet fromDao = petDao.getPet(doggo.getId());
		assertTrue(fromDao.getBreedList().size() == 2);
		petDao.deleteBreedLink(doggo.getId(), breedList);

		boolean result = petDao.deletePet(doggo.getId());
		fromDao = petDao.getPet(doggo.getId());
		assertNull(fromDao);
	}
}
