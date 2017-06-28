-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2017 at 12:58 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sma`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `studentId` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `replied` int(11) NOT NULL DEFAULT '0',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT 'Status is 0 for pending, 1 for confirmed, 3 for declined ',
  `appointment_date` varchar(30) NOT NULL,
  `date_created` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`id`, `studentId`, `doctor_id`, `description`, `replied`, `status`, `appointment_date`, `date_created`) VALUES
(1, 1, 16, 'Hello sir, these days i get constant headache, i need to meet you and you give me some advise,  thanks', 1, 1, '6-25-2017  17:15', '1498211672'),
(7, 2, 9, 'This is the first doctor message', 1, 1, '6-28-2017  15:59', '1498224701'),
(8, 1, 22, 'Message to Doctor Andrew', 1, 0, '7-29-2018 ', '1498224738'),
(9, 2, 23, 'This is Makerer University inquiry about the examination deadline', 1, 0, '6-25-2017  15:38', '1498225369'),
(10, 1, 9, 'am so sick', 1, 1, '8-28-2017  5:40', '1498225523'),
(11, 1, 21, 'am so sick', 0, 0, '', '1498225713'),
(12, 1, 20, 'yeah man', 1, 0, '6-29-2017  9:10', '1498494430'),
(13, 4, 9, 'I have stomach ache', 0, 0, '', '1498553884'),
(14, 4, 9, 'I have stomach ache', 0, 0, '', '1498553886'),
(15, 4, 20, 'Hello boss, i need your assistance', 0, 0, '', '1498553915'),
(16, 4, 16, 'ok ok', 0, 0, '', '1498554346'),
(17, 4, 9, 'wen should I meet u', 1, 0, '6-28-2017  12:22', '1498554356'),
(18, 4, 9, 'are you ready', 0, 0, '', '1498554418'),
(19, 7, 9, 'i have a problem with my head\n\n', 0, 0, '', '1498567263');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_reply`
--

