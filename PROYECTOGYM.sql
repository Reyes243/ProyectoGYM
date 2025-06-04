-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: pro.freedb.tech
-- Tiempo de generación: 04-06-2025 a las 21:57:38
-- Versión del servidor: 8.0.42-0ubuntu0.22.04.1
-- Versión de PHP: 8.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `PROYECTOGYM`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia`
--

CREATE TABLE `asistencia` (
  `id_asistencia` int NOT NULL,
  `id_usuario` int NOT NULL,
  `id_clase` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase`
--

CREATE TABLE `clase` (
  `id_clase` int NOT NULL,
  `id_usuario` int NOT NULL,
  `nombre_clase` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `clase`
--

INSERT INTO `clase` (`id_clase`, `id_usuario`, `nombre_clase`) VALUES
(1, 3, 'YOGA RELAX');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clase_horario`
--

CREATE TABLE `clase_horario` (
  `id_clase_horario` int NOT NULL,
  `id_clase` int NOT NULL,
  `dia_semana` enum('Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo') DEFAULT NULL,
  `turno` enum('Matutino','Vespertino','Mixto') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `clase_horario`
--

INSERT INTO `clase_horario` (`id_clase_horario`, `id_clase`, `dia_semana`, `turno`) VALUES
(1, 1, 'Lunes', 'Matutino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inscripcion`
--

CREATE TABLE `inscripcion` (
  `id_inscripcion` int NOT NULL,
  `id_usuario` int NOT NULL,
  `id_clase` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_Rol` int NOT NULL,
  `nombre_rol` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_Rol`, `nombre_rol`) VALUES
(1, 'admin'),
(2, 'cliente'),
(3, 'instructor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifa`
--

CREATE TABLE `tarifa` (
  `id_tarifa` int NOT NULL,
  `nombre_tarifa` varchar(45) NOT NULL,
  `descripcion` text NOT NULL,
  `precio` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `tarifa`
--

INSERT INTO `tarifa` (`id_tarifa`, `nombre_tarifa`, `descripcion`, `precio`) VALUES
(1, 'estandar', 'Se incluye acceso al área de cardio únicamente y al equipo correspondiente.\nDurante su membresía se le aplicara un 15% de descuento al comprar productos de la marca EVOLVEFIT.\nMiembros máximos para veneficios: 1\nMembrecía mensual con costo de $300.', 300),
(2, 'premium', '\nSe incluye acceso completo a todo el equipo y áreas del gimnasio.\nDurante su membresía se le aplicara un 25% de descuento al comprar productos de la marca EVOLVEFIT.\nMiembros máximos para veneficios: 1\nMembrecía mensual con costo de $600.', 600),
(3, 'familiar', 'Se incluye acceso completo a todo el equipo y áreas del gimnasio.\nDurante su membresía se le aplicara un 30% de descuento a todos los miembros del plan al comprar productos de la marca EVOLVEFIT.\nMiembros máximos para veneficios: 4\nMembrecía mensual con costo de $1099.', 1099);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL,
  `id_rol` int NOT NULL DEFAULT '2',
  `nombre` varchar(45) DEFAULT NULL,
  `primer_apellido` varchar(20) DEFAULT NULL,
  `segundo_apellido` varchar(20) DEFAULT NULL,
  `correo` varchar(45) NOT NULL,
  `contraseña` char(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `especialidad` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `nombre`, `primer_apellido`, `segundo_apellido`, `correo`, `contraseña`, `telefono`, `especialidad`) VALUES
(1, 1, NULL, NULL, NULL, 'jojo@gmail.com', '12345', NULL, NULL),
(3, 3, 'Laura', 'Mendez', NULL, 'laura.mendez@evolvefit.com', NULL, '6125567788', 'Pilates Y yoga'),
(14, 1, NULL, NULL, NULL, 'jesus@gmail.com', '789456123', NULL, NULL),
(17, 1, NULL, NULL, NULL, 'q', '1', NULL, NULL),
(20, 1, NULL, NULL, NULL, 't', '1', NULL, NULL),
(21, 1, NULL, NULL, NULL, 'r', '1', NULL, NULL),
(27, 1, NULL, NULL, NULL, '3', '1', NULL, NULL),
(32, 1, NULL, NULL, NULL, 'josereyes@gmail.com', '12345678', NULL, NULL),
(33, 1, NULL, NULL, NULL, 'yu@gmail.com', '12345678', NULL, NULL),
(37, 1, NULL, NULL, NULL, 'pepetish@gmail.com', 'vadurotish123', NULL, NULL),
(38, 1, NULL, NULL, NULL, 'luquin@gmail.com', '12345678', NULL, NULL),
(39, 1, NULL, NULL, NULL, '123@gmail.com', '12345678', NULL, NULL),
(40, 1, NULL, NULL, NULL, 'no@gmail.com', '12345678', NULL, NULL),
(41, 2, 'Carlostish', 'Hernandeztish', 'Armentatish', 'Durotish@gmail.com', '123', '6122887916', NULL),
(42, 2, 'Reyes', 'Hinojosa', 'Torres', 'reyes@gmail.com', '789456123', '6121273045', NULL),
(43, 2, 'Rafael', 'Dominguez', NULL, 'rafa@gmail.com', '789456', '6154829544', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_tarifa`
--

CREATE TABLE `usuario_tarifa` (
  `id_usuario_tarifa` int NOT NULL,
  `id_usuario` int NOT NULL,
  `id_tarifa` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `usuario_tarifa`
--

INSERT INTO `usuario_tarifa` (`id_usuario_tarifa`, `id_usuario`, `id_tarifa`) VALUES
(19, 41, 1),
(20, 42, 1),
(21, 43, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD PRIMARY KEY (`id_asistencia`),
  ADD KEY `id_usuario_idx` (`id_usuario`),
  ADD KEY `id_clase_idx` (`id_clase`);

--
-- Indices de la tabla `clase`
--
ALTER TABLE `clase`
  ADD PRIMARY KEY (`id_clase`),
  ADD KEY `fk_usuarios_idx` (`id_usuario`);

--
-- Indices de la tabla `clase_horario`
--
ALTER TABLE `clase_horario`
  ADD PRIMARY KEY (`id_clase_horario`),
  ADD KEY `id_clase_idx` (`id_clase`);

--
-- Indices de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD PRIMARY KEY (`id_inscripcion`),
  ADD KEY `id_usuario_idx` (`id_usuario`),
  ADD KEY `id_clase_idx` (`id_clase`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_Rol`);

--
-- Indices de la tabla `tarifa`
--
ALTER TABLE `tarifa`
  ADD PRIMARY KEY (`id_tarifa`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `correo_UNIQUE` (`correo`),
  ADD KEY `fk_Usuarios_Rol1_idx` (`id_rol`);

--
-- Indices de la tabla `usuario_tarifa`
--
ALTER TABLE `usuario_tarifa`
  ADD PRIMARY KEY (`id_usuario_tarifa`),
  ADD KEY `id_Usuarios_idx` (`id_usuario`),
  ADD KEY `id_Tarifa_idx` (`id_tarifa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencia`
--
ALTER TABLE `asistencia`
  MODIFY `id_asistencia` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `clase`
--
ALTER TABLE `clase`
  MODIFY `id_clase` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  MODIFY `id_inscripcion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_Rol` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tarifa`
--
ALTER TABLE `tarifa`
  MODIFY `id_tarifa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `usuario_tarifa`
--
ALTER TABLE `usuario_tarifa`
  MODIFY `id_usuario_tarifa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asistencia`
--
ALTER TABLE `asistencia`
  ADD CONSTRAINT `fk_clase2` FOREIGN KEY (`id_clase`) REFERENCES `clase` (`id_clase`),
  ADD CONSTRAINT `fk_usuario3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `clase`
--
ALTER TABLE `clase`
  ADD CONSTRAINT `fk_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `clase_horario`
--
ALTER TABLE `clase_horario`
  ADD CONSTRAINT `fk_clase1` FOREIGN KEY (`id_clase`) REFERENCES `clase` (`id_clase`);

--
-- Filtros para la tabla `inscripcion`
--
ALTER TABLE `inscripcion`
  ADD CONSTRAINT `fk_clase3` FOREIGN KEY (`id_clase`) REFERENCES `clase` (`id_clase`),
  ADD CONSTRAINT `fk_usuario2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_Usuarios_Rol1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_Rol`);

--
-- Filtros para la tabla `usuario_tarifa`
--
ALTER TABLE `usuario_tarifa`
  ADD CONSTRAINT `fk_tarifa` FOREIGN KEY (`id_tarifa`) REFERENCES `tarifa` (`id_tarifa`),
  ADD CONSTRAINT `fk_usuario4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
