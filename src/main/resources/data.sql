-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_gerenciamento
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_criacao` datetime(6) NOT NULL,
  `data_fim` datetime(6) NOT NULL,
  `data_inicio` datetime(6) NOT NULL,
  `descricao` text,
  `nome` varchar(255) NOT NULL,
  `status` enum('ABERTA','CONCLUIDA','EM_ANDAMENTO','PAUSADA') NOT NULL,
  `id_projeto` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn5t7pcego12a1lwjgwd25f1u3` (`id_projeto`),
  CONSTRAINT `FKn5t7pcego12a1lwjgwd25f1u3` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (1,_binary '','2025-03-14 11:18:24.481121','2025-03-18 11:18:13.806000','2025-03-10 11:18:09.657000','Descricao da atividade inicial','Atividade inicial','EM_ANDAMENTO',2),(2,_binary '','2025-03-14 11:20:02.586340','2025-03-17 11:19:45.164000','2025-03-04 11:19:40.503000','Descricao da atividade','Aticidade final','PAUSADA',5),(3,_binary '','2025-03-14 11:21:08.207546','2025-03-28 11:20:49.442000','2025-03-14 11:20:48.078000','Breve descricao da atividade','Realização de relatorios','ABERTA',6),(4,_binary '','2025-03-14 11:22:30.438941','2025-03-27 11:22:18.659000','2025-03-25 11:22:15.250000','Breve descricao da finalizaçao dos relatorios','Finalização de relatorios','CONCLUIDA',2),(5,_binary '','2025-03-14 11:23:37.092001','2025-03-18 11:23:20.431000','2025-03-18 11:23:17.050000','Breve descricao da reuniao','Reunião da equipe','ABERTA',4),(6,_binary '','2025-03-14 11:24:53.191750','2025-02-26 11:24:46.434000','2025-02-23 11:24:41.670000','Breve descricao da atividade','Atualizar relatórios','CONCLUIDA',5),(7,_binary '\0','2025-03-14 13:25:10.475730','2025-03-26 13:24:19.346000','2025-03-19 13:24:16.198000','dres','Atividade teste','ABERTA',3);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamentos_horas`
--

DROP TABLE IF EXISTS `lancamentos_horas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lancamentos_horas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_fim` datetime(6) NOT NULL,
  `data_inicio` datetime(6) NOT NULL,
  `data_registro` datetime(6) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `id_atividade` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfyhsjuqucmw4osr323og9inmi` (`id_atividade`),
  KEY `FK7rut0gbj0ljnnlece152umdtp` (`id_usuario`),
  CONSTRAINT `FK7rut0gbj0ljnnlece152umdtp` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKfyhsjuqucmw4osr323og9inmi` FOREIGN KEY (`id_atividade`) REFERENCES `atividade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos_horas`
--

LOCK TABLES `lancamentos_horas` WRITE;
/*!40000 ALTER TABLE `lancamentos_horas` DISABLE KEYS */;
INSERT INTO `lancamentos_horas` VALUES (1,_binary '','2025-03-14 16:30:00.000000','2025-03-14 10:26:00.000000','2025-03-14 11:27:00.387730','Breve descricao da atividade',3,7),(2,_binary '','2025-03-02 22:34:00.000000','2025-03-02 10:27:00.000000','2025-03-14 11:27:57.351272','Breve descricao dos relatorios',6,6),(3,_binary '','2025-03-02 07:28:00.000000','2025-03-02 05:28:00.000000','2025-03-14 11:28:48.664536','Descricao do lançamento',1,1),(4,_binary '','2025-03-11 00:29:00.000000','2025-03-10 16:29:00.000000','2025-03-14 11:30:01.271539','Descricao do lançamento',1,1),(5,_binary '\0','2025-02-24 09:30:00.000000','2025-02-24 07:30:00.000000','2025-03-14 11:30:57.144204','Breve descricao',5,3),(6,_binary '','2025-03-14 10:05:00.000000','2025-03-14 09:05:00.000000','2025-03-14 12:05:23.158922','drescricao',3,7),(7,_binary '\0','2025-03-20 10:30:00.000000','2025-03-20 10:26:00.000000','2025-03-14 13:26:47.269429','Des',3,3),(8,_binary '\0','2025-03-15 01:58:00.000000','2025-03-15 01:46:00.000000','2025-03-15 01:47:06.705776','descricao teste',3,7),(9,_binary '','2025-03-15 01:47:00.000000','2025-03-15 01:47:00.000000','2025-03-15 01:47:53.935065','desc',3,7);
/*!40000 ALTER TABLE `lancamentos_horas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projeto`
--

DROP TABLE IF EXISTS `projeto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projeto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_criacao` datetime(6) NOT NULL,
  `data_fim` datetime(6) NOT NULL,
  `data_inicio` datetime(6) NOT NULL,
  `descricao` text NOT NULL,
  `nome` varchar(255) NOT NULL,
  `prioridade` enum('ALTA','BAIXA','MEDIA') NOT NULL,
  `status` enum('CANCELADO','CONCLUIDO','EM_ANDAMENTO','PLANEJADO') NOT NULL,
  `id_usuario_responsavel` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4fp7e8ve9e79qy5q4td7mee1x` (`id_usuario_responsavel`),
  CONSTRAINT `FK4fp7e8ve9e79qy5q4td7mee1x` FOREIGN KEY (`id_usuario_responsavel`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projeto`
--

LOCK TABLES `projeto` WRITE;
/*!40000 ALTER TABLE `projeto` DISABLE KEYS */;
INSERT INTO `projeto` VALUES (1,_binary '','2025-03-14 11:10:06.509056','2025-03-30 11:09:40.108000','2025-03-04 11:09:38.472000','Descricao do Projeto Alfa','Projeto Alfa','ALTA','PLANEJADO',5),(2,_binary '','2025-03-14 11:11:28.774453','2025-03-31 11:11:10.129000','2025-03-10 11:11:07.048000','Descricao do projeto Beta','Projeto Beta','MEDIA','EM_ANDAMENTO',4),(3,_binary '','2025-03-14 11:13:20.574784','2025-03-29 11:12:48.355000','2025-03-24 11:12:45.576000','Descricao do projeto Gamma','Projeto Gamma','ALTA','PLANEJADO',6),(4,_binary '\0','2025-03-14 11:14:57.351999','2025-03-12 11:14:21.513000','2025-03-03 11:14:14.427000','Descricao do projeto Delta','Projeto Delta','BAIXA','EM_ANDAMENTO',6),(5,_binary '','2025-03-14 11:16:03.516475','2025-03-18 11:15:42.552000','2025-03-02 11:15:38.143000','Descricao do projeto Epsilon','Projeto Epsilon','BAIXA','CANCELADO',3),(6,_binary '','2025-03-14 11:17:08.932874','2025-03-27 11:16:52.621000','2025-02-23 11:16:48.448000','Descricao do projeto Omega','Projeto Omega','BAIXA','EM_ANDAMENTO',4),(7,_binary '','2025-03-14 13:22:38.930102','2025-03-21 13:21:40.575000','2025-03-14 13:21:39.247000','Deesd','Projeto Teste','ALTA','EM_ANDAMENTO',3);
/*!40000 ALTER TABLE `projeto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `data_criacao` datetime(6) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `perfil` enum('ADMIN','USUARIO') NOT NULL,
  `senha` varchar(255) NOT NULL,
  `ultimo_login` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,_binary '','2025-03-14 10:58:49.803246','roberto@fenix.com','Roberto','ADMIN','$2a$10$/HFSTcpuyJoQ8Bb6Ba/bvOduOg7mUmZr3E95Ty6sSHhWpeC6gmL02','2025-03-15 01:53:03.042758'),(2,_binary '\0','2025-03-14 11:03:52.766838','pedro@fenix.com','Pedro Perez','ADMIN','$2a$10$k/Kcc/zI2o6qyX5TbMiwluEb2PiVebq/BmauIOGhgeTOsKlaNXDkW',NULL),(3,_binary '','2025-03-14 11:04:35.636911','maria@fenix.com','Maria Eduarda','USUARIO','$2a$10$Dw.D8/7GdsL8xpv6iQRIHO5.xe3PDhQg33pr.pwwPQF3Q9.a2S2Im','2025-03-14 23:44:49.803399'),(4,_binary '','2025-03-14 11:05:28.186612','sandra@fenix.com','Sandra Martinez','USUARIO','$2a$10$YgJg6WhIarXedmKIJ1dxauVVk13.nqlrAlYys7QCMSMQRWjNkQ.R2',NULL),(5,_binary '','2025-03-14 11:06:34.076383','gabriel@fenix.com','Gabriel Da Silva','USUARIO','$2a$10$DGWoZDZj10GHzQtGDjchd.ggs8TfgWkyWGvTVmdxDq5YeTXjBwI6S',NULL),(6,_binary '','2025-03-14 11:07:22.096074','jose@fenix.com','Jose Manoel','USUARIO','$2a$10$1DOtHfoEI6LMRi/5aoi4t.55RRuTfuPwvx3W8hWj1bbqxv5W876CK','2025-03-14 11:59:32.607290'),(7,_binary '','2025-03-14 11:08:37.912842','andrea@fenix.com','Andrea','USUARIO','$2a$10$i7B7E0aWkQdpEiO41X.dPOtq24RHO3zvIcVgxHlo0SZQrBG0hn9BO','2025-03-15 00:22:53.424697'),(8,_binary '','2025-03-14 13:20:08.469737','email@mail','Pedro','USUARIO','$2a$10$dJm2iFUWlcvcHzwTsWmmpOVNYjn96ADSWwh/RPtMvaYSFQfHr8LAG',NULL),(9,_binary '','2025-03-14 21:28:11.614252','admin@admin.com','admin','ADMIN','$2a$10$8YzKkVGXmTe3GPHg73HiM.a1wM1Nd3pm0KMDtDx7zlpe34cs3uVYq','2025-03-14 23:40:52.444646');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_atividades`
--

DROP TABLE IF EXISTS `usuarios_atividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_atividades` (
  `id_usuario` bigint NOT NULL,
  `id_atividade` bigint NOT NULL,
  KEY `FK9ujln7saij2hd1r9f2fivuvac` (`id_atividade`),
  KEY `FKlnaupm2uc2givp9b9ub16f6hf` (`id_usuario`),
  CONSTRAINT `FK9ujln7saij2hd1r9f2fivuvac` FOREIGN KEY (`id_atividade`) REFERENCES `atividade` (`id`),
  CONSTRAINT `FKlnaupm2uc2givp9b9ub16f6hf` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_atividades`
