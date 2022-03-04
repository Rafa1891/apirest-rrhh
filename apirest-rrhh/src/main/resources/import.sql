INSERT INTO departamentos(nombre,ubicacion) VALUES ('Ventas','Langreo');
INSERT INTO departamentos(nombre,ubicacion) VALUES ('Recursos','Madrid');

INSERT INTO empleados(DNI,nombre,salario,telefono,id_departamento) VALUES ('71902900R','Rafa',15000,663811558,1);
INSERT INTO empleados(DNI,nombre,salario,telefono,id_departamento) VALUES ('45622233Z','Jose',14000,654333222,2);

INSERT INTO jefes(DNI,nombre,salario,telefono,id_departamento) VALUES ('87444231A','Manuel',23000,613444576,2);
INSERT INTO jefes(DNI,nombre,salario,telefono,id_departamento) VALUES ('98377766B','María',25000,622444598,1);


INSERT INTO usuarios (username,password,enabled) VALUES ('Santi','$2a$10$j6/hnFZgnuL5CrrA04q/T.WxXz6qI.N0Y4plgTyefs2YeZ4xTen4a',1);
INSERT INTO usuarios (username,password,enabled) VALUES ('Rafa','$2a$10$/iIppF.3RXfFqe5pX0kyVerGkT0MS.l65VclrkceRUM8cpqHCxJ4q',1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(1,1);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,2);
INSERT INTO usuarios_roles (usuario_id,rol_id) VALUES(2,1);