CREATE TABLE `appointment_reply` (
  `id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `reply` text NOT NULL,
  `date_created` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment_reply`
--

INSERT INTO `appointment_reply` (`id`, `appointment_id`, `doctor_id`, `reply`, `date_created`) VALUES
(1, 1, 1, 'This is the reply to the appointment with the Id: 1', '1498242319'),
(2, 1, 1, 'Nomis wilson reply', '1498418329'),
(3, 1, 1, 'Nomis wilson reply and its true', '1498418412'),
(4, 1, 1, 'Nomis wilson reply and its true and after midnight', '1498425269'),
(5, 1, 1, 'Nomis wilson reply and its true and after midnight', '1498425312'),
(6, 1, 1, 'hello nomisType reply here', '1498428470'),
(7, 9, 1, 'i like to see how it works', '1498428719'),
(8, 9, 1, 'i like to see how it works fdgggggggggggggggggggg', '1498429181'),
(9, 9, 1, 'bhhhhib Type reply here', '1498429670'),
(10, 1, 1, 'klType reply here', '1498429852'),
(11, 1, 1, ' vbbType reply here', '1498429949'),
(12, 1, 1, ' vbbType reply here', '1498429994'),
(13, 1, 1, 'Type reply here dh bbd bnfdb', '1498430134'),
(14, 9, 1, 'Type reply hereb   nbb', '1498430227'),
(15, 9, 1, 'Type reply here nbhjb hj', '1498430369'),
(16, 9, 1, 'Type reply here bbd', '1498430585'),
(17, 9, 1, 'Type reply here bbd', '1498430633'),
(18, 9, 1, 'Type reply here bbd', '1498430661'),
(19, 9, 1, 'Type reply here bbd hgghhgd', '1498430684'),
(20, 9, 1, 'Type reply here hbjhf jhvs b v jh', '1498430771'),
(21, 8, 1, 'Type reply here this is to andrew', '1498431002'),
(22, 1, 1, 'Type reply here', '1498431135'),
(23, 8, 1, 'Type reply here', '1498431192'),
(24, 8, 1, 'Type reply here', '1498431241'),
(25, 8, 1, 'Type reply here', '1498458807'),
(26, 7, 1, 'Am Trying out this', '1498459380'),
(27, 7, 1, 'Am Trying out this', '1498459432'),
(28, 7, 1, 'Am Trying out this', '1498459461'),
(29, 1, 1, 'hello', '1498460373'),
(30, 1, 1, 'Type reply here hh', '1498460750'),
(31, 1, 1, 'Type reply here hello', '1498460925'),
(32, 1, 1, 'Nomis wilson reply and its true and after midnight', '1498461058'),
(33, 1, 1, 'Type reply here', '1498461114'),
(34, 1, 1, 'vghgf Type reply here', '1498461419'),
(35, 1, 1, 'vghgf Type reply here', '1498461833'),
(36, 1, 1, 'vghgf Type reply here', '1498461861'),
(37, 1, 1, 'Type reply here ok', '1498462048'),
(38, 1, 1, 'Type reply here', '1498462157'),
(39, 1, 1, 'Type reply here ok boss', '1498462318'),
(40, 1, 1, 'Type reply here ok ok', '1498463965'),
(41, 10, 9, 'Hello replying', '1498481049'),
(42, 12, 20, 'I will respond to you l', '1498494665'),
(43, 17, 9, 'monday', '1498555410');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Service_id` varchar(50) DEFAULT NULL,
  `PhotoUrl` text NOT NULL,
  `loginname` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`Id`, `UserName`, `Password`, `Service_id`, `PhotoUrl`, `loginname`) VALUES
(34, 'kk', 'k', '2', '1498554196.jpg', 'kk');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '1',
  `date_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'This is stored as atimestamp',
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`id`, `name`, `status`, `date_created`, `description`) VALUES
(1, 'Surgery', '1', '0000-00-00 00:00:00', 'surgery description'),
(2, 'Mental Illness', '1', '0000-00-00 00:00:00', 'Mental illness description'),
(3, 'Maternity services', '1', '0000-00-00 00:00:00', 'Maternity service description'),
(4, 'caunselling', '1', '0000-00-00 00:00:00', 'Canselling services description'),
(5, 'Advisory', '1', '0000-00-00 00:00:00', 'Advisoryservices description'),
(6, 'Lungs', '1', '0000-00-00 00:00:00', 'Lungs infesctious description'),
(7, 'Dental ', '1', '2017-06-28 12:46:10', '');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Id` int(11) NOT NULL,
  `password` varchar(50) NOT NULL,
  `StudentNo` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `residence` varchar(29) NOT NULL,
  `regNo` varchar(50) NOT NULL,
  `course` text NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `photo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Id`, `password`, `StudentNo`, `name`, `Gender`, `residence`, `regNo`, `course`, `tel`, `email`, `photo`) VALUES
(1, 'qwerty', '12345', 'Student Student', 'male', 'kampala', '11/u/23662/ps', 'Software Eng', '0788899615', '', '1496232484.jpg'),
(2, 'qwerty', '1234567', 'Andrew', 'Andrew', 'Makindye', '23454/drt', 'It', '0704480638', 'kk@gmail.com', '1496231914.png'),
(3, 'qerty', '2222', 'Bobi Wine', 'Male', 'Kamwokya', '11/u/2344/Ps', 'Software Engineering', '', '', ''),
(6, '123', '214140659', 'bosco', '---Select ', 'Kawempe', '12/u/8343/ps', 'csc', '', '', '1498556654.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id` int(11) NOT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Level` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id`, `UserName`, `Password`, `Level`) VALUES
(5, 'admin', 'admin', 'admin'),
(6, 'frank', 'frank', 'admi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `appointment_reply`
--
ALTER TABLE `appointment_reply`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `appointment_reply`
--
ALTER TABLE `appointment_reply`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
