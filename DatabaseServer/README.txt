DatabaseServer
Proyecto de Sistemas Distribuidos
ITAM
Primavera 2014

Javier Sagastuy 		122627
Alejandro Escalante		125653

Para poder desplegar el proyecto lo unico que se necesita es que exista la base de datos que lleva el registro de los usuarios. 

Para ello, en la parte de servicios hay que crear una base de datos con las siguientes caracteristicas:

El nombre debe ser: UserDB

El usuario debe ser: useradmin

El password debe ser: AdminPW

Por ultimo, hay que crear una tabla en la que se insertaran los nombres de los usuarios, sus contraseñas y los nombres de sus bases de datos. 
Esto se puede hacer con el comando:

create table Users(
	userId int not null primary key,
	username varchar(30) not null unique,
	password varchar(30) not null,
	database varchar(30) not null unique
);

Para ejecutar este comando basta con hacer doble click sobre la conexion recien creada a la base de datos UserDB para conectarla. 
Posteriormente hay que hacer click derecho sobre la conexion y seleccionar la opcion ejecutar comando. 
Finalmente, una vez pegado el comando en el editor, hay que hacer click en el boton RunSQL, el cual tiene el icono de una base de datos amarilla y el tipico triangulo de color verde para correr codigo. 

Con ello, se puede proceder a desplegar y ejecutar el proyecto del mismo modo como se ha hecho anteriormente en clase. 
