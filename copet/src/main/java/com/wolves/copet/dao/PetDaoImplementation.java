package com.wolves.copet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.wolves.copet.dto.Breed;
import com.wolves.copet.dto.Location;
import com.wolves.copet.dto.Pet;

@Repository
public class PetDaoImplementation implements PetDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Pet getPet(int petId) {
		try {
			final String SELECT_PET_BY_ID = "SELECT * FROM pets WHERE id = ?";
			Pet thePet = jdbc.queryForObject(SELECT_PET_BY_ID, new PetMapper(), petId);
			thePet.setBreedList(getBreedsForPet(petId));
			thePet.setLocation(getLocationForPet(petId));
			return thePet;
		} catch (DataAccessException ex) {
			return null;
		}
	}

	public  List<Breed> getBreedsForPet(int petId) {
		final String SELECT_BREEDS_FOR_PET = "SELECT b.* FROM breeds b join petBreeds pb on b.id = pb.breedId "
				+ "where pb.petId = ?";
		return jdbc.query(SELECT_BREEDS_FOR_PET, new BreedMapper(), petId);
	}

	private Location getLocationForPet(int petId) {
		final String SELECT_LOCATION_FOR_PET = "Select l.* FROM locations l JOIN pets p ON l.id = p.locationId where p.id = ?";
		return jdbc.queryForObject(SELECT_LOCATION_FOR_PET, new LocationMapper(), petId);

	}

	@Override
	public List<Pet> getAllPets() {
		final String SELECT_ALL_PETS = "SELECT * FROM pets";
		List<Pet> thePets = jdbc.query(SELECT_ALL_PETS, new PetMapper());
		addLocationAndBreedsToPets(thePets);
		return thePets;
	}

	private void addLocationAndBreedsToPets(List<Pet> thePets) {
		for (Pet pet : thePets) {
			pet.setLocation(getLocationForPet(pet.getId()));
			pet.setBreedList(getBreedsForPet(pet.getId()));
		}
	}

	@Override
	public Pet createPet(Pet newPet) {
		final String sql = "INSERT INTO pets(petName, species, petDescription, age, locationId) VALUES(?, ?, ?, ?, ?);";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update((Connection conn) -> {
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newPet.getPetName());
			statement.setString(2, newPet.getSpecies());
			statement.setString(3, newPet.getPetDescription());
			statement.setInt(4, newPet.getAge());
			statement.setInt(5, newPet.getLocationId());
			return statement;
		}, keyHolder);

		newPet.setId(keyHolder.getKey().intValue());

		return newPet;
	}

	@Override
	public boolean deletePet(int petId) {
		String sql = "DELETE FROM pets WHERE id = ?";
		int rowsDeleted = jdbc.update(sql, petId);
		if (rowsDeleted > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Pet> getPetsByUser(int userId) {
		String sql = "SELECT * FROM pets WHERE adopterId = ?";
		List<Pet> pets = jdbc.query(sql, new PetMapper(), userId);
		addLocationAndBreedsToPets(pets);
		return pets;
	}

	public static final class BreedMapper implements RowMapper<Breed> {
		@Override
		public Breed mapRow(ResultSet rs, int index) throws SQLException {
			Breed theBreed = new Breed();
			theBreed.setId(rs.getInt("id"));
			theBreed.setBreedName(rs.getString("breedName"));
			theBreed.setActivityLevel(rs.getString("activityLevel"));
			theBreed.setSize(rs.getString("size"));
			return theBreed;
		}
	}

	public static final class PetMapper implements RowMapper<Pet> {
		@Override
		public Pet mapRow(ResultSet rs, int index) throws SQLException {
			Pet thePet = new Pet();
			thePet.setId(rs.getInt("id"));
			thePet.setPetName(rs.getString("petName"));
			thePet.setSpecies(rs.getString("species"));
			thePet.setAge(rs.getInt("age"));
			thePet.setPetDescription(rs.getString("petDescription"));
			thePet.setLocationId(rs.getInt("locationId"));
			thePet.setAdopterId(rs.getInt("adopterId"));
			return thePet;
		}
	}

	public static final class LocationMapper implements RowMapper<Location> {
		@Override
		public Location mapRow(ResultSet rs, int index) throws SQLException {
			Location newLocation = new Location();
			newLocation.setId(rs.getInt("id"));
			newLocation.setState(rs.getString("state"));
			newLocation.setLocationName(rs.getString("locationName"));
			newLocation.setAddress(rs.getString("address"));
			newLocation.setContactInfo(rs.getString("contactInfo"));
			newLocation.setLocationDescription(rs.getString("locationDescription"));
			return newLocation;
		}
	}

	@Override
	public void linkPetToBreeds(int petId, List<Integer> breedIds) {
		final String INSERT_PET_BREED = "INSERT INTO petBreeds (petId, breedId) VALUES(?,?)";
		for (Integer breedId : breedIds) {
			jdbc.update(INSERT_PET_BREED, petId, breedId);
		}
	}

	@Override
	public void deleteBreedLink(int petId, List<Integer> breedIds) {
		final String DELETE_PET_BREED = "DELETE FROM petBreeds WHERE (petId = ? AND breedId = ?)";
		for (Integer breedId : breedIds) {
			jdbc.update(DELETE_PET_BREED, petId, breedId);
		}
	}

	@Override
	public boolean updatePet(Pet pet) {
		String sql = "UPDATE pets SET petName = ?, age = ?, species = ?, petDescription = ?, adopterId = ? WHERE id = ?";
		int rowsUpdated = jdbc.update(sql, pet.getPetName(), pet.getAge(), pet.getSpecies(), pet.getPetDescription(), pet.getAdopterId(),
				pet.getId());
		if (rowsUpdated > 0) {
			// update successful, create a new pet object with the updated values and return
			return true;
		} else {
			// update failed, return false
			return false;
		}
	}
}
