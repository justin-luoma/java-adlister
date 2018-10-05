-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;
USE adlister_db;
DROP TABLE `ads`;


DROP TABLE `users`;



-- ************************************** `users`

CREATE TABLE `users`
(
 `id`       INT unsigned NOT NULL AUTO_INCREMENT ,
 `username` VARCHAR(45) NOT NULL ,
 `email`    VARCHAR(45) NOT NULL ,
 `password` VARCHAR(45) NOT NULL ,

PRIMARY KEY (`id`)
);






-- ************************************** `ads`

CREATE TABLE `ads`
(
 `id`          INT unsigned NOT NULL AUTO_INCREMENT ,
 `user_id`     INT unsigned NOT NULL ,
 `title`       VARCHAR(45) NOT NULL ,
 `description` VARCHAR(5000) NOT NULL ,

PRIMARY KEY (`id`),
KEY `users_ads` (`user_id`),
CONSTRAINT `users_ads` FOREIGN KEY `users_ads` (`user_id`) REFERENCES `users` (`id`)
);

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE users;
TRUNCATE ads;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (username, email, password) VALUES
  ('user1', 'user1@example.com', 'user1pass'),
  ('user2', 'user2@example.com', 'user2pass'),
  ('user3', 'user3@example.com', 'user3pass');


INSERT INTO ads (user_id, title, description) VALUES
  (1, 'playstation for sale', 'This is a slightly used playstation'),
  (1, 'Super Nintendo', 'Get your game on with the old-school classic!'),
  (2, 'Junior Java Developer Position',
   'Minimum 7 years of experience required.
   You will be working in the scripting language for Java, JavaScript'),
  (2, 'JavaScript Developer needed', 'Must have strong Java skills');





