
DROP TABLE IF EXISTS `pets`;

CREATE TABLE `pets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `petName` varchar(50) NOT NULL,
  `species` varchar(25) NOT NULL,
  `petDescription` mediumtext NOT NULL,
  `age` int NOT NULL,
  `adopterId` int DEFAULT NULL,
  `locationId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pet_location` (`locationId`),
  KEY `fk_pet_user` (`adopterId`),
  CONSTRAINT `fk_pet_location` FOREIGN KEY (`locationId`) REFERENCES `locations` (`id`),
  CONSTRAINT `fk_pet_user` FOREIGN KEY (`adopterId`) REFERENCES `users` (`id`)
)


INSERT INTO `pets` VALUES (4,'Mega Lassy','Dog','This is a dog',12,6,1),(5,'Meower','Cat','This is a cat',11,1,2),(6,'BigMeow','Cat','This is a large cat',13,NULL,1),(8,'Max','Dog','Friendly and active, loves to play fetch',2,NULL,1),(9,'Buddy','Dog','Loves belly rubs and going for walks',3,NULL,1),(10,'Lucy','Dog','Loyal and affectionate, great with kids',5,NULL,2),(11,'Daisy','Dog','Happy-go-lucky, loves to play and snuggle',1,NULL,2),(12,'Molly','Dog','Gentle and loving, good with other pets',4,6,3),(13,'Rocky','Dog','Energetic and playful, enjoys going for runs',2,NULL,3),(14,'Luna','Dog','Smart and loyal, loves to learn new tricks',1,NULL,1),(15,'Charlie','Dog','Sweet and friendly, great with children',6,6,3),(16,'Tiger','Cat','Playful and curious, loves to explore',1,NULL,2),(17,'Mittens','Cat','Affectionate and cuddly, enjoys being petted',2,NULL,1),(18,'Simba','Cat','Independent and intelligent, likes to play with toys',4,1,3),(19,'Garfield','Cat','Laid-back and lazy, loves to sleep and eat',3,NULL,2),(20,'Fluffy','Cat','Friendly and social, likes to be around people',1,NULL,2),(21,'Whiskers','Cat','Adventurous and curious, enjoys exploring new places',2,NULL,1),(22,'Bella','Dog','Smart and obedient, loves to learn new tricks',5,NULL,3),(23,'Fluffy','cat','A fluffy white Persian cat with blue eyes',3,NULL,1),(24,'Spot','dog','A friendly Dalmatian who loves to play fetch',2,NULL,1),(25,'Garfield','cat','A lazy orange tabby who loves lasagna',5,NULL,1),(26,'Rex','dog','A loyal German shepherd who is good at guarding',4,6,2),(27,'Mittens','cat','A cute black and white kitten who likes to chase mice',1,NULL,2),(28,'Fido','dog','A playful golden retriever who likes to swim',3,NULL,2),(29,'Snowy','cat','A beautiful Siamese cat with blue eyes and a loud meow',4,NULL,3),(30,'Lassie','dog','A smart collie who can do tricks and save people in trouble',6,NULL,3),(31,'Simba','cat','A majestic lion-like cat with a golden mane and a roar',7,NULL,3),(32,'Snoopy','dog','A funny beagle who likes to sleep on top of his doghouse and pretend he is a pilot',5,NULL,1),(33,'Nala','cat','A sweet calico cat who likes to cuddle and purr',2,NULL,1),(34,'Max','dog','A energetic Jack Russell terrier who likes to dig and chase squirrels ',3,NULL,1),(35,'Luna','cat','A mysterious black cat who likes to explore at night and has green eyes ',4,NULL,2),(36,'Buddy','dog','A friendly Labrador retriever who likes to fetch and play with other dogs ',2,NULL,2),(37,'Tigger','cat','A striped orange cat who likes to bounce and pounce on everything ',1,NULL,2),(38,'Bella','dog','A beautiful Maltese who likes to be groomed and pampered ',4,5,2),(39,'Oreo','cat','A delicious-looking black and white cat who likes to eat treats and nap ',3,NULL,3),(40,'Rocky','dog','A strong boxer who likes to run and jump ',5,NULL,3),(41,'Chloe','cat','A elegant gray cat who likes to sit on the window sill and watch birds ',6,NULL,1),(42,'Bailey','dog','A adorable cocker spaniel who likes to wag his tail and lick people ',2,NULL,1),(43,'CoolDogGuy','Dog','this dog is super cool man, like super rad.',12,NULL,1),(44,'Cool dog','Cat','this is catdog',12,NULL,3),(45,'Cool cat','Cat','this is one cool cat',12,NULL,3),(46,'newpet','dog','isadog',1,NULL,1);


