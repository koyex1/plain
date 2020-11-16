-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 16, 2020 at 11:54 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `plain`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `fullName` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `taskId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `fullName`, `address`, `email`, `phone`, `status`, `taskId`) VALUES
(1, 'olu', '$2a$10$7DtR1OK6Sfm9VbctBfxkGuPFpLqDHvftUI2ayybMJ9a5cdMLm7ZM.', 'Olumide Koyenikan', 'No 2, Housing Estate Iguosa Benin City', 'olumide.koyenikan@gmail.com', '123', 1, 1),
(10, 'KoyeSupplier', '$2a$10$DzMg1Xc9EJgcxJspS8ES8OunJgcyr/.0vxLqgXAqMQBw7M4t8jZJ2', 'KoyeSupplier', 'No 2, ikeja, Lagos', 'koye@yahoo.com', '123', 1, 9),
(11, 'OluBuyer', '$2a$10$ExxzuTxu/taEbyDt.uKIcuHmctJ0PM8l.e.EhyvljgRfc2ynbLyT.', 'OluBuyer', 'No 2, ikeja, Lagos', 'olu@yahoo.com', '123', 1, 2),
(12, 'KoyeSupplier', '$2a$10$7jcbfeHWuAJTRYO7Ns3rBebnxuCS5HBRIPfKCSJUgfd8A.vqFAOee', '', '', '', '', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `accountId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `cartline`
--

CREATE TABLE `cartline` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `quantity` int(11) NOT NULL,
  `inStock` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `accountId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `quantity`) VALUES
(11, 'Phones', 2),
(14, 'Vehicles', 4),
(15, 'Televisions', 1),
(16, 'Books', 1),
(17, 'Electronics', 0);

-- --------------------------------------------------------

--
-- Table structure for table `orderline`
--

CREATE TABLE `orderline` (
  `id` int(11) NOT NULL,
  `fullName` varchar(250) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orderline`
--

INSERT INTO `orderline` (`id`, `fullName`, `Address`, `name`, `quantity`, `price`, `totalPrice`, `supplierId`) VALUES
(14, 'OluBuyer', 'No 2, ikeja, Lagos', 'Mercedes e350', 3, 200000, 600000, 10),
(15, 'OluBuyer', 'No 2, ikeja, Lagos', 'iphone11', 10, 240000, 2400000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `accountId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `quantity`, `categoryId`, `accountId`) VALUES
(19, 'iphone11', 'It weighs about 194 grams (6.84 ounces).It features a 6.1-inch all-screen LCD display and is powered by Apple\'s new A13 bionic chip with Third-Generation Neural Engine. The display is the company\'s proprietary Liquid Retina HD display having Multi-Touch capability with IPS technology.', 240000, 15, 11, 10),
(20, 'Samsung s10', 'Samsung Galaxy S10 smartphone runs on Android v9. 0 (Pie) operating system. The phone is powered by Octa core (2.73 GHz, Dual core, M4 Mongoose + 2.31 GHz, Dual core, Cortex A75 + 1.95 GHz, Quad core, Cortex A55) processor. It runs on the Samsung Exynos 9 Octa 9820 Chipset. It has 8 GB RAM and 128 GB internal storage.', 240000, 20, 11, 10),
(21, 'RangeRover', '. Full-size SUV. Layout. Front-engine, four-wheel-drive. The Land Rover Range Rover is both a full-sized luxury sport utility vehicle (SUV) (generally known simply as a Range Rover) produced by Land Rover, a marque of Jaguar Land Rover, and its own sub-brand.', 19000000, 2, 14, 10),
(22, 'Gwagon', 'Mercedes-Benz G-Class, sometimes called G-Wagen (short for Gel?ndewagen, \"terrain vehicle\"), is a mid-size four-wheel drive luxury SUV manufactured by Magna Steyr (formerly Steyr-Daimler-Puch) in Austria and sold by Mercedes-Benz. ... The G-Wagen is characterised by its boxy styling and body-on-frame construction.', 10000000, 1, 14, 10),
(23, 'Lexus 2020', 'Image result for lexus 2020 description\r\nThe 2020 Lexus RX 350 ranks in the top half of the luxury midsize SUV class. It has an abundance of standard safety tech, a pillowy ride, and a potent V6 engine, but most rivals have more cargo room.', 100000, 4, 14, 10),
(24, 'Mercedes e350', 'Mercedes-Benz E-class sedan is getting a more powerful base engine for 2020, and with it, the designation for the four-cylinder model changes from E300 to E350. The updated turbocharged 2.0-liter inline-four makes 255 horsepower and 273 lb-ft of torque, 14 hp more than before and the same amount of torque.', 200000, 1, 14, 10),
(25, 'Samsung SmartTV', 'A smart TV device is either a television set with integrated Internet capabilities or a set-top box for television that offers more advanced computing ability and connectivity than a contemporary basic television set.', 200000, 2, 15, 10),
(26, 'Sanctum', 'In this thrilling and creepy digital original story set in the world of ', 2000, 14, 16, 10),
(27, 'jsoniphone11', 'It weighs about 194 grams (6.84 ounces).It features a 6.1-inch all-screen LCD display and is powered by Apple\'s new A13 bionic chip with Third-Generation Neural Engine. The display is the company\'s proprietary Liquid Retina HD display having Multi-Touch capability with IPS technology.', 240000, 15, 11, 10);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'buyer'),
(9, 'supplier');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `taskId` (`taskId`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `accountId` (`accountId`);

--
-- Indexes for table `cartline`
--
ALTER TABLE `cartline`
  ADD PRIMARY KEY (`id`),
  ADD KEY `accountId` (`accountId`),
  ADD KEY `productId` (`productId`),
  ADD KEY `supplierId` (`supplierId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orderline`
--
ALTER TABLE `orderline`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `accountId` (`accountId`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `cartline`
--
ALTER TABLE `cartline`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `orderline`
--
ALTER TABLE `orderline`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK8btb47qksripso67hqqtmd5hh` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`),
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`);

--
-- Constraints for table `cartline`
--
ALTER TABLE `cartline`
  ADD CONSTRAINT `cartline_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `cartline_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `cartline_ibfk_3` FOREIGN KEY (`supplierId`) REFERENCES `account` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
