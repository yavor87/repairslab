CREATE TABLE &PREF&tipoapparecchiature (
  id INTEGER NOT NULL,
  nome VARCHAR(200),
  descrizione VARCHAR(4000),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&schede (
  id INTEGER NOT NULL,
  idCliente INTEGER,
  idStato INTEGER,
  idTipoRip INTEGER,
  accessoriConsegnati VARCHAR(2500),
  statoGenerale VARCHAR(2500),
  difettoSegnalato VARCHAR(2500),
  nonConform VARCHAR(2500),
  descrizioneRiparazione VARCHAR(3000),
  noteStampa VARCHAR(2500),
  noteUsoInterno VARCHAR(2500),
  costoPreventivato FLOAT,
  costoInterno FLOAT,
  pagatoDalCliente FLOAT,
  dataInserimento DATE,
  dataChiusura DATE,
  descApparecchio VARCHAR(2500),
  imei VARCHAR(200),
  serial VARCHAR(200),
  idTipoApparecchiatura INTEGER,
  idModello INTEGER,
  idMarca INTEGER,
  numeroDatiAcq VARCHAR(200),
  dataDatiAcq DATE,
  idTipoDatiAcq INTEGER,
  deleted VARCHAR(1),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&tpodatiacquisto (
  id INTEGER NOT NULL,
  tipo VARCHAR(200),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&tiporiparazione (
  id INTEGER NOT NULL,
  nomeTipoRip VARCHAR(200),
  descTipoRip VARCHAR(4000),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&clienti (
  id INTEGER NOT NULL,
  nome VARCHAR(200),
  cognome VARCHAR(200),
  pIva VARCHAR(200),
  azienda VARCHAR(200),
  phone VARCHAR(200),
  mobilePhone VARCHAR(200),
  email VARCHAR(200),
  indirizzo VARCHAR(200),
  city VARCHAR(200),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&anastati (
  id INTEGER NOT NULL,
  nomeStato VARCHAR(200),
  descStato VARCHAR(4000),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&modelli (
  id INTEGER NOT NULL,
  nome VARCHAR(200),
  descModello VARCHAR(4000),
  flagAttivo VARCHAR(1),
  idMarchi INTEGER,
  idTipoApp INTEGER,
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&marchi (
  id INTEGER NOT NULL,
  nome VARCHAR(200),
  descrizione VARCHAR(4000),
  flagAttivo VARCHAR(1),
  PRIMARY KEY(id)
) ENGINE = InnoDB;

CREATE TABLE &PREF&info (
  id VARCHAR(100) NOT NULL,
  value VARCHAR(300) NOT NULL,
  PRIMARY KEY(id)
) ENGINE = InnoDB;

INSERT INTO &PREF&info (id, value) VALUES ('metadata_version', '1.0.6');
