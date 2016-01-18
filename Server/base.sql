Create DataBase FoodEspol;
use FoodEspol;


CREATE TABLE Restaurante
(idRestaurante integer AUTO_INCREMENT,
nombre varchar (20),
administrador integer,
capacidad integer,
latitud FLOAT(9,6),
longitud FLOAT(9,6),
clienteAprox integer
logo MEDIUMBLOB,
PRIMARY KEY (idRestaurante)
);


CREATE TABLE Administrador
(cedula varchar(10),
nombre varchar(100),
clave varchar (20)
PRIMARY KEY (cedula)
);

CREATE TABLE Plato
(idPlato integer AUTO_INCREMENT,
nombre varchar(20),
precio double,
tipo varchar(10),
imagen MEDIUMBLOB,
idRestaurante integer,
PRIMARY KEY (idPlato)
FOREIGN KEY (idRestaurante) REFERENCES Restaurante(idRestaurante) ON UPDATE CASCADE ON DELETE CASCADE,
);

CREATE TABLE Menu
(idMenu integer AUTO_INCREMENT,
Fecha date,
idRestaurante integer,
PRIMARY KEY (idMenu),
FOREIGN KEY (idRestaurante) REFERENCES Restaurante(idRestaurante) ON UPDATE CASCADE ON DELETE CASCADE,
);


CREATE TABLE MenuPlato
(idMenuPlato integer AUTO_INCREMENT,
Fecha date,
idMenu integer,
idPlato integer,
PRIMARY KEY (idMenuPlato),
FOREIGN KEY (idMenu) REFERENCES Menu(idMenu) ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (idPlato) REFERENCES Plato(idPlato) ON UPDATE CASCADE ON DELETE CASCADE
);

