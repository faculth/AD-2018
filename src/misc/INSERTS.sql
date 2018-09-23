--ROLES
INSERT INTO [dbo].[roles] ([nombre]) VALUES ('Administrador')
INSERT INTO [dbo].[roles] ([nombre]) VALUES ('Vendedor')
GO

--CLIENTES
INSERT INTO [dbo].[clientes]([dni_cuit], [nombre], [apellido], [direccion], [email], [telefono], [particular])
  VALUES(30155849, 'UADE', 'asd', 'Lima 1154', 'uade@uade.edu.ar', '111111111', 0)
GO
INSERT INTO [dbo].[clientes]([dni_cuit], [nombre], [apellido], [direccion], [email], [telefono], [particular])
  VALUES(39155848, 'Matias', 'Boccardo', 'Av Belgrano', 'a@a.a', '426375', 1)
GO
INSERT INTO [dbo].[clientes]([dni_cuit], [nombre], [apellido], [direccion], [email], [telefono], [particular])
  VALUES(94907406, 'Daniel', 'Naranjo', 'Cochabamba 830', 'daniel.naranjo@gmail.com', '1155019211', 1)
GO
--USUARIOS
INSERT INTO [dbo].[usuarios]([dni], [nombre], [rol_id], [password], [activo])
  VALUES(39155848, 'Matias Boccardo', 1, '12345', 1)
GO
INSERT INTO [dbo].[usuarios]([dni], [nombre], [rol_id], [password], [activo])
  VALUES(39155849, 'Facundo Cortabarria', 2, '12345', 1)
GO
INSERT INTO [dbo].[usuarios]([dni], [nombre], [rol_id], [password], [activo])
  VALUES(39155850, 'Daniel Naranjo', 1, '1234', 0)
GO
INSERT INTO [dbo].[usuarios]([dni], [nombre], [rol_id], [password], [activo])
  VALUES(39155851, 'Lautaro Olivares', 2, '12345', 0)
GO
--PRODUCTOS
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi sport 2015', 4, 'Audi', '2015')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero BMW I3 sportline', 1, 'BMW', '2011')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW I3 baseline', 2, 'BWM', '2012')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat Palio pack2', 5, 'Fiat', '2017')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2015')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2011')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2012')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2017')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2010')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2010')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2010')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2010')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2011')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2011')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2011')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2011')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2012')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2012')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2012')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2012')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2013')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2013')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2013')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2013')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2015')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2015')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2015')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2015')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2016')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2016')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2016')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2016')
GO

INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Luneta', 5750, 'Luneta audi', 4, 'Audi', '2009')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro Trasero', 3000, 'Faro trasero', 1, 'BMW', '2009')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Faro delantero', 4000, 'Acrilico delantero BMW', 2, 'BWM', '2009')
GO
INSERT INTO [dbo].[productos]([nombre], [precio], [descripcion], [unidades_disponibles], [marca], [modelo])
  VALUES('Espejo retrovisor', 3000, 'Esperjo retrovisor Fiat', 5, 'Fiat', '2009')
GO