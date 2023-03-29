package com.wolves.copet.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolves.copet.dto.Location;
import com.wolves.copet.dto.Pet;
import com.wolves.copet.dto.User;
import com.wolves.copet.service.CopetServiceImpl;
import com.wolves.copet.service.EntryDataInfoValidationException;

@RestController
@RequestMapping("/api")
public class CopetController {
	private CopetServiceImpl service;

	public CopetController(CopetServiceImpl service) {
		this.service = service;
	}

	@CrossOrigin
	@PostMapping("/Login")
	public ResponseEntity<User> login(@RequestBody User theUser) {
		User loggedInUser = null;
		try {
			loggedInUser = service.login(theUser.getUserName(), theUser.getHashedPw());
		} catch (NoSuchAlgorithmException e) {
			return new ResponseEntity("User details not found.", HttpStatus.I_AM_A_TEAPOT);
		}
		if (loggedInUser == null) {
			return new ResponseEntity("User details not found.", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(loggedInUser);
	}

	@CrossOrigin
	@GetMapping("/pets")
	public ResponseEntity<List<Pet>> getAllPets() {
		return ResponseEntity.ok(service.getAllPets());
	}

	@CrossOrigin
	@GetMapping("/pets/{id}")
	public ResponseEntity<Pet> getPetById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getPetById(id));
	}

	@CrossOrigin
	@PostMapping("/pets")
	public ResponseEntity<Pet> createPet(RequestEntity<String> requestEntity) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(requestEntity.getBody());
		Pet newPet = new Pet();
		newPet.setPetName(jsonNode.get("petName").asText());
		newPet.setSpecies(jsonNode.get("species").asText());
		newPet.setPetDescription(jsonNode.get("petDescription").asText());
		newPet.setAge(jsonNode.get("age").asInt());
		newPet.setLocationId(jsonNode.get("locationId").asInt());
		List<Integer> breedInts = new ArrayList<>();
		JsonNode breeds = jsonNode.get("breeds");
		for (JsonNode breed : breeds) {
			breedInts.add(breed.asInt());
		}

		try {
			Pet addedPet = service.createNewPet(newPet, breedInts);
			return ResponseEntity.ok(addedPet);
		} catch (EntryDataInfoValidationException e) {
			System.out.println("ERROR: " + e.getMessage());
			return new ResponseEntity("Error in pet information.", HttpStatus.BAD_REQUEST);
		}

	}

	@CrossOrigin
	@PutMapping("/pets/{id}")
	public ResponseEntity<?> updatePet(RequestEntity<String> requestEntity) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(requestEntity.getBody());
		Pet newPet = new Pet();

		newPet.setId(jsonNode.get("id").asInt());
		newPet.setPetName(jsonNode.get("petName").asText());
		newPet.setSpecies(jsonNode.get("species").asText());
		newPet.setPetDescription(jsonNode.get("petDescription").asText());
		newPet.setAge(jsonNode.get("age").asInt());
		newPet.setAdopterId(jsonNode.get("adopterId").asInt());
		newPet.setLocationId(jsonNode.get("locationId").asInt());
		List<Integer> breedInts = new ArrayList<>();

		if (jsonNode.get("breeds") != null) {

			JsonNode breeds = jsonNode.get("breeds");
			for (JsonNode breed : breeds) {
				breedInts.add(breed.asInt());
			}
		}
		boolean res = service.updatePet(newPet, breedInts);
		if (res == false) {
			return new ResponseEntity(null, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	@CrossOrigin
	@GetMapping("/locations")
	public ResponseEntity<List<Location>> getAllLocations() {
		return ResponseEntity.ok(service.getAllLocations());
	}

	@CrossOrigin
	@GetMapping("/locations/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getLocationById(id));
	}

	@CrossOrigin
	@PutMapping("/locations/{id}")
	public ResponseEntity<Location> updateLocation(@RequestBody Location updateInfo) {
		try {
			Location updatedLocal = service.updateLocationDetails(updateInfo);
			return ResponseEntity.ok(updatedLocal);
		} catch (EntryDataInfoValidationException e) {
			System.out.println("ERROR: " + e.getMessage());
			return new ResponseEntity("Error in location information.", HttpStatus.BAD_REQUEST);
		}
	}

	@CrossOrigin
	@GetMapping("/users/pets/{id}")
	public ResponseEntity<List<Pet>> getPetsForUser(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getPetsByUser(id));
	}

	@CrossOrigin
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUserInfo(@RequestBody User updateInfo) {
		return ResponseEntity.ok(service.updateUserInfo(updateInfo));
	}

	@CrossOrigin
	@DeleteMapping("/pets/{id}")
	public ResponseEntity<?> updatePet(@PathVariable Integer id) {
		System.out.println(id);

		return ResponseEntity.ok(service.deletePet(id));
	}
}
