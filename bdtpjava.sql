-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 15 jan. 2026 à 10:56
-- Version du serveur : 9.4.0
-- Version de PHP : 8.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdtpjava`
--

DROP DATABASE IF EXISTS `bdtpjava`;
CREATE DATABASE `bdtpjava` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `bdtpjava`;

-- --------------------------------------------------------

--
-- Structure de la table `programmeur`
--

DROP TABLE IF EXISTS `programmeur`;
CREATE TABLE `programmeur` (
  `id` int NOT NULL,
  `NOM` varchar(25) DEFAULT NULL,
  `PRENOM` varchar(25) DEFAULT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `PSEUDO` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `ANNAISSANCE` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `hobby` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `responsable` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `SALAIRE` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `PRIME` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `programmeur`
--

INSERT INTO `programmeur` (`id`, `NOM`, `PRENOM`, `ADRESSE`, `PSEUDO`, `ANNAISSANCE`, `hobby`, `responsable`, `SALAIRE`, `PRIME`) VALUES
(2, 'Simpson', 'Bart', NULL, 'bartdev', '1994', 'prog', 'prof', '277', '13'),
(3, 'Lagaffe', 'Gaston', NULL, 'gastondev', '1964', 'prog', 'prof', '278', '14'),
(4, 'Mafalda', 'Querida', NULL, 'mafaldadev', '1977', 'prog', 'prof', '279', '17'),
(5, 'a', 'a', NULL, 'az', '1000', 'foo', 'bar', '20', '20');

-- --------------------------------------------------------

--
-- Structure de la table `programmeur_projet`
--

DROP TABLE IF EXISTS `programmeur_projet`;
CREATE TABLE `programmeur_projet` (
  `idProgrammeur` int NOT NULL,
  `idProjet` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

DROP TABLE IF EXISTS `projet`;
CREATE TABLE `projet` (
  `id` int NOT NULL,
  `intitule` varchar(45) DEFAULT NULL,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `etat` varchar(50) DEFAULT 'PAS_DEMARE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`id`, `intitule`, `dateDebut`, `dateFin`, `etat`) VALUES
(1, 'Java', '2025-12-12', '2025-12-12', 'PAS_DEMARE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `programmeur`
--
ALTER TABLE `programmeur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `programmeur_projet`
--
ALTER TABLE `programmeur_projet`
  ADD PRIMARY KEY (`idProgrammeur`,`idProjet`),
  ADD KEY `idProjet` (`idProjet`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `programmeur`
--
ALTER TABLE `programmeur`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `programmeur_projet`
--
ALTER TABLE `programmeur_projet`
  ADD CONSTRAINT `programmeur_projet_ibfk_2` FOREIGN KEY (`idProjet`) REFERENCES `projet` (`id`),
  ADD CONSTRAINT `programmeur_projet_ibfk_3` FOREIGN KEY (`idProgrammeur`) REFERENCES `programmeur` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
