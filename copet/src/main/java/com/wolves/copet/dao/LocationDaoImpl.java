package com.wolves.copet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.wolves.copet.dto.Location;

@Repository
public class LocationDaoImpl implements LocationDao {
	@Autowired
	private JdbcTemplate jdbc;
	private HashMap<Integer, Location> shelters = new HashMap<>();

	@Override
	public Location getLocation(Integer id) {

		if (!shelters.containsKey(id)) {
			final String SELECT_LOCATION = "SELECT * from locations WHERE id = ?;";
			return jdbc.queryForObject(SELECT_LOCATION, new LocationDaoImpl.LocationMapper(), id);
		} else {
			return shelters.get(id);
		}
	}

	@Override
	public List<Location> getAllLocations() {
		final String SELECT_LOCATION = "SELECT * from locations;";
		return jdbc.query(SELECT_LOCATION, new LocationDaoImpl.LocationMapper());
	}

	@Override
	public Location updateLocation(Integer id, String field, String updatedInfo) {
		// This function will update one of 3 fields contactInfo, description, or
		// locationName
		// Validation of this information is meant to be done in the service layer where
		// it will
		// also pass the field that was selected to be edited.
		final String UPDATE_LOCATION = "UPDATE locations SET " + field + " = ? WHERE id = ?;";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update((Connection conn) -> {

			PreparedStatement statement = conn.prepareStatement(UPDATE_LOCATION, Statement.RETURN_GENERATED_KEYS);

			// statement.setString(1, field.toString());
			statement.setString(1, updatedInfo.toString());
			statement.setString(2, id.toString());
			return statement;
		}, keyHolder);
		loadAllToShelters();
		return shelters.get(id);
	}

	private void loadAllToShelters() {
		List<Location> locationList = getAllLocations();
		if (!locationList.isEmpty()) {
			for (Location shelter : locationList) {
				shelters.put(shelter.getId(), shelter);
			}
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
}
