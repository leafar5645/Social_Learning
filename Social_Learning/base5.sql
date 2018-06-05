-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
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
INSERT INTO `comentarios` VALUES (1,'hola',4,1);
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
  `tipo` int(11) DEFAULT NULL,
  `pass` varchar(20) DEFAULT NULL,
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
INSERT INTO `curso` VALUES (13,'AUto Judicial',1,0,'AUto Judicial',0,'1'),(14,'Aviones',1,0,'Aviones',0,'1'),(15,'Sistemas Operativos',5,0,'Analizar la estructura interna de los sistemas operativos para el ANÃ?LISIS, DiseÃ±o e implementacion de sistemas computacionales  ',0,'1'),(16,'Ingles',5,0,'we will learn English\r\n',0,'1'),(17,'Teoria Computacional',7,0,'En este curso estudiaremos los principios de los lenguajes formales y a los automatas.',0,'1'),(18,'Logica',7,0,'En este curso veremos los fundamentos de la Logica',0,'1'),(19,'Inteligencia Artificial',8,0,'El campo de la inteligencia artificial se va actualizando continuamente requiriendo conocimientos muy diversos que muchas veces no son consecuencia de un grado de estudio ',0,'1'),(20,'Estructuras de Gestores de Bases de datos',8,0,'Es ente curso se habla de los indices y estructuras que principalmente usan los SGBD',0,'1'),(21,'prueba',1,0,'prueba',1,'12345');
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
INSERT INTO `examen` VALUES (1,0,3,23,'2018-05-24 05:48:58'),(2,0,6,29,'2018-06-03 01:11:40'),(3,0,6,31,'2018-06-03 02:03:48'),(4,0,3,29,'2018-06-04 04:42:02');
/*!40000 ALTER TABLE `examen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `megusta`
--

DROP TABLE IF EXISTS `megusta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `megusta` (
  `idpubli` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  `megusta` int(11) DEFAULT NULL,
  PRIMARY KEY (`idpubli`,`idusuario`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `megusta_ibfk_1` FOREIGN KEY (`idpubli`) REFERENCES `publicacion` (`idpubli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `megusta_ibfk_2` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `megusta`
--

LOCK TABLES `megusta` WRITE;
/*!40000 ALTER TABLE `megusta` DISABLE KEYS */;
INSERT INTO `megusta` VALUES (1,4,1);
/*!40000 ALTER TABLE `megusta` ENABLE KEYS */;
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
INSERT INTO `pregunta` VALUES (1,29,'Â¿Un sistema operativos es programa que actua intermediario entre _______ y la computadora?','Usuario','Hardaware','Software','Internet'),(1,31,'The festival, Â­Â­Â­Â­Â­Â­Â­Â­Â­Â­Â­______________ lasted all day, ended with a banquet.','which','who','that','where'),(1,46,'Â¿CuÃ¡l es el objetivo de la prueba de Turing? ','Intenta ofrecer una satisfactoria definiciÃ³n operativa de lo que es inteligencia. ','Intenta generar inteligencia artificial. ','Intenta Hacer imposible la Inteligencia Artificial','Intenta ofrecer una satisfactoria definiciÃ³n operativa de lo que es inteligencia. '),(2,29,'El principal Proposito de un sistema operativo es:','Proporcionar un entorno para que el usuario pueda ejecutar programas ','La seguridad e integridad de la computadora','Controlar todo el Hardware','cOMUNICAR SOFTWARE CON HARDWARE'),(2,31,'I am looking for someone __________ can watch my dog while I go on vacation.','who','that','which','what'),(2,46,'Â¿Como define Turing a una Conducta Inteligente?','La capacidad de logar eficiencia de nivel humano en el desarrollo de actividades de tipo cognoscitivo','La capacidad de logar eficiencia de alto nivel en el desarrollo de actividades de tipo cognoscitivo','La capacidad de logar eficiencia de alto nivel en el desarrollo de actividades y pensamientos.','La capacidad de logar eficiencia de  nivel hmano en el desarrollo de actividades y pensamientos.'),(3,29,'Â¿Proporcionar interfaz de lÃ­nea de comando o una interfaz grÃ¡fica al usuario ES UNA TAREA PRINCIPAL DEL so?','Si ','no ','Solo la interfaz de la linea de comandos ','Solo la interfaz GRÃ?FICA '),(3,31,'The police needed details _____________ could help identify the robber.','that','whatever','which','what'),(3,46,'Â¿QuÃ© capacidades debe de tener una maquina para pasar la prueba de Turing?','Procesar un lenguaje natural, Representar el conocimiento, Razonar automÃ¡ticamente, Autoaprendizaje de la mÃ¡quina','Procesar un lenguaje natural, Razonar automÃ¡ticamente, Autoaprendizaje de la mÃ¡quina','Procesar un lenguaje natural, Autoaprendizaje de la mÃ¡quina, Representar el conocimiento,','Procesar un lenguaje maquina, generar el conocimiento, Razonar automÃ¡ticamente, Autoaprendizaje de la mÃ¡quina'),(4,29,'Â¿eS UNA TAREA PRINCIPAL DE UN SISTEMA OPERATIVO?','Administrar los dispositivos de hardware en la computadora. El Sistema Operativo sirve de intermediario entre los programas y el hardware.','Encargarse de la tramas recibidas de internet','Proteger la informacion del usuario','ninguna de las anteriores '),(4,31,'I’d like to take you to a café _______________ serves excellent coffee.','which','whatever','whow','what'),(4,46,'Â¿QuÃ© es procesar lenguaje Natural?','establecer comunicaciÃ³n eficiente en cualquier idioma humano;','interpretar cualquier sonido','Conocer lenguajes de alto nivel','interpretar cualquier simbologÃ­a.'),(5,29,'Es una caracteristica de un sistema operativo:','Eficiencia','Robustes','Seguridad','Ninguna de las anteriores'),(5,31,'The clubhouse, in __________ the dance was held, housed about 200 people.','which','whatever','whow','what'),(5,46,'¿A qué se refiere con 	Representar el conocimiento?','guardar toda la información que se le haya dado antes y durante el interrogatorio','guardar toda la información que se le haya dado antes  el interrogatorio','guardar toda la información que se le haya dado despues  de el interrogatorio','guardar  la información más importante que se le haya dado antes y durante el interrogatorio'),(6,29,'¿A que se refiere la habilidad para evolucionar',' Un Sistema Operativo deberá construirse de manera que permita el desarrollo, prueba o introducción efectiva de nuevas funciones del sistema sin interferir con el servicio.','El Sistema Operativo se encarga de manejar de una mejor manera los recursos de la computadora en cuanto a hardware se refiere.','Un Sistema Operativo debe hacerle fácil al usuario el acceso y manejo de los dispositivos de Entrada/ Salida de la computadora.','ninguna de las anteriores'),(6,31,'You can choose one person, __________ you like, to share the cruise with you.','Whomever','that','whow','what'),(6,46,'¿A qué se refiere con 	Razonar automáticamente?','poder utilizar la información guardada al responder una pregunta y obtener nuevas conclusiones;','poder utilizar la informacióndel momento al responder una pregunta y obtener nuevas conclusiones;','poder utilizar la informacióndel momento al responder una pregunta','poder utilizar la informacióndel guardada al responder una pregunta'),(7,29,'¿que es lo primero que hace la computadora al encenderse? ',' llevar a cabo un autodiagnóstico llamado auto prueba de encendido','Cargar el sistema operativo','conectarse a la red','examinar el disco duro'),(7,31,'I saw the shoes __________ you bought last week on sale for less this week.','that','where','whow','what'),(7,46,'¿A qué se refiere con  Autoaprendizaje de la máquina?','que se adapte a nuevas circunstancias','que se evite las nuevas circunstancias','que se descargue nueva información','que use una gran base de datos para decidir '),(8,29,'Defincion informal de sistema operativo','Es un programa encargado de controlar todos los elementos de una computadora','Es el primer software que corre sobre una computadora','Es un software de base encargado de administrar cada uno de los componentes de un equipo de computo de manera rapida y eficiente','ninguna de las anteriores'),(8,31,'The winners, __________ known, will receive money and other prizes','when','where','whow','what'),(8,46,'¿Qué es una captcha?','','','',''),(9,29,'Defincion formal de sistema operativo','Es un software de base encargado de administrar cada uno de los componentes de un equipo de computo de manera rapida y eficiente','Es el primer software que corre sobre una computadora','Es un programa encargado de controlar todos los elementos de una computadora','ninguna de las anteriores'),(9,31,'This is the place __________ we met.','when','where','whow','what'),(9,46,'¿Qué es una captcha?','prueba de Turing completamente automática y pública para diferenciar ordenadores de humanos','prueba de Turing completamente automática y pública para diferenciar ordenadores','prueba de Turing completamente automática y pública para clasificar ordenadores','prueba de Turing completamente automática y pública para diferenciar tipos de inteligencia'),(10,29,'El sistema operativo es un software de: ','Base','Tiempo Real','Aplicacion','Driver'),(10,31,'The baby, ________ nap had been interrupted, wailed loudly.','whose','where','whow','what'),(10,46,'¿Cual es el objetivo de las maquinas en la prueba de Turing?','Es hacer creer al entrevistador que es una persona','Es resolver un problema matematico','es hacer creer al entrevistador que es una maquina','Es ayudar al antrevitador'),(11,29,'¿Un sistema operativo esta compuesto por un conjunto de administradores?','Si','No','Solo es uno ','esta compuesto por un conjunto de protocolos'),(11,31,'Yeah, I have a friend________ is a writer, and he uses them all the time ','that','where','whow','what'),(11,46,'¿Qué se necestia para realizar la prueba de Turing?','dos personas y la máquina que se desea evaluar. ','una personas y la máquina que se desea evaluar. ','dos máquinas que se desea evaluar. ','dos personas y la dos máquinas que se desea evaluar. '),(12,46,'¿En que consiste el enfoque de Turing?','Actuar como humano','Actuar como animal','Actuar como matematico','Actuar como maquina'),(13,46,'¿en que consiste procesar un lenguaje Natural?','establecer comunicación eficiente en cualquier idioma humano;','establecer comunicación de alto nivel','establecer comunicación eficiente en cualquier simbologia;','establecer comunicación eficiente en cualquier idioma;');
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
INSERT INTO `publicacion` VALUES (1,13,4,'Increible!',NULL,1),(2,19,8,'Como funcionan los indices primarios',NULL,0);
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
INSERT INTO `tema` VALUES (23,'Definicion','El auto judicial o mandato judicial (tambiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©n llamado en algunos ordenamientos sentencia interlocutoria) es una resoluciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n judicial mediante la cual un tribunal se pronuncia sobre peticiones de las partes, resolviendo las incidencias, es decir, las cuestiones diversas del asunto principal del litigio, pero relacionadas con ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©l, que surgen a lo largo de un proceso jurisdiccional.\r\n\r\nEl auto, como la mayorÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­a de las resoluciones, debe ir acompaÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ±ado de un razonamiento jurÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­dico (consideraciones y fundamentos), en los casos en que las leyes de procedimiento (civil o penal) asÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­ lo determinan.\r\n\r\nDado que el auto es una resoluciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n decisoria, en la mayorÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­a de los casos es posible impugnarlo mediante la interposiciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n de un recurso judicial.\r\n\r\nAl auto judicial tambiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©n se le denomina sentencia interlocutoria, que se refiere a toda aquella decisiÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n judicial que resuelve una controversia incidental suscitada entre las partes en un juicio. Se distingue de la sentencia definitiva en que esta resuelve el asunto principal objeto del litigio. En este sentido, la razÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n por la que se denomina interlocutoria es porque sus efectos jurÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­dicos en relaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n con las partes son provisionales, en el sentido de que pueden modificarse sus consecuencias a travÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©s de la sentencia definitiva.\r\n\r\nTipos de autos:\r\n\r\nÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ Los autos de sustanciaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n: tal y como los ha considerado la doctrina y jurisprudencia patria son simples decisiones de actos o solicitudes sencillas sin exigencias de motivaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n que no repercuten mayor trascendencia dentro del proceso, lo cual les permite ser analizados nuevamente y ser decididos sin complicaciones, ratificando o cambiando de opiniÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n. Su carÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡cter tal y como los seÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ±alamos anteriormente estÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡ en la naturaleza del acto a decidir, son actos de simple trÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡mite del proceso.\r\n\r\nÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ Los autos motivados: si son trascendentales, porque deciden actos importantes dentro del proceso como una medida cautelar privativa de libertad. Son autos que tienen la facultad de cambiar situaciones procesales y hasta extra procesales de las partes, incluso con ellos se puede llegar a finalizar el proceso, en el caso de un sobreseimiento definitivo en nuestra legislaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n. Entonces sobre la base de la naturaleza de lo que se decida, los obliga a ser autos motivados con caracterÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­sticas similares a una sentencia.\r\n\r\nNunca bajo ningÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂºn concepto un auto de mera sustanciaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n que no conlleva una motivaciÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³n y que sÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ³lo se refiere a aspectos procesales tÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ©cnicos tendrÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¡ caracterÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ­sticas similares a las de una sentencia.',13,'0'),(24,'Decreto Judicial','Decreto, providencia, providencia simple o proveÃ­do es un tipo de resoluciÃ³n judicial que sin fallar sobre incidentes o sobre trÃ¡mites que sirvan de base para el pronunciamiento de una sentencia, tiene sÃ³lo por objeto permitir el desarrollo normal del proceso u ordenar actos de mera ejecuciÃ³n.\r\n\r\nEjemplos\r\n\r\nSon decretos o providencias las resoluciones judiciales que resuelven, proveen o se pronuncian sobre:\r\n\r\n    Traslado de la demanda\r\n    ContestaciÃ³n de la demanda\r\n    RÃ©plica\r\n    DÃºplica\r\n    Solicitud de certificaciÃ³n de ministro de fe\r\n    DelegaciÃ³n de poder\r\n    Solicitud de oficio\r\n    Solicitud de copia\r\n    AcompaÃ±amiento de documento\r\n    Solicitud de citaciÃ³n a oÃ­r sentencia\r\n    Providencia de Castilla La Vieja (1764)\r\n    Solicitud de embargo de bienes\r\n    Solicitud de regulaciÃ³n de costas\r\n    Giro de cheque\r\n',13,'0'),(25,'Sentencia judicial','La sentencia es una resoluciÃ³n judicial dictada por un juez o tribunal que pone fin a la litis (civil, de familia, mercantil, laboral, contencioso-administrativo, etc.) o causa penal.\r\n\r\nLa sentencia declara o reconoce el derecho o razÃ³n de una de las partes, obligando a la otra a pasar por tal declaraciÃ³n y cumplirla. En derecho penal, la sentencia absuelve o condena al acusado, imponiÃ©ndole la pena correspondiente.',13,'0'),(26,'ClasificaciÃ³n','ClasificaciÃ³n\r\n\r\n    Sentencia constitutiva (proceso civil): las que crean, modifican o extinguen una relaciÃ³n judicial\r\n\r\n    Por la presencia/ausencia del demandado:\r\n\r\n    Sentencia contradictoria: cuando el demandado estÃ¡ presente en la causa.\r\n    En rebeldÃ­a: cuando la sentencia se dicta sin la presencia del demandado.\r\n\r\n    Por la posibilidad de impugnaciÃ³n:\r\n\r\n    Sentencia firme: aquella contra la que no cabe la interposiciÃ³n de ningÃºn recurso, ordinario o extraordinario. Y cuando ambas partes dejan transcurrir el tiempo y no interpone recurso impugnatorio. EstÃ¡ amparada por el principio de cosa juzgada.\r\n    Sentencia no firme o recurrible: es aquella contra la que se pueden interponer recursos.\r\n\r\n    Por el grado de jurisdicciÃ³n\r\n\r\n    Sentencia en primera instancia: la que devienen de los Ã³rganos de primera instancia, por su competencia y jurisdicciÃ³n.\r\n    Sentencia en apelaciÃ³n: cuando se recurre, bien sea al mismo Ã³rgano o al inmediatamente superior (Audiencia Provincial).\r\n    Sentencia en casaciÃ³n: es aquella que se emite por el Tribunal Supremo pretendiendo casar la causa.\r\n\r\n    Por la forma:\r\n\r\n    Sentencia escrita: la que se redacta por escrito y de esa manera se da a conocer a las partes.\r\n    Sentencia oral: la que se expone oralmente ante las partes involucradas, quienes quedan notificadas en ese mismo acto. ',13,'0'),(27,'Requisitos','La sentencia debe reunir los requisitos de tiempo, lugar y forma. Debe dictarse en un periodo de tiempo apto para la realizaciÃ³n de los actos del juez o tribunal. La fijaciÃ³n de este plazo varÃ­a segÃºn el procedimiento de que se trate.\r\n\r\nRespecto de la forma, las sentencias generalmente se componen de tres secciones:\r\n\r\n    Encabezamiento o parte expositiva: en el que se seÃ±ala la fecha y ciudad en que se dicta, las partes intervinientes, sus procuradores y abogados, sin que se puedan omitir sus nombres sin afectar a la debida integridad y publicidad de las sentencias. Se hacen constar tambiÃ©n las peticiones presentadas por las partes, junto a los presupuestos o antecedentes de hecho en que se fundan.\r\n\r\n    Parte considerativa: en la que se expresan los fundamentos de hecho y de derecho, que contienen los argumentos de las partes y los que utiliza el tribunal para resolver el objeto del proceso, en relaciÃ³n con las normas que se consideran aplicables al caso.\r\n\r\n    Parte resolutiva: en la que se contiene la decisiÃ³n o fallo de condena o absoluciÃ³n del demandado o acusado. Asimismo, suele incorporarse el nombre del juez que la ha redactado y la firma de todos los que han concurrido a su acuerdo.\r\n\r\nPor otro lado, las sentencias deben ser congruentes, es decir, deben resolver acerca de todas las cuestiones que hayan sido objeto de debate en el proceso. El fallo no debe contener mÃ¡s, ni algo distinto, de lo pedido por las partes. Cuando se trata de sentencias penales, la congruencia significa que debe mediar una relaciÃ³n entre la sentencia y la acciÃ³n penal ejercitada. Por ejemplo, si una persona es acusada de homicidio, el juez no puede condenarle por robo (para ello harÃ­a falta aplicar otro procedimiento), ya que estÃ¡ limitado por los hechos alegados. Sin embargo, podrÃ­a realizar una calificaciÃ³n jurÃ­dica diversa de la hecha por las partes, por ejemplo, en el mismo caso, condenar por asesinato o parricidio y no por homicidio.\r\n\r\nPuede clasificarse la incongruencia en la sentencia por: 1) Falta de exhaustividad, omitiÃ©ndose el pronunciamiento sobre un tema debido. 2) Incongruencia ultrapetitum, concediÃ©ndose mÃ¡s de lo pretendido por el actor. 3) Incongruencia extrapetitum, concediÃ©ndose otra cosa y no lo pedido.\r\n\r\nLos elementos de la estructura de una sentencia son preÃ¡mbulo, resultando, considerando y puntos resolutivos. En las sentencias espaÃ±olas su estructura es encabezamiento (nombre de las partes y sus datos, identificaciÃ³n de procurador y abogado, objeto del juicio, fecha, lugar y tribunal, jueces o magistrados, asÃ­ como el ponente si es tribunal colegiado), antecedentes de hecho (en pÃ¡rrafos separados y numerados, exponiÃ©ndose las peticiones de las partes, los hechos en que las funden y las pruebas que se hubieran propuesto y practicado -hechos probados-), fundamentos de derecho (en pÃ¡rrafos separados y numerados, donde se apreciarÃ¡ el derecho que funda las pretensiones, con cita de las leyes o doctrina aplicables) y, finalmente, el fallo (que es la parte dispositiva, donde se resuelve el pleito).',13,'0'),(28,'AviÃ³n de caza','Un aviÃ³n de caza (tambiÃ©n llamado aviÃ³n de combate), o simplemente caza,1â es una aeronave militar diseÃ±ada fundamentalmente para la guerra aÃ©rea con otras aeronaves, en oposiciÃ³n a los bombarderos, que estÃ¡n diseÃ±ados principalmente para atacar objetivos terrestres mediante el lanzamiento de bombas. Los cazas son pequeÃ±os, veloces y de gran maniobrabilidad. Muchos cazas poseen capacidades secundarias de ataque a tierra, y algunos son de doble propÃ³sito para actuar como cazabombarderos; tÃ©rmino tambiÃ©n usado para nombrar a los aviones de ataque a tierra con capacidades de caza.\r\n\r\nLos cazas son el principal medio con el que las fuerzas armadas consiguen la superioridad aÃ©rea sobre sus oponentes en batalla. Por lo menos desde la Segunda Guerra Mundial, lograr y mantener la superioridad aÃ©rea viene siendo un componente clave a la hora de conseguir la victoria en la guerra, particularmente en una guerra convencional entre ejÃ©rcitos regulares (no asÃ­ en una guerra de guerrillas).2â De este modo, la adquisiciÃ³n, el entrenamiento y el mantenimiento de una flota de cazas representa una parte muy sustancial de los presupuestos de defensa para las fuerzas armadas actuales.3â',14,'0'),(29,'Definicion y objetivos de un Sistema operativos','Los sistemas operativos estan estrictamente vinculados con el hardware de los Equipos de computo, mANTENIENDO UNA RELACIÃ?N EN SU EVALUACIÃ?N DIRECTAMENTE RELACIONADO CON LA EVOLUCIÃ?N DE ESTOS EQUIPOS.\r\nProporcionar ya sea una interfaz de lÃ­nea de comando o una interfaz grÃ¡fica al usuario, para que este Ãºltimo se pueda comunicar con la computadora.\r\n-Interfaz de lÃ­nea de comando: Se introducen palabras y sÃ­mbolos desde el teclado de la computadora, ejemplo, el MS-DOS.\r\n\r\n-Interfaz grÃ¡fica del Usuario (GUI): Se seleccionan las acciones mediante el uso de un Mouse para pulsar sobre figuras llamadas iconos o seleccionar opciones de los menÃºs.\r\n\r\nAdministrar los dispositivos de hardware en la computadora. El Sistema Operativo sirve de intermediario entre los programas y el hardware.\r\nAdministrar y mantener los sistemas de archivo de disco. Los SO agrupan la informaciÃ³n dentro de compartimientos lÃ³gicos para almacenarlos en el disco. Estos grupos de informaciÃ³n son llamados archivos. Los archivos pueden contener instrucciones de programas o informaciÃ³n creada por el usuario. El SO mantiene una lista de los archivos en un disco, y nos proporciona las herramientas necesarias para organizar y manipular estos archivos.\r\nApoyar a otros programas. Por ejemplo, listar los archivos, grabarlos en el disco, eliminar archivos, revisar espacio disponible, etc.\r\n1. Objetivos para la creaciÃ³n de los Sistemas Operativos.\r\n\r\nEl objetivo fundamental de los sistemas de computaciÃ³n es ejecutar los programas de los usuarios y facilitar la resoluciÃ³n de sus problemas. El hardware se construye con este fin, pero como este no es fÃ¡cil de utilizar, se desarrollan programas de aplicaciÃ³n que requieren ciertas operaciones comunes.\r\n\r\nOtros objetivos son:\r\n\r\nTransformar el complejo hardware de una computadora a una mÃ¡quina accesible al usuario.\r\nLograr el mejor uso posible de los recursos.\r\nHacer eficiente el uso del recurso.\r\n2. Funciones de los Sistemas Operativos.\r\n\r\nAceptar todos los trabajos y conservarlos hasta su finalizaciÃ³n.\r\nInterpretaciÃ³n de comandos: Interpreta los comandos que permiten al usuario comunicarse con el ordenador.\r\nControl de recursos: Coordina y manipula el hardware de la computadora, como la memoria, las impresoras, las unidades de disco, el teclado o el Mouse.\r\nManejo de errores: Gestiona los errores de hardware y la pÃ©rdida de datos.\r\nSecuencia de tareas: El sistema operativo debe administrar la manera en que se reparten los procesos. Definir el orden. (Quien va primero y quien despuÃ©s).\r\nProtecciÃ³n: Evitar que las acciones de un usuario afecten el trabajo que esta realizando otro usuario.\r\nMulti acceso: Un usuario se puede conectar a otra mÃ¡quina sin tener que estar cerca de ella.\r\nContabilidad de recursos: establece el costo que se le cobra a un usuario por utilizar determinados recursos.\r\n3, CaracterÃ­sticas de los Sistemas Operativos.\r\n\r\nEn general, se puede decir que un Sistema Operativo tiene las siguientes caracterÃ­sticas:\r\n\r\nConveniencia. Un Sistema Operativo hace mÃ¡s conveniente el uso de una computadora.\r\n\r\nEficiencia. Un Sistema Operativo permite que los recursos de la computadora se usen de la manera mÃ¡s eficiente posible.',15,'0'),(30,'Memoria Virual','En informÃ¡tica, la memoria virtual es una tÃ©cnica de gestiÃ³n de la memoria que permite que el sistema operativo disponga, tanto para el software de usuario como para sÃ­ mismo, de mayor cantidad de memoria que estÃ© disponible fÃ­sicamente. La mayorÃ­a de los ordenadores tienen cuatro tipos de memoria: registros en la CPU, la memoria cachÃ© (tanto dentro como fuera del CPU), la memoria RAM y el disco duro. En ese orden, van de menor capacidad y mayor velocidad a mayor capacidad y menor velocidad.\r\n\r\nMuchas aplicaciones requieren acceso a mÃ¡s informaciÃ³n (cÃ³digo y datos) que la que se puede mantener en memoria fÃ­sica. Esto es asÃ­ sobre todo cuando el sistema operativo permite mÃºltiples procesos y aplicaciones ejecutÃ¡ndose simultÃ¡neamente. Una soluciÃ³n al problema de necesitar mayor cantidad de memoria de la que se posee consiste en que las aplicaciones mantengan parte de su informaciÃ³n en disco, moviÃ©ndola a la memoria principal cuando sea necesario. Hay varias formas de hacer esto.\r\n\r\nUna opciÃ³n es que la aplicaciÃ³n misma sea responsable de decidir quÃ© informaciÃ³n serÃ¡ guardada en cada sitio (segmentaciÃ³n), y de traerla y llevarla. La desventaja de esto, ademÃ¡s de la dificultad en el diseÃ±o e implementaciÃ³n del programa, es que es muy probable que los intereses sobre la memoria de dos o varios programas generen conflictos entre sÃ­: cada programador podrÃ­a realizar su diseÃ±o teniendo en cuenta que es el Ãºnico programa ejecutÃ¡ndose en el sistema. La alternativa es usar memoria virtual, donde la combinaciÃ³n entre hardware especial y el sistema operativo hace uso de la memoria principal y la secundaria para hacer parecer que el ordenador tiene mucha mÃ¡s memoria principal (RAM) que la que realmente posee. Este mÃ©todo es invisible a los procesos. La cantidad de memoria mÃ¡xima que se puede hacer ver que hay tiene que ver con las caracterÃ­sticas del procesador. Por ejemplo, en un sistema de 32 bits, el mÃ¡ximo es 232, lo que da 4096 Megabytes (4 Gigabytes). Todo esto hace el trabajo del programador de aplicaciones mucho mÃ¡s fÃ¡cil, al poder ignorar completamente la necesidad de mover datos entre los distintos espacios de memoria.\r\n',15,'0'),(31,'rELATIVE PRONOUNS',' Relative pronouns introduce relative clauses. The most common relative pronouns are who, whom, whose, which, that. The relative pronoun we use depends on what we are referring to and the type of relative clause.\r\n\r\nwho\r\n\r\npeople and sometimes pet animals\r\n\r\ndefining and non-defining\r\n\r\nwhich\r\n\r\nanimals and things\r\n\r\ndefining and non-defining; clause referring to a whole sentence\r\n\r\nthat\r\n\r\npeople, animals and things; informal\r\n\r\ndefining only\r\n\r\nwhose\r\n\r\npossessive meaning;\r\n\r\nfor people and animals usually; sometimes for things in formal situations\r\n\r\ndefining and non-defining\r\n\r\nwhom\r\n\r\npeople in formal styles or in writing; often with a preposition; rarely in conversation; used instead of who if who is the object\r\n\r\ndefining and non-defining\r\n\r\nno relative pronoun\r\n\r\nwhen the relative pronoun defines the object of the clause\r\n\r\ndefining only\r\n\r\nthe relative pronoun is in brackets to show where it is not essential; the person or thing being referred to is underlined.)\r\n\r\nWe donâ??t know the person who donated this money.\r\n\r\nWe drove past my old school, which is celebrating its 100th anniversary this year.\r\n\r\nHe went to the school (that) my father went to.',16,'0'),(32,'Quantifiers','In English grammar, a quantifier is a word (or phrase) which indicates the number or amount being referred to. It generally comes before the noun (or noun phrase). The chart below shows which type of noun goes with which quantifier.\r\n\r\nHowever, note that some of the examples in the chart can take on several different roles within a sentence. For example, â??anyâ?? can be used as a quantifier, a pronoun or an adverb:\r\n\r\nany as a quantifier: Have you got any tomatoes?\r\nany as a pronoun: I donâ??t want any of you making a noise.\r\nany as an adverb: Canâ??t this car go any faster?\r\nIn these notes, we are only considering these words/phrases as quantifiers.\r\n\r\nQuantifier  (Singular nouns (C))  (Plural nouns (C)) (Uncountable nouns (U))\r\nall	                      â??	                                       Yes	                   Yes\r\nany	                 No, but see note.	               Yes	                    Yes\r\nboth	                                                        * Yes	*\r\nfew/a few/fewer	   â??	                                  Yes                            	â??\r\nlittle/a little/less	â??	                               â??	                          Yes\r\nlots of / a lot of	â??	                                  Yes	                         Yes\r\nmany	                    â??	                                  Yes	                           â??\r\nmore	                    â??	                                  Yes	                           Yes\r\nno	                       Yes	                              Yes	                           Yes\r\nseveral	                 â??	                                   Yes	                         â??\r\nsome	                     â??	                                    Yes	                       Yes',16,'0'),(33,'Alfabetos','QUE ES UN ALFABETO?\r\nBueno, un alfabeto es un conjunto finito de letras o simbolos, y se representan con la letra griega sigma mayuscula y entre llaves los simbolos serados por comas.\r\n\r\nEjemplos:\r\nSigma mayuscula{0,1} es el alfabeto binario.\r\nSigma mayuscula{0,1,2,...,9} es el alfabeto de los numeros base diez.\r\nSigma mayuscula{a,b,c,...,x,y,z} es el alfabeto latino.',17,'0'),(34,'Palabras','QUE SON LAS PALABRAS?\r\nUna palabra o cadena es una secuencia finita de los simbolos seleccionados de  algun alfabeto. Suelen representarse con las letras \"x\", \"y\" o \"z\".\r\n\r\nLa longitud de una cadena es igual a la cantidad de simbolos que la componen\r\nEjemplos\r\n\r\nx=101110, es una cadena del alfabeto binario aunque tambien puede pertenecer al alfabeto de los numeros base 10. Por esto debemos especificar el alfabeto\r\n\r\ny=hola, es una cadena del alfabeto latino.',17,'0'),(35,'Lenguaje Universal','Â¿Que es un lenguaje universal?\r\nUn lenguaje universal o universo del discurso es el conjunto de todas las cadenas que se pueden formar con los sÃ­mbolos de un alfabeto. Son conjuntos infinitos sin importar la cantidad de sÃ­mbolos del alfabeto.\r\nSe denotan con W(alfabeto).\r\n\r\nEjemplo\r\nSupongamos el lenguaje de alfabeto de la letra a.\r\nW(Sigma mayuscula{a})={a,aa,aaa,aaaa,aaaaa,...}',17,'0'),(36,'Concatenacion de cadenas','Concatenacion\r\nLa operacion de concatenacion la podemos ver como la union de dos cadenas es simplemente tomar las cadenas y rescribirlas una junto a la otra.\r\n\r\nEjemplo sea x,y,z cadenas pertenecientes al lenguaje binario\r\nx=1001, y=0001, z=101  \r\nxy=10010001                   x(yz)=10010001101\r\nyx=00011001                   (xy)z=10010001101\r\ncon estos ejemplos observamos que la concatenacion es una operacion cerrada pues el resultado de la operacion pertenece al mismo lenguaje, NO es conmutativa pues xy!=yx pero si es asociativa pues (xy)z=x(yz). ',17,'0'),(37,'Introduccion','La loÌgica es una forma sistemaÌtica de pensar que nos permite deducir nueva informacioÌn desde la informacioÌn que ya conocemos.\r\n\r\nRecuerda que la loÌgica es un proceso de deducir la informacioÌn correctamente, no soÌlo deducir la informacioÌn correcta.',18,'0'),(38,'Proposiciones','Â¿QueÌ son?\r\n\r\nSon proposiciones las frases que pueden adquirir un valor de verdadero o falso.\r\n\r\nDefinicion mas formal: Es una oracioÌn aseverativa de la que tiene sentido decir que es verdadera o falsa.\r\n\r\nCon frase nos referimos a:\r\n\r\n            Secuencia finita de signos con significado y sentido de ser calificado \r\n            como verdadero o falso. (es decir una simple expresioÌn matemaÌtica).\r\n\r\n            ExpresioÌn linguÌiÌstica susceptible de ser calificada de verdadera o falsa. \r\n            (es decir una frase aseverativa).',18,'0'),(39,'Sentencias Abiertas','Pueden llegar a confundirse con las proposiciones, por lo que hay que definirlas correctamente.Sera mas facil de entender con un ejemplo.\r\n\r\np(x): x es un numero par.\r\n\r\nPuesto que la validez de p(x) depende de que numero sea x, p(x)no es no totalmente cierta ni totalmente falsa, por lo tanto no es una proposicion.\r\n\r\nUna oracion como esta, cuya verdad depende del valor de una o mas variables, se llama sentencias abierta.',18,'0'),(40,'Propiedades de una Proposicion','Tautologia: Cuando para todos los valores posibles de un conjunto de proposiciones siempre serÃ¡ verdadero el conjunto.\r\n\r\nContradiccion: Cuando para todos los valores posibles de un conjunto de proposiciones esta serÃ¡ siempre falso.\r\n\r\nContingencia: Una proposicion âcomunâ son basicamente todas las que no son ni tautologias ni contradicciones.',18,'0'),(41,'Palabras necesarias de conocer','Proposicion: Enunciado que encierra un valor de verdad.\r\n\r\nAxioma: Principio tan claro y evidente que no necesita demostracion.\r\n\r\nCorolario: Proposicion demostrado que provoca una afirmacion.\r\n\r\nDemostracion: Razonamiento por el cual se da prueba de la exactitud de una proposicion.\r\n\r\nLema: Proposicion que es necesaria demostrar antes de establecer un teorema.\r\n',18,'0'),(42,'Conectores logicos','p^q, conjuncion de p con q. Compuerta AND.\r\npvq, disyuncion de p con q. Compuerta OR.\r\nÂ¬p, negaciond e p. Compuerta NOT.\r\n\r\np->q, p implica q. Si p, entonces q\r\np<->q, equivalencia de p con q. p ssi q. Bicondicional',18,'0'),(44,'Definición','Es el estudio de las facultades mentales a través del uso de modelos computacionales. Este término data de mediados del siglo XX y se debe a un grupo de científicos que estudiaron al cerebro humano como modelo natural, integrando la cibernética y las computadoras (McCarthy, Minsky, Newell, Simon). Marvin Minsky dijo que la inteligencia artificial es “el estudio de como programar computadoras que posean la facultad de hacer aquello que la mente humana puede realizar”, en otras palabras, la inteligencia artificial es la ciencia que busca diseñar y construir máquinas capaces de implementar tareas propias de un humano dotado de inteligencia. Otra definición reconocida es la de Hayes que considero que la inteligencia artificial es la implementación de razonamientos inteligentes mediante técnicas propias de la computación. \r\n\r\nLa meta fundamental de esta disciplina no es simplemente imitar a la inteligencia o lograr un simulador ingenioso. No, la inteligencia artificial solo desea el artículo verdadero que tenga mente. \r\n',19,'apoyo44.mp4'),(45,'Historia de la inteligencia artificial','Para el tiempo en que varios autores irrevocablemente juntaron la ciencia moderna con los mitos, los fundamentos filosóficos del trabajo moderno en el ámbito de la inteligencia artificial se han desarrollado desde hace varios años atrás. A pesar de los problemas de la cultura y la moral, levantados por la inteligencia artificial, estos dos son muy interesantes e importantes. \r\nPara Aristóteles, en Física, definió su “filosofía de naturaleza” como el “estudio de las cosas a tender a cambiar”. Él distinguió entre la materia y la forma de las cosas: una escultura está hecha por el material bronce y tiene una forma de humano. El cambio ocurre cuando el bronce es moldeado con una nueva forma. La distinción de materia y forma hace una base filosófica para las nociones modernas como una computación simbólica y abstracción de datos. \r\nAún más relevante para la inteligencia artificial fue la epistemológica de Aristóteles o el análisis como los humanos “conocen” su mundo, discutido en su Lógica.\r\n',19,'apoyo45.mp4'),(46,'1)	Actuar como humano, comúnmente llamado el enfoque de la prueba de Turing','En el estudio de la IA el éxito se puede dividir en dos grandes ramas, la primera consiste en la fidelidad en que la maquina tiene la capacidad de asemejar su conducta a la humana y la segunda toma como prioridad el concepto ideal de inteligencia que se refiere a la racionalidad.  \r\n\r\nMediante la prueba de Turing, propuesta por Alan Turing (1950), se intenta ofrecer una satisfactoria definición operativa de lo que es inteligencia. Turing definió como una conducta inteligente a la capacidad de logar eficiencia de nivel humano en el desarrollo de actividades de tipo cognoscitivo, suficiente para engañar a un evaluador. Para realizar la prueba se necesitan dos personas y la máquina que se desea evaluar. Una de las personas actúa como entrevistador y se encuentra en una habitación, separado de la computadora y la otra persona. El entrevistador hace las preguntas tanto a la persona como a la maquina mecanografiando las cuestiones y recibe las respuestas de la misma manera. El entrevistador solo los conoce por A y B, y debe intentar determinar quién es la persona y quien la máquina.\r\n\r\nEl esfuerzo que requiere programar una máquina que supere la prueba es extenso, debido a que la computadora debe ser capaz de lo siguiente:\r\n•	Procesar un lenguaje natural, para establecer comunicación eficiente en cualquier idioma humano;\r\n•	Representar el conocimiento, para así guardar toda la información que se le haya dado antes y durante el interrogatorio;\r\n•	Razonar automáticamente, con el fin de poder utilizar la información guardada al responder una pregunta y obtener nuevas conclusiones;\r\n•	Autoaprendizaje de la máquina, para que se adapte a nuevas circunstancias y para detectar y extrapolar esquemas determinados.\r\n\r\nEn el estudio de la IA no se han hecho muchos esfuerzos por para pasar la prueba de Turing, debido a que la utilidad de actuar como humano se presenta básicamente cuando la IA debe interactuar con gente.\r\nUna aplicación sumamente utilizada de la prueba de Turing son las CAPTCHAS Completely Automated Public Turing test to tell Computers and Humans Apart (prueba de Turing completamente automática y pública para diferenciar ordenadores de humanos). Esta podría verse como una prueba de Turing inversa debido a que es una maquina la que esta cargo de la prueba.\r\n\r\n',19,'0'),(47,'Niveles de Aprendizaje en la Inteligencia Artificial ','Un sistema que aprende es aquel sistema organizado que transforma un mensaje de entrada en una salida, el método de transformación se debe ajustar con el fin de mejorar el funcionamiento.\r\nExisten 4 niveles de aprendizaje en la inteligencia Artificial:\r\n•	Aprendizaje por memorización \r\n•	Aprendizaje estadístico \r\n•	Aprendizaje simbólico\r\n•	Aprendizaje Inductivo\r\nA continuación se explicara más a fondo en qué consisten:\r\n\r\nAprendizaje por memorización.\r\nEn estos sistemas todas las situaciones ya han sido memorizadas incluyendo la reacción a cada una de estas situaciones. Estos sistemas no tienen estados internos solo actúan “estimulo- respuesta”.\r\n\r\nAprendizaje Estadístico.\r\nEn estos sistemas se toma una decisión estadística con base en varios casos anteriores. \r\n\r\nAprendizaje Simbólico.\r\nEste nivel de aprendizaje está basado en el aprendizaje a través de ejemplos proporcionados por un llamado “profesor”. Se basa en que si a la computadora se le da un objeto correctamente procesado simbólicamente esta será capaz de distinguir las características que distinguen a otro objeto parecido.\r\n\r\n\r\n',19,'0'),(48,'Indices primarios','Índices\r\nUn archivo de índices consta de registros (denominados entradas de índice) de la forma:\r\nClave de Búsqueda	Puntero\r\n\r\nLos archivos de índices generalmente son más pequeños que el archivo original.\r\n\r\nÍndices Primarios:\r\nSe construye sobre el campo clave de  ordenamiento de un archivo ordenado de registros.\r\nCaracterísticas: \r\n•	Archivo ordenado con registros de largo fijo.\r\n•	El número de entradas en el índice es igual al número de bloques de disco.\r\n•	índice no denso\r\n•	ocupa menos bloques que el área de datos.\r\nÍndices\r\nUn archivo de índices consta de registros (denominados entradas de índice) de la forma:\r\n\r\nLos archivos de índices generalmente son más pequeños que el archivo original.\r\nÍndices Primarios:\r\nSe construye sobre el campo clave de  ordenamiento de un archivo ordenado de registros.\r\nCaracterísticas: \r\n•	Archivo ordenado con registros de largo fijo.\r\n•	El número de entradas en el índice es igual al número de bloques de disco.\r\n•	índice no denso\r\n•	ocupa menos bloques que el área de datos.\r\n',20,'0'),(49,'Índice de Agrupamiento','Es un  índice que está definido sobre el campo de ordenación de un fichero ordenado siendo  este un campo no clave.\r\nSGBD:\r\n•	Oracle \r\n•	sql server\r\n•	DB2\r\nCaracterísticas:\r\n•	Una entrada por cada valor distinto del campo de agrupamiento.\r\n•	el campo de ordenamiento presenta repeticiones.\r\n•	El puntero apunta al primer bloque que contiene un registro con dicho valor.\r\n•	sobre un fichero ordenado por un campo no clave sólo puede definirse un índice de agrupamiento.\r\n',20,'0'),(50,' Índice secundario:','\r\nSe quieren encontrar todos los registros cuyos valores en un cierto campo (que no es la clave de búsqueda del índice primario) cumplen alguna condición.\r\n\r\nSe puede tener un índice secundario con un registro del índice por cada valor de la clave de búsqueda; el registro del índice apunta a un cajón que contiene punteros a todos los registros actuales.\r\nSGBD:\r\n•	Oracle \r\n•	sql server\r\n•	Informix\r\n•	DB2\r\nCaracterísticas:\r\n•	Si el campo de indexación es clave, entonces se dice que el índice es denso.\r\n•	pueden  definirse  varios  índices secundarios sobre un mismo fichero.\r\n',20,'0'),(51,'Índices multinivel:','Permiten  reducir  la  parte  del  índice  que  se  requiere  acceder  en  un  valor equivalente al factor de bloqueo del índice.\r\nSGBD:\r\n•	sql server\r\n•	informix\r\n•	PostGresd\r\n•	DB2\r\nCaracterísticas:\r\n•	Primer nivel: fichero ordenado con entradas de tamaño fijo y un valor distinto del campo de indexación en cada una.\r\n•	Siguientes niveles: índices primarios sobre el nivel anterior.\r\n•	Reducen el número de accesos a bloque al hacer búsquedas, pero son ficheros ordenados.\r\n•	La principal desventaja es su naturaleza estática.\r\n',20,'0');
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
INSERT INTO `usuario` VALUES (1,'humberto@gmail.com','beto','Humberto','P','1','no'),(2,'pepe@pepe.com','pepe','pepe','P','2','no'),(3,'beto@gmail.com','beto','beto alumno','A','3','no'),(4,'mau@hotmail.com','123','mau','A','mau@hotmail.comfoto','no'),(5,'rafassassinscreed@gmail.com','1234','Rafael Hernandez Ruiz','P','rafassassinscreed@gmail.comfoto','no'),(6,'lokilloxbox.10@gmail.com','1234','Belem Ruiz','A','lokilloxbox.10@gmail.comfoto','no'),(7,'arturorivas61@yahoo.com','1234','Arturo Rivas Rojas','P','arturorivas61@yahoo.comfoto','no'),(8,'humbertodl2709@gmail.com','beto','Humberto Dominguez','P','humbertodl2709@gmail.comfoto','no');
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
INSERT INTO `usuariocurso` VALUES (13,3),(15,3),(16,3),(17,3),(19,3),(13,4),(15,6),(16,6);
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

-- Dump completed on 2018-06-04 19:06:11
