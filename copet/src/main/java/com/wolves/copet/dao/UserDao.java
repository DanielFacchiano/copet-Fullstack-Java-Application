package com.wolves.copet.dao;

import com.wolves.copet.dto.User;

public interface UserDao {

	public User getUser(int id);

	public User createUser(User newUser);

	public boolean updateUser(User newUser);
}
