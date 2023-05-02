CREATE SCHEMA coderHouse;

USE coderHouse;

CREATE TABLE  clientes (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(75) NOT NULL,
apellido VARCHAR(75) NOT NULL,
dni VARCHAR(11) UNIQUE NOT NULL);



CREATE TABLE  productos (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
descripcion VARCHAR(100) NOT NULL,
codigo VARCHAR(50) NOT NULL,
stock INTEGER NOT NULL,
precio DOUBLE  NOT NULL);



CREATE TABLE  facturas (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
cliente_id INTEGER NOT NULL,
fcreacion DATE NOT NULL,
total DOUBLE NOT NULL,
CONSTRAINT fk_facturas_clientes FOREIGN KEY (cliente_id) REFERENCES clientes(id));



CREATE TABLE  detallefacturas (
dfacturas_id INTEGER PRIMARY KEY AUTO_INCREMENT,
factura_id INTEGER NOT NULL,
producto_id INTEGER NOT NULL,
cantidad INTEGER NOT NULL,
precio DOUBLE NOT NULL,
CONSTRAINT fk_detallefacturas_facturas FOREIGN KEY (factura_id) REFERENCES facturas(id),
CONSTRAINT fk_detallefacturas_productos FOREIGN KEY (producto_id) REFERENCES productos(id));

  INSERT INTO clientes(nombre, apellido,dni) VALUES
  ('Andrea','Testa','25442630'),
  ('Estefania','Dorfmuller','46558669'),
  ('Patricio','Dorfmuller','26545454');

 INSERT INTO productos(descripcion, codigo ,stock, precio) VALUES
  ('Producto1','001', 20, 250.0),
  ('Producto2','002', 30, 120.0),
  ('Producto3','003', 10, 350.0),
  ('Producto4','004', 15, 155.0);



