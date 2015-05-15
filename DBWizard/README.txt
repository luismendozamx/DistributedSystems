DB Wizard

Sistemas Distribuidos, ITAM, 2015

Luis Enrique Mendoza 124847
Gian Paolo Alessi 125480

Es necesario crear una base de datos con las siguientes características:

El nombre debe ser: UserDBW
El usuario debe ser: root
El password debe ser: root

Por ultimo, hay que crear una tabla en la que se insertaran los nombres de los usuarios, sus contraseñas y los nombres de sus bases de datos. 
Esto se puede hacer con el comando execute statement:

create table Users(
	userId int not null primary key,
	username varchar(30) not null unique,
	password varchar(30) not null,
	database varchar(30) not null unique
); 
