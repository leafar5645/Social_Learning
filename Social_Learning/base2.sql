-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto_adoo
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comentarios` (
  `idcomen` int(11) NOT NULL,
  `texto` varchar(150) DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  `idpubli` int(11) NOT NULL,
  PRIMARY KEY (`idcomen`),
  KEY `idusuario` (`idusuario`),
  KEY `idpubli` (`idpubli`),
  CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`idpubli`) REFERENCES `publicacion` (`idpubli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `idcreador` int(11) DEFAULT NULL,
  `numinscritos` int(11) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `idcreador` (`idcreador`),
  CONSTRAINT `curso_ibfk_1` FOREIGN KEY (`idcreador`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'adasd',1,0,'asdsdsd'),(2,'curso1',1,0,'sdfsd'),(3,'asdd',1,0,'asdsd'),(4,'sdasd',1,0,'asd'),(5,'curso2',1,0,'prueba'),(6,'curso2especial',1,0,'el perro\r\nsds'),(7,'curso superespacial',1,0,'especial\r\nespecial');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examen`
--

DROP TABLE IF EXISTS `examen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examen` (
  `idexamen` int(11) NOT NULL,
  `calif` int(11) DEFAULT NULL,
  `idalumno` int(11) NOT NULL,
  `idt` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idexamen`,`idalumno`,`idt`),
  KEY `idalumno` (`idalumno`),
  KEY `idt` (`idt`),
  CONSTRAINT `examen_ibfk_1` FOREIGN KEY (`idalumno`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `examen_ibfk_2` FOREIGN KEY (`idt`) REFERENCES `tema` (`idt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examen`
--

LOCK TABLES `examen` WRITE;
/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pregunta` (
  `idpregunta` int(11) NOT NULL,
  `idt` int(11) NOT NULL,
  `pregunta` varchar(300) DEFAULT NULL,
  `respuesta` varchar(300) DEFAULT NULL,
  `a` varchar(300) DEFAULT NULL,
  `b` varchar(300) DEFAULT NULL,
  `c` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idpregunta`,`idt`),
  KEY `idt` (`idt`),
  CONSTRAINT `pregunta_ibfk_1` FOREIGN KEY (`idt`) REFERENCES `tema` (`idt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pregunta`
--

LOCK TABLES `pregunta` WRITE;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicacion`
--

DROP TABLE IF EXISTS `publicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publicacion` (
  `idpubli` int(11) NOT NULL,
  `idcurso` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `likes` int(11) DEFAULT NULL,
  `contenido` varchar(500) DEFAULT NULL,
  `mediaUrl` varchar(200) DEFAULT NULL,
  `validacion` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idpubli`),
  KEY `idcurso` (`idcurso`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`),
  CONSTRAINT `publicacion_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicacion`
--

LOCK TABLES `publicacion` WRITE;
/*!40000 ALTER TABLE `publicacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `publicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tema`
--

DROP TABLE IF EXISTS `tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tema` (
  `idt` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `texto` varchar(5000) DEFAULT NULL,
  `idcurso` int(11) NOT NULL,
  `recurso` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idt`),
  KEY `idcurso` (`idcurso`),
  CONSTRAINT `tema_ibfk_1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (1,'tema2','dasd\rsdasd\rsdasdas\rasdsd',2,'0'),(2,'sdfsf','ffffffffffffffsdfsdffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffdfsfsdfsdfsdf',2,'0'),(3,'dsadas','aaaaaaaaaaaaaaaaaaaaaaaaaaadddddddddddddddddddddddd',2,'0'),(4,'hola','aaaaaaaaaaaaaaaaaaaaaaaaaaa\rdddddddddddddddddddddddd',2,'0'),(5,'asdsd','aaaaaaaaaaaaaaaaaaaaaaaaaaaaa\rSLATOdddddddddddddddddddddddddd',3,'0'),(6,'adsdasda','sdfgsdffffffffffff',4,'0'),(7,'dasdas','sdadsadasd\r\nsadsdsads',4,'0'),(8,'asdasd','sdadsdasd',5,'0'),(9,'asdasd','sadsdasd',5,'0'),(10,'tema1','asddasdasd',6,'0'),(11,'tema2','asdsdasdasd',6,'0'),(12,'tema3','sadasdasd',6,'0'),(13,'tema1','tea\r\ncoasa\r\nmas cosas\r\nmas cosas',7,'apoyo13.mp4');
/*!40000 ALTER TABLE `tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `nombre` varchar(150) DEFAULT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'humberto@gmail.com','beto','Humberto','P','P335506'),(2,'pepe@pepe.com','pepe','pepe','P','P874621');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuariocurso`
--

DROP TABLE IF EXISTS `usuariocurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuariocurso` (
  `idcurso` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`idcurso`,`idusuario`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `usuariocurso_ibfk_1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usuariocurso_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuariocurso`
--

LOCK TABLES `usuariocurso` WRITE;
/*!40000 ALTER TABLE `usuariocurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuariocurso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13  1:08:44
