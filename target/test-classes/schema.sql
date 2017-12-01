drop table usuarios if exists;
CREATE TABLE usuarios
(
	id identity,
	nombre VARCHAR(45),
	correo VARCHAR(45),
	password VARCHAR(45)
);