--

LOCK TABLES `usuarios_atividades` WRITE;
/*!40000 ALTER TABLE `usuarios_atividades` DISABLE KEYS */;
INSERT INTO `usuarios_atividades` VALUES (7,3),(1,1),(1,4),(2,2),(2,5),(6,2),(6,6),(3,3),(3,5),(3,7),(5,5),(5,7);
/*!40000 ALTER TABLE `usuarios_atividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_projetos`
--

DROP TABLE IF EXISTS `usuarios_projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_projetos` (
  `id_usuario` bigint NOT NULL,
  `id_projeto` bigint NOT NULL,
  KEY `FK32wq2fagnfky5qicb2q1ko0a5` (`id_projeto`),
  KEY `FKl7varytucjf0tkjnk9q7omi9e` (`id_usuario`),
  CONSTRAINT `FK32wq2fagnfky5qicb2q1ko0a5` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`),
  CONSTRAINT `FKl7varytucjf0tkjnk9q7omi9e` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_projetos`
--

LOCK TABLES `usuarios_projetos` WRITE;
/*!40000 ALTER TABLE `usuarios_projetos` DISABLE KEYS */;
INSERT INTO `usuarios_projetos` VALUES (2,1),(2,4),(2,5),(6,5),(7,1),(7,6),(3,3),(3,4),(3,6),(3,7),(1,2),(1,3),(1,7),(5,3),(5,4),(5,7),(8,7);
/*!40000 ALTER TABLE `usuarios_projetos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-15 13:53:50
