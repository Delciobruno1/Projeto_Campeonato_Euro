selecaoDROP TABLE IF EXISTS `cidade`;
CREATE TABLE IF NOT EXISTS `cidade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `pais_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pais_id` (`pais_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cidade`
--

INSERT INTO `cidade` (`id`, `nome`, `pais_id`) VALUES
(1, 'Lisboa', 1),
(2, 'Madrid', 2),
(3, 'Paris', 3),
(4, 'Berlim', 4),
(5, 'Roma', 5),
(6, 'Londres', 6),
(7, 'Amsterdã', 7),
(8, 'Bruxelas', 8),
(9, 'Zurique', 9),
(10, 'Estocolmo', 10),
(11, 'Copenhague', 11),
(12, 'Oslo', 12),
(13, 'Helsinque', 13),
(14, 'Viena', 14),
(15, 'Zagreb', 15),
(16, 'Atenas', 16),
(17, 'Varsóvia', 17),
(18, 'Budapeste', 18),
(19, 'Edimburgo', 19),
(20, 'Istambul', 20);


DROP TABLE IF EXISTS `estadio`;
CREATE TABLE IF NOT EXISTS `estadio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `capacidade` int DEFAULT NULL,
  `cidade_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cidade_id` (`cidade_id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `estadio`
--

INSERT INTO `estadio` (`id`, `nome`, `capacidade`, `cidade_id`) VALUES
(1, 'Estádio da Luz', NULL, 1),
(2, 'Santiago Bernabéu', NULL, 2),
(3, 'Parc des Princes', NULL, 3),
(4, 'Olympiastadion', NULL, 4),
(5, 'Estádio Olímpico', NULL, 5),
(6, 'Wembley', NULL, 6),
(7, 'Johan Cruijff Arena', NULL, 7),
(8, 'King Baudouin Stadium', NULL, 8),
(9, 'St. Jakob-Park', NULL, 9),
(10, 'Friends Arena', NULL, 10),
(11, 'Parken Stadium', NULL, 11),
(12, 'Ullevaal Stadion', NULL, 12),
(13, 'Olympic Stadium Helsinki', NULL, 13),
(14, 'Ernst Happel Stadion', NULL, 14),
(15, 'Maksimir Stadium', NULL, 15),
(16, 'Olympic Stadium Athens', NULL, 16),
(17, 'PGE Narodowy', NULL, 17),
(18, 'Puskás Aréna', NULL, 18),
(19, 'Hampden Park', NULL, 19),
(20, 'Atatürk Olympic Stadium', NULL, 20);


DROP TABLE IF EXISTS `jogador`;
CREATE TABLE IF NOT EXISTS `jogador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `posicao` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `selecao_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `selecao_id` (`selecao_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `jogador`
--

INSERT INTO `jogador` (`id`, `nome`, `data_nascimento`, `posicao`, `selecao_id`) VALUES
(1, 'Cristiano Ronaldo', '1985-02-05', 'Atacante', 1),
(2, 'Sergio Ramos', '1986-03-30', 'Defensor', 2),
(3, 'Kylian Mbappe', '1998-12-20', 'Atacante', 3),
(4, 'Toni Kroos', '1990-01-04', 'Meio-Campo', 4),
(5, 'Gianluigi Donnarumma', '1999-02-25', 'Goleiro', 5),
(6, 'Harry Kane', '1993-07-28', 'Atacante', 6),
(7, 'Virgil van Dijk', '1991-07-08', 'Defensor', 7),
(8, 'Eden Hazard', '1991-01-07', 'Meio-Campo', 8),
(9, 'Xherdan Shaqiri', '1991-10-10', 'Meio-Campo', 9),
(10, 'Zlatan Ibrahimovic', '1981-10-03', 'Atacante', 10),
(11, 'Christian Eriksen', '1992-02-14', 'Meio-Campo', 11),
(12, 'Erling Haaland', '2000-07-21', 'Atacante', 12),
(13, 'Teemu Pukki', '1990-03-29', 'Atacante', 13),
(14, 'David Alaba', '1992-06-24', 'Defensor', 14),
(15, 'Luka Modric', '1985-09-09', 'Meio-Campo', 15),
(16, 'Kostas Manolas', '1991-06-14', 'Defensor', 16),
(17, 'Robert Lewandowski', '1988-08-21', 'Atacante', 17),
(18, 'Dominik Szoboszlai', '2000-10-25', 'Meio-Campo', 18),
(19, 'Andrew Robertson', '1994-03-11', 'Defensor', 19),
(20, 'Hakan Çalhanoğlu', '1994-02-08', 'Meio-Campo', 20);


DROP TABLE IF EXISTS `partida`;
CREATE TABLE IF NOT EXISTS `partida` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `estadio_id` int DEFAULT NULL,
  `selecao_casa_id` int DEFAULT NULL,
  `selecao_fora_id` int DEFAULT NULL,
  `gols_casa` int DEFAULT NULL,
  `gols_fora` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `estadio_id` (`estadio_id`),
  KEY `selecao_casa_id` (`selecao_casa_id`),
  KEY `selecao_fora_id` (`selecao_fora_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `partida`
--

INSERT INTO `partida` (`id`, `data`, `estadio_id`, `selecao_casa_id`, `selecao_fora_id`, `gols_casa`, `gols_fora`) VALUES
(1, '2024-06-01', 1, 1, 2, 2, 1),
(2, '2024-06-02', 2, 3, 4, 1, 1),
(3, '2024-06-03', 3, 5, 6, 0, 0),
(4, '2024-06-04', 4, 7, 8, 3, 2),
(5, '2024-06-05', 5, 9, 10, 2, 3),
(6, '2024-06-06', 6, 11, 12, 1, 1),
(7, '2024-06-07', 7, 13, 14, 0, 2),
(8, '2024-06-08', 8, 15, 16, 1, 0),
(9, '2024-06-09', 9, 17, 18, 4, 1),
(10, '2024-06-10', 10, 19, 20, 2, 2),
(11, '2024-07-11', 8, 1, 2, 3, 3),
(12, '2024-07-11', 9, 7, 8, 4, 4),
(13, '2024-07-11', 1, 1, 2, 4, 1);



DROP TABLE IF EXISTS `grupo_selecao`;
CREATE TABLE IF NOT EXISTS `grupo_selecao` (
  `id` int NOT NULL AUTO_INCREMENT,
  `grupo_id` int DEFAULT NULL,
  `selecao_id` int DEFAULT NULL,
  `jogos` int DEFAULT '0',
  `vitorias` int DEFAULT '0',
  `empates` int DEFAULT '0',
  `derrotas` int DEFAULT '0',
  `pontos` int DEFAULT '0',
  `gols_marcados` int DEFAULT '0',
  `gols_sofridos` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `grupo_id` (`grupo_id`),
  KEY `selecao_id` (`selecao_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `grupo_selecao`
--

INSERT INTO `grupo_selecao` (`id`, `grupo_id`, `selecao_id`, `jogos`, `vitorias`, `empates`, `derrotas`, `pontos`, `gols_marcados`, `gols_sofridos`) VALUES
(1, 1, 1, 2, 1, 1, 0, 4, 7, 4),
(2, 1, 2, 2, 0, 1, 1, 1, 4, 7),
(3, 2, 3, 0, 0, 0, 0, 0, 0, 0),
(4, 2, 4, 0, 0, 0, 0, 0, 0, 0),
(5, 3, 5, 0, 0, 0, 0, 0, 0, 0),
(6, 3, 6, 0, 0, 0, 0, 0, 0, 0),
(7, 4, 7, 1, 0, 1, 0, 1, 4, 4),
(8, 4, 8, 1, 0, 1, 0, 1, 4, 4),
(9, 5, 9, 0, 0, 0, 0, 0, 0, 0),
(10, 5, 10, 0, 0, 0, 0, 0, 0, 0),
(11, 6, 11, 0, 0, 0, 0, 0, 0, 0),
(12, 6, 12, 0, 0, 0, 0, 0, 0, 0),
(13, 7, 13, 0, 0, 0, 0, 0, 0, 0),
(14, 7, 14, 0, 0, 0, 0, 0, 0, 0),
(15, 8, 15, 0, 0, 0, 0, 0, 0, 0),
(16, 8, 16, 0, 0, 0, 0, 0, 0, 0),
(17, 9, 17, 0, 0, 0, 0, 0, 0, 0),
(18, 9, 18, 0, 0, 0, 0, 0, 0, 0),
(19, 10, 19, 0, 0, 0, 0, 0, 0, 0),
(20, 10, 20, 0, 0, 0, 0, 0, 0, 0);


DROP TABLE IF EXISTS `estatisticas_individuais`;
CREATE TABLE IF NOT EXISTS `estatisticas_individuais` (
  `estatistica_id` int NOT NULL AUTO_INCREMENT,
  `jogador_id` int NOT NULL,
  `golos` int DEFAULT '0',
  `remates` int DEFAULT '0',
  `fora_jogos` int DEFAULT '0',
  `faltas` int DEFAULT '0',
  `assistencias` int DEFAULT '0',
  `passes` int DEFAULT '0',
  `partida_id` int DEFAULT NULL,
  PRIMARY KEY (`estatistica_id`),
  KEY `FK_estatisticas_individuais_jogador` (`jogador_id`),
  KEY `FK_estatisticas_individuais_partida` (`partida_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `estatisticas_individuais`
--

INSERT INTO `estatisticas_individuais` (`estatistica_id`, `jogador_id`, `golos`, `remates`, `fora_jogos`, `faltas`, `assistencias`, `passes`, `partida_id`) VALUES
(1, 1, 1, 5, 1, 2, 1, 20, 1),
(2, 2, 0, 2, 1, 3, 0, 15, 1),
(3, 3, 1, 4, 1, 1, 0, 25, 2),
(4, 4, 0, 1, 1, 0, 1, 30, 2),
(5, 5, 0, 0, 1, 0, 0, 35, 3),
(6, 6, 0, 3, 1, 2, 0, 20, 3),
(7, 7, 1, 4, 1, 0, 1, 25, 4),
(8, 8, 1, 4, 1, 2, 1, 30, 4),
(9, 9, 2, 5, 1, 1, 0, 20, 5),
(10, 10, 1, 6, 1, 2, 0, 25, 5),
(11, 11, 0, 2, 1, 0, 1, 30, 6),
(12, 12, 1, 5, 1, 2, 0, 20, 6),
(13, 13, 0, 3, 1, 1, 1, 25, 7),
(14, 14, 1, 4, 1, 0, 1, 30, 7),
(15, 15, 0, 1, 1, 2, 0, 20, 8),
(16, 16, 1, 2, 1, 1, 0, 25, 8),
(17, 17, 3, 6, 1, 0, 2, 30, 9),
(18, 18, 0, 2, 1, 1, 1, 20, 9),
(19, 19, 0, 1, 1, 0, 0, 25, 10),
(20, 20, 1, 4, 1, 2, 1, 30, 10),
(21, 1, 1, 1, 0, 0, 1, 29, 0),
(22, 2, 0, 2, 1, 1, 1, 11, 0),
(23, 7, 0, 3, 1, 1, 0, 27, 0),
(24, 8, 1, 4, 1, 1, 1, 18, 0),
(25, 1, 1, 1, 1, 1, 1, 5, 0),
(26, 2, 0, 2, 0, 1, 1, 8, 0);
