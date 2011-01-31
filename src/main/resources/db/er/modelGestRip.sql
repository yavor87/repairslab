CREATE DATABASE 'C:\wip\eclipse\f2\workspace\it.f2.gestRip\db\gestRip.fbd' page_size 8192;
CONNECT 'C:\wip\eclipse\f2\workspace\it.f2.gestRip\db\gestRip.fbd' user SYSDBA password masterkey;

drop table tipoapparecchiature;

CREATE TABLE tipoapparecchiature (
  id INTEGER NOT NULL,
  nome VARCHAR(50),
  descrizione VARCHAR(200),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
);

drop table schede;

CREATE TABLE schede (
  id INTEGER NOT NULL,
  idCliente INTEGER,
  idStato INTEGER,
  idTipoRip INTEGER,
  accessoriConsegnati VARCHAR(300),
  statoGenerale VARCHAR(300),
  difettoSegnalato VARCHAR(300),
  nonConform VARCHAR(300),
  descrizioneRiparazione VARCHAR(300),
  noteStampa VARCHAR(300),
  noteUsoInterno VARCHAR(300),
  costoPreventivato FLOAT,
  costoInterno FLOAT,
  pagatoDalCliente FLOAT,
  dataInserimento DATE,
  dataChiusura DATE,
  descApparecchio VARCHAR(200),
  imei VARCHAR(30),
  serial VARCHAR(30),
  idTipoApparecchiatura INTEGER,
  idModello INTEGER,
  idMarca INTEGER,
  numeroDatiAcq VARCHAR(100),
  dataDatiAcq DATE,
  idTipoDatiAcq INTEGER,
  deleted VARCHAR(1),
  PRIMARY KEY(id)
);

drop table tpodatiacquisto;

CREATE TABLE tpodatiacquisto (
  id INTEGER NOT NULL,
  tipo VARCHAR(45),
  PRIMARY KEY(id)
);

drop table tiporiparazione;

CREATE TABLE tiporiparazione (
  id INTEGER NOT NULL,
  nomeTipoRip VARCHAR(50),
  descTipoRip VARCHAR(200),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
);

drop table clienti;

CREATE TABLE clienti (
  id INTEGER NOT NULL,
  nome VARCHAR(50),
  cognome VARCHAR(50),
  pIva VARCHAR(50),
  azienda VARCHAR(15),
  phone VARCHAR(30),
  mobilePhone VARCHAR(30),
  email VARCHAR(50),
  indirizzo VARCHAR(100),
  city VARCHAR(50),
  PRIMARY KEY(id)
);

drop table anastati;

CREATE TABLE anastati (
  id INTEGER NOT NULL,
  nomeStato VARCHAR(50),
  descStato VARCHAR(200),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
);

drop table modelli;

CREATE TABLE modelli (
  id INTEGER NOT NULL,
  nome VARCHAR(50),
  descModello VARCHAR(200),
  flagAttivo VARCHAR(1),
  idMarchi INTEGER,
  idTipoApp INTEGER,
  PRIMARY KEY(id)
);

drop table marchi;

CREATE TABLE marchi (
  id INTEGER NOT NULL,
  nome VARCHAR(50),
  descrizione VARCHAR(200),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
);


