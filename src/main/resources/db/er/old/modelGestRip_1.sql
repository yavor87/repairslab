SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `gestRip` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
SHOW WARNINGS;
USE `gestRip`;

-- -----------------------------------------------------
-- Table `gestRip`.`ANASTATI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`ANASTATI` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`ANASTATI` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nomeStato` VARCHAR(50) NULL ,
  `descStato` VARCHAR(200) NULL ,
  `flagAttivo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`TIPOAPPARECCHIATURE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`TIPOAPPARECCHIATURE` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`TIPOAPPARECCHIATURE` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NULL ,
  `descizione` VARCHAR(200) NULL ,
  `flagAttivo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`MODELLI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`MODELLI` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`MODELLI` (
  `id` INT NOT NULL ,
  `nome` VARCHAR(50) NULL ,
  `descModello` VARCHAR(200) NULL ,
  `flagAttivo` VARCHAR(1) NULL ,
  `idMarchi` INT NULL ,
  `idTipoApp` INT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;
CREATE INDEX index1 ON `gestRip`.`MODELLI` (`idMarchi` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`MARCHI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`MARCHI` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`MARCHI` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NULL ,
  `descrizione` VARCHAR(200) NULL ,
  `flagAttivo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_MARCHI_MODELLI`
    FOREIGN KEY (`id` )
    REFERENCES `gestRip`.`MODELLI` (`idMarchi` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;
CREATE INDEX fk_MARCHI_MODELLI ON `gestRip`.`MARCHI` (`id` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`TIPORIPARAZIONE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`TIPORIPARAZIONE` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`TIPORIPARAZIONE` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nomeTipoRip` VARCHAR(50) NULL ,
  `descTipoRip` VARCHAR(200) NULL ,
  `flagAttivo` VARCHAR(1) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`CLIENTI`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`CLIENTI` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`CLIENTI` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NULL ,
  `cognome` VARCHAR(50) NULL ,
  `pIva` VARCHAR(50) NULL ,
  `azienda` VARCHAR(15) NULL ,
  `phone` VARCHAR(30) NULL ,
  `mobilePhone` VARCHAR(30) NULL ,
  `email` VARCHAR(50) NULL ,
  `indirizzo` VARCHAR(100) NULL ,
  `city` VARCHAR(50) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
PACK_KEYS = 0
ROW_FORMAT = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`TPODATIACQUISTO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`TPODATIACQUISTO` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`TPODATIACQUISTO` (
  `id` INT NOT NULL ,
  `tipo` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`SCHEDE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`SCHEDE` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`SCHEDE` (
  `id` INT NOT NULL ,
  `idCliente` INT NULL ,
  `idStato` INT NULL ,
  `idTipoRip` INT NULL ,
  `accessoriConsegnati` VARCHAR(300) NULL ,
  `statoGenerale` VARCHAR(300) NULL ,
  `difettoSegnalato` VARCHAR(300) NULL ,
  `nonConformità` VARCHAR(300) NULL ,
  `descrizioneRiparazione` VARCHAR(300) NULL ,
  `noteStampa` VARCHAR(300) NULL ,
  `noteUsoInterno` VARCHAR(300) NULL ,
  `costoPreventivato` FLOAT NULL ,
  `costoInterno` FLOAT NULL ,
  `pagatoDalCliente` FLOAT NULL ,
  `dataInserimento` DATE NULL ,
  `dataChiusura` DATE NULL ,
  `descApparecchio` VARCHAR(200) NULL ,
  `imei` VARCHAR(30) NULL ,
  `serial` VARCHAR(30) NULL ,
  `idTipoApparecchiatura` INT NULL ,
  `idModello` INT NULL ,
  `idMarca` INT NULL ,
  `numeroDatiAcq` INT NULL ,
  `dataDatiAcq` DATE NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gestRip`.`STATOSCHEDA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gestRip`.`STATOSCHEDA` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gestRip`.`STATOSCHEDA` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `idStato` INT NOT NULL ,
  `data` DATE NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
