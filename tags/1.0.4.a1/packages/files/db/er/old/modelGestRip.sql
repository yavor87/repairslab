CREATE TABLE tipoapparecchiature (
  id INTEGER(10) UNSIGNED NOT NULL,
  nome VARCHAR(50) NOT NULL,
  descrizione VARCHAR(200) NOT NULL,
  flagAttivo VARCHAR(1) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE schede (
  id INTEGER(11) NOT NULL,
  idCliente INTEGER(11) NOT NULL,
  idStato INTEGER(11) NOT NULL,
  idTipoRip INTEGER(11) NOT NULL,
  accessoriConsegnati VARCHAR(300) NOT NULL,
  statoGenerale VARCHAR(300) NOT NULL,
  difettoSegnalato VARCHAR(300) NOT NULL,
  nonConform VARCHAR(300) NOT NULL,
  descrizioneRiparazione VARCHAR(300) NOT NULL,
  noteStampa VARCHAR(300) NOT NULL,
  noteUsoInterno VARCHAR(300) NOT NULL,
  costoPreventivato FLOAT NOT NULL,
  costoInterno FLOAT NOT NULL,
  pagatoDalCliente FLOAT NOT NULL,
  dataInserimento DATE NOT NULL,
  dataChiusura DATE NOT NULL,
  descApparecchio VARCHAR(200) NOT NULL,
  imei VARCHAR(30) NOT NULL,
  serial VARCHAR(30) NOT NULL,
  idTipoApparecchiatura INTEGER(11) NOT NULL,
  idModello INTEGER(11) NOT NULL,
  idMarca INTEGER(11) NOT NULL,
  numeroDatiAcq VARCHAR(100) NOT NULL,
  dataDatiAcq DATE NOT NULL,
  idTipoDatiAcq INTEGER(11) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE tpodatiacquisto (
  id INTEGER(11) NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE tiporiparazione (
  id INTEGER(10) UNSIGNED NOT NULL,
  nomeTipoRip VARCHAR(50) NOT NULL,
  descTipoRip VARCHAR(200) NOT NULL,
  flagAttivo VARCHAR(1) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE clienti (
  id INTEGER(10) UNSIGNED NOT NULL,
  nome VARCHAR(50) NOT NULL,
  cognome VARCHAR(50) NOT NULL,
  pIva VARCHAR(50) NOT NULL,
  azienda VARCHAR(15) NOT NULL,
  phone VARCHAR(30) NOT NULL,
  mobilePhone VARCHAR(30) NOT NULL,
  email VARCHAR(50) NOT NULL,
  indirizzo VARCHAR(100) NOT NULL,
  city VARCHAR(50) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE anastati (
  id INTEGER(10) UNSIGNED NOT NULL,
  nomeStato VARCHAR(50) NOT NULL,
  descStato VARCHAR(200) NOT NULL,
  flagAttivo VARCHAR(1) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE modelli (
  id INTEGER(11) NOT NULL,
  nome VARCHAR(50) NOT NULL,
  descModello VARCHAR(200) NOT NULL,
  flagAttivo VARCHAR(1) NOT NULL,
  idMarchi INTEGER(11) NOT NULL,
  idTipoApp INTEGER(11) NOT NULL,
  PRIMARY KEY(id),
  INDEX index1(idMarchi)
);

CREATE TABLE marchi (
  id INTEGER(10) UNSIGNED NOT NULL,
  nome VARCHAR(50) NOT NULL,
  descrizione VARCHAR(200) NOT NULL,
  flagAttivo VARCHAR(1) NOT NULL,
  PRIMARY KEY(id)
);


