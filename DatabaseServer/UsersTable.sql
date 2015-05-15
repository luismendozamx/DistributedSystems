create DATABASE UserDB;

create table Users(
	userId int not null primary key,
	username varchar(30) not null unique,
	password varchar(30) not null,
	database varchar(30) not null unique
);

insert into Users values (1,'us1','contá','DataBase');
insert into Users values (2,'us2','vm as +as ','DataBase2');
insert into Users values (3,'us3','aécm A12','DataBase3');
insert into Users values (4,'prueba1','r a s,smc ','miDataBase');
insert into Users values (5,'prueba2','r a s,smc ','miDataBase2');

create table Users(
	userId int not null primary key,
	username varchar(30) not null unique,
	balance double not null,
	database varchar(30) not null unique
);

insert into Users values (1,'us1',4.5,'DataBase');
insert into Users values (2,'us2',54.1,'DataBase2');
insert into Users values (3,'us3',12.8,'DataBase3');
insert into Users values (4,'prueba1',100.1,'miDataBase');
insert into Users values (5,'prueba2',-20.4,'miDataBase2');