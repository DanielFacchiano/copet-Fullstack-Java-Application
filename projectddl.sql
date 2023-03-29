
CREATE TABLE users (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50) NOT NULL,
	hashedPw VARCHAR(50) NOT NULL,
    isAdmin boolean NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE locations (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    state VARCHAR(25) NOT NULL,
	locationName VARCHAR(50) NOT NULL,
    address VARCHAR(75) NOT NULL,
    contactInfo VARCHAR(50) NOT NULL,
    locationDescription mediumText NOT NULL
);

CREATE TABLE breeds (
    id  INT PRIMARY KEY AUTO_INCREMENT,
    breedName VARCHAR(50) NOT NULL,
	activityLevel VARCHAR(25) NOT NULL,
    size VARCHAR(25) NOT NULL
);

CREATE TABLE pets(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    petName VARCHAR(50) NOT NULL,
    species VARCHAR(25) NOT NULL,
    petDescription mediumText NOT NULL,
    age INT NOT NULL,
    adopterId INT,
    locationId INT,
    CONSTRAINT fk_pet_location
		FOREIGN KEY(locationId)
		REFERENCES locations(id),
    CONSTRAINT fk_pet_user
		FOREIGN KEY (adopterId)
		REFERENCES users(id)	
);

CREATE TABLE petBreeds(
    petId INT,
    breedId INT,
    CONSTRAINT pk_petBreeds
		PRIMARY KEY(petId, breedId),
    CONSTRAINT fk_petBreeds_breeds
		FOREIGN KEY(breedId)
		REFERENCES breeds(id),
    CONSTRAINT fk_petBreeds_pets
		FOREIGN KEY (petId)
		REFERENCES pets(id)	
);
