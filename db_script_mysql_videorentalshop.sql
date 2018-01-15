-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15-Jan-2018 às 21:18
-- Versão do servidor: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `video_rental_store`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `address_street` varchar(50) NOT NULL,
  `address_number` int(11) NOT NULL,
  `address_number2` varchar(50) DEFAULT NULL,
  `address_zipcode` int(11) NOT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `address`
--

INSERT INTO `address` (`address_id`, `address_street`, `address_number`, `address_number2`, `address_zipcode`, `city_id`) VALUES
  (1, 'Cieszynska', 6, '6A', 30015, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `basket`
--

CREATE TABLE `basket` (
  `order_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `delivery_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `payment_status` char(1) DEFAULT NULL,
  `data_order` datetime(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `basket`
--

INSERT INTO `basket` (`order_id`, `client_id`, `delivery_id`, `payment_id`, `payment_status`, `data_order`) VALUES
  (97, 1, 1, 1, NULL, NULL),
  (99, 1, 1, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `cities`
--

CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL,
  `city_name` varchar(50) NOT NULL,
  `city_state` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cities`
--

INSERT INTO `cities` (`city_id`, `city_name`, `city_state`) VALUES
  (1, 'Krakow', 'Malopolska');

-- --------------------------------------------------------

--
-- Estrutura da tabela `client`
--

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `client_name` varchar(50) NOT NULL,
  `client_lastname` varchar(50) NOT NULL,
  `address_id` int(11) NOT NULL,
  `client_email` varchar(50) NOT NULL,
  `client_login` varchar(50) NOT NULL,
  `client_password` varchar(50) NOT NULL,
  `client_phone` int(11) NOT NULL,
  `client_data_birth` date DEFAULT NULL,
  `client_sex` char(1) NOT NULL,
  `client_date_registration` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `client`
--

INSERT INTO `client` (`client_id`, `client_name`, `client_lastname`, `address_id`, `client_email`, `client_login`, `client_password`, `client_phone`, `client_data_birth`, `client_sex`, `client_date_registration`) VALUES
  (1, 'Rafael', 'Battezzati', 1, 'rafael.battezzati@gmail.com', 'rafael.battezzati', 'a', 690945000, NULL, 'M', '2017-05-05'),
  (2, 'Zaneta', 'Glowka', 1, 'zaneta.glowka@gmail.com', 'zaneta.glowka', '12345678', 690945099, NULL, 'F', '2018-01-11');

-- --------------------------------------------------------

--
-- Estrutura da tabela `delivery`
--

CREATE TABLE `delivery` (
  `delivery_id` int(11) NOT NULL,
  `delivery_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `delivery`
--

INSERT INTO `delivery` (`delivery_id`, `delivery_type`) VALUES
  (1, 'Delivery'),
  (2, 'Pick up');

-- --------------------------------------------------------

--
-- Estrutura da tabela `films`
--

CREATE TABLE `films` (
  `film_id` int(11) NOT NULL,
  `filmType_id` int(11) NOT NULL,
  `film_name` varchar(50) NOT NULL,
  `film_category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `films`
--

INSERT INTO `films` (`film_id`, `filmType_id`, `film_name`, `film_category`) VALUES
  (26, 2, 'Daredevil', NULL),
  (27, 1, 'Avengers Voltron 2', NULL),
  (29, 3, 'Pulp Fiction', NULL),
  (33, 1, 'Matrix 11', NULL),
  (34, 2, 'Spider Man', NULL),
  (35, 2, 'Spider Man 2', NULL),
  (36, 3, 'Out of Africa', NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `filmtype`
--

CREATE TABLE `filmtype` (
  `filmType_id` int(11) NOT NULL,
  `description` varchar(50) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  `days_discount` int(11) NOT NULL,
  `bonus_points` int(11) NOT NULL,
  `discountDescription` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `filmtype`
--

INSERT INTO `filmtype` (`filmType_id`, `description`, `price`, `days_discount`, `bonus_points`, `discountDescription`) VALUES
  (1, 'New release', '40.00', 0, 2, 'Price is <New Release> times number of days rented. '),
  (2, 'Regular film', '30.00', 2, 1, 'Price is <Regular film price> for the first 5 days<br> and then <Regular film price price> times the number of days over 5.'),
  (3, 'Old film', '30.00', 4, 1, 'Price is <Old film price> for the first 3 days and then <Old film price> times the number of days over 3.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `film_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `start_rent_date` datetime NOT NULL,
  `expected_end_rent_date` datetime NOT NULL,
  `end_rent_date` datetime DEFAULT NULL,
  `expected_price` decimal(15,2) NOT NULL,
  `final_price` decimal(15,2) DEFAULT NULL,
  `daysBetween` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `orders`
--

INSERT INTO `orders` (`order_id`, `film_id`, `quantity`, `start_rent_date`, `expected_end_rent_date`, `end_rent_date`, `expected_price`, `final_price`, `daysBetween`) VALUES
  (97, 33, 1, '2018-01-15 00:00:00', '2018-01-16 00:00:00', '2018-01-18 00:00:00', '40.00', '120.00', 3),
  (97, 34, 1, '2018-01-15 00:00:00', '2018-01-20 00:00:00', '2018-01-21 00:00:00', '90.00', '360.00', 6),
  (97, 35, 1, '2018-01-15 00:00:00', '2018-01-17 00:00:00', '2018-01-17 00:00:00', '30.00', '30.00', 2),
  (97, 36, 1, '2018-01-15 00:00:00', '2018-01-22 00:00:00', '2018-01-22 00:00:00', '90.00', '90.00', 7),
  (99, 29, 1, '2018-01-15 00:00:00', '2018-01-19 00:00:00', NULL, '30.00', '0.00', 4),
  (99, 26, 1, '2018-01-15 00:00:00', '2018-01-29 00:00:00', NULL, '360.00', '0.00', 14);

-- --------------------------------------------------------

--
-- Estrutura da tabela `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `payment_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `payment`
--

INSERT INTO `payment` (`payment_id`, `payment_type`) VALUES
  (1, 'Cash'),
  (2, 'Debit/Credit Card');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `city_id_fk` (`city_id`);

--
-- Indexes for table `basket`
--
ALTER TABLE `basket`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `client_id` (`client_id`),
  ADD KEY `delivery_id` (`delivery_id`),
  ADD KEY `payment_id` (`payment_id`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`client_id`),
  ADD KEY `address_id_fk` (`address_id`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`delivery_id`);

--
-- Indexes for table `films`
--
ALTER TABLE `films`
  ADD PRIMARY KEY (`film_id`),
  ADD KEY `filmType_id_fk` (`filmType_id`);

--
-- Indexes for table `filmtype`
--
ALTER TABLE `filmtype`
  ADD PRIMARY KEY (`filmType_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD KEY `film_id` (`film_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `basket`
--
ALTER TABLE `basket`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `city_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `client_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `films`
--
ALTER TABLE `films`
  MODIFY `film_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `filmtype`
--
ALTER TABLE `filmtype`
  MODIFY `filmType_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `city_id_fk` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`);

--
-- Limitadores para a tabela `basket`
--
ALTER TABLE `basket`
  ADD CONSTRAINT `client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  ADD CONSTRAINT `delivery_id_fk` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`delivery_id`),
  ADD CONSTRAINT `payment_id_fk` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`);

--
-- Limitadores para a tabela `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `address_id_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`);

--
-- Limitadores para a tabela `films`
--
ALTER TABLE `films`
  ADD CONSTRAINT `filmType_id_fk` FOREIGN KEY (`filmType_id`) REFERENCES `filmtype` (`filmType_id`);

--
-- Limitadores para a tabela `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `film_id_fk` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`),
  ADD CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `basket` (`order_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
