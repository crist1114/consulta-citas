create table paciente (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 tipo_paciente varchar(100) not null,
 primary key (id)
);

create table historia (
 id int(11) not null auto_increment,
 id_paciente int(11) not null,
 fecha_emision DATE not null,
 ubicacion varchar(100) not null,
 primary key (id)
);

create table cita (
 id int(11) not null auto_increment,
 id_paciente int(11) not null,
 tipo_procedimiento varchar(100) not null,
 fecha DATE not null,
 estado varchar(50),
 primary key (id)
);

ALTER TABLE historia
ADD CONSTRAINT paciente_fk
  FOREIGN KEY (id_paciente)
  REFERENCES paciente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE cita
ADD CONSTRAINT paciente_fk
  FOREIGN KEY (id_paciente)
  REFERENCES paciente (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;