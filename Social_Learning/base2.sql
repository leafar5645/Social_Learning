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
INSERT INTO `curso` VALUES (13,'AUto Judicial',1,0,'AUto Judicial'),(14,'Aviones',1,0,'Aviones');
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
  CONSTRAINT `examen_ibfk_3` FOREIGN KEY (`idalumno`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `examen_ibfk_4` FOREIGN KEY (`idt`) REFERENCES `tema` (`idt`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examen`
--

LOCK TABLES `examen` WRITE;
/*!40000 ALTER TABLE `examen` DISABLE KEYS */;
INSERT INTO `examen` VALUES (1,0,3,23,'2018-05-24 05:48:58');
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
  CONSTRAINT `pregunta_ibfk_1` FOREIGN KEY (`idt`) REFERENCES `tema` (`idt`) ON DELETE CASCADE ON UPDATE CASCADE
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
  CONSTRAINT `tema_ibfk_1` FOREIGN KEY (`idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tema`
--

LOCK TABLES `tema` WRITE;
/*!40000 ALTER TABLE `tema` DISABLE KEYS */;
INSERT INTO `tema` VALUES (23,'Definicion','El auto judicial o mandato judicial (tambiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©n llamado en algunos ordenamientos sentencia interlocutoria) es una resoluciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n judicial mediante la cual un tribunal se pronuncia sobre peticiones de las partes, resolviendo las incidencias, es decir, las cuestiones diversas del asunto principal del litigio, pero relacionadas con ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©l, que surgen a lo largo de un proceso jurisdiccional.\r\n\r\nEl auto, como la mayorÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­a de las resoluciones, debe ir acompaÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ±ado de un razonamiento jurÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­dico (consideraciones y fundamentos), en los casos en que las leyes de procedimiento (civil o penal) asÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­ lo determinan.\r\n\r\nDado que el auto es una resoluciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n decisoria, en la mayorÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­a de los casos es posible impugnarlo mediante la interposiciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n de un recurso judicial.\r\n\r\nAl auto judicial tambiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©n se le denomina sentencia interlocutoria, que se refiere a toda aquella decisiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n judicial que resuelve una controversia incidental suscitada entre las partes en un juicio. Se distingue de la sentencia definitiva en que esta resuelve el asunto principal objeto del litigio. En este sentido, la razÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n por la que se denomina interlocutoria es porque sus efectos jurÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­dicos en relaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n con las partes son provisionales, en el sentido de que pueden modificarse sus consecuencias a travÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©s de la sentencia definitiva.\r\n\r\nTipos de autos:\r\n\r\nÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ Los autos de sustanciaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n: tal y como los ha considerado la doctrina y jurisprudencia patria son simples decisiones de actos o solicitudes sencillas sin exigencias de motivaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n que no repercuten mayor trascendencia dentro del proceso, lo cual les permite ser analizados nuevamente y ser decididos sin complicaciones, ratificando o cambiando de opiniÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n. Su carÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡cter tal y como los seÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ±alamos anteriormente estÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡ en la naturaleza del acto a decidir, son actos de simple trÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡mite del proceso.\r\n\r\nÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ Los autos motivados: si son trascendentales, porque deciden actos importantes dentro del proceso como una medida cautelar privativa de libertad. Son autos que tienen la facultad de cambiar situaciones procesales y hasta extra procesales de las partes, incluso con ellos se puede llegar a finalizar el proceso, en el caso de un sobreseimiento definitivo en nuestra legislaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n. Entonces sobre la base de la naturaleza de lo que se decida, los obliga a ser autos motivados con caracterÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­sticas similares a una sentencia.\r\n\r\nNunca bajo ningÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂºn concepto un auto de mera sustanciaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n que no conlleva una motivaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n y que sÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³lo se refiere a aspectos procesales tÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©cnicos tendrÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡ caracterÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­sticas similares a las de una sentencia.',13,'0'),(24,'Decreto Judicial','Decreto, providencia, providencia simple o proveÃ­do es un tipo de resoluciÃ³n judicial que sin fallar sobre incidentes o sobre trÃ¡mites que sirvan de base para el pronunciamiento de una sentencia, tiene sÃ³lo por objeto permitir el desarrollo normal del proceso u ordenar actos de mera ejecuciÃ³n.\r\n\r\nEjemplos\r\n\r\nSon decretos o providencias las resoluciones judiciales que resuelven, proveen o se pronuncian sobre:\r\n\r\n    Traslado de la demanda\r\n    ContestaciÃ³n de la demanda\r\n    RÃ©plica\r\n    DÃºplica\r\n    Solicitud de certificaciÃ³n de ministro de fe\r\n    DelegaciÃ³n de poder\r\n    Solicitud de oficio\r\n    Solicitud de copia\r\n    AcompaÃ±amiento de documento\r\n    Solicitud de citaciÃ³n a oÃ­r sentencia\r\n    Providencia de Castilla La Vieja (1764)\r\n    Solicitud de embargo de bienes\r\n    Solicitud de regulaciÃ³n de costas\r\n    Giro de cheque\r\n',13,'0'),(25,'Sentencia judicial','La sentencia es una resoluciÃ³n judicial dictada por un juez o tribunal que pone fin a la litis (civil, de familia, mercantil, laboral, contencioso-administrativo, etc.) o causa penal.\r\n\r\nLa sentencia declara o reconoce el derecho o razÃ³n de una de las partes, obligando a la otra a pasar por tal declaraciÃ³n y cumplirla. En derecho penal, la sentencia absuelve o condena al acusado, imponiÃ©ndole la pena correspondiente.',13,'0'),(26,'ClasificaciÃ³n','ClasificaciÃ³n\r\n\r\n    Sentencia constitutiva (proceso civil): las que crean, modifican o extinguen una relaciÃ³n judicial\r\n\r\n    Por la presencia/ausencia del demandado:\r\n\r\n    Sentencia contradictoria: cuando el demandado estÃ¡ presente en la causa.\r\n    En rebeldÃ­a: cuando la sentencia se dicta sin la presencia del demandado.\r\n\r\n    Por la posibilidad de impugnaciÃ³n:\r\n\r\n    Sentencia firme: aquella contra la que no cabe la interposiciÃ³n de ningÃºn recurso, ordinario o extraordinario. Y cuando ambas partes dejan transcurrir el tiempo y no interpone recurso impugnatorio. EstÃ¡ amparada por el principio de cosa juzgada.\r\n    Sentencia no firme o recurrible: es aquella contra la que se pueden interponer recursos.\r\n\r\n    Por el grado de jurisdicciÃ³n\r\n\r\n    Sentencia en primera instancia: la que devienen de los Ã³rganos de primera instancia, por su competencia y jurisdicciÃ³n.\r\n    Sentencia en apelaciÃ³n: cuando se recurre, bien sea al mismo Ã³rgano o al inmediatamente superior (Audiencia Provincial).\r\n    Sentencia en casaciÃ³n: es aquella que se emite por el Tribunal Supremo pretendiendo casar la causa.\r\n\r\n    Por la forma:\r\n\r\n    Sentencia escrita: la que se redacta por escrito y de esa manera se da a conocer a las partes.\r\n    Sentencia oral: la que se expone oralmente ante las partes involucradas, quienes quedan notificadas en ese mismo acto. ',13,'0'),(27,'Requisitos','La sentencia debe reunir los requisitos de tiempo, lugar y forma. Debe dictarse en un periodo de tiempo apto para la realizaciÃ³n de los actos del juez o tribunal. La fijaciÃ³n de este plazo varÃ­a segÃºn el procedimiento de que se trate.\r\n\r\nRespecto de la forma, las sentencias generalmente se componen de tres secciones:\r\n\r\n    Encabezamiento o parte expositiva: en el que se seÃ±ala la fecha y ciudad en que se dicta, las partes intervinientes, sus procuradores y abogados, sin que se puedan omitir sus nombres sin afectar a la debida integridad y publicidad de las sentencias. Se hacen constar tambiÃ©n las peticiones presentadas por las partes, junto a los presupuestos o antecedentes de hecho en que se fundan.\r\n\r\n    Parte considerativa: en la que se expresan los fundamentos de hecho y de derecho, que contienen los argumentos de las partes y los que utiliza el tribunal para resolver el objeto del proceso, en relaciÃ³n con las normas que se consideran aplicables al caso.\r\n\r\n    Parte resolutiva: en la que se contiene la decisiÃ³n o fallo de condena o absoluciÃ³n del demandado o acusado. Asimismo, suele incorporarse el nombre del juez que la ha redactado y la firma de todos los que han concurrido a su acuerdo.\r\n\r\nPor otro lado, las sentencias deben ser congruentes, es decir, deben resolver acerca de todas las cuestiones que hayan sido objeto de debate en el proceso. El fallo no debe contener mÃ¡s, ni algo distinto, de lo pedido por las partes. Cuando se trata de sentencias penales, la congruencia significa que debe mediar una relaciÃ³n entre la sentencia y la acciÃ³n penal ejercitada. Por ejemplo, si una persona es acusada de homicidio, el juez no puede condenarle por robo (para ello harÃ­a falta aplicar otro procedimiento), ya que estÃ¡ limitado por los hechos alegados. Sin embargo, podrÃ­a realizar una calificaciÃ³n jurÃ­dica diversa de la hecha por las partes, por ejemplo, en el mismo caso, condenar por asesinato o parricidio y no por homicidio.\r\n\r\nPuede clasificarse la incongruencia en la sentencia por: 1) Falta de exhaustividad, omitiÃ©ndose el pronunciamiento sobre un tema debido. 2) Incongruencia ultrapetitum, concediÃ©ndose mÃ¡s de lo pretendido por el actor. 3) Incongruencia extrapetitum, concediÃ©ndose otra cosa y no lo pedido.\r\n\r\nLos elementos de la estructura de una sentencia son preÃ¡mbulo, resultando, considerando y puntos resolutivos. En las sentencias espaÃ±olas su estructura es encabezamiento (nombre de las partes y sus datos, identificaciÃ³n de procurador y abogado, objeto del juicio, fecha, lugar y tribunal, jueces o magistrados, asÃ­ como el ponente si es tribunal colegiado), antecedentes de hecho (en pÃ¡rrafos separados y numerados, exponiÃ©ndose las peticiones de las partes, los hechos en que las funden y las pruebas que se hubieran propuesto y practicado -hechos probados-), fundamentos de derecho (en pÃ¡rrafos separados y numerados, donde se apreciarÃ¡ el derecho que funda las pretensiones, con cita de las leyes o doctrina aplicables) y, finalmente, el fallo (que es la parte dispositiva, donde se resuelve el pleito).',13,'0'),(28,'AviÃ³n de caza','Un aviÃ³n de caza (tambiÃ©n llamado aviÃ³n de combate), o simplemente caza,1â es una aeronave militar diseÃ±ada fundamentalmente para la guerra aÃ©rea con otras aeronaves, en oposiciÃ³n a los bombarderos, que estÃ¡n diseÃ±ados principalmente para atacar objetivos terrestres mediante el lanzamiento de bombas. Los cazas son pequeÃ±os, veloces y de gran maniobrabilidad. Muchos cazas poseen capacidades secundarias de ataque a tierra, y algunos son de doble propÃ³sito para actuar como cazabombarderos; tÃ©rmino tambiÃ©n usado para nombrar a los aviones de ataque a tierra con capacidades de caza.\r\n\r\nLos cazas son el principal medio con el que las fuerzas armadas consiguen la superioridad aÃ©rea sobre sus oponentes en batalla. Por lo menos desde la Segunda Guerra Mundial, lograr y mantener la superioridad aÃ©rea viene siendo un componente clave a la hora de conseguir la victoria en la guerra, particularmente en una guerra convencional entre ejÃ©rcitos regulares (no asÃ­ en una guerra de guerrillas).2â De este modo, la adquisiciÃ³n, el entrenamiento y el mantenimiento de una flota de cazas representa una parte muy sustancial de los presupuestos de defensa para las fuerzas armadas actuales.3â',14,'0');
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
  `olvidar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'humberto@gmail.com','beto','Humberto','P','1','no'),(2,'pepe@pepe.com','pepe','pepe','P','2','no'),(3,'beto@gmail.com','beto','beto alumno','A','3','no');
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
INSERT INTO `usuariocurso` VALUES (13,3);
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

-- Dump completed on 2018-05-24  0:53:53
