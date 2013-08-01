-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 01, 2013 at 03:46 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `gamedayapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `adm_pk` int(11) NOT NULL AUTO_INCREMENT,
  `adm_id` varchar(15) NOT NULL,
  `adm_pw` varchar(15) NOT NULL,
  `adm_fname` varchar(15) NOT NULL,
  `adm_lname` varchar(15) NOT NULL,
  `adm_email` varchar(40) NOT NULL,
  `is_del` int(1) NOT NULL,
  PRIMARY KEY (`adm_pk`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adm_pk`, `adm_id`, `adm_pw`, `adm_fname`, `adm_lname`, `adm_email`, `is_del`) VALUES
(1, 'admin', '25e4ee4e9229397', 'Project', 'OMGameday', 'sbyrd@fncinc.com', 0),
(2, 'sbyrd', '25e4ee4e9229397', 'Sandy', 'Byrd', 'sbyrd@fncinc.com', 0),
(3, 'drobinson', '25e4ee4e9229397', 'Caleb', 'Robinson', 'drobinson@fncinc.com', 0),
(4, 'jwagner', '25e4ee4e9229397', 'Josh', 'Wagner', 'jwagner@fncinc.com', 0),
(5, 'cselvati', '25e4ee4e9229397', 'Camila', 'Selvati', 'cselvati@fncinc.com', 0),
(6, 'bbabcock', '25e4ee4e9229397', 'Bret', 'Babcock', 'bbabcock@fncinc.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `a_pk` int(11) NOT NULL AUTO_INCREMENT,
  `q_pk` int(11) DEFAULT NULL,
  `a_text` varchar(1000) DEFAULT NULL,
  `answer_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`a_pk`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=81 ;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`a_pk`, `q_pk`, `a_text`, `answer_count`) VALUES
(1, 1, 'Caleb', 104),
(2, 1, 'Camilla', 28),
(3, 1, 'Sandy', 7),
(4, 1, 'Bret', 8),
(5, 1, 'Josh', 22),
(6, 2, 'Intern', 128),
(7, 2, 'Burger Flipper Extraordinaire', 141),
(8, 4, 'This', 1),
(9, 4, 'Code', 3),
(10, 4, 'Out', 2),
(11, 5, 'Cause I deal in facts', 1),
(12, 5, 'Is it an exclamation?', 0),
(13, 5, 'Number 2 is a question', 1),
(14, 6, 'TGIF', 7),
(15, 6, 'Dia do Dragão', 7),
(16, 6, 'idk', 3),
(17, 6, 'whatever', 9),
(18, 7, 'Nothing', 2),
(19, 7, 'Muc', 0),
(20, 7, 'Dude', 2),
(21, 8, 'a. Project "Everest"', 1),
(22, 8, 'b.  Project "Fueled Neural Charmers"', 2),
(23, 8, 'c. Project "Green Initiative"', 4),
(24, 8, 'd. Project "Ole Miss GameDay"', 5),
(25, 9, 'a. 1', 5),
(26, 9, 'b. 5', 5),
(27, 9, 'c. 22', 10),
(28, 9, 'd. Let me finish this free meal, then we will hash out the details', 19),
(29, 10, 'Answer', 0),
(30, 10, 'Answer', 0),
(31, 10, 'Answer', 0),
(32, 11, 'uestion', 0),
(33, 11, 'asdasd', 0),
(34, 12, 'This', 1),
(35, 12, 'App', 1),
(36, 12, 'Is', 0),
(37, 12, 'Sweet', 0),
(38, 13, 'Ours', 3),
(39, 13, 'Theirs', 1),
(40, 14, 'Enter answer choice', 0),
(41, 15, 'yes', 2),
(42, 15, 'no', 0),
(43, 15, 'maybe', 1),
(44, 15, 'Enter answer choice', 0),
(45, 16, '!', 2),
(46, 16, '!', 0),
(47, 16, 'Enter answer choice', 1),
(48, 17, 'Enter ansdfswer choice', 0),
(49, 17, 'sdfsdf', 0),
(50, 18, 'Enter answadsasder choice', 0),
(51, 18, 'asdasdasd', 0),
(52, 19, 'Enterasd', 0),
(53, 19, 'Enter answer choice', 0),
(54, 20, 'Enter answ123er choice', 1),
(55, 20, 'Enter answer choice', 3),
(56, 21, 'asd', 0),
(57, 21, 'asd', 0),
(58, 21, 'asd', 0),
(59, 21, 'Enter answerasd choice', 0),
(60, 21, 'asdsdadsadas', 0),
(61, 23, 'Test', 1),
(62, 23, 'Ing', 0),
(63, 23, 'This', 0),
(64, 24, 'New answer', 1),
(65, 24, 'Dude', 1),
(66, 25, 'Enter answer chogice', 1),
(67, 25, 'g', 0),
(68, 26, 'Quite', 12),
(69, 26, 'ZOMGYES', 6),
(70, 14, 'Enter answer choice', 0),
(71, 14, 'Enter answer choice', 0),
(72, 14, 'Enter answer choice', 0),
(73, 14, 'Enter answer choice', 0),
(74, 14, 'Enter answer choice', 0),
(75, 14, 'Enter answer choice', 0),
(76, 14, 'Enter answer choice', 0),
(77, 14, 'Enter answer choice', 0),
(78, 14, 'Enter answer choice', 0),
(79, 40, 'Yes', 10),
(80, 40, 'Most definitely', 14);

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE IF NOT EXISTS `questions` (
  `q_pk` int(11) NOT NULL AUTO_INCREMENT,
  `q_text` varchar(1000) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`q_pk`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`q_pk`, `q_text`, `is_del`) VALUES
(1, 'What is your name', 0),
(2, 'What is your occupation', 0),
(4, 'Testing', 1),
(5, 'This is not a question', 1),
(6, 'What day is it today?', 1),
(7, 'Wuddup', 1),
(8, 'Who wins Most Valuable Project?', 1),
(9, 'How many interns does it take to change a light bulb?', 1),
(10, 'Question', 1),
(11, 'My q', 1),
(12, 'Calebs Question', 1),
(13, 'Which group is the awesomest', 1),
(14, 'Enter your question here', 1),
(15, 'Is camilla and her phone here', 1),
(16, 'Calebs New Question!', 1),
(17, 'szdfsdfsdfsdf', 1),
(18, 'My Question', 1),
(19, 'asdf', 1),
(20, '123', 1),
(21, 'asd', 1),
(22, 'asd', 1),
(23, 'Caleb Robinson', 1),
(24, 'New Question', 1),
(25, 'ffgsdffsggsdsdgfdsf', 1),
(26, 'Is this application awesome?', 1),
(27, 'Enter your question here', 1),
(28, 'Enter your question here', 1),
(29, 'Enter your question here', 1),
(30, 'Enter your question here', 1),
(31, 'Enter your question here', 1),
(32, 'Enter your question here', 1),
(33, 'Enter your question here', 1),
(34, 'Enter your question here', 1),
(35, 'Enter your question here', 1),
(36, 'Enter your question here', 1),
(37, 'Enter your question here', 1),
(38, 'Enter your question here', 1),
(39, 'Enter your question here', 1),
(40, 'Is this presentation awesome?', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
