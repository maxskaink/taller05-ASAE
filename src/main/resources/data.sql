-- Insertar oficinas primero (para que existan cuando se referencien)
INSERT INTO Oficina (nombre, ubicacion) VALUES 
('Oficina A-201', 'Bloque A - Segundo Piso');
INSERT INTO Oficina (nombre, ubicacion) VALUES 
('Oficina B-105', 'Bloque B - Primer Piso');
INSERT INTO Oficina (nombre, ubicacion) VALUES 
('Oficina C-301', 'Bloque C - Tercer Piso');
INSERT INTO Oficina (nombre, ubicacion) VALUES 
('Oficina D-202', 'Bloque D - Segundo Piso');

-- Insertar datos en la tabla Persona (que incluye Docente por herencia SINGLE_TABLE)
INSERT INTO Persona (DTYPE, nombre, apellido, correo, oficina_id) VALUES 
('Docente', 'María', 'González', 'maria.gonzalez@unicauca.edu.co', 1);
INSERT INTO Persona (DTYPE, nombre, apellido, correo, oficina_id) VALUES 
('Docente', 'Carlos', 'Rodríguez', 'carlos.rodriguez@unicauca.edu.co', 2);
INSERT INTO Persona (DTYPE, nombre, apellido, correo, oficina_id) VALUES 
('Docente', 'Ana', 'Martínez', 'ana.martinez@unicauca.edu.co', 3);
INSERT INTO Persona (DTYPE, nombre, apellido, correo, oficina_id) VALUES 
('Administrativo', 'Pedro', 'Silva', 'pedro.silva@unicauca.edu.co', 4);

-- Insertar asignaturas
INSERT INTO Asignatura (nombre, codigo) VALUES 
('Arquitecturas de Software', 'ASAE');
INSERT INTO Asignatura (nombre, codigo) VALUES 
('Base de Datos', 'BD');
INSERT INTO Asignatura (nombre, codigo) VALUES 
('Ingeniería de Software', 'IS');
INSERT INTO Asignatura (nombre, codigo) VALUES 
('Programación Web', 'PW');
INSERT INTO Asignatura (nombre, codigo) VALUES 
('Sistemas Distribuidos', 'SD');

-- Insertar espacios físicos
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Aula Magna', 100);
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Laboratorio 1', 25);
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Laboratorio 2', 25);
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Aula 301', 40);
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Aula 302', 35);
INSERT INTO EspacioFisico (nombre, capacidad) VALUES 
('Sala de Conferencias', 50);

-- Insertar cursos
INSERT INTO Curso (nombre, asignatura_id) VALUES 
('ASAE - Grupo A', 1);
INSERT INTO Curso (nombre, asignatura_id) VALUES 
('ASAE - Grupo B', 1);
INSERT INTO Curso (nombre, asignatura_id) VALUES 
('Base de Datos - Grupo 1', 2);
INSERT INTO Curso (nombre, asignatura_id) VALUES 
('Ingeniería de Software - Grupo 1', 3);
INSERT INTO Curso (nombre, asignatura_id) VALUES 
('Programación Web - Grupo 1', 4);

-- Insertar relaciones curso-docente (tabla intermedia)
INSERT INTO curso_docente (curso_id, docente_id) VALUES (1, 1);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (1, 2);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (2, 1);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (3, 2);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (4, 3);
INSERT INTO curso_docente (curso_id, docente_id) VALUES (5, 3);

-- Insertar franjas horarias
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Lunes', '08:00:00', '10:00:00', 1, 1);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Miércoles', '08:00:00', '10:00:00', 1, 1);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Viernes', '14:00:00', '16:00:00', 1, 2);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Martes', '10:00:00', '12:00:00', 2, 3);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Jueves', '10:00:00', '12:00:00', 2, 3);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Lunes', '14:00:00', '16:00:00', 3, 4);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Miércoles', '16:00:00', '18:00:00', 3, 2);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Martes', '08:00:00', '10:00:00', 4, 5);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Jueves', '14:00:00', '16:00:00', 4, 5);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Viernes', '08:00:00', '10:00:00', 5, 2);
INSERT INTO FranjaHorario (dia, horaInicio, horaFin, curso_id, espacio_fisico_id) VALUES 
('Viernes', '10:00:00', '12:00:00', 5, 3);