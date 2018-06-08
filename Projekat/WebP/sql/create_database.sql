DROP SCHEMA IF EXISTS webprojekat;
CREATE SCHEMA webprojekat DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE webprojekat;

CREATE TABLE users (
	#id INT AUTO_INCREMENT,
	username VARCHAR(10) NOT NULL, 
	password VARCHAR(20) NOT NULL,
    name VARCHAR(20),
    lastname VARCHAR(20),
    email VARCHAR(30) NOT NULL,
    description VARCHAR(100),
    registrationDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    blocked BIT NOT NULL DEFAULT 0,
    deleted BIT NOT NULL DEFAULT 0,
    subsNumber INT DEFAULT '0',
    likeVideo INT DEFAULT '0',
    dislikeVideo INT DEFAULT '0',
    likeComment INT DEFAULT '0',
    dislikeComment INT DEFAULT '0',
	 
    PRIMARY KEY(username)
);


CREATE TABLE videos (
	id INT AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	videoURL VARCHAR(100) NOT NULL,
	videoImg VARCHAR(300) DEFAULT 'https://i.ytimg.com/vi/6ItdYJaQOjQ/maxresdefault.jpg',
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
	user_id VARCHAR(10) NOT NULL,
    
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users(username));
    
    
CREATE TABLE comments (
	id INT AUTO_INCREMENT,
    content VARCHAR(100) NOT NULL,
    comment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    author VARCHAR(10) NOT NULL,
    videoId INT NOT NULL,
    likesNumber INT NOT NULL DEFAULT 0,
    dislikesNumber INT NOT NULL DEFAULT 0,
    deleted BOOLEAN DEFAULT false,
    
    PRIMARY KEY (id),
    FOREIGN KEY (author) REFERENCES users(username),
    FOREIGN KEY (videoId) REFERENCES videos(id)
);


CREATE TABLE videoRatings (
	id INT AUTO_INCREMENT,
    liked BOOLEAN DEFAULT true,
    rated_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    who_rated VARCHAR(10) NOT NULL,
    rated_video INT NOT NULL,

	PRIMARY KEY (id),
    FOREIGN KEY (who_rated) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (rated_video) REFERENCES videos(id) ON DELETE CASCADE
);


CREATE TABLE commentRatings (
	id INT AUTO_INCREMENT,
    liked BOOLEAN DEFAULT true,
    rated_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    who_rated VARCHAR(10) NOT NULL,
    rated_comment INT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (who_rated) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (rated_comment) REFERENCES comments(id) ON DELETE CASCADE
);


INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('admin1', 'a', 'aa', 'aa', 'a@mail', '1.1.2000', 'ADMIN');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('admin2', 'a', 'bb', 'bb', 'a@mail', '1.1.2000', 'ADMIN');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('c', 'c', 'cc', 'cc', 'a@mail', '1.1.2000', 'USER');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('d', 'd', 'dd', 'dd', 'a@mail', '1.1.2000', 'USER');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('e', 'e', 'ee', 'ee', 'a@mail', '1.1.2000', 'USER');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('f', 'f', 'ff', 'ff', 'a@mail', '1.1.2000', 'USER');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role) VALUES ('g', 'g', 'gg', 'gg', 'a@mail', '1.1.2000', 'USER');
INSERT INTO users (username, password, name, lastname, email, registrationDate, role, blocked) VALUES ('blok', 'b', 'blokiran', 'bbb', 'a@mail', '1.1.2000', 'USER', 1);
INSERT INTO users (username, password, name, lastname, email, registrationDate, role, deleted) VALUES ('del', 'd', 'obrisan', 'ddd', 'a@mail', '1.1.2000', 'USER', 1);


