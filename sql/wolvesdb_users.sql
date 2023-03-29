
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL,
  `hashedPw` varchar(50) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
)

INSERT INTO `users` VALUES (1,'coolguy','hasChillDay',0,'ayy@gmail.com'),(5,'CoolGuy','1509442',1,'coolguy3@gmail.com'),(6,'lameGuy','1509442',0,'lameguy@gmail.com');
