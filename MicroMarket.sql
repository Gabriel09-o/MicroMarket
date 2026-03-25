CREATE DATABASE MicroMarket;
USE MicroMarket;

CREATE TABLE Categories (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE Products (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    codigo_barras VARCHAR(100) UNIQUE NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    estado BOOLEAN DEFAULT TRUE,
    id_categoria INT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_categoria) REFERENCES Categories(id_categoria)
);

CREATE TABLE Proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    nit VARCHAR(50) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(200),
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE Productos_Proveedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    id_proveedor INT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_producto) REFERENCES Products(id_producto),
    FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id_proveedor)
);

-- 👨‍💼 EMPLEADOS
CREATE TABLE Empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    cargo ENUM('ADMINISTRADOR','CAJERO','AUXILIAR') NOT NULL,
    fecha_ingreso DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE Ventas (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_empleado INT NOT NULL,
    subtotal DECIMAL(10,2),
    iva DECIMAL(10,2),
    total DECIMAL(10,2),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado)
);

CREATE TABLE Detalle_Venta (
    id_detalle BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2),
    deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_venta) REFERENCES Ventas(id_venta),
    FOREIGN KEY (id_producto) REFERENCES Products(id_producto)
);