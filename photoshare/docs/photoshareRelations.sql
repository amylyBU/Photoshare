DROP TABLE Pictures CASCADE;
DROP TABLE Users CASCADE;
DROP TABLE Albums CASCADE;
DROP TABLE Friendship CASCADE;
DROP TABLE PictureComments CASCADE;
DROP TABLE PictureTags CASCADE;
DROP TABLE AlbumContains CASCADE;

DROP SEQUENCE Pictures_picture_id_seq;
DROP SEQUENCE Users_user_id_seq;
DROP SEQUENCE Picturetags_tagid_seq;
DROP SEQUENCE Picturecomments_commentid_seq;
DROP SEQUENCE Albums_albumid_seq;

CREATE SEQUENCE Users_user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Users
(
  user_id int4 NOT NULL DEFAULT nextval('Users_user_id_seq'),
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  role_name varchar(255) NOT NULL DEFAULT 'RegisteredUser',
  firstname varchar(50) NOT NULL,
  lastname varchar(50) NOT NULL,
  dob char(8) NOT NULL,
  gender char(1),
  clocation varchar(100),
  hometown varchar(100),
  education varchar(50),
  CONSTRAINT users_pk PRIMARY KEY (user_id)
);

INSERT INTO Users (email, password, firstname, lastname, dob) VALUES ('test@bu.edu', 'test', 'test', 'test', 'testtest');
INSERT INTO Users (user_id, email, password, firstname, lastname, dob) VALUES (2, 'anon@bu.edu', 'anon', 'anon', '', 'anonyDOB');
INSERT INTO Users (email, password, firstname, lastname, dob, gender, clocation, hometown, education) VALUES ('amyly@bu.edu', 'anything', 'amy', 'ly', '06031993', 'F', 'boston', 'quincy', 'bu');
INSERT INTO Users (email, password, firstname, lastname, dob, gender, clocation, hometown, education) VALUES ('cs460@bu.edu', 'anything', 'eric', 'park', '10203023', 'M', 'boston', 'boston', 'bu');

CREATE SEQUENCE Pictures_picture_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Pictures
(
  picture_id int4 NOT NULL DEFAULT nextval('Pictures_picture_id_seq'),
  caption varchar(255) NOT NULL,
  imgdata bytea NOT NULL,
  size int4 NOT NULL,
  content_type varchar(255) NOT NULL,
  thumbdata bytea NOT NULL,
  ownerid integer NOT NULL,
  CONSTRAINT pictures_pk PRIMARY KEY (picture_id),
  CONSTRAINT ownerid_fk FOREIGN KEY (ownerid) REFERENCES Users(user_id)
); 

CREATE SEQUENCE Albums_albumid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE Albums
(
	albumid integer NOT NULL DEFAULT nextval('Albums_albumid_seq'),
	ownerid integer NOT NULL,
	name varchar(45) NOT NULL,
	dateofcreation date NOT NULL,
	CONSTRAINT albumid_pk PRIMARY KEY (albumid),
	CONSTRAINT ownerid_fk2 FOREIGN KEY (ownerid) REFERENCES Users(user_id)
);

CREATE SEQUENCE Picturecomments_commentid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE PictureComments 
(
	commentid integer NOT NULL DEFAULT nextval('Picturecomments_commentid_seq'),
	ownerid integer NOT NULL,
	photoid integer,
	text varchar(255),
	dateofcomment date NOT NULL,
	CONSTRAINT commentid_pk PRIMARY KEY (commentid),
	CONSTRAINT ownerid_fk2 FOREIGN KEY (ownerid) REFERENCES Users(user_id),
	CONSTRAINT photoid FOREIGN KEY (photoid) REFERENCES Pictures(picture_id)
);

CREATE SEQUENCE Picturetags_tagid_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;

CREATE TABLE PictureTags
(
	pictureid integer NOT NULL,
	tagid integer NOT NULL DEFAULT nextval('Picturetags_tagid_seq'),
	tag varchar(100),
	CONSTRAINT tags_pk PRIMARY KEY (tagid),
  CONSTRAINT pictureid_fk2 FOREIGN KEY (pictureid) REFERENCES Pictures(picture_id)
);

CREATE TABLE AlbumContains
(
	albumid integer NOT NULL,
	pictureid integer NOT NULL,
	CONSTRAINT albumcontains_pk PRIMARY KEY (albumid, pictureid),
	CONSTRAINT albumid_fk FOREIGN KEY (albumid) REFERENCES Albums(albumid),
  CONSTRAINT pictureid_fk FOREIGN KEY (pictureid) REFERENCES Pictures(picture_id)
);

CREATE TABLE Friendship
(
	user1 integer NOT NULL,
	user2 integer NOT NULL,
	CONSTRAINT friendship_pk PRIMARY KEY (user1, user2),
	CONSTRAINT user1_fk FOREIGN KEY (user1) REFERENCES Users(user_id),
	CONSTRAINT user2_fk FOREIGN KEY (user2) REFERENCES Users(user_id)
);
