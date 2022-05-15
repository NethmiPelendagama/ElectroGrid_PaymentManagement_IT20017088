-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307:8111
-- Generation Time: May 15, 2022 at 06:29 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `paymentCode` int(11) NOT NULL,
  `paymentName` varchar(50) NOT NULL,
  `paymentAddress` varchar(100) NOT NULL,
  `paymentMeterNo` varchar(15) NOT NULL,
  `paymentAccountNo` varchar(15) NOT NULL,
  `paymentAmount` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`paymentCode`, `paymentName`, `paymentAddress`, `paymentMeterNo`, `paymentAccountNo`, `paymentAmount`) VALUES
(18, 'Nethmi', 'Kalutara', '12546985632', '25698563214', '800.00'),
(19, 'Shanika ', 'Anuradhapura', '78965847856', '53269874512', '250.00'),
(20, 'Devangi', 'Malabe', '789658456321', '896457893210', '256.00'),
(21, 'Hiruni', 'Kuliyapitiya', '89568745631', '78996655745', '400.00'),
(22, 'Chamalka', 'Kalutara', '5698563241', '14598523664', '800.00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`paymentCode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `paymentCode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
