-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2024 at 12:16 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `seminarski`
--

-- --------------------------------------------------------

--
-- Table structure for table `projekatstavke`
--

CREATE TABLE `projekatstavke` (
  `id` int(11) NOT NULL,
  `projekatID` int(11) DEFAULT NULL,
  `zaposleniID` int(11) DEFAULT NULL,
  `zadatakID` int(11) DEFAULT NULL,
  `obrisan` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projekatstavke`
--

INSERT INTO `projekatstavke` (`id`, `projekatID`, `zaposleniID`, `zadatakID`, `obrisan`) VALUES
(1, 1, 2, 6, 0),
(2, 1, 3, 1, 0),
(3, 2, 3, 1, 0),
(6, 4, 1, 1, 0),
(7, 5, 1, 1, 0),
(8, 3, 1, 6, 0),
(9, 4, 2, 7, 1),
(10, 2, 2, 4, 0),
(11, 6, 1, 9, 0),
(13, 6, 1, 5, 0),
(14, 7, 2, 1, 0),
(15, 7, 1, 5, 0),
(17, 14, 3, 6, 0),
(18, 13, 1, 1, 0),
(19, 16, 2, 5, 0),
(20, 17, 2, 5, 0),
(21, 16, 11, 11, 0),
(22, 20, 12, 5, 0);

-- --------------------------------------------------------

--
-- Table structure for table `projekti`
--

CREATE TABLE `projekti` (
  `id` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `vodjaID` int(11) DEFAULT 0,
  `obrisan` tinyint(1) DEFAULT 0,
  `aktivan` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projekti`
--

INSERT INTO `projekti` (`id`, `naziv`, `vodjaID`, `obrisan`, `aktivan`) VALUES
(1, 'P4', 3, 0, 1),
(2, 'P2', 1, 0, 1),
(3, 'P3', 3, 0, 0),
(4, 'P5', 1, 0, 0),
(5, 'P1', 2, 0, 1),
(6, 'P8', 2, 1, 1),
(7, 'P9', 3, 0, 1),
(13, 'P10', 2, 0, 1),
(14, 'P11', 10, 0, 1),
(15, 'P12', 1, 0, 1),
(16, 'P13', 3, 0, 1),
(17, 'P14', 3, 1, 1),
(18, 'P15', 3, 1, 1),
(19, 'P16', 2, 1, 1),
(20, 'P20', 12, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `zadaci`
--

CREATE TABLE `zadaci` (
  `id` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` varchar(255) DEFAULT NULL,
  `obrisan` tinyint(1) UNSIGNED ZEROFILL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `zadaci`
--

INSERT INTO `zadaci` (`id`, `naziv`, `opis`, `obrisan`) VALUES
(1, 'Z1', 'Opis1', 0),
(4, 'Z2', 'Opis2', 1),
(5, 'Z3', 'Opis3', 0),
(6, 'Z4', 'Opis4', 0),
(7, 'Z5', 'Opis5', 0),
(8, 'Z6', 'Opis6', 1),
(9, 'Z7', 'Opis7', 0),
(10, 'Z8', 'Opis8', 1),
(11, 'Z9', 'Opis9', 0);

-- --------------------------------------------------------

--
-- Table structure for table `zaposleni`
--

CREATE TABLE `zaposleni` (
  `id` int(11) NOT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `tip` int(11) DEFAULT NULL,
  `obrisan` tinyint(1) UNSIGNED ZEROFILL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `zaposleni`
--

INSERT INTO `zaposleni` (`id`, `ime`, `prezime`, `username`, `tip`, `obrisan`) VALUES
(1, 'Sofija', 'Opacic', 'sofija', 1, 0),
(2, 'Danilo', 'Djurdjic', 'danilo', 1, 0),
(3, 'Ana', 'Antic', 'ana', 0, 0),
(9, 'Marko', 'Markovic', 'marko', 1, 1),
(10, 'Milena', 'Petrovic', 'milena', 1, 0),
(11, 'Sara', 'Jankovic', 'sara', 1, 0),
(12, 'Katarina', 'Prokic', 'katarina', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `projekatstavke`
--
ALTER TABLE `projekatstavke`
  ADD PRIMARY KEY (`id`),
  ADD KEY `projekatID` (`projekatID`),
  ADD KEY `zaposleniID` (`zaposleniID`),
  ADD KEY `zadatakID` (`zadatakID`);

--
-- Indexes for table `projekti`
--
ALTER TABLE `projekti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `vodjaID` (`vodjaID`);

--
-- Indexes for table `zadaci`
--
ALTER TABLE `zadaci`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `NAZIV` (`naziv`);

--
-- Indexes for table `zaposleni`
--
ALTER TABLE `zaposleni`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UniqueUsername` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `projekatstavke`
--
ALTER TABLE `projekatstavke`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `projekti`
--
ALTER TABLE `projekti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `zadaci`
--
ALTER TABLE `zadaci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `zaposleni`
--
ALTER TABLE `zaposleni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `projekatstavke`
--
ALTER TABLE `projekatstavke`
  ADD CONSTRAINT `projekatID` FOREIGN KEY (`projekatID`) REFERENCES `projekti` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `zadatakID` FOREIGN KEY (`zadatakID`) REFERENCES `zadaci` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `zaposleniID` FOREIGN KEY (`zaposleniID`) REFERENCES `zaposleni` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `projekti`
--
ALTER TABLE `projekti`
  ADD CONSTRAINT `vodjaID` FOREIGN KEY (`vodjaID`) REFERENCES `zaposleni` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
