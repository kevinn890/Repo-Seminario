-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SistemaDeGestionPsicoterapeutica
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SistemaDeGestionPsicoterapeutica
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SistemaDeGestionPsicoterapeutica` DEFAULT CHARACTER SET utf8 ;
USE `SistemaDeGestionPsicoterapeutica` ;

-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`ObraSocial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`ObraSocial` (
  `idObraSocial` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `telefono` INT NOT NULL,
  PRIMARY KEY (`idObraSocial`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`Paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`Paciente` (
  `dni` INT NOT NULL,
  `cuil` BIGINT(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `domicilio` VARCHAR(45) NOT NULL,
  `estadoCivil` VARCHAR(45) NULL,
  `pacientePsiquiatrico` TINYINT(1) NOT NULL,
  `pacienteDerivado` TINYINT(1) NULL,
  `diagnosticoPresuntivo` VARCHAR(45) NULL,
  `contEmerNombre` VARCHAR(45) NOT NULL,
  `contEmerTelefono` INT NOT NULL,
  `ObraSocial_idObraSocial` INT NOT NULL,
  PRIMARY KEY (`dni`),
  INDEX `fk_Paciente_ObraSocial1_idx` (`ObraSocial_idObraSocial` ASC) VISIBLE,
  CONSTRAINT `fk_Paciente_ObraSocial1`
    FOREIGN KEY (`ObraSocial_idObraSocial`)
    REFERENCES `SistemaDeGestionPsicoterapeutica`.`ObraSocial` (`idObraSocial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`Cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`Cita` (
  `idCita` INT NOT NULL AUTO_INCREMENT,
  `Paciente_dni` INT NOT NULL,
  `fechaYHora` DATETIME NOT NULL,
  `profesional` VARCHAR(45) NOT NULL,
  `citaActiva` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idCita`, `Paciente_dni`),
  CONSTRAINT `fk_Cita_Paciente1`
    FOREIGN KEY (`Paciente_dni`)
    REFERENCES `SistemaDeGestionPsicoterapeutica`.`Paciente` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`Prestacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`Prestacion` (
  `idPrestacion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `porcentajeCobertura` FLOAT NOT NULL,
  `ObraSocial_idObraSocial` INT NOT NULL,
  PRIMARY KEY (`idPrestacion`, `ObraSocial_idObraSocial`),
  INDEX `fk_Prestacion_ObraSocial1_idx` (`ObraSocial_idObraSocial` ASC) VISIBLE,
  CONSTRAINT `fk_Prestacion_ObraSocial1`
    FOREIGN KEY (`ObraSocial_idObraSocial`)
    REFERENCES `SistemaDeGestionPsicoterapeutica`.`ObraSocial` (`idObraSocial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`Cuenta` (
  `idCuenta` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `nombreApellido` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCuenta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SistemaDeGestionPsicoterapeutica`.`GrupoConviviente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SistemaDeGestionPsicoterapeutica`.`GrupoConviviente` (
  `idmiembro` INT NOT NULL AUTO_INCREMENT,
  `Paciente_dni` INT NOT NULL,
  `Relacion` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  PRIMARY KEY (`idmiembro`, `Paciente_dni`),
  CONSTRAINT `fk_GrupoConviviente_Paciente`
    FOREIGN KEY (`Paciente_dni`)
    REFERENCES `SistemaDeGestionPsicoterapeutica`.`Paciente` (`dni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
