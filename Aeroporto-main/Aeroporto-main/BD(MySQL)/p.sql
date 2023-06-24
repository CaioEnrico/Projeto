-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pessoa` (
  `idPessoa` INT NOT NULL,
  `nomePessoa` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `idade` VARCHAR(45) NULL,
  `ocupacao` VARCHAR(45) NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE INDEX `idPessoa_UNIQUE` (`idPessoa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aviao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aviao` (
  `idAviao` INT NOT NULL,
  `nomeAviao` VARCHAR(45) NULL,
  `qndAssentos` INT NULL,
  PRIMARY KEY (`idAviao`),
  UNIQUE INDEX `idaviao_UNIQUE` (`idAviao` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`classe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`classe` (
  `idClasse` INT NOT NULL,
  `tipoClasse` VARCHAR(45) NULL,
  `aviao_idAviao` INT NOT NULL,
  PRIMARY KEY (`idClasse`, `aviao_idAviao`),
  INDEX `fk_classe_aviao_idx` (`aviao_idAviao` ASC) VISIBLE,
  CONSTRAINT `fk_classe_aviao`
    FOREIGN KEY (`aviao_idAviao`)
    REFERENCES `mydb`.`aviao` (`idAviao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`aviao_has_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`aviao_has_pessoa` (
  `aviao_idAviao` INT NOT NULL,
  `pessoa_idPessoa` INT NOT NULL,
  PRIMARY KEY (`aviao_idAviao`, `pessoa_idPessoa`),
  INDEX `fk_aviao_has_pessoa_pessoa1_idx` (`pessoa_idPessoa` ASC) VISIBLE,
  INDEX `fk_aviao_has_pessoa_aviao1_idx` (`aviao_idAviao` ASC) VISIBLE,
  CONSTRAINT `fk_aviao_has_pessoa_aviao1`
    FOREIGN KEY (`aviao_idAviao`)
    REFERENCES `mydb`.`aviao` (`idAviao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aviao_has_pessoa_pessoa1`
    FOREIGN KEY (`pessoa_idPessoa`)
    REFERENCES `mydb`.`pessoa` (`idPessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
