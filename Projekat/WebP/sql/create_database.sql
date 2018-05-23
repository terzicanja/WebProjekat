DROP SCHEMA IF EXISTS webprojekat;
CREATE SCHEMA webprojekat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE webprojekat;

CREATE TABLE users (
	id INT AUTO_INCREMENT,
	username VARCHAR(10) NOT NULL, 
	password VARCHAR(20) NOT NULL,
    name VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    registrationDate DATE NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    blocked BIT NOT NULL DEFAULT 0,
    deleted BIT NOT NULL DEFAULT 0,
    subsNumber INT DEFAULT '0',
    likeVideo INT DEFAULT '0',
    dislikeVideo INT DEFAULT '0',
    likeComment INT DEFAULT '0',
    dislikeComment INT DEFAULT '0',
	 
    PRIMARY KEY(id)
);


CREATE TABLE videos (
	id INT AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	videoURL VARCHAR(100) NOT NULL,
	videoImg VARCHAR(100) NOT NULL,
	description VARCHAR(1000),
	visibility ENUM('PUBLIC', 'UNLISTED', 'PRIVATE') NOT NULL DEFAULT 'PUBLIC',
	commentsAllowed BOOLEAN DEFAULT true,
	ratingAllowed BOOLEAN DEFAULT true,
	blocked BOOLEAN DEFAULT false,
	deleted BOOLEAN DEFAULT FALSE,
	views INT NOT NULL DEFAULT 0,
	likes INT NOT NULL DEFAULT 0,
	dislikes INT NOT NULL DEFAULT 0,
	dateCreated DATETIME DEFAULT CURRENT_TIMESTAMP,
--	user_id INT NOT NULL,
	PRIMARY KEY (id));
--	FOREIGN KEY (user_id) REFERENCES users(id)



INSERT INTO videos (name, videoURL, videoImg, description, visibility) VALUES 
	('Marshall Jefferson - Floating', 'https://www.youtube.com/embed/EGuiXTUQeH4', 'https://img.youtube.com/vi/EGuiXTUQeH4/0.jpg', '', 'PUBLIC'),
	('Dave Brubeck - Take Five', 'https://www.youtube.com/embed/vmDDOFXSgAs', 'https://img.youtube.com/vi/vmDDOFXSgAs/0.jpg', '', 'UNLISTED'),
	('Ryo Fukui - Scenery 1976 (FULL ALBUM)', 'https://www.youtube.com/embed/Hrr3dp7zRQY', 'https://img.youtube.com/vi/Hrr3dp7zRQY/0.jpg', '', 'PRIVATE'),
	('The Doors - L. A. Woman', 'https://www.youtube.com/embed/JskztPPSJwY', 'https://img.youtube.com/vi/JskztPPSJwY/0.jpg', '', 'PUBLIC'),
	('Wu-Tang Clan - Forever FULL ALBUM', 'https://www.youtube.com/embed/5CzsXvAZ6R4', 'https://img.youtube.com/vi/5CzsXvAZ6R4/0.jpg', '', 'PUBLIC'),
	('Bob Marley - I Shot The Sheriff', 'https://www.youtube.com/embed/2XiYUYcpsT4', 'https://img.youtube.com/vi/2XiYUYcpsT4/0.jpg', '', 'PUBLIC');



INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (1, 'a', 'a', 'aa', 'aa', 'a@mail', '1.1.2000', 'ADMIN', 22);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (2, 'b', 'b', 'bb', 'bb', 'a@mail', '1.1.2000', 'USER', 11);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (3, 'c', 'c', 'cc', 'cc', 'a@mail', '1.1.2000', 'USER', 33);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (4, 'd', 'd', 'dd', 'dd', 'a@mail', '1.1.2000', 'USER', 15);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (5, 'e', 'e', 'ee', 'ee', 'a@mail', '1.1.2000', 'USER', 43);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (6, 'f', 'f', 'ff', 'ff', 'a@mail', '1.1.2000', 'USER', 7);
INSERT INTO users (id, username, password, name, lastname, email, registrationDate, role, subsNumber) VALUES (7, 'g', 'g', 'gg', 'gg', 'a@mail', '1.1.2000', 'USER', 20);


CREATE TABLE subs(
	subscriber VARCHAR(10),
	subsribed_to VARCHAR(10),
	
	FOREIGN KEY (subscriber) REFERENCES users(id) ON DELETE RESTRICT,
	FOREIGN KEY (subsribed_to) REFERENCES users(id) ON DELETE RESTRICT
);


INSERT INTO subs(subscriber, subsribed_to)VALUES(1, 2);
INSERT INTO subs(subscriber, subsribed_to)VALUES(3, 2);
INSERT INTO subs(subscriber, subsribed_to)VALUES(7, 2);
INSERT INTO subs(subscriber, subsribed_to)VALUES(2, 1);
INSERT INTO subs(subscriber, subsribed_to)VALUES(6, 1);
INSERT INTO subs(subscriber, subsribed_to)VALUES(5, 6);
INSERT INTO subs(subscriber, subsribed_to)VALUES(1, 7);







