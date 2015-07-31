# MyFirstSpringMVCProject
My First Spring MVC Project

#base de datos: TestMVC
#usuario: bw_user
#pass: dba
#script para la tabla

CREATE TABLE Person (
  id int NOT NULL IDENTITY(1, 1) PRIMARY KEY,
  name varchar(20) NOT NULL DEFAULT '',
  country varchar(20) DEFAULT NULL
)
