package com.wolves.copet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.wolves.copet.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public boolean updateUser(User newUser) {
		final String UPDATE_USER = "UPDATE users SET userName = ?, hashedPw = ?, isAdmin = ?, email = ? WHERE id = ?;";
		int rowsUpdated = jdbc.update(UPDATE_USER, newUser.getUserName(), newUser.getHashedPw(), newUser.isAdmin(),
				newUser.getEmail(), newUser.getId());
		if (rowsUpdated > 0) {
			// update successful, create a new pet object with the updated values and return
			return true;
		} else {
			// update failed, return false
			return false;
		}
	}

	@Override
	public User getUser(int id) {
		final String SELECT_USER = "SELECT * from users WHERE id = ?;";
		return jdbc.queryForObject(SELECT_USER, new UserMapper(), id);

	}

	public User getUserByName(String userName, String password) {
		final String SELECT_USER = "SELECT * from users WHERE (userName = ? AND hashedPw = ?)";
		return jdbc.queryForObject(SELECT_USER, new UserMapper(), userName, password);
	}

	@Override
	public User createUser(User newUser) {
		final String CREATE_USER = "INSERT INTO users(userName, hashedPw, isAdmin, email) VALUES(?,?,?,?);";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update((Connection conn) -> {
			PreparedStatement statement = conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newUser.getUserName());
			statement.setString(2, newUser.getHashedPw());
			statement.setBoolean(3, newUser.isAdmin());
			statement.setString(4, newUser.getEmail());
			return statement;
		}, keyHolder);
		newUser.setId(keyHolder.getKey().intValue());
		return newUser;
	}

	public static final class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int index) throws SQLException {
			User theUser = new User();
			theUser.setId(rs.getInt("id"));
			theUser.setUserName(rs.getString("userName"));
			theUser.setHashedPw(rs.getString("hashedPw"));
			theUser.setAdmin(rs.getBoolean("isAdmin"));
			theUser.setEmail(rs.getString("email"));
			return theUser;
		}
	}
}
