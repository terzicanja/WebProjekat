DROP SCHEMA IF EXISTS webprojekat;
CREATE SCHEMA webprojekat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE webprojekat;

CREATE TABLE korisnici (
	username VARCHAR(10) NOT NULL, 
	password VARCHAR(20) NOT NULL,
    ime VARCHAR(20),
    prezime VARCHAR(20),
    email VARCHAR(30) NOT NULL,
    opisKanala VARCHAR(100),
    datumRegistracije DATE NOT NULL,
    tip ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    blockedUser BIT NOT NULL DEFAULT 0,
    pratioci INT DEFAULT '0',
    likeVideo INT DEFAULT '0',
    dislikeVideo INT DEFAULT '0',
    likeComment INT DEFAULT '0',
    dislikeComment INT DEFAULT '0',
	 
    PRIMARY KEY(username)
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
	('Bob Marley - I Shot The Sheriff', 'https://www.youtube.com/embed/2XiYUYcpsT4', 'https://img.youtube.com/vi/2XiYUYcpsT4/0.jpg', '', 'PUBLIC'),
	('Motorhead - Ace Of Spades', 'https://www.youtube.com/embed/vcf7DnHi54g', 'https://img.youtube.com/vi/vcf7DnHi54g/0.jpg', '*NOTE* its for entertainment purposes only', 'UNLISTED'),
	('Marko Nastic - Smekerica Kulijana', 'https://www.youtube.com/embed/gM-QEcuDY8I', 'https://img.youtube.com/vi/gM-QEcuDY8I/0.jpg', '', 'PUBLIC'),
	('Carl Cox - Fantasee', 'https://www.youtube.com/embed/-Ndw3lp0D7E', 'https://img.youtube.com/vi/-Ndw3lp0D7E/0.jpg', 'Carl Cox - Fantasee 
Written and produced by Carl Cox, Davide Carbone and Josh Abrahams ', 'PUBLIC');



INSERT INTO korisnici (username, password, ime, prezime, email, datumRegistracije, tip) VALUES ('a', 'a', 'aa', 'aa', 'a@mail', '1.1.2000', 'ADMIN');
INSERT INTO korisnici (username, password, ime, prezime, email, datumRegistracije, tip) VALUES ('b', 'b', 'bb', 'bb', 'a@mail', '1.1.2000', 'USER');
