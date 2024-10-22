CREATE DATABASE  IF NOT EXISTS `restauranteintegrador` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restauranteintegrador`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: restauranteintegrador
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador_empresa`
--

DROP TABLE IF EXISTS `administrador_empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador_empresa` (
  `idempresa` bigint NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` varchar(50) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nombre_empresa` varchar(50) NOT NULL,
  PRIMARY KEY (`idempresa`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador_empresa`
--

LOCK TABLES `administrador_empresa` WRITE;
/*!40000 ALTER TABLE `administrador_empresa` DISABLE KEYS */;
INSERT INTO `administrador_empresa` VALUES (1,'Empresa Ejemplo','12345','admin_ejemplo@example.com','password123','nombre_empresa1'),(2,NULL,'asd','asda@example.com','defaultPassword','asd'),(3,NULL,'12333','empresa3@example.com','defaultPassword','hola'),(4,NULL,'322','exameple@gmail.com','defaultPassword','prueba 4'),(5,NULL,'222','prueba5@example.com','defaultPassword','prueba 5'),(6,NULL,'111111','asda@example.com','defaultPassword','empresa6'),(7,NULL,'123','empresa3@example.com','defaultPassword','qwe123'),(8,NULL,'asd','exameple@gmail.com','defaultPassword','8awesqweqwe'),(9,NULL,'asd','exameple@gmail.com','defaultPassword','prueba 4'),(10,NULL,'123','empresa3@example.com','defaultPassword','prueba 5'),(11,NULL,'221323','empresa3@example.comd','defaultPassword','das'),(12,NULL,'2','asa@example.com','defaultPassword','1324'),(13,NULL,'qweqwe','empresa3@example.com','defaultPassword','prueba'),(14,NULL,'hola','asda@example.com','defaultPassword','123345'),(15,NULL,'123','empresa3@example.com','defaultPassword','dasdf'),(16,NULL,'990204101','user1','contraseña1','nombreEmpresa'),(17,NULL,'123','empresa3@example.com','defaultPassword','prueba 40');
/*!40000 ALTER TABLE `administrador_empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comensal`
--

DROP TABLE IF EXISTS `comensal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comensal` (
  `idComensal` char(5) NOT NULL,
  `nombreComensal` varchar(50) DEFAULT NULL,
  `apellidoComensal` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `apellido_comensal` varchar(50) NOT NULL,
  `nombre_comensal` varchar(50) NOT NULL,
  PRIMARY KEY (`idComensal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comensal`
--

LOCK TABLES `comensal` WRITE;
/*!40000 ALTER TABLE `comensal` DISABLE KEYS */;
/*!40000 ALTER TABLE `comensal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comida`
--

DROP TABLE IF EXISTS `comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comida` (
  `idComida` char(5) NOT NULL,
  `nombreComida` varchar(50) DEFAULT NULL,
  `tipoComida` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `precio` double NOT NULL,
  `nombre_comida` varchar(50) NOT NULL,
  `tipo_comida` varchar(50) NOT NULL,
  PRIMARY KEY (`idComida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comida`
--

LOCK TABLES `comida` WRITE;
/*!40000 ALTER TABLE `comida` DISABLE KEYS */;
/*!40000 ALTER TABLE `comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprobante`
--

DROP TABLE IF EXISTS `comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprobante` (
  `idComprobante` char(5) NOT NULL,
  `fechaComprobante` date DEFAULT NULL,
  `idPago` char(5) DEFAULT NULL,
  PRIMARY KEY (`idComprobante`),
  KEY `idPago` (`idPago`),
  CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`idPago`) REFERENCES `pago` (`idPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprobante`
--

LOCK TABLES `comprobante` WRITE;
/*!40000 ALTER TABLE `comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idempleado` bigint NOT NULL AUTO_INCREMENT,
  `nombreEmpleado` varchar(50) DEFAULT NULL,
  `apellidoEmpleado` varchar(50) DEFAULT NULL,
  `tipoEmpleado` varchar(20) DEFAULT NULL,
  `idEmpresa` char(5) DEFAULT NULL,
  `apellido_empleado` varchar(50) NOT NULL,
  `nombre_empleado` varchar(50) NOT NULL,
  `tipo_empleado` varchar(20) NOT NULL,
  `id_empresa` bigint NOT NULL,
  PRIMARY KEY (`idempleado`),
  KEY `idEmpresa` (`idEmpresa`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa_comida` (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa_comida`
--

DROP TABLE IF EXISTS `empresa_comida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa_comida` (
  `idEmpresa` char(5) NOT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa_comida`
--

LOCK TABLES `empresa_comida` WRITE;
/*!40000 ALTER TABLE `empresa_comida` DISABLE KEYS */;
/*!40000 ALTER TABLE `empresa_comida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local`
--

DROP TABLE IF EXISTS `local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local` (
  `idEmpresa` char(5) NOT NULL,
  `nroMesa` int NOT NULL,
  `nro_mesa` int NOT NULL,
  PRIMARY KEY (`idEmpresa`,`nroMesa`),
  CONSTRAINT `local_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa_comida` (`idEmpresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local`
--

LOCK TABLES `local` WRITE;
/*!40000 ALTER TABLE `local` DISABLE KEYS */;
/*!40000 ALTER TABLE `local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesa`
--

DROP TABLE IF EXISTS `mesa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesa` (
  `nroMesa` int NOT NULL,
  `cantidadSillas` int DEFAULT NULL,
  `estado` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nroMesa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesa`
--

LOCK TABLES `mesa` WRITE;
/*!40000 ALTER TABLE `mesa` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden` (
  `idorden` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int DEFAULT NULL,
  `subtotal` double NOT NULL,
  `idEmpresa` char(5) DEFAULT NULL,
  `nroMesa` int DEFAULT NULL,
  `idComida` char(5) DEFAULT NULL,
  `id_comida` bigint NOT NULL,
  `id_empresa` bigint NOT NULL,
  PRIMARY KEY (`idorden`),
  KEY `idEmpresa` (`idEmpresa`,`nroMesa`),
  KEY `idComida` (`idComida`),
  CONSTRAINT `orden_ibfk_1` FOREIGN KEY (`idEmpresa`, `nroMesa`) REFERENCES `local` (`idEmpresa`, `nroMesa`),
  CONSTRAINT `orden_ibfk_2` FOREIGN KEY (`idComida`) REFERENCES `comida` (`idComida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `idPago` char(5) NOT NULL,
  `fechaPago` date DEFAULT NULL,
  `monto` double NOT NULL,
  `fecha_pago` date NOT NULL,
  PRIMARY KEY (`idPago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registro` (
  `idRegistro` char(5) NOT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `fechaPago` date DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  `idComensal` char(5) DEFAULT NULL,
  PRIMARY KEY (`idRegistro`),
  KEY `idComensal` (`idComensal`),
  CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`idComensal`) REFERENCES `comensal` (`idComensal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `idReserva` char(5) NOT NULL,
  `cantidadPersonas` int DEFAULT NULL,
  `fechaReserva` date DEFAULT NULL,
  `horaReserva` time DEFAULT NULL,
  `idComensal` char(5) DEFAULT NULL,
  PRIMARY KEY (`idReserva`),
  KEY `idComensal` (`idComensal`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`idComensal`) REFERENCES `comensal` (`idComensal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'restauranteintegrador'
--

--
-- Dumping routines for database 'restauranteintegrador'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-20 23:48:59
