
DROP TABLE IF EXISTS `petBreeds`;

CREATE TABLE `petBreeds` (
  `petId` int NOT NULL,
  `breedId` int NOT NULL,
  PRIMARY KEY (`petId`,`breedId`),
  KEY `fk_petBreeds_breeds` (`breedId`),
  CONSTRAINT `fk_petBreeds_breeds` FOREIGN KEY (`breedId`) REFERENCES `breeds` (`id`),
  CONSTRAINT `fk_petBreeds_pets` FOREIGN KEY (`petId`) REFERENCES `pets` (`id`)
)


INSERT INTO `petBreeds` VALUES (6,1),(8,1),(43,1),(46,1),(5,2),(6,2),(43,2),(5,3),(8,5),(9,12),(45,13),(44,14),(45,14),(44,15),(45,15),(45,17),(44,19),(45,19);
