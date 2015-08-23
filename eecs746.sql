-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2015 at 03:57 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `eecs746`
--

-- --------------------------------------------------------
--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `departmentId` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) NOT NULL,
  `director` varchar(255) NOT NULL,
  PRIMARY KEY (`departmentId`)
);

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`departmentName`, `director`) VALUES
('EECS', 'Dr. James Stiles'),
('Bussiness', 'Dr. Rajendra Sreevatsava'),
('BioSciences', 'Dr. Gavosto Estella'),
('Physics & Astronomy', 'Dr. Wang Hui');

-- --------------------------------------------------------

--
-- Table structure for table `accountrequest`
--

CREATE TABLE `accountrequest` (
  `requestId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` char(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `kuId` varchar(20) DEFAULT NULL,
  `kuOnlineId` varchar(20) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `requestedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `departmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`requestId`),
  KEY `FK_DeptID` (`departmentId`)
);

--
-- Dumping data for table `accountrequest`
--
-- --------------------------------------------------------

-- --------------------------------------------------------
--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` char(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `kuId` varchar(20) DEFAULT NULL,
  `kuOnlineId` varchar(20) DEFAULT NULL,
  `lastName` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `departmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DeptID` (`departmentId`)
);

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `email`, `kuId`, `kuOnlineId`, `lastName`, `firstName`, `role`) VALUES
('admin', 'admin', 'admin@ku.edu', '2813662', 'r764s953', 'Sompalli', 'Ranjith', 'admin') ;
--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseId` int(11) NOT NULL AUTO_INCREMENT,
  `courseNumber` varchar(30) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  `instructor` varchar(255) NOT NULL,
  PRIMARY KEY (`courseId`),
  UNIQUE KEY `courseNumber` (`courseNumber`)
);

--
-- Dumping data for table `course`
--

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `postedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `departmentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`messageId`),
  KEY `departmentId` (`departmentId`)
);

--
-- Dumping data for table `message`
--

--
-- Table structure for table `studentcourse`
--

CREATE TABLE `studentcourse` (
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `semester` varchar(30) NOT NULL,
  `year` int(5) NOT NULL,
  `gpa` double(5,3) DEFAULT NULL,
  KEY `userId` (`userId`),
  KEY `courseId` (`courseId`)
);

--
-- Dumping data for table `studentcourse`
--


-- --------------------------------------------------------
--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`);

--
-- Constraints for table `studentcourse`
--
ALTER TABLE `studentcourse`
  ADD CONSTRAINT `studentcourse_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `studentcourse_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `course` (`courseId`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_DeptID` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
