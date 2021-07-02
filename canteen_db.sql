-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 02, 2021 at 05:37 PM
-- Server version: 5.7.26
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `canteen_db`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `getDailySales`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getDailySales` ()  BEGIN DECLARE sum_total INT DEFAULT 0; SELECT SUM(orders.total_price) into sum_total FROM `inventory`,`orders` WHERE `inventory`.`item_id` = `orders`.`item_id` AND DATE(`orders`.`order_date`) = CURRENT_DATE; INSERT INTO `sales` (`date`, `total_sales`) VALUES (CURRENT_DATE, sum_total); END$$

DROP PROCEDURE IF EXISTS `get_my_cart`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_my_cart` (IN `uid` INT)  BEGIN  
SELECT `cart`.`cart_id`, `cart`.`item_id`, `cart`.`user_id`, `inventory`.`item_name`, `inventory`.`item_price`, `inventory`.`item_qty` FROM `cart`,`inventory` WHERE `cart`.`item_id`=`inventory`.`item_id` AND `user_id` = uid;
END$$

DROP PROCEDURE IF EXISTS `update_payed_order`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_payed_order` (IN `orderid` INT)  BEGIN  
UPDATE `orders` SET `status`=2 WHERE `order_id` = orderid;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`cart_id`, `item_id`, `user_id`) VALUES
(1, 2, 1),
(2, 2, 1),
(3, 2, 2),
(4, 1, 1),
(5, 1, 2),
(6, 1, 2),
(7, 1, 1),
(8, 3, 1),
(9, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `item_price` double(9,2) DEFAULT NULL,
  `item_qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `inventory`
--

INSERT INTO `inventory` (`item_id`, `item_name`, `item_price`, `item_qty`) VALUES
(3, 'Cack', 25.00, 0),
(4, 'Rice', 100.00, 15);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `order_qty` int(11) DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_price` float DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `item_id`, `order_qty`, `order_date`, `status`, `total_price`, `order_by`) VALUES
(37, 4, 2, '2021-07-02 22:20:07.000000', 2, 200, 1),
(38, 4, 3, '2021-07-02 22:32:15.000000', 2, 300, 1),
(39, 4, 3, '2021-07-02 22:32:47.000000', 1, 300, 1);

--
-- Triggers `orders`
--
DROP TRIGGER IF EXISTS `before_insert_orders`;
DELIMITER $$
CREATE TRIGGER `before_insert_orders` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN  
SET NEW.order_date = now();
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `decrease_inventory`;
DELIMITER $$
CREATE TRIGGER `decrease_inventory` AFTER INSERT ON `orders` FOR EACH ROW BEGIN  

UPDATE `inventory` SET `item_qty`=  `item_qty`- NEW.order_qty  WHERE `item_id`= NEW.item_id; 

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
CREATE TABLE IF NOT EXISTS `sales` (
  `id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `total_sales` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

DROP TABLE IF EXISTS `spring_session`;
CREATE TABLE IF NOT EXISTS `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('268c8f81-e02e-4f21-a1f1-ebe0220008f9', '22a733c5-28c0-453e-99b2-3a452d7158b7', 1625244146366, 1625246600194, 1800, 1625248400194, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

DROP TABLE IF EXISTS `spring_session_attributes`;
CREATE TABLE IF NOT EXISTS `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session_attributes`
--

INSERT INTO `spring_session_attributes` (`SESSION_PRIMARY_ID`, `ATTRIBUTE_NAME`, `ATTRIBUTE_BYTES`) VALUES
('268c8f81-e02e-4f21-a1f1-ebe0220008f9', 'USER_SESSION', 0xaced0005737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a6578700000000577040000000574000131740013527576696e6475204d616468757368616e6b61740012736e61707275753040676d61696c2e636f6d74000a303738343434363633397400047573657278);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
CREATE TABLE IF NOT EXISTS `status` (
  `id` int(10) NOT NULL,
  `description` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`id`, `description`) VALUES
(0, 'Deleted'),
(1, 'Pending'),
(2, 'Payed'),
(3, 'Delivered');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `phone`, `role`) VALUES
(1, 'snapruu0@gmail.com', 'Ruvindu Madhushanka', '0000', '0784446639', 'user'),
(2, 'ruvindumadushanka@gmail.com', 'Kumara', '1', '0784446639', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

DELIMITER $$
--
-- Events
--
DROP EVENT `sales_event`$$
CREATE DEFINER=`root`@`localhost` EVENT `sales_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-03 20:00:00' ON COMPLETION PRESERVE ENABLE DO CALL getDailySales()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
