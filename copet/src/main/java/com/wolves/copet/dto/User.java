package com.wolves.copet.dto;

import java.util.Objects;

public class User {
	private int id;
	private String userName;
	private String hashedPw;
	private boolean isAdmin;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHashedPw() {
		return hashedPw;
	}

	public void setHashedPw(String hashedPw) {
		this.hashedPw = hashedPw;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getAdmin(){return this.isAdmin;}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, hashedPw, id, isAdmin, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(hashedPw, other.hashedPw) && id == other.id
				&& isAdmin == other.isAdmin && Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", hashedPw=" + hashedPw + ", isAdmin=" + isAdmin
				+ ", email=" + email + "]";
	}

}
