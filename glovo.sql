-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2023 at 01:37 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `glovo`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `password`) VALUES
('Bilal', 'Bilal'),
('b', 'b');

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

CREATE TABLE `commande` (
  `Id_Commande` varchar(20) NOT NULL,
  `Date_Debut` varchar(20) DEFAULT NULL,
  `Date_Fin` varchar(20) DEFAULT NULL,
  `Km` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `commande`
--

INSERT INTO `commande` (`Id_Commande`, `Date_Debut`, `Date_Fin`, `Km`) VALUES
('1', '10-4-2023', '10-5-2023', '18'),
('2', '10-3-2023', '10-5-2023', '15'),
('3', '9-4-2023', '11-4-2023', '25'),
('4', '9-4-2023', '10-4-2023', '17'),
('5', '19-5-2023', '19-5-2023', '19'),
('6', '26-5-2023', '1-6-2023', '25'),
('7', '27-5-2023', '28-5-2023', '9');

-- --------------------------------------------------------

--
-- Table structure for table `livreur`
--

CREATE TABLE `livreur` (
  `Id_Livreur` varchar(20) NOT NULL,
  `Nom` varchar(30) DEFAULT NULL,
  `Telephone` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `livreur`
--

INSERT INTO `livreur` (`Id_Livreur`, `Nom`, `Telephone`) VALUES
('1', 'Youssef ', '0626589865'),
('2', 'Bilal', '0789562345'),
('3', 'Ahmed', '0678451256'),
('4', 'Omar', '0745892399');

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

CREATE TABLE `produit` (
  `Id_Produit` varchar(20) NOT NULL,
  `Nom` varchar(30) DEFAULT NULL,
  `Prix` varchar(30) DEFAULT NULL,
  `Quantite` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produit`
--

INSERT INTO `produit` (`Id_Produit`, `Nom`, `Prix`, `Quantite`) VALUES
('1', 'Burger', '50', '25'),
('2', 'Salade Italienne', '65', '12'),
('3', 'Filet de Boeuf ', '155', '10'),
('4', 'American Pizza ', '65', '20'),
('5', 'Pizza 4 Saisons', '75', '19'),
('6', 'Soft drink', '17', '25'),
('7', 'Soda ', '17', '26'),
('8', 'Doppio Mocha', '32', '12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`Id_Commande`);

--
-- Indexes for table `livreur`
--
ALTER TABLE `livreur`
  ADD PRIMARY KEY (`Id_Livreur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
