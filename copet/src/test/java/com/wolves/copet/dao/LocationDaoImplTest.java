package com.wolves.copet.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wolves.copet.CopetApplication;
import com.wolves.copet.dto.Location;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CopetApplication.class)
class LocationDaoImplTest {
	@Autowired
	LocationDao locationDao;

	@Test
	void getLocation() {
		Location dbLocation = locationDao.getLocation(1);

		assertEquals("Texas", dbLocation.getState());
	}

	@Test
	void getAllLocations() {
		List<Location> allDBLocations = locationDao.getAllLocations();
		for (Location shelter : allDBLocations) {
			System.out.println(shelter.toString());
		}

		assertFalse(allDBLocations.isEmpty());
	}

	@Test
	void updateLocation() {
		locationDao.updateLocation(3, "locationDescription", "Test.");
		String oldDesc = locationDao.getLocation(3).getLocationDescription();
		Location updatedLocation = locationDao.updateLocation(3, "locationDescription",
				"Small shelter focused on rescue cats from the local area.");
		System.out.println(updatedLocation);
		assertNotEquals(oldDesc, updatedLocation.getLocationDescription());

	}
}