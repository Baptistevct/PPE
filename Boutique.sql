-- phpMyAdmin SQL Dump
-- version 5.2.1deb1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 07 mai 2025 à 06:14
-- Version du serveur : 10.11.11-MariaDB-0+deb12u1
-- Version de PHP : 8.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `Boutique`
--

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

CREATE TABLE `categories` (
  `idCategories` int(11) NOT NULL,
  `type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`idCategories`, `type`) VALUES
(1, 'Jeans'),
(2, 'Chaussure');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `idUtilisateur` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mdp` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`idUtilisateur`, `nom`, `prenom`, `email`, `mdp`) VALUES
(1, 'admin', 'admin', 'admin@gmail.com', '$2y$10$QUrNhvyBHTjJsdTgkBS2tuQ07mxWf.3PR0pNwzj8H7iwAwodTJn0K'),
(3, 'test', 'test', 'testest@gmail.com', '$2y$10$VY3YysBBUajydwHMzz3vJezSmxex2YhdqLt0PS8Rme5.h0qV4yICC'),
(4, 'testnom', 'testprenom', 'testmail@gmail.com', '$2y$10$in7qyxcyzxVO3HjbgJMiZ.oGwW8C2dUJurmL9tu5ivgZ8/nYBgr.G');

-- --------------------------------------------------------

--
-- Structure de la table `vetements`
--

CREATE TABLE `vetements` (
  `idVetement` int(11) NOT NULL,
  `typeVetement` varchar(100) DEFAULT NULL,
  `modele` varchar(100) DEFAULT NULL,
  `taille` varchar(100) DEFAULT NULL,
  `prix` double DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL,
  `idCategories` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `vetements`
--

INSERT INTO `vetements` (`idVetement`, `typeVetement`, `modele`, `taille`, `prix`, `idUtilisateur`, `idCategories`) VALUES
(1, 'Jean', 'Slim Noir Complices', 'L', 15, NULL, 1),
(3, 'chaussure', 'Nike', '43', 45, NULL, 2),
(4, 'Jean', 'Slim Noir Complices', 'M', 35, NULL, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`idCategories`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idUtilisateur`);

--
-- Index pour la table `vetements`
--
ALTER TABLE `vetements`
  ADD PRIMARY KEY (`idVetement`),
  ADD UNIQUE KEY `vetements_UN` (`taille`,`modele`),
  ADD KEY `vetements_FK` (`idUtilisateur`),
  ADD KEY `vetements_FK_1` (`idCategories`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categories`
--
ALTER TABLE `categories`
  MODIFY `idCategories` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `vetements`
--
ALTER TABLE `vetements`
  MODIFY `idVetement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `vetements`
--
ALTER TABLE `vetements`
  ADD CONSTRAINT `vetements_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `users` (`idUtilisateur`),
  ADD CONSTRAINT `vetements_FK_1` FOREIGN KEY (`idCategories`) REFERENCES `categories` (`idCategories`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
