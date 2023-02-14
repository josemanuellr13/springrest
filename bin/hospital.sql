-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-02-2023 a las 12:47:25
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--
CREATE DATABASE IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `hospital`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

CREATE TABLE `citas` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `numeroConsulta` int(11) NOT NULL,
  `especialidad` varchar(50) NOT NULL,
  `idpaciente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `citas`
--

INSERT INTO `citas` (`id`, `fecha`, `numeroConsulta`, `especialidad`, `idpaciente`) VALUES
(1, '2023-01-03', 3, 'Traumatologia', 3),
(2, '2023-01-06', 4, 'Urologia', 2),
(3, '2023-02-01', 2, 'Pediatria', 2),
(4, '2023-01-13', 3, 'Traumatologia', 7),
(5, '2023-01-15', 3, 'Neurologia', 5),
(6, '2023-02-01', 6, 'Traumatologia', 10),
(7, '2023-01-20', 1, 'Geriatria', 1),
(8, '2023-01-18', 3, 'Cardiologia', 8),
(9, '2023-01-30', 2, 'Rehabilitacion', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

CREATE TABLE `pacientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `genero` tinyint(1) NOT NULL,
  `edad` int(11) NOT NULL,
  `telefono` int(11) DEFAULT NULL,
  `poblacion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id`, `nombre`, `genero`, `edad`, `telefono`, `poblacion`) VALUES
(1, 'Antonio Ramos Rodriguez', 0, 25, NULL, 'Dos Hermanas'),
(2, 'Maria Candela Gutierrez', 1, 37, 643233123, 'Utrera'),
(3, 'Paco Martinez López', 0, 16, NULL, 'Dos Hermanas'),
(4, 'Miguel Contreras', 0, 47, 655453488, 'Utrera'),
(5, 'Inmaculada Rodriguez Ramos ', 1, 55, 678943234, 'Utrera'),
(6, 'Victoria Ortega Roldan', 1, 42, 633239872, 'Sevilla'),
(7, 'Oscar Martinez Perez', 0, 33, 654333243, 'Sevilla'),
(8, 'Pedro Lucas de Palma', 0, 20, 678945344, 'Sevilla'),
(9, 'Isabel Ramos Martinez', 1, 46, NULL, 'Dos Hermanas'),
(10, 'Rocio Roldan Terrero', 1, 18, 675438768, 'Dos Hermanas');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `citas`
--
ALTER TABLE `citas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idpaciente` (`idpaciente`);

--
-- Indices de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `citas`
--
ALTER TABLE `citas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `pacientes`
--
ALTER TABLE `pacientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `citas`
--
ALTER TABLE `citas`
  ADD CONSTRAINT `fk_idpaciente` FOREIGN KEY (`idpaciente`) REFERENCES `pacientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
