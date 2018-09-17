CREATE TABLE roles (
    id			INT				IDENTITY(1,1) NOT NULL,
    nombre		VARCHAR (50)	NOT NULL
	
	PRIMARY KEY (id)
  );

 CREATE TABLE usuarios (
   dni			INT				NOT NULL,
   nombre		VARCHAR (40)	NOT NULL,
   rol_id 		INT				NOT NULL,
   password		VARCHAR (128)	NOT NULL,
   activo		BIT				NOT NULL DEFAULT 1
   
   PRIMARY KEY (dni),
   FOREIGN KEY (rol_id) REFERENCES roles(id)
  );

  CREATE TABLE productos (
    codigo					INT				IDENTITY(1,1) NOT NULL,
    nombre 					VARCHAR (20)	NOT NULL,
	precio 					INT				NOT NULL,
    descripcion 			VARCHAR (256)	NOT NULL,
    unidades_disponibles 	INT				NOT NULL,
    marca 					VARCHAR (8) 	NOT NULL,
    modelo 					VARCHAR (8) 	NOT NULL
	
	PRIMARY KEY (codigo)
    );

  CREATE TABLE clientes (
    dni_cuit 				INT				NOT NULL,
    nombre 					varchar(20)  	NOT NULL,
    apellido 				varchar (20) 	NOT NULL,
    direccion 				varchar (64)	NOT NULL,
    email 					varchar (64)	NOT NULL,
    telefono 				varchar (64)	NOT NULL,
    particular 				BIT				NOT NULL DEFAULT 0
	
	PRIMARY KEY (dni_cuit)
  );
  
  CREATE TABLE envios(
    id_envio				INT				IDENTITY(1,1) NOT NULL,
	estado 					varchar(20)  	NOT NULL
	
	PRIMARY KEY(id_envio)
  );
  
  CREATE TABLE reclamos(
    id_reclamo				INT				IDENTITY(1,1) NOT NULL,
    descripcion 			varchar (64),
    estado 					varchar (64)
	
	PRIMARY KEY (id_reclamo)
  );

  CREATE TABLE ventas (
   id_venta					INT				IDENTITY(1,1) NOT NULL,
   fecha 					DATETIME 		NOT NULL,
   total 					FLOAT 			NOT NULL DEFAULT 0,
   cliente_dni_cuit 		INT 			NOT NULL,
   usuario_dni 				INT 			NOT NULL,
   descuento 				FLOAT 			NOT NULL,
   envio_id 				INT 			NULL,
   reclamo_id 				INT 			NULL
   
   PRIMARY KEY (id_venta)
   FOREIGN KEY (cliente_dni_cuit) REFERENCES clientes (dni_cuit),
   FOREIGN KEY (usuario_dni) REFERENCES usuarios (dni),
   FOREIGN KEY (envio_id) REFERENCES envios (id_envio),
   FOREIGN KEY (reclamo_id) REFERENCES reclamos (id_reclamo)
  );

  CREATE TABLE item_venta(
    venta_id 				INT 			NOT NULL,
    producto_codigo 		INT 			NOT NULL,
    producto_cantidad 		INT 			NOT NULL,
    precio_unidad 			INT 			NOT NULL,
	
	PRIMARY KEY (venta_id, producto_codigo),
    FOREIGN KEY (venta_id) REFERENCES ventas (id_venta),
    FOREIGN KEY (producto_codigo) REFERENCES productos (codigo)
  );
  