INSERT INTO videos (name, videoURL, videoImg, description, visibility, user_id, deleted) VALUES 
	('public video', 'https://www.youtube.com/embed/krtnt191Drg', 'https://i.ytimg.com/vi/bDY5SK4SE-g/maxresdefault.jpg', 'A scene from the movie Inglorious Basterds when 3 Americans come to German movie premiere and try to speak Italian.', 'PUBLIC', 'c', 0),
	('unlisted video', 'https://www.youtube.com/embed/R59TevgzN3k', 'https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/yRF5c-O/traditional-and-classic-sunburst-or-starburst-background-green-4k-and-full-hd_njhnts86__F0000.png', '', 'UNLISTED', 'c', 0),
	('private video', 'https://www.youtube.com/embed/7Vj5M0qKh8g', 'http://backgroundcheckall.com/wp-content/uploads/2017/12/mavi-background-tumblr-4.jpg', 'Unboxing the 50 mil sub reward ruby playbutton.', 'PRIVATE', 'c', 0),
	('public', 'https://www.youtube.com/embed/OMJCsytmfnw', 'https://i.ytimg.com/vi/0lqN_v5ljlA/maxresdefault.jpg', '', 'PUBLIC', 'c', 0),
	('public', 'https://www.youtube.com/embed/A7eb1DHZ9GQ', 'https://i.ytimg.com/vi/R3unPcJDbCc/mqdefault.jpg', '', 'PUBLIC', 'e', 0),
    ('video blokiranog korisnika', 'https://www.youtube.com/embed/xTlNMmZKwpA', 'hhttps://i.ytimg.com/vi/54Scxs8u1gk/maxresdefault.jpg', '', 'PUBLIC', 'blok', 0),
    ('video obrisanog korisnika', 'https://www.youtube.com/embed/_BcYBFC6zfY', 'https://i.ytimg.com/vi/0lqN_v5ljlA/maxresdefault.jpg', '', 'PUBLIC', 'del', 0),
	('deleted', 'https://www.youtube.com/embed/2XiYUYcpsT4', 'http://backgroundcheckall.com/wp-content/uploads/2017/12/triangles-background-2.jpg', '', 'PUBLIC', 'g', 1);


INSERT INTO comments (content, author, videoId) VALUES ('komentar 1', 'f', 2);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 2', 'c', 1);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 3', 'f', 2);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 4', 'c', 1);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 5', 'c', 2);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 6', 'd', 1);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 7', 'c', 2);
INSERT INTO comments (content, author, videoId) VALUES ('komentar 8', 'f', 2);


INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (true, '2013-12-12', 'f', 1);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (true, '2013-12-12', 'c', 2);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (true, '2013-12-12', 'c', 1);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (true, '2013-12-12', 'f', 3);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (true, '2013-12-12', 'f', 1);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (false, '2013-12-12', 'f', 1);
INSERT INTO videoRatings (liked, rated_time, who_rated, rated_video) VALUES (false, '2013-12-12', 'f', 1);


INSERT INTO commentRatings (liked, rated_time, who_rated, rated_comment) VALUES (true, '2013-12-12', 'f', 1);
INSERT INTO commentRatings (liked, rated_time, who_rated, rated_comment) VALUES (true, '2013-12-12', 'c', 2);
INSERT INTO commentRatings (liked, rated_time, who_rated, rated_comment) VALUES (true, '2013-12-12', 'c', 1);
INSERT INTO commentRatings (liked, rated_time, who_rated, rated_comment) VALUES (false, '2013-12-12', 'f', 3);
INSERT INTO commentRatings (liked, rated_time, who_rated, rated_comment) VALUES (true, '2013-12-12', 'f', 4);



CREATE TABLE subs(
	subscriber VARCHAR(10),
	subsribed_to VARCHAR(10),
	
	FOREIGN KEY (subscriber) REFERENCES users(username) ON DELETE RESTRICT,
	FOREIGN KEY (subsribed_to) REFERENCES users(username) ON DELETE RESTRICT
);


INSERT INTO subs(subscriber, subsribed_to)VALUES('admin1', 'c');
INSERT INTO subs(subscriber, subsribed_to)VALUES('admin2', 'c');
INSERT INTO subs(subscriber, subsribed_to)VALUES('f', 'c');
INSERT INTO subs(subscriber, subsribed_to)VALUES('c', 'admin1');
INSERT INTO subs(subscriber, subsribed_to)VALUES('f', 'admin1');
INSERT INTO subs(subscriber, subsribed_to)VALUES('c', 'f');
INSERT INTO subs(subscriber, subsribed_to)VALUES('admin1', 'f');







