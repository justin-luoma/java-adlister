USE adlister_db;

DROP TABLE IF EXISTS ad_category;


DROP TABLE IF EXISTS ads;


DROP TABLE IF EXISTS categories;


DROP TABLE IF EXISTS users;



-- ************************************** categories

CREATE TABLE categories
(
 id   INT UNSIGNED NOT NULL AUTO_INCREMENT ,
 name VARCHAR(15) NOT NULL ,

PRIMARY KEY (id)
);

-- ************************************** users

CREATE TABLE users
(
 id       INT UNSIGNED NOT NULL AUTO_INCREMENT ,
 username VARCHAR(50) NOT NULL ,
 email    VARCHAR(100) NOT NULL ,
 password CHAR(98) NOT NULL ,

PRIMARY KEY (id),
UNIQUE (username),
 UNIQUE (email)
);

-- ************************************** ads

CREATE TABLE ads
(
 id          INT UNSIGNED NOT NULL AUTO_INCREMENT ,
 created_by  INT UNSIGNED NOT NULL ,
 title       VARCHAR(100) NOT NULL ,
 description VARCHAR(5000) NOT NULL ,
 created     DATETIME NOT NULL,

PRIMARY KEY (id),
FOREIGN KEY (created_by) REFERENCES users(id)
    ON DELETE CASCADE,
FULLTEXT INDEX FT_TD (title, description),
FULLTEXT INDEX FT_Title (title),
FULLTEXT INDEX FT_Description (description)
);

-- ************************************** ad_category

CREATE TABLE ad_category
(
 category_id INT UNSIGNED NOT NULL ,
 ad_id       INT UNSIGNED NOT NULL ,
FOREIGN KEY (ad_id) REFERENCES ads(id)
    ON DELETE CASCADE,
FOREIGN KEY (category_id) REFERENCES categories(id)
    ON DELETE CASCADE
);

INSERT INTO categories (name) VALUES
    ('furniture'),
    ('video games'),
    ('appliances'),
    ('freelancer'),
    ('cell phones');

insert into users (username, email, password) values
    ('user1', 'user1@example.com', '$argon2id$v=19$m=65536,t=63,p=4$gUUXzHtpfCW6B1gHZu1CTg$gcM5/pBsW7oPvft8yZp8oA12RnPafNIRx+uPb0y7nbM'),
    ('user2', 'user2@example.com', '$argon2id$v=19$m=65536,t=63,p=4$tgzm6Xl/djbM4bGViCUq3A$6IcKJZtRyaLDairbJ2vjSLcqekj7+yyT867nvfYTMXs'),
    ('user3', 'user3@example.com', '$argon2id$v=19$m=65536,t=63,p=4$zAUkGE91eMJToXrYCdOWkA$zvV5eg28uT6XaUcRNmykWvutYrxc4+DNufp6WfV+Ovw');

insert into ads (created_by, title, description, created) values
    (1, 'Playstation for sale', 'This is a slightly used playstation', (select now())),
    (1, 'Super Nintendo', 'Get your game on with the old-school classic!', '2018-10-01 12:11:01'),
    (2, 'Junior Java Developer Position',
     'Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript', '2018-09-10 09:01:00'),
    (3, 'JavaScript Developer needed', 'Must have strong Java skills', '2018-01-01 21:00:59');
