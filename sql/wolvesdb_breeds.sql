
DROP TABLE IF EXISTS `breeds`;

CREATE TABLE `breeds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `breedName` varchar(50) NOT NULL,
  `activityLevel` varchar(25) NOT NULL,
  `size` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
)
INSERT INTO `breeds` VALUES (1,'Labrador Retriever','High','Large'),(2,'German Shepherd','High','Large'),(3,'Golden Retriever','High','Large'),(4,'French Bulldog','Low','Small'),(5,'Beagle','Medium','Medium'),(6,'Poodle','High','Medium'),(7,'Boxer','High','Large'),(8,'Bulldog','Low','Medium'),(9,'Siberian Husky','High','Large'),(10,'Chihuahua','Low','Small'),(11,'Siamese','High','Medium'),(12,'Persian','Low','Medium'),(13,'Bengal','High','Medium'),(14,'Sphynx','High','Small'),(15,'Maine Coon','Medium','Large'),(16,'Scottish Fold','Low','Medium'),(17,'American Shorthair','Medium','Medium'),(18,'Ragdoll','Low','Large'),(19,'British Shorthair','Low','Medium'),(20,'Russian Blue','Medium','Medium');

