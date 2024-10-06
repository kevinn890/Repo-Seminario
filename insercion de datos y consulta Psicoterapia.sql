USE sistemadegestionpsicoterapeutica;
/*insercion de datos*/

INSERT INTO obrasocial
VALUES (NULL,'Largavida','plan economico',01145545551),
(null,'bienestar','plan estandar',01123324552);

INSERT INTO paciente
VALUES (45667887,30456678870,'roberto','perez','robertoperez@mail.com',0113443685,'belgrano 1122','soltero',1,0,'diagnostico de prueba 1','Gonzalez Pedro',01132345455,1),
(54679833,20546798334,'flor', 'marin','flor@mail.com',01198992111,'roca 345','soltero',0,1,'diagnostico de prueba 1','aldana Barca',01154321223,1),
(21099566,5021099566,'Pablo','Gomez','gpablo@mail.com',01124544342,'iriart 233','casado',1,1,'diagnotico de prueba 3','Gomez Ariel',01165667321,2),
(45511233,30455112333,'Mariano', 'Roque','roque@mail.com',01144332211,'dellepiane 455','divorciado',0,0,'diagnostico de prueba 2','Heiden Marcela',01189912344,1);

INSERT INTO grupoconviviente
VALUES (null,45667887,'padre','Marcos',45),
(null,45667887,'madre','Ana',43),
(null,54679833,'madre','Maria',51),
(null,21099566,'hermano','Rodrigo',32),
(null,45511233,'madre','Marcela',78);



INSERT INTO prestacion
VALUES (null,'sesion adulto',0.3,1),
(null,'sesion infantil',0.6,1),
(null,'test psicologico',0.4,1),
(null,'sesion adulto',0.4,2),
(null,'sesion infantil',1,2),
(null,'test psicologico',0.5,2);

INSERT INTO cita
VALUES (null,45667887,'2024-12-01 11:30:00.0000000','Lic. Gonzalez Maria',1),
(null,45667887,'2024-09-01 10:00:00.0000000','Lic. Gonzalez Maria',0),
(null,54679833,'2024-10-10 09:00:00.0000000','Lic. Gonzalez Maria',1),
(null,21099566,'2024-10-21 16:00:00.0000000','Lic. Darsio Marco',1),
(null,21099566,'2024-06-02 15:45:00.0000000','Lic. Darsio Marco',0),
(null,45511233,'2024-05-05 15:00:00.0000000','Lic. Martinez Sofia',0);

/*consultas*/

/*consulta por grupo conviviente por dni del paciente*/
SELECT * FROM grupoconviviente
WHERE Paciente_dni=45667887;

/*consulta por pacientes psiquiatricos ordenados por dni*/
SELECT * FROM paciente
WHERE pacientePsiquiatrico=1
ORDER BY dni;

/*consulta por proxima cita*/
SELECT * FROM cita
WHERE Paciente_dni=45667887 AND citaActiva=1
ORDER BY fechaYHora  
LIMIT 1;

/*consulta por pacientes por estado civil*/
SELECT * FROM paciente
WHERE estadoCivil = 'soltero';

/*consulta de prestaciones de obra social del paciente por dni*/
SELECT a.dni,a.nombre,a.apellido,a.ObraSocial_idObraSocial,b.*,c.*
FROM paciente AS a
INNER JOIN obrasocial AS b
ON a.ObraSocial_idObraSocial=b.idObraSocial
INNER JOIN prestacion AS c
ON b.idObraSocial=c.ObraSocial_idObraSocial
WHERE a.dni=45667887;



