CREATE DATABASE arackiralama;
USE arackiralama;

CREATE TABLE  user(
   id int NOT NULL AUTO_INCREMENT,
   name  varchar(450) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
   password  varchar(450) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
   firstName  varchar(450) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
   lastName  varchar(450) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY ( id ),
  UNIQUE KEY  id_UNIQUE  ( id )
) ;

CREATE TABLE arac  (
   id  int NOT NULL AUTO_INCREMENT,
   plakaNo  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   model  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   marka  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   renk  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY ( id )
) ;
CREATE TABLE   musteri  (
   id  int NOT NULL AUTO_INCREMENT,
   ad  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   soyad  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   telefon  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
   adres  varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY ( id )
) ;

CREATE TABLE  kiralama  (
   id  int NOT NULL AUTO_INCREMENT,
   arac  int NOT NULL,
   musteri  int NOT NULL,
   baslangicTarihi  date NOT NULL,
   bitisTarihi  date NOT NULL,
  PRIMARY KEY ( id ),
  KEY  arac  ( arac ),
  KEY  musteri  ( musteri ),
  CONSTRAINT  kiralama_ibfk_1  FOREIGN KEY ( arac ) REFERENCES  arac  ( id ) ON UPDATE CASCADE,
  CONSTRAINT  kiralama_ibfk_2  FOREIGN KEY ( musteri ) REFERENCES  musteri  ( id ) ON UPDATE CASCADE
) ;

CREATE TABLE  aracTeslim  (
   id  int NOT NULL AUTO_INCREMENT,
   arac  int NOT NULL,
   user  int NOT NULL,
   teslimTarihi  date NOT NULL,
  PRIMARY KEY ( id ),
  KEY  arac  ( arac ),
  KEY  user  ( user ),
   FOREIGN KEY ( arac ) REFERENCES  arac  ( id ) ON UPDATE CASCADE,
   FOREIGN KEY ( user ) REFERENCES  user  ( id ) ON UPDATE CASCADE
) ;