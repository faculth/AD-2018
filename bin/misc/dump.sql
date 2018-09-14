
CREATE DATABASE IF NOT EXISTS demo;

USE demo;

DROP TABLE IF EXISTS user;


 CREATE TABLE IF NOT EXISTS roles (
    id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(20)  NOT NULL
  );

 CREATE TABLE IF NOT EXISTS usuarios (
   dni INT(8) NOT NULL PRIMARY KEY,
   nombre VARCHAR(40) NOT NULL,
   rol_id INT (8) NOT NULL,
   password varchar (128) NOT NULL,
   activo TINYINT (3) DEFAULT 1,
   FOREIGN KEY (rol_id) REFERENCES roles(id)
  );

  CREATE TABLE IF NOT EXISTS productos (
    codigo int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(20)  NOT NULL,
	  precio int(64) NOT NULL,
    descripcion varchar (256) NOT NULL,
    unidades_disponibles int (8) DEFAULT 0,
    marca varchar (8) NOT NULL,
    modelo varchar (8) NOT NULL
    );

  CREATE TABLE IF NOT EXISTS clientes (
    dni_cuit int(10) NOT NULL PRIMARY KEY,
    nombre varchar(20)  NOT NULL,
    apellido varchar (20) NOT NULL,
    direccion varchar (64) NOT NULL,
    email varchar (64),
    telefono varchar (64),
    particular TINYINT (3)
  );
  
  CREATE TABLE IF NOT EXISTS envios(
    id int(64) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	estado varchar(20)  NOT NULL
  );
  
  CREATE TABLE IF NOT EXISTS reclamos(
    id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion varchar (64),
    estado varchar (64)
  );

  CREATE TABLE IF NOT EXISTS ventas (
   id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY,
   fecha DATETIME NOT NULL,
   total FLOAT NOT NULL DEFAULT 0,
   cliente_dni_cuit INT (10) NOT NULL,
   usuario_dni INT (8) NOT NULL,
   descuento float(2) NOT NULL,
   envio_id int(64),
   reclamo_id int(64),
   FOREIGN KEY (cliente_dni_cuit) REFERENCES clientes (dni_cuit),
   FOREIGN KEY (usuario_dni) REFERENCES usuarios (dni),
   FOREIGN KEY (envio_id) REFERENCES envios (id),
   FOREIGN KEY (reclamo_id) REFERENCES reclamos (id)
  );

  CREATE TABLE IF NOT EXISTS item_venta(
    venta_id int not null,
    producto_codigo int not null,
    producto_cantidad int not null,
    precio_unidad int not null,
    FOREIGN KEY (venta_id) REFERENCES ventas (id),
    FOREIGN KEY (producto_codigo) REFERENCES productos (codigo),
    PRIMARY KEY (venta_id, producto_codigo)
  );
  
    