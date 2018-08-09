/* Insertar Alergenos */
INSERT INTO alergenos (id,nombre, foto) values (1,'Gluten', '/imagenes/alergenos/Gluten.ico');
INSERT INTO alergenos (id,nombre, foto) values (2,'Marisco', '/imagenes/alergenos/Moluscos.ico');
INSERT INTO alergenos (id,nombre, foto) values (3,'Huevos', '/imagenes/alergenos/Huevo.ico');
INSERT INTO alergenos (id,nombre, foto) values (4,'Cacahuetes', '/imagenes/alergenos/Cacahuete.ico');
INSERT INTO alergenos (id,nombre, foto) values (5,'Soja', '/imagenes/alergenos/Soja.ico');
INSERT INTO alergenos (id,nombre, foto) values (6,'Lacteos', '/imagenes/alergenos/Lacteos.ico');
INSERT INTO alergenos (id,nombre, foto) values (7,'Frutos secos', '/imagenes/alergenos/FrutosSecos.ico');
INSERT INTO alergenos (id,nombre, foto) values (8,'Apio', '/imagenes/alergenos/Apio.ico');
INSERT INTO alergenos (id,nombre, foto) values (9,'Mostaza', '/imagenes/alergenos/Mostaza.ico');
INSERT INTO alergenos (id,nombre, foto) values (10,'Sesamo', '/imagenes/alergenos/Sesamo.ico');
INSERT INTO alergenos (id,nombre, foto) values (11,'Moluscos', '/imagenes/alergenos/Moluscos.ico');
INSERT INTO alergenos (id,nombre, foto) values (12,'Pesacado', '/imagenes/alergenos/Pescado.ico');
 /* Insertar Componentes*/
/* BEBIDAS*/
INSERT INTO componentes (id, nombre, stock) values (1,'CocaCola 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (2,'Fanta Naranja 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (3,'Fanta Limon 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (4,'Nestea 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (5,'Acuraius 0.3', 10);
INSERT INTO componentes (id,nombre, stock) values (6,'Agua 0.5', 10);
INSERT INTO componentes (id,nombre, stock) values (7,'Agua Gas 0.5', 10);
INSERT INTO componentes (id,nombre, stock) values (8,'Botella TierraBlanca 3/4', 10);
INSERT INTO componentes (id,nombre, stock) values (9,'Botella Portos 3/4', 10);
INSERT INTO componentes (id,nombre, stock) values (10,'Botella Penascal 3/4', 10);
INSERT INTO componentes (id,nombre, stock) values (11,'LECHE 1L', 10);
INSERT INTO componentes (id, nombre, stock) values(17, 'Cafe 1K', 10);
 /* COMIDAS */
INSERT INTO componentes (id,nombre, stock) values (12,'PAN BIMBO 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (13,'ATUN 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (14,'CARNE HAMBURGUESA', 10);
INSERT INTO componentes (id,nombre, stock) values (15,'QUESO LONCHA 0.33', 10);
INSERT INTO componentes (id,nombre, stock) values (16,'HUEVO', 10);
 /* Alergenos Comida */
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(11, 6);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(12, 10);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(12,1);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(13, 12);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(14, 1);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(15, 6);
INSERT INTO componentes_alergenos(componente_id, alergenos_id) values(16, 3);
 /* Agregar Productos */
 INSERT INTO Productos (id, nombre, precio) values (1, 'Copa vino blanco', 1.5);
INSERT INTO Productos (id, nombre, precio) values (2, 'Copa vino tinto', 1.5);
INSERT INTO Productos (id, nombre, precio) values (3, 'Copa vino rosado', 1.5);
INSERT INTO Productos (id, nombre, precio) values (4, 'Cocacola', 1.5);
INSERT INTO Productos (id, nombre, precio) values (5, 'Cafe con leche ', 1);
INSERT INTO Productos (id, nombre, precio) values (6, 'Cafe manchado', 1);
INSERT INTO Productos (id, nombre, precio) values (7, 'Cafe capuchino', 1.2);
INSERT INTO Productos (id, nombre, precio) values (8, 'Hamburguesa con queso', 7);
INSERT INTO Productos (id, nombre, precio) values (9, 'SandWich Atun', 7);
INSERT INTO Productos (id, nombre, precio) values (10, 'FANTA Naranja', 1.5);
INSERT INTO Productos (id, nombre, precio) values (11, 'Penascal 3/4', 8);
INSERT INTO Productos (id, nombre, precio) values (12, 'Portos 3/4', 8);
INSERT INTO Productos (id, nombre, precio) values (13, 'Tiera Blanca 3/4', 8);
 /* Conexion de Componente y Producto */
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (1, 8, 0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (2, 9, 0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (3, 10, 0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (4,1,1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (5,11,0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (5,17,0.01);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (6,11,0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (6,17,0.02);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (7,11,0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (7,17,0.2);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (8, 12, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (8, 14, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (8,15, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (8,16, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (9, 12, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (9, 13, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (9, 15, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (11, 10, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (12, 9, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (13, 8, 1);
INSERT INTO componentes_productos( id_producto, id_componente, cantidad) VALUES (10, 2, 1);
 /* CATEGORIAS */
INSERT INTO categorias (id, nombre, foto) VALUES (1,'CAFE', '/imagenes/categorias/Coffe.png');
INSERT INTO categorias (id, nombre, foto) VALUES (2,'ALCOHOL', '/imagenes/categorias/cocktel.png');
INSERT INTO categorias (id, nombre, foto) VALUES (3,'BEBIDAS', '/imagenes/categorias/water.png');
INSERT INTO categorias (id, nombre, foto) VALUES (4,'REFRESO', '/imagenes/categorias/Coffe.png');
INSERT INTO categorias (id, nombre, foto) VALUES (5,'VINO', '/imagenes/categorias/wine.png');
INSERT INTO categorias (id, nombre, foto) VALUES (6,'COMIDA', '/imagenes/categorias/food.png');
INSERT INTO categorias (id, nombre, foto) VALUES (7,'SNACK', '/imagenes/categorias/fastfood.png');
 /* CONEION PPODUCTOS CATEGORIAS */
 INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (1, 2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (1, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (1, 5);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (2, 2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (2, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (2, 5);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (3, 2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (3, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (3, 5);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (4, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (4, 4);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (5, 1);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (5, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (6, 1);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (6, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (7, 1);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (7, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (8, 6);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (8, 7);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (9, 6);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (9, 7);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (10, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (10, 4);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (11,2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (12, 2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (13, 2);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (11, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (12, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (13, 3);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (11, 5);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (12, 5);
INSERT INTO categorias_productos (id_producto, id_categoria) VALUES (13, 5);


/* INSERTAR CLIENTES Y SUS ROLES */
/* Creamos algunos usuarios con sus roles */
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `users` (username, password, enabled) VALUES ('user','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_USER');