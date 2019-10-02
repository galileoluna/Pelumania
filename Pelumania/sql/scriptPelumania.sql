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
  `Estado` VARCHAR(55) NOT NULL,
  `idSucursalOrigen` int NOT NULL,
  `idSucursalTransferencia` int NULL,

  PRIMARY KEY(`IdProfesional`),
  FOREIGN KEY (`idSucursalOrigen`) REFERENCES `Sucursal`(`idSucursal`)
);

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
  `Descripcion` VARCHAR(55) NOT NULL,  
  `FechaInicio` DATE NOT NULL,
  `FechaFin` DATE NOT NULL,
  `Descuento` INT(11)  NULL,
  `Puntos` INT(11)  NULL,

 
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
  `idCliente` INT(11) NOT NULL,
  `EstadoTurno` VARCHAR(55) NOT NULL,
  `IdProfesional` INT (11) NOT NULL,
  `PrecioLocal` INT (11) NOT NULL,
  `PrecioDolar` INT (11) NOT NULL,
  `Hora` TIME NOT NULL,
  `Dia` DATE NOT NULL,
  `idSucursal` INT (11) NOT NULL,

    PRIMARY KEY(`idCita`),
    FOREIGN KEY (`IdProfesional`) REFERENCES `Profesional`(`IdProfesional`),
    FOREIGN KEY (`idCliente`) REFERENCES `Cliente`(`idCliente`),
    FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal`(`idSucursal`)
     
);

CREATE TABLE IF NOT EXISTS `Caja`(

  `IdCaja` INT(11) NOT NULL AUTO_INCREMENT,
  `idSucursal` INT(11) NOT NULL,
  `Categoria` VARCHAR(55) NOT NULL,
  `Fecha`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `Descripcion` VARCHAR(80) NOT NULL,  
  `Concepto` VARCHAR(55) NOT NULL,    
  `TipoDeCambio` VARCHAR(55) NOT NULL,
  `idPromocion` INT(11)  NULL,
  `PrecioLocal` INT(11) NOT NULL,
  `PrecioDolar` INT(11) NOT NULL,
  `idCita` INT(11)  NULL,
  `idCliente` INT(11)  NULL,
 
 PRIMARY KEY(`IdCaja`),
 FOREIGN KEY (`idSucursal`) REFERENCES `Sucursal`(`idSucursal`),
 FOREIGN KEY (`idPromocion`) REFERENCES `Promocion`(`idPromocion`),
 FOREIGN KEY (`idCliente`) REFERENCES `Cliente`(`idCliente`),
 FOREIGN KEY (`idCita`) REFERENCES `Cita`(`idCita`)

);

CREATE TABLE IF NOT EXISTS `ServicioTurno`(

  `IdServicio` INT(11) NOT NULL,
  `idCita` INT(11) NOT NULL,
    
  FOREIGN KEY (`idServicio`) REFERENCES `Servicio`(`IdServicio`),
  FOREIGN KEY (`idCita`) REFERENCES `Cita`(`idCita`)
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