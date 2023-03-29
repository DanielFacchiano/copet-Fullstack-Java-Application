

DROP TABLE IF EXISTS `locations`;

CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `state` varchar(25) NOT NULL,
  `locationName` varchar(50) NOT NULL,
  `address` varchar(75) NOT NULL,
  `contactInfo` varchar(50) NOT NULL,
  `locationDescription` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
)
INSERT INTO `locations` VALUES (1,'Texas','AnimalsRUs','123 Main Street','Brenda: (512) 453-7761','Large shelter focusing on rescues. Wide selection of cats and dogs for adoption!'),(2,'California','AnimalsAnimalsAnimals','7171 Street Road','Jenna: (618) 536-7471','Small shelter.'),(3,'Florida','AnimalsAndMore','1172 Rocky Avenue','Joy: (512) 763-9832','Small animal shelter with limited cats and dogs.');
