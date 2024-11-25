-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 21, 2024 at 07:32 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `budgetbuddy`
--

-- --------------------------------------------------------

--
-- Table structure for table `earning`
--

DROP TABLE IF EXISTS `earning`;
CREATE TABLE IF NOT EXISTS `earning` (
  `earn_amount` double DEFAULT NULL,
  `earn_is_yearly` bit(1) NOT NULL,
  `earnid` int NOT NULL,
  `weekly_hours` double NOT NULL,
  `earn_date` varchar(255) COLLATE utf8mb4_0900_bin DEFAULT NULL,
  PRIMARY KEY (`earnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin;

--
-- Dumping data for table `earning`
--

INSERT INTO `earning` (`earn_amount`, `earn_is_yearly`, `earnid`, `weekly_hours`, `earn_date`) VALUES
(20, b'0', 52, 40, '2024-11-21'),
(80000, b'1', 53, 0, '2024-11-21');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
