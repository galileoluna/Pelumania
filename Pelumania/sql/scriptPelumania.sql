
CREATE DATABASE `Pelumania`;
USE Pelumania;


CREATE TABLE IF NOT EXISTS `Sucursal`(

  `IdSucursal` INT(11) NOT NULL AUTO_INCREMENT,
  `NombreSucursal` VARCHAR(55) NOT NULL,
  `Direccion` VARCHAR(55) NOT NULL,
  `Numero` INT(11) NOT NULL,
  `EstadoSucursal` VARCHAR(55) NOT NULL,
   
  PRIMARY KEY(`IdSucursal`)
);
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(-1, "[Sucursal Generica]", "Calle", 123, "Activa");

CREATE TABLE IF NOT EXISTS `Servicio`(

  `IdServicio` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(55) NOT NULL,
  `PrecioLocal` decimal(13,2) NOT NULL,
  `PrecioDolar` decimal(13,2) NOT NULL,
  `Duracion` time NOT NULL,
  `Puntos` INT (4) NOT NULL,
  `Estado` VARCHAR(10) NOT NULL,

  PRIMARY KEY(`IdServicio`)

);

  CREATE TABLE  IF NOT EXISTS `Cliente`(
    `idCliente` int(11) NOT NULL AUTO_INCREMENT,
    `Nombre` varchar(45) NOT NULL,
    `Apellido` varchar(45) NOT NULL,
    `Telefono` varchar(20)  NULL,
    `Mail` varchar(45) NOT NULL,
    `Puntos` int(11) NULL ,
    `EstadoCliente` varchar(20)  NULL,
    `Deuda` decimal(13,2)  NULL,

    PRIMARY KEY (`idCliente`)
  );
  
  INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
	VALUES(-1, "Cliente", "Generico", 11111111, "default@default.com", 0, "Activo", 00.00);

INSERT INTO Cliente ()
CREATE TABLE IF NOT EXISTS `Rol`(

  `idRol` INT(11) NOT NULL AUTO_INCREMENT,
  `Cargo` VARCHAR(55) NOT NULL,
   
  PRIMARY KEY(`idRol`)

);

CREATE TABLE IF NOT EXISTS `Usuario`(
  `idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(55) NOT NULL,
  `Apellido` VARCHAR(55) NOT NULL,
  `nombreUsuario` VARCHAR(55) NOT NULL,
  `Contrasenia` VARCHAR(55) NOT NULL,
  `Mail` VARCHAR(55) NOT NULL,
  `EstadoUsuario` VARCHAR(55) NOT NULL,
  `idRol` int NOT NULL,
  `idSucursal` int NOT NULL,

  PRIMARY KEY(`idUsuario`),
  FOREIGN KEY (`idRol`) REFERENCES `Rol`(`idRol`),
  FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal`(`idSucursal`)

);

CREATE TABLE IF NOT EXISTS `Profesional`(
  `IdProfesional` INT(11) NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(55) NOT NULL,
  `Apellido` VARCHAR(55) NOT NULL,
  `idSucursalOrigen` int NOT NULL,
  `idSucursalTransferencia` int NULL,
  `estado` VARCHAR(55) NOT NULL,

  PRIMARY KEY(`IdProfesional`),
  FOREIGN KEY (`idSucursalOrigen`) REFERENCES `Sucursal`(`idSucursal`)
);

INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(-1,"[Sin", "Profesional]", -1, "Activo");

CREATE TABLE IF NOT EXISTS `DiasLaborales`(

  `IdDiasLaborales` INT(11) NOT NULL AUTO_INCREMENT,
  `Dia` VARCHAR(55) NOT NULL,
  `HoraEntrada` TIME NOT NULL,
  `HoraSalida` TIME NOT NULL,
  `IdProfesional` INT(11) NOT NULL,
   
  PRIMARY KEY(`idDiasLaborales`),
  FOREIGN KEY (`IdProfesional`) REFERENCES `Profesional`(`IdProfesional`)
);

CREATE TABLE IF NOT EXISTS `ServicioProfesional`(

  `IdProfesional` INT(11) NOT NULL,
  `IdServicio` INT(11) NOT NULL,

  FOREIGN KEY (`IdProfesional`) REFERENCES `Profesional`(`IdProfesional`),
  FOREIGN KEY (`IdServicio`) REFERENCES `Servicio`(`idServicio`)
);

