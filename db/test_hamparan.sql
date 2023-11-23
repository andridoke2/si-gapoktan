-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2023 at 03:17 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_hamparan`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota_hamparan`
--

CREATE TABLE `anggota_hamparan` (
  `kd_anggota` varchar(9) NOT NULL,
  `nama_anggota` varchar(100) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `jenis_kelamin` char(1) DEFAULT NULL,
  `no_telpon` varchar(20) DEFAULT NULL,
  `is_active` int(1) DEFAULT NULL,
  `golongan` varchar(30) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `image` varchar(128) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `date_created` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `anggota_hamparan`
--

INSERT INTO `anggota_hamparan` (`kd_anggota`, `nama_anggota`, `alamat`, `jenis_kelamin`, `no_telpon`, `is_active`, `golongan`, `email`, `password`, `image`, `role`, `date_created`) VALUES
('PMB1001', 'Tom Cruz', 'Ketapang', 'L', '082152085281', 1, 'Mobil', 'tom@gmail.com', '$2a$10$4j1aEeaucII25P.JyN9bR.4CJ35Gg2rjNY7YBv4qT0g3xt1DV5g9G', 'default.png', 'USER', '1680201145237'),
('PMB1002', 'Abdur Arsyad', 'Larantuka', 'L', '082152085304', 1, 'Mobil', 'abdur@gmail.com', '$2a$10$GIRrYww3z8tSwGUyFeYijuoDBEtgIHhR3omOyo3HO6VD1zBqUfnsi', 'default.png', 'USER', '1682009967118'),
('PMT1001', 'Asep', 'Ketapang', 'L', '082152085304', 1, 'Pemuat', 'asep@gmail.com', '$2a$10$fICGzpeeiBbxl.ztoWa6K.ZSwT6J3jER1PJ7FF1FoEhhEUPiSPEXC', 'default.png', 'USER', '1679914842858'),
('PMT1002', 'Hendrik', 'Ketapang', 'L', '082152085304', 1, 'Pemuat', 'hendrik@gmail.com', '$2a$10$R9NqgF1ZDfFWEUDTsQiNjeiL7WCyZo812NXrCuntpkooyB8weYlvK', 'default.png', 'USER', '1679918755079'),
('PMT1003', 'Iko Uwais', 'Ketapang', 'L', '082152085304', 1, 'Pemuat', 'ikouwais@gmail.com', '$2a$10$RTB7T4K3bhwB7xEKmd6.meLhdaJZkCy48SSmPBoBBFotzqjOQ/nbG', 'default.png', 'USER', '1679919211936'),
('PNB1001', 'Riko Ade', 'Ketapang', 'L', '082152085304', 1, 'Penimbang', 'riko@gmail.com', '$2a$10$H71H9ispFGxx5Q/76N0EMO9fJP0gOA0y4WJ4UpKdm3HRGrPnekGzy', 'default.png', 'USER', '1679914978033'),
('PNB1002', 'Nurdianto', 'Ketapang', 'L', '082152085304', 1, 'Penimbang', 'nurdianto@gmail.com', '$2a$10$5xl7qfbhkM2vYg1LimHXYujhP1hc7SBon6Kv4M7sasYtHC9bhFEya', 'default.png', 'USER', '1679919720992'),
('PRS1001', 'Andri Aryanto Doke', 'Ketapang', 'L', '082152085281', 1, 'Pengurus', 'andridoke2@gmail.com', '$2a$10$XIbi2zJjGZ4cIsH105MKOeXnKubOxkTpeHFdqeN4OyR.Wv2oC43dq', 'default.png', 'ADMIN', '1677598968637'),
('PRS1002', 'John Standly Gade', 'Singkup', 'L', '082152085281', 1, 'Pengurus', 'johngade@gmail.com', '$2a$10$ifRI/4dRTK7wlXHr0XRiqeRrXCkgN8xkTMoyYN4.lJ/WTKb6eltVe', 'default.png', 'ADMIN', '1679818253810'),
('PRS1003', 'Administrator', 'Singkup', 'L', '082152085281', 1, 'Pengurus', 'admin@gmail.com', '$2a$10$cfuJYHsjZw42G1rroZsZeeLx7ETz0dXlDysdYMg0CVsK1hpM04ZkC', '', 'ADMIN', ''),
('PTI1001', 'Leonardo De Caprio', 'New York Of Amerika Serikat', 'L', '082152085281', 1, 'Petani', 'leonardo@gmail.com', '10011333', 'default.png', 'USER', '1677963986446'),
('PTI1002', 'Rinto Raga', 'Singkup', 'L', '082152085281', 1, 'Petani', 'rinto@gmail.com', '$2a$10$cXdmswE.4y/d27rnf9WuQOKmnR.5hdgyddXbgH75f1DpavBkqsS.6', 'default.png', 'USER', '1680177130177'),
('PTI1003', 'Ralin Shaa', 'Jakarta', 'P', '082152085304', 1, 'Petani', 'ralin@gmail.com', '$2a$10$oiEI8qyu71z7CkR9iOodBeLWodqXCGpUHDjt.E9OqsLngxhPYxneO', 'default.png', 'USER', '1680177222453'),
('PTI1004', 'Jesica Mila', 'Jakarta', 'P', '082152085304', 1, 'Petani', 'jesicamila@gmail.com', '$2a$10$9hDzx9AQxxxTssASW9768O41LL5r1vVeZ/iYVqlF1v38yG3VZFFZW', 'default.png', 'USER', '1680177251236'),
('PTI1005', 'Eminem', 'New York', 'L', '082152085304', 1, 'Petani', 'eminem@gmail.com', '$2a$10$b.OPoeYFH7DeA2YItHMo3Ott6SzkLQ4W1yiyUISHQO1KjIW6G4B.2', 'default.png', 'USER', '1680177278379'),
('PTI1006', 'Arkilaus', 'Ketapang', 'L', '082152085304', 1, 'Petani', 'arki@gmail.com', '$2a$10$ENwelCnKDsTqI7q3RzUgJOZGh/YSxDUcNrobN6GVRNH0ANllNyVdS', 'default.png', 'USER', '1680177325380'),
('PTI1007', 'Patrisius', 'Singkup', 'L', '082152085304', 1, 'Petani', 'patris@gmail.com', '$2a$10$uRoTzW4/WXmMrMzMn4S76u2NIb9if4xnRcfswX5EBvcI3r39BJoRi', 'default.png', 'USER', '1680177353037'),
('PTI1008', 'User', 'Ketapang', 'L', '082152085304', 1, 'Petani', 'user@gmail.com', '$2a$10$EvwHwuPNy.Xo8HKY9Jn97ujetgxDGWestxQt5akp/W77oaKMrShA2', 'default.png', 'USER', '1689219686643'),
('PTI1009', 'Christian R.', 'Ketapang', 'L', '082152085281', 1, 'Petani', 'christian@gmail.com', '$2a$10$Z6Os1ZM0EjvwE.OhnP2eiObWVvKv2E9oeX1ZdzxPbYyQq.GpBWH6a', 'default.png', 'USER', '1690787639885');

-- --------------------------------------------------------

--
-- Table structure for table `detail_hasil_hamparan`
--

CREATE TABLE `detail_hasil_hamparan` (
  `kd_anggota` varchar(9) NOT NULL,
  `kd_kebun` varchar(9) DEFAULT NULL,
  `kd_mobil` varchar(9) DEFAULT NULL,
  `kd_hasil` varchar(9) NOT NULL,
  `status` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_hasil_hamparan`
--

INSERT INTO `detail_hasil_hamparan` (`kd_anggota`, `kd_kebun`, `kd_mobil`, `kd_hasil`, `status`) VALUES
('PTI1001', 'KBN1003', NULL, 'H-PTI1001', 'Y'),
('PTI1001', 'KBN1001', NULL, 'H-PTI1002', 'Y'),
('PMB1001', NULL, 'MBL1001', 'H-PMB1001', 'Y'),
('PTI1001', 'KBN1001', NULL, 'H-PTI1003', 'Y'),
('PMT1001', NULL, NULL, 'H-PMT1001', 'Y'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1004', 'Y'),
('PMB1001', NULL, 'MBL1001', 'H-PMB1002', 'Y'),
('PNB1001', NULL, NULL, 'H-PNB1001', 'Y'),
('PMT1001', NULL, NULL, 'H-PMT1002', 'Y'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1005', 'Y'),
('PTI1001', 'KBN1001', NULL, 'H-PTI1006', 'Y'),
('PTI1001', 'KBN1001', NULL, 'H-PTI1007', 'Y'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1008', 'Y'),
('PTI1001', 'KBN1001', NULL, 'H-PTI1009', 'N'),
('PTI1008', 'KBN1004', NULL, 'H-PTI1010', 'N'),
('PTI1009', 'KBN1005', NULL, 'H-PTI1011', 'Y'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1012', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1013', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1014', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1015', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1016', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1017', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1018', 'N'),
('PTI1002', 'KBN1002', NULL, 'H-PTI1019', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `harga_hamparan`
--

CREATE TABLE `harga_hamparan` (
  `kd_harga` varchar(9) NOT NULL,
  `sawit` int(11) DEFAULT NULL,
  `mobil` int(11) DEFAULT NULL,
  `pemuat` int(11) DEFAULT NULL,
  `penimbang` int(11) DEFAULT NULL,
  `pengurus` int(11) DEFAULT NULL,
  `pajak` double DEFAULT NULL,
  `date_created` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `harga_hamparan`
--

INSERT INTO `harga_hamparan` (`kd_harga`, `sawit`, `mobil`, `pemuat`, `penimbang`, `pengurus`, `pajak`, `date_created`) VALUES
('HRG1001', 1450, 100, 25, 25, 5, 0.25, '1680617657069'),
('HRG1002', 1500, 150, 30, 30, 10, 0.25, '1680617687169');

-- --------------------------------------------------------

--
-- Table structure for table `hasil_hamparan`
--

CREATE TABLE `hasil_hamparan` (
  `kd_hasil` varchar(9) NOT NULL,
  `tonase` int(11) DEFAULT NULL,
  `tanggal` varchar(15) DEFAULT NULL,
  `total_hasil` int(11) DEFAULT NULL,
  `potongan_mobil` int(11) DEFAULT NULL,
  `potongan_pemuat` int(11) DEFAULT NULL,
  `potongan_penimbang` int(11) DEFAULT NULL,
  `potongan_pengurus` int(11) DEFAULT NULL,
  `potongan_pajak` int(11) DEFAULT NULL,
  `total_potongan` int(11) DEFAULT NULL,
  `kd_harga` varchar(9) NOT NULL,
  `golongan` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hasil_hamparan`
--

INSERT INTO `hasil_hamparan` (`kd_hasil`, `tonase`, `tanggal`, `total_hasil`, `potongan_mobil`, `potongan_pemuat`, `potongan_penimbang`, `potongan_pengurus`, `potongan_pajak`, `total_potongan`, `kd_harga`, `golongan`) VALUES
('H-PMB1001', 10000, '22/04/2023', 1500000, 0, 0, 0, 0, 0, 0, 'HRG1002', 'Mobil'),
('H-PMB1002', 20000, '22/04/2023', 3000000, 0, 0, 0, 0, 0, 0, 'HRG1002', 'Mobil'),
('H-PMT1001', 20000, '22/04/2023', 600000, 0, 0, 0, 0, 0, 0, 'HRG1002', 'Pemuat'),
('H-PMT1002', 10000, '22/04/2023', 300000, 0, 0, 0, 0, 0, 0, 'HRG1002', 'Pemuat'),
('H-PNB1001', 10000, '22/04/2023', 300000, 0, 0, 0, 0, 0, 0, 'HRG1002', 'Penimbang'),
('H-PTI1001', 2743, '22/04/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1002', 1357, '22/04/2023', 1731872, 203550, 40710, 40710, 13570, 5088, 303628, 'HRG1002', 'Petani'),
('H-PTI1003', 3000, '22/04/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani'),
('H-PTI1004', 3000, '22/04/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani'),
('H-PTI1005', 2743, '22/04/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1006', 2743, '22/04/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1007', 3000, '13/05/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani'),
('H-PTI1008', 2743, '13/05/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1009', 2743, '10/07/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1010', 2743, '13/05/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1011', 2743, '31/07/2023', 3500754, 411450, 82290, 82290, 27430, 10286, 613746, 'HRG1002', 'Petani'),
('H-PTI1012', 2000, '01/01/2023', 2552500, 300000, 60000, 60000, 20000, 7500, 447500, 'HRG1002', 'Petani'),
('H-PTI1013', 3000, '15/01/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani'),
('H-PTI1014', 1000, '30/01/2023', 1276250, 150000, 30000, 30000, 10000, 3750, 223750, 'HRG1002', 'Petani'),
('H-PTI1015', 2000, '01/02/2023', 2552500, 300000, 60000, 60000, 20000, 7500, 447500, 'HRG1002', 'Petani'),
('H-PTI1016', 3000, '15/02/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani'),
('H-PTI1017', 4000, '30/02/2023', 5105000, 600000, 120000, 120000, 40000, 15000, 895000, 'HRG1002', 'Petani'),
('H-PTI1018', 2000, '01/03/2023', 2552500, 300000, 60000, 60000, 20000, 7500, 447500, 'HRG1002', 'Petani'),
('H-PTI1019', 3000, '07/08/2023', 3828750, 450000, 90000, 90000, 30000, 11250, 671250, 'HRG1002', 'Petani');

-- --------------------------------------------------------

--
-- Table structure for table `kebun_hamparan`
--

CREATE TABLE `kebun_hamparan` (
  `kd_kebun` varchar(9) NOT NULL,
  `kd_anggota` varchar(9) NOT NULL,
  `lokasi` varchar(50) DEFAULT NULL,
  `jenis_kebun` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kebun_hamparan`
--

INSERT INTO `kebun_hamparan` (`kd_kebun`, `kd_anggota`, `lokasi`, `jenis_kebun`) VALUES
('KBN1001', 'PTI1001', 'Hamparan A', 'Pekarangan'),
('KBN1002', 'PTI1002', 'Hamparan B', 'Kavlingan'),
('KBN1003', 'PTI1001', 'Hamparan D', 'Kavlingan'),
('KBN1004', 'PTI1008', 'Hamparan D', 'Kavlingan'),
('KBN1005', 'PTI1009', 'Hamparan D', 'Kavlingan'),
('KBN1006', 'PTI1002', 'Hamparan B', 'Kavlingan');

-- --------------------------------------------------------

--
-- Table structure for table `mobil_hamparan`
--

CREATE TABLE `mobil_hamparan` (
  `kd_mobil` varchar(9) NOT NULL,
  `kd_anggota` varchar(9) NOT NULL,
  `merk_mobil` varchar(50) DEFAULT NULL,
  `nomor_plat_kendaraan` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mobil_hamparan`
--

INSERT INTO `mobil_hamparan` (`kd_mobil`, `kd_anggota`, `merk_mobil`, `nomor_plat_kendaraan`) VALUES
('MBL1001', 'PMB1001', 'Hino Dutro', 'KB4321C'),
('MBL1002', 'PMB1002', 'Ragasa R2', 'KB1234C'),
('MBL1003', 'PMB1001', 'Ragasa R3', 'KB4321C');

-- --------------------------------------------------------

--
-- Table structure for table `pajak_hamparan`
--

CREATE TABLE `pajak_hamparan` (
  `kd_anggota` varchar(9) NOT NULL,
  `kd_hasil` varchar(9) NOT NULL,
  `kd_harga` varchar(9) NOT NULL,
  `tanggal` varchar(15) DEFAULT NULL,
  `total_pajak` int(11) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pajak_hamparan`
--

INSERT INTO `pajak_hamparan` (`kd_anggota`, `kd_hasil`, `kd_harga`, `tanggal`, `total_pajak`, `id`) VALUES
('PTI1001', 'H-PTI1001', 'HRG1002', '22/04/2023', 10286, 55),
('PTI1001', 'H-PTI1002', 'HRG1002', '22/04/2023', 5088, 56),
('PTI1001', 'H-PTI1003', 'HRG1002', '22/04/2023', 11250, 57),
('PTI1002', 'H-PTI1004', 'HRG1002', '22/04/2023', 11250, 58),
('PTI1002', 'H-PTI1005', 'HRG1002', '22/04/2023', 10286, 59),
('PTI1001', 'H-PTI1006', 'HRG1002', '22/04/2023', 10286, 60),
('PTI1001', 'H-PTI1007', 'HRG1002', '13/05/2023', 11250, 61),
('PTI1002', 'H-PTI1008', 'HRG1002', '13/05/2023', 10286, 62),
('PTI1001', 'H-PTI1009', 'HRG1002', '10/07/2023', 10286, 63),
('PTI1008', 'H-PTI1010', 'HRG1002', '13/05/2023', 10286, 64),
('PTI1009', 'H-PTI1011', 'HRG1002', '31/07/2023', 10286, 65),
('PTI1002', 'H-PTI1012', 'HRG1002', '01/01/2023', 7500, 66),
('PTI1002', 'H-PTI1013', 'HRG1002', '15/01/2023', 11250, 67),
('PTI1002', 'H-PTI1014', 'HRG1002', '30/01/2023', 3750, 68),
('PTI1002', 'H-PTI1015', 'HRG1002', '01/02/2023', 7500, 69),
('PTI1002', 'H-PTI1016', 'HRG1002', '15/02/2023', 11250, 70),
('PTI1002', 'H-PTI1017', 'HRG1002', '30/02/2023', 15000, 71),
('PTI1002', 'H-PTI1018', 'HRG1002', '01/03/2023', 7500, 72),
('PTI1002', 'H-PTI1019', 'HRG1002', '07/08/2023', 11250, 73);

-- --------------------------------------------------------

--
-- Table structure for table `pengurus_hamparan`
--

CREATE TABLE `pengurus_hamparan` (
  `kd_anggota` varchar(9) NOT NULL,
  `kd_hasil` varchar(9) NOT NULL,
  `tanggal` varchar(15) DEFAULT NULL,
  `total_pengurus` int(11) DEFAULT NULL,
  `kd_harga` varchar(9) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengurus_hamparan`
--

INSERT INTO `pengurus_hamparan` (`kd_anggota`, `kd_hasil`, `tanggal`, `total_pengurus`, `kd_harga`, `id`) VALUES
('PTI1001', 'H-PTI1001', '22/04/2023', 27430, 'HRG1002', 54),
('PTI1001', 'H-PTI1002', '22/04/2023', 13570, 'HRG1002', 55),
('PTI1001', 'H-PTI1003', '22/04/2023', 30000, 'HRG1002', 56),
('PTI1002', 'H-PTI1004', '22/04/2023', 30000, 'HRG1002', 57),
('PTI1002', 'H-PTI1005', '22/04/2023', 27430, 'HRG1002', 58),
('PTI1001', 'H-PTI1006', '22/04/2023', 27430, 'HRG1002', 59),
('PTI1001', 'H-PTI1007', '13/05/2023', 30000, 'HRG1002', 60),
('PTI1002', 'H-PTI1008', '13/05/2023', 27430, 'HRG1002', 61),
('PTI1001', 'H-PTI1009', '10/07/2023', 27430, 'HRG1002', 62),
('PTI1008', 'H-PTI1010', '13/05/2023', 27430, 'HRG1002', 63),
('PTI1009', 'H-PTI1011', '31/07/2023', 27430, 'HRG1002', 64),
('PTI1002', 'H-PTI1012', '01/01/2023', 20000, 'HRG1002', 65),
('PTI1002', 'H-PTI1013', '15/01/2023', 30000, 'HRG1002', 66),
('PTI1002', 'H-PTI1014', '30/01/2023', 10000, 'HRG1002', 67),
('PTI1002', 'H-PTI1015', '01/02/2023', 20000, 'HRG1002', 68),
('PTI1002', 'H-PTI1016', '15/02/2023', 30000, 'HRG1002', 69),
('PTI1002', 'H-PTI1017', '30/02/2023', 40000, 'HRG1002', 70),
('PTI1002', 'H-PTI1018', '01/03/2023', 20000, 'HRG1002', 71),
('PTI1002', 'H-PTI1019', '07/08/2023', 30000, 'HRG1002', 72);

-- --------------------------------------------------------

--
-- Table structure for table `user_access_menu`
--

CREATE TABLE `user_access_menu` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_access_menu`
--

INSERT INTO `user_access_menu` (`id`, `role_id`, `menu_id`) VALUES
(1, 1, 1),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_menu`
--

CREATE TABLE `user_menu` (
  `id` int(11) NOT NULL,
  `menu` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_menu`
--

INSERT INTO `user_menu` (`id`, `menu`) VALUES
(1, 'Admin'),
(2, 'User'),
(3, 'Hamparan'),
(4, 'Kud');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `role` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `role`) VALUES
(1, 'Admin'),
(2, 'User'),
(25, 'Member');

-- --------------------------------------------------------

--
-- Table structure for table `user_sub_menu`
--

CREATE TABLE `user_sub_menu` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `icon` varchar(128) DEFAULT NULL,
  `is_active` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_sub_menu`
--

INSERT INTO `user_sub_menu` (`id`, `menu_id`, `title`, `url`, `icon`, `is_active`) VALUES
(1, 1, 'Dashboard', '/dashboard', 'fas fa-fw fa-tachometer-alt', 1),
(2, 3, 'Anggota', '/anggota', 'fas fa-fw fa-users', 1),
(3, 3, 'Kebun', '/kebun', 'fas fa-fw fa-tree', 1),
(4, 3, 'Mobil', '/mobil', 'fas fa-fw fa-car', 1),
(5, 3, 'Hasil', '/hasil', 'fas fa-fw fa-poll', 1),
(7, 3, 'Pajak', '/pajak', 'fas fa-fw fa-cut', 1),
(8, 3, 'Pengurus', '/pengurus', 'fas fa-fw fa-user-tie', 1),
(9, 3, 'Harga', '/harga', 'fas fa-fw fa-money-bill-alt', 1),
(10, 2, 'Dashboard', '/dashboarduser', 'fas fa-fw fa-tachometer-alt', 1),
(11, 1, 'Role', '/role', 'fas fa-fw fa-sitemap', 1),
(12, 4, 'Hamparan', '/hamparan', 'fas fa-fw fa-stream', 1),
(17, 2, 'Penghasilan', '/penghasilan', 'fas fa-fw fa-funnel-dollar', 1),
(18, 2, 'Kebun | Mobil', '/kebunormobil', 'fas fa-fw fa-air-freshener', 1),
(19, 4, 'Jadwal Hamparan', '/jadwal', 'fas fa-fw fa-calendar-alt', 1),
(20, 2, 'Jadwal Panen', '/jadwalpanen', 'fas fa-fw fa-calendar-alt', 1),
(21, 1, 'Sub Menu', '/submenu', 'fas fa-map-signs', 1),
(26, 2, 'Profil', '/profil', 'fas fa-fw fa-user', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota_hamparan`
--
ALTER TABLE `anggota_hamparan`
  ADD PRIMARY KEY (`kd_anggota`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `password` (`password`);

--
-- Indexes for table `detail_hasil_hamparan`
--
ALTER TABLE `detail_hasil_hamparan`
  ADD KEY `kd_hasil` (`kd_hasil`),
  ADD KEY `detail_hasil_hamparan_ibfk_2` (`kd_kebun`),
  ADD KEY `detail_hasil_hamparan_ibfk_4` (`kd_mobil`),
  ADD KEY `detail_hasil_hamparan_ibfk_1` (`kd_anggota`);

--
-- Indexes for table `harga_hamparan`
--
ALTER TABLE `harga_hamparan`
  ADD PRIMARY KEY (`kd_harga`);

--
-- Indexes for table `hasil_hamparan`
--
ALTER TABLE `hasil_hamparan`
  ADD PRIMARY KEY (`kd_hasil`),
  ADD KEY `hasil_hamparan_ibfk_1` (`kd_harga`);

--
-- Indexes for table `kebun_hamparan`
--
ALTER TABLE `kebun_hamparan`
  ADD PRIMARY KEY (`kd_kebun`),
  ADD KEY `kebun_hamparan_ibfk_1` (`kd_anggota`);

--
-- Indexes for table `mobil_hamparan`
--
ALTER TABLE `mobil_hamparan`
  ADD PRIMARY KEY (`kd_mobil`),
  ADD KEY `mobil_hamparan_ibfk_1` (`kd_anggota`);

--
-- Indexes for table `pajak_hamparan`
--
ALTER TABLE `pajak_hamparan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kd_hasil` (`kd_hasil`),
  ADD KEY `pajak_hamparan_ibfk_1` (`kd_anggota`),
  ADD KEY `pajak_hamparan_ibfk_3` (`kd_harga`);

--
-- Indexes for table `pengurus_hamparan`
--
ALTER TABLE `pengurus_hamparan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `detail_hasil_hamparan_ibfk_3` (`kd_hasil`),
  ADD KEY `pengurus_hamparan_ibfk_3` (`kd_harga`),
  ADD KEY `pengurus_hamparan_ibfk_1` (`kd_anggota`);

--
-- Indexes for table `user_access_menu`
--
ALTER TABLE `user_access_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_menu`
--
ALTER TABLE `user_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_sub_menu`
--
ALTER TABLE `user_sub_menu`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pajak_hamparan`
--
ALTER TABLE `pajak_hamparan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `pengurus_hamparan`
--
ALTER TABLE `pengurus_hamparan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `user_access_menu`
--
ALTER TABLE `user_access_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user_menu`
--
ALTER TABLE `user_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `user_sub_menu`
--
ALTER TABLE `user_sub_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_hasil_hamparan`
--
ALTER TABLE `detail_hasil_hamparan`
  ADD CONSTRAINT `detail_hasil_hamparan_ibfk_1` FOREIGN KEY (`kd_anggota`) REFERENCES `anggota_hamparan` (`kd_anggota`),
  ADD CONSTRAINT `detail_hasil_hamparan_ibfk_2` FOREIGN KEY (`kd_kebun`) REFERENCES `kebun_hamparan` (`kd_kebun`),
  ADD CONSTRAINT `detail_hasil_hamparan_ibfk_4` FOREIGN KEY (`kd_mobil`) REFERENCES `mobil_hamparan` (`kd_mobil`);

--
-- Constraints for table `hasil_hamparan`
--
ALTER TABLE `hasil_hamparan`
  ADD CONSTRAINT `hasil_hamparan_ibfk_1` FOREIGN KEY (`kd_harga`) REFERENCES `harga_hamparan` (`kd_harga`);

--
-- Constraints for table `kebun_hamparan`
--
ALTER TABLE `kebun_hamparan`
  ADD CONSTRAINT `kebun_hamparan_ibfk_1` FOREIGN KEY (`kd_anggota`) REFERENCES `anggota_hamparan` (`kd_anggota`);

--
-- Constraints for table `mobil_hamparan`
--
ALTER TABLE `mobil_hamparan`
  ADD CONSTRAINT `mobil_hamparan_ibfk_1` FOREIGN KEY (`kd_anggota`) REFERENCES `anggota_hamparan` (`kd_anggota`);

--
-- Constraints for table `pajak_hamparan`
--
ALTER TABLE `pajak_hamparan`
  ADD CONSTRAINT `pajak_hamparan_ibfk_1` FOREIGN KEY (`kd_anggota`) REFERENCES `anggota_hamparan` (`kd_anggota`),
  ADD CONSTRAINT `pajak_hamparan_ibfk_3` FOREIGN KEY (`kd_harga`) REFERENCES `harga_hamparan` (`kd_harga`);

--
-- Constraints for table `pengurus_hamparan`
--
ALTER TABLE `pengurus_hamparan`
  ADD CONSTRAINT `detail_hasil_hamparan_ibfk_3` FOREIGN KEY (`kd_hasil`) REFERENCES `hasil_hamparan` (`kd_hasil`),
  ADD CONSTRAINT `pajak_hamparan_ibfk_2` FOREIGN KEY (`kd_hasil`) REFERENCES `hasil_hamparan` (`kd_hasil`),
  ADD CONSTRAINT `pengurus_hamparan_ibfk_1` FOREIGN KEY (`kd_anggota`) REFERENCES `anggota_hamparan` (`kd_anggota`),
  ADD CONSTRAINT `pengurus_hamparan_ibfk_2` FOREIGN KEY (`kd_hasil`) REFERENCES `hasil_hamparan` (`kd_hasil`),
  ADD CONSTRAINT `pengurus_hamparan_ibfk_3` FOREIGN KEY (`kd_harga`) REFERENCES `harga_hamparan` (`kd_harga`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
