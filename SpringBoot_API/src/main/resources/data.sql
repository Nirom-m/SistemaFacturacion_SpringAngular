
-- regionesEntity.sql
INSERT INTO region (id, nombre) VALUES (1, 'África');
INSERT INTO region (id, nombre) VALUES (2, 'Asia');
INSERT INTO region (id, nombre) VALUES (3, 'Europa');
INSERT INTO region (id, nombre) VALUES (4, 'América del Norte');
INSERT INTO region (id, nombre) VALUES (5, 'América del Sur');
INSERT INTO region (id, nombre) VALUES (6, 'Oceanía');
INSERT INTO region (id, nombre) VALUES (7, 'Antártida');
INSERT INTO region (id, nombre) VALUES (8, 'Medio Oriente');
INSERT INTO region (id, nombre) VALUES (9, 'Caribe');
INSERT INTO region (id, nombre) VALUES (10, 'América Central');
-- cliente.sql

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('John', 'Doe', 'john.doe@email.com', '2023-08-01', 1);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Jane', 'Smith', 'jane.smith@email.com', '2023-08-02', 2);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Michael', 'Johnson', 'michael.johnson@email.com', '2023-08-03', 1);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Emily', 'Williams', 'emily.williams@email.com', '2023-08-04', 3);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('David', 'Brown', 'david.brown@email.com', '2023-08-05', 2);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Sarah', 'Jones', 'sarah.jones@email.com', '2023-08-06', 3);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Daniel', 'Davis', 'daniel.davis@email.com', '2023-08-07', 1);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Jennifer', 'Miller', 'jennifer.miller@email.com', '2023-08-08', 2);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Christopher', 'Wilson', 'christopher.wilson@email.com', '2023-08-09', 3);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Jessica', 'Martinez', 'jessica.martinez@email.com', '2023-08-10', 1);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Andrew', 'Garcia', 'andrew.garcia@email.com', '2023-08-11', 2);

INSERT INTO cliente (nombre, apellido, email, create_at, region_id)
VALUES ('Elizabeth', 'Lopez', 'elizabeth.lopez@email.com', '2023-08-12', 3);

-- usuarios con roles

-- Inserción de usuario 1
INSERT INTO usuarios (username, password, enable)
VALUES ('nick', '$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq', 1);

-- Inserción de usuario 2
INSERT INTO usuarios (username, password, enable)
VALUES ('admin', '$2a$10$56VCAiApLO8NQYeOPiu2De/EBC5RWrTZvLl7uoeC3r7iXinRR1iiq', 1);

-- Inserción del rol "ROLE_USER"
INSERT INTO roles (nombre)
VALUES ('ROLE_USER');

-- Inserción del rol "ROLE_ADMIN"
INSERT INTO roles (nombre)
VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id)
VALUES (1, 1);

INSERT INTO usuarios_roles (usuario_id, rol_id)
VALUES (2, 2);

INSERT INTO usuarios_roles (usuario_id, rol_id)
VALUES (2, 1);

/* Populate tabla productos */
INSERT INTO producto (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO producto (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO factura (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO factura (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO factura_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);