CREATE TABLE IF NOT EXISTS `Promocion`(

  `idPromocion` INT(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(200) NOT NULL,  
  `FechaInicio` DATE NOT NULL,
  `FechaFin` DATE NOT NULL,
  `Descuento` DOUBLE NULL,
  `Puntos` INT(11)  NULL,
  `Estado` VARCHAR(55) NOT NULL,	
 
 PRIMARY KEY(`idPromocion`)
);

CREATE TABLE IF NOT EXISTS `ServicioPromocion`(

  `idPromocion` INT(11) NOT NULL,
  `IdServicio` INT(11) NOT NULL,

  FOREIGN KEY (`idPromocion`) REFERENCES `Promocion`(`idPromocion`),
  FOREIGN KEY (`IdServicio`) REFERENCES `Servicio`(`idServicio`)
);

CREATE TABLE IF NOT EXISTS `Cita`(

  `idCita` INT(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` INT(11) NOT NULL,
  `IdCliente` INT(11),
  `NombreCliente` VARCHAR(55) NOT NULL,
  `ApellidoCliente` VARCHAR(55) NOT NULL,
  `EstadoTurno` VARCHAR(55) NOT NULL,
  `PrecioLocal` DECIMAL (13,2) NOT NULL,
  `PrecioDolar` DECIMAL (13,2) NOT NULL,
  `HoraInicio` TIME NOT NULL,
  `HoraFin` TIME NOT NULL,
  `Dia` DATE NOT NULL,
  `IdSucursal` INT (11) NOT NULL,

    PRIMARY KEY(`idCita`),
    -- falta la foreign key a idUsuario
    FOREIGN KEY (`IdCliente`) REFERENCES `Cliente`(`idCliente`),
    FOREIGN KEY (`IdSucursal`) REFERENCES `Sucursal`(`idSucursal`)
);

CREATE TABLE IF NOT EXISTS `CategoriaCaja`(
	`idCategoriaCaja` INT(11) NOT NULL AUTO_INCREMENT,
	`Nombre` VARCHAR(55) NOT NULL,
    `Estado` VARCHAR(55) NOT NULL,
    `TipoMovimiento` VARCHAR(55) NOT NULL
    -- Esta linea que sigue es borrable, es solo para que los insert funcionen
    -- ________________________________________________________________________
    DEFAULT("ValorPorDefecto"),
    -- ________________________________________________________________________
    PRIMARY KEY(`idCategoriaCaja`)
);

CREATE TABLE IF NOT EXISTS `Caja`(

  `IdCaja` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` INT(11) NULL,
  `idCategoriaCaja` INT(11) NOT NULL,
  `Fecha`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `Descripcion` VARCHAR(80) NULL,  
  `TipoDeCambio` VARCHAR(55) NOT NULL,
  `idPromocion` INT(11)  NULL,
  `PrecioLocal` DECIMAL (13,2) NOT NULL,
  `PrecioDolar` DECIMAL (13,2) NOT NULL,
  `idCita` INT(11)  NULL,
  `idCliente` INT(11)  NULL,
  `IdProfesional` INT(11)  NULL,
  `idServicio` INT(11) NULL,
 
 PRIMARY KEY(`IdCaja`),
 FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal`(`idSucursal`),
 FOREIGN KEY (`idPromocion`) REFERENCES `Promocion`(`idPromocion`),
 FOREIGN KEY (`idCliente`) REFERENCES `Cliente`(`idCliente`),
 FOREIGN KEY (`idProfesional`) REFERENCES `Profesional`(`IdProfesional`),
 FOREIGN KEY (`idCita`) REFERENCES `Cita`(`idCita`),
 FOREIGN KEY (`idCategoriaCaja`) REFERENCES `CategoriaCaja`(`idCategoriaCaja`),
 FOREIGN KEY (`idServicio`) REFERENCES `Servicio`(`idServicio`)

);

CREATE TABLE IF NOT EXISTS `ServicioTurno`(

  `idCita` INT(11) NOT NULL,
  `idServicio` INT(11) NOT NULL,
  `idProfesional` INT(11),
  `horaInicio` TIME NOT NULL,
  `horaFin` time NOT NULL,
  
  FOREIGN KEY (`idServicio`) REFERENCES `Servicio`(`idServicio`),
  FOREIGN KEY (`idCita`) REFERENCES `Cita`(`idCita`),
  FOREIGN KEY (`idProfesional`) REFERENCES `Profesional`(`idProfesional`) 
);

CREATE TABLE IF NOT EXISTS `Puntos`(

  `IdPuntos` INT(11) NOT NULL,
  `idServicio` INT(11) NOT NULL,
  `Puntos` INT(11) NOT NULL,

    
   PRIMARY KEY(`IdPuntos`),
   FOREIGN KEY (`idServicio`) REFERENCES `Servicio`(`IdServicio`)
);

CREATE TABLE IF NOT EXISTS `ServicioProfesional`(

  `IdProfesional` INT(11) NOT NULL,
  `IdServicio` INT(11) NOT NULL,

  FOREIGN KEY (`IdProfesional`) REFERENCES `Profesional`(`IdProfesional`),
  FOREIGN KEY (`IdServicio`) REFERENCES `Servicio`(`idServicio`)
);

CREATE TABLE IF NOT EXISTS `TipoMail`(

  `IdTipoMail` INT(11) NOT NULL AUTO_INCREMENT,
  `CuerpoMail` VARCHAR(55) NOT NULL,

  PRIMARY KEY(`IdTipoMail`)

);

CREATE TABLE IF NOT EXISTS `EnviarMail`(

  `idEnviarMail` INT(11) NOT NULL AUTO_INCREMENT,
  `IdCita` INT(11) NOT NULL,
  `IdTipoMail` INT(11) NOT NULL,  
  `Enviado` BOOLEAN  NULL DEFAULT FALSE,
  `FechaEnviado` TIMESTAMP  NULL,

  PRIMARY KEY(`idEnviarMail`),
  FOREIGN KEY (`IdCita`) REFERENCES `Cita`(`IdCita`),
  FOREIGN KEY (`IdTipoMail`) REFERENCES `TipoMail`(`IdTipoMail`)

);

CREATE TABLE IF NOT EXISTS `LogTransferencia`(

  `idLogTransferencia` INT(11) NOT NULL AUTO_INCREMENT,
  `idProfesional` INT(11) NOT NULL,
  `idSucursal` INT(11) NOT NULL,
  `FechaTransferencia` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY(`idLogTransferencia`),
  FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal`(`idSucursal`),
  FOREIGN KEY (`idProfesional`) REFERENCES `Profesional`(`idProfesional`)
);

CREATE TABLE IF NOT EXISTS `permisosUsuario`(

  `idRol` INT(11) NOT NULL ,
  `idModulo` INT(11) NOT NULL,
  
  FOREIGN KEY (`idRol`) REFERENCES `Rol`(`idRol`)
  

);

SET time_zone = "-3:00";
USE Pelumania;

-- Insert Rol
INSERT INTO `rol` (`idRol`, `Cargo`) VALUES (1, 'Dueño');
INSERT INTO `rol` (`idRol`, `Cargo`) VALUES (2, 'Contador');
INSERT INTO `rol` (`idRol`, `Cargo`) VALUES (3, 'Recepcionista');
INSERT INTO `rol` (`idRol`, `Cargo`) VALUES (4, 'Supervisor');
INSERT INTO `rol` (`idRol`, `Cargo`) VALUES (5, 'Administrador');


-- Inserts para las sucursales
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(1, "San Miguel", "Peron", 1100, "Activa");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(2, "Bella Vista", "Ricchieri", 450, "Activa");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(3, "Jose Cuchillo Paz", "Rivadavia", 1000, "Activa");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(4, "Muñiz", "Conesa", 500, "Activa");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(5, "Los Polvorines", "9 de Julio", 3500, "Activa");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(6, "Torcuato", "Av. Balvastro", 7200, "Inactiva");
INSERT INTO SUCURSAL (IdSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) 
VALUES(7, "Hurlingham", "Calle falsa", 123, "Activa");

-- Insert usuario
INSERT INTO `usuario` (`idUsuario`, `Nombre`, `Apellido`, `nombreUsuario`, `Contrasenia`, `Mail`, `EstadoUsuario`, `idRol`, `idSucursal`) 
VALUES (-1, 'Default', 'user', 'a', 'a', 'a@a.com', 'Activo', 1, 1);

INSERT INTO `usuario` (`idUsuario`, `Nombre`, `Apellido`, `nombreUsuario`, `Contrasenia`, `Mail`, `EstadoUsuario`, `idRol`, `idSucursal`) 
VALUES (1, 'Nicolas', 'Cirillo', 'ncirillo', 'ncirillo', 'nico@gmail.com', 'Activo', 1, 1);

INSERT INTO `usuario` (`idUsuario`, `Nombre`, `Apellido`, `nombreUsuario`, `Contrasenia`, `Mail`, `EstadoUsuario`, `idRol`, `idSucursal`) 
VALUES (2, 'Matias', 'Arriola', 'marriola', 'password', 'matiasarriola.luigi@gmail.com', 'Activo', 1, 1);

INSERT INTO `usuario` (`idUsuario`, `Nombre`, `Apellido`, `nombreUsuario`, `Contrasenia`, `Mail`, `EstadoUsuario`, `idRol`, `idSucursal`) 
VALUES (3, 'Default', 'user', 'e', 'e', 'a@a.com', 'Activo', 1, 2);

-- Inserts para los servicios
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(1, "Corte de pelo", 350.00, 6.06, "00:20:00", 25, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(2, "Lavado", 100.00, 1.73, "00:05:00", 5, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(3, "Secado", 100, 1.73, "00:05:00", 5, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(4, "Tintura", 600, 10.39, "01:30:00", 40, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(5, "Recorte de Barba", 150.00, 2.60, "00:10:00", 15, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(6, "Corte con Navaja NDEAH", 225.00, 3.90, "00:20:00", 25, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(7, "Alisado", 1000.00, 17.32, "02:00:00", 50, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(8, "Reflejos", 400.00, 6.93, "00:30:00", 20, "Inactivo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(9, "Rapado", 200.00, 3.46, "00:10:00", 15, "Activo");
INSERT INTO SERVICIO (IdServicio, Nombre, PrecioLocal, PrecioDolar, Duracion, Puntos, Estado)
VALUES(10, "Afano", 6000.00, 103.91, "00:05:00", 5, "Activo");

-- Inserts para Clientes 
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(1, "Matias", "Arriola", 44512626, "prueba1@gmail.com", 0, "Activo", 00.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(2, "Tomas", "Sierra", 44512727, "test2@gmail.com", 0, "Inactivo", 00.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(3, "Nicolas", "Cirillo", 44512828, "prueba3@gmail.com", 0, "Activo", 00.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(4, "Galileo", "Luna", 44512929, "test4@gmail.com", 0, "Activo", 10.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(5, "Santiago", "Moreno", 44513030, "prueba5@gmail.com", 0, "Activo", 500.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(6, "Adolf", "Gonzalez", 44513131, "test6@gmail.com", 0, "Inactivo", 00.00);
INSERT INTO CLIENTE(idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) 
VALUES(7, "Juan Domingo", "Perez", 44513232, "prueba7@gmail.com", 0, "Activo", 00.00);

-- Inserts para profesionales 
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(1,"Enzo", "Perez", 7, "Activo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(2,"Roberto", "Gonzalez", 5, "Activo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(3,"Francisco", "Alcatraza", 4, "Inactivo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(4,"Armando", "Casas", 2, "Inactivo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(5,"Manuel", "Niestskjsa", 3, "Activo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(6,"John", "White", 6, "Activo");
INSERT INTO PROFESIONAL (IdProfesional, Nombre, Apellido, idSucursalOrigen, estado) 
VALUES(7,"Juana", "De Arco", 2, "Activo");

-- insert para categoria de caja
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUES (1, "Viaticos", "Activo", "Egreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUES (2,"Insumos","Activo", "Egreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUES (3, "Sueldos", "Activo", "Egreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUES (4, "Publicidad","Activo", "Egreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUE (5, "Administrativo", "Inactivo", "Egreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUE (6, "Productos", "Activo", "Ingreso");
INSERT INTO CategoriaCaja ( idCategoriaCaja, Nombre, Estado, tipoMovimiento)
VALUE (7, "Servicios", "Activo", "Ingreso");

-- inserts para dias laborales
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (1, "Lunes", "08:00:00", "14:00:00", 1);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (2, "Martes","08:00:00", "14:00:00", 1);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (3, "Miercoles", "08:00:00", "14:00:00", 1);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (4, "Jueves", "08:00:00", "14:00:00", 1);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (5, "Viernes", "08:00:00", "14:00:00", 1);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (6, "Sabado", "08:00:00", "14:00:00", 1);

INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (7, "Lunes", "14:00:00", "20:00:00", 2);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (8, "Martes", "14:00:00", "20:00:00", 2);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (9, "Miercoles", "14:00:00", "20:00:00", 2);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (10, "Jueves", "14:00:00", "20:00:00", 2);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (11, "Viernes", "14:00:00", "20:00:00", 2);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (12, "Sabado", "14:00:00", "20:00:00", 2);

INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (13, "Lunes", "08:00:00", "14:00:00", 3);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (14, "Martes","08:00:00", "14:00:00", 3);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (15, "Miercoles", "08:00:00", "14:00:00", 3);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (16, "Jueves", "08:00:00", "14:00:00", 3);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (17, "Viernes", "08:00:00", "14:00:00", 3);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (18, "Sabado", "08:00:00", "14:00:00", 3);

INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (19, "Lunes", "14:00:00", "20:00:00", 4);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (20, "Martes", "14:00:00", "20:00:00", 4);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (21, "Miercoles", "14:00:00", "20:00:00", 4);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (22, "Jueves", "14:00:00", "20:00:00", 4);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (23, "Viernes", "14:00:00", "20:00:00", 4);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (24, "Sabado", "14:00:00", "20:00:00", 4);

INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (25, "Lunes", "08:00:00", "14:00:00", 5);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (26, "Martes","08:00:00", "14:00:00", 5);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (27, "Miercoles", "08:00:00", "14:00:00", 5);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (28, "Jueves", "08:00:00", "14:00:00", 5);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (29, "Viernes", "08:00:00", "14:00:00", 5);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (30, "Sabado", "08:00:00", "14:00:00", 5);
  
 INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (31, "Lunes", "14:00:00", "20:00:00", 6);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (32, "Martes", "14:00:00", "20:00:00", 6);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (33, "Miercoles", "14:00:00", "20:00:00", 6);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (34, "Jueves", "14:00:00", "20:00:00", 6);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (35, "Viernes", "14:00:00", "20:00:00", 6);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (36, "Sabado", "14:00:00", "20:00:00", 6); 

 INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (37, "Lunes", "12:00:00", "20:00:00", 7);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (38, "Martes", "12:00:00", "20:00:00", 7);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (39, "Miercoles", "12:00:00", "20:00:00", 7);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (40, "Jueves", "12:00:00", "20:00:00", 7);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (41, "Viernes", "12:00:00", "20:00:00", 7);
INSERT INTO DiasLaborales (IdDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) 
VALUES (42, "Sabado", "142:00:00", "20:00:00", 7); 


-- inserts para ServicioProfesional
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (1,1); 
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (1,2);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (1,5);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (1,7);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (1,3);

INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (2,5);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (2,7);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (2,1);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (2,4);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (2,3);


INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (3,2);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (3,3);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (3,4);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (3,5);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (3,6);

INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (4,1);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (4,7);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (4,2);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (4,6);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (4,4);

INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,4);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,6);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,2);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,3);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,1);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (5,7);


INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (6,2);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (6,1);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (6,5);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (6,7);
INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (6,4);

INSERT INTO ServicioProfesional (idProfesional, idServicio)
VALUES (7,7);

-- DELETE FROM CAJA;
-- DELETE FROM ServicioTurno;
-- DELETE FROM Cita;
-- DROP TABLE IF EXISTS CategoriaCaja;
-- DROP TABLE IF EXISTS Caja;

SET SQL_SAFE_UPDATES = 0;

SELECT * FROM Profesional;

SELECT * FROM Cita; 
SELECT * FROM servicioturno;

SELECT 1 AS Disponible
FROM DiasLaborales  
WHERE DiasLaborales.IdProfesional = 1 AND HoraEntrada <= "13:00" AND HoraSalida >= "14:00" AND Dia = "Lunes";

SELECT 1 AS Ocupado, idCita, idServicio, horaInicio, horaFin 
FROM ServicioTurno
WHERE idProfesional = 5 
AND ((HoraInicio < "11:14" AND HoraFin < "11:14") 
AND (HoraInicio < "11:50" AND HoraFin < "11:50"
AND idCita IN
(SELECT idCita FROM Cita WHERE dia = "2019-10-30" AND EstadoTurno = "Activa");

SELECT * FROM ServicioTurno;
AND ("11:50" BETWEEN HoraInicio AND HoraFin))


SELECT 1 as ocupado 
FROM servicioturno st 
JOIN profesional p USING (IdProfesional) 
JOIN cita c USING (idCita) 
JOIN diaslaborales d ON  p.IdProfesional=d.IdProfesional 
WHERE st.horaInicio < "10:25:00" AND st.horaFin > "10:45:00" 
AND d.HoraEntrada < "10:25:00" AND d.HoraSalida > "10:45:00" 
AND d.Dia = 'Lunes' AND p.IdProfesional = 1 AND c.Dia='2019-11-04';

USE Pelumania;
SELECT 1 as ocupado FROM
 servicioturno st JOIN profesional
  p USING (IdProfesional) JOIN cita c USING 
  (idCita) JOIN diaslaborales d ON  p.IdProfesional=d.IdProfesional 
  WHERE st.horaInicio < ? AND st.horaFin > ? AND d.HoraEntrada < ? AND
   d.HoraSalida > ? AND d.Dia = ? AND p.IdProfesional = ? AND c.Dia=?;
	 parameters : [10:20:00,10:40:00,10:20:00,10:40:00,'Lunes',1,'2019-11-04']

