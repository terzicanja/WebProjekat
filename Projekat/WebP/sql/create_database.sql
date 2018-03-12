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
INSERT INTO korisnici (username, password, ime, prezime, email, datumRegistracije, tip) VALUES ('a', 'a', 'aa', 'aa', 'a@mail', '1.1.2000', 'ADMIN');
INSERT INTO korisnici (username, password, ime, prezime, email, datumRegistracije, tip) VALUES ('b', 'b', 'bb', 'bb', 'a@mail', '1.1.2000', 'USER');