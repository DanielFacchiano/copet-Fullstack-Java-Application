package com.wolves.copet.dto;

import java.util.Objects;

public class Breed {
	private int id;
	private String breedName;
	private String activityLevel;
	private String size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBreedName() {
		return breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public String getActivityLevel() {
		return activityLevel;
	}

	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activityLevel, breedName, id, size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Breed other = (Breed) obj;
		return Objects.equals(activityLevel, other.activityLevel) && Objects.equals(breedName, other.breedName)
				&& id == other.id && Objects.equals(size, other.size);
	}

	@Override
	public String toString() {
		return "Breed [id=" + id + ", breedName=" + breedName + ", activityLevel=" + activityLevel + ", size=" + size
				+ "]";
	}

}
