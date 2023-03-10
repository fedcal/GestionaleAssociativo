CREATE DATABASE IF NOT EXISTS associazione;

CREATE TABLE IF NOT EXISTS socio(tessera int primary key, data_iscrizione varchar(50) not null, data_approvazione varchar(50)
, cognome varchar(50) not null, nome varchar(50) not null, nascita varchar(50) not null, luogo_nascita varchar(50), via varchar(150) not null,
citta varchar(50) not null,cap varchar(10), telefono varchar(15) not null, provincia varchar(50) not null, email varchar(200) not null,
data_annullamento varchar(50), consenso varchar(5), minorenne varchar(5),note varchar(500), int fk_ruolo not null, foreign key (fk_ruolo) references ruolo(id));

CREATE TABLE IF NOT EXISTS ruolo(idRuolo int primary key, titolo varchar(20));

INSERT INTO ruolo VALUES (0,"Socio"),(1,"Carica vuota"),(2, "Segretario"),(3,"Vice Presidente"),(4,"Presidente"),(5,"Volontario");