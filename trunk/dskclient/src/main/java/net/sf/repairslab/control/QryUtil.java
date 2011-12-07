package net.sf.repairslab.control;

import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.ui.messages.Messages;


public class QryUtil {
	
	public static String TABLE_PREFIX = EnvProperties.getInstance().getProperty(EnvProperties.DB_TABLE_PREFIX);
	
	public static String QRY_METADATA_VERSION = "select value from " + TABLE_PREFIX + "info where id = 'metadata_version'";
	
	public static String QRY_ANA_STATI = 
		"select " +
		" id," +
		" nomeStato," +
		" descStato," +
		" flagAttivo" +
		" from " + TABLE_PREFIX + "anastati";
	
	public static String QRY_ANA_CLIENTI = 
		"select " +
		" id," +
		" nome," +
		" cognome," +
		" piva," +
		" azienda," +
		" phone," +
		" mobilephone," +
		" email," +
		" indirizzo," +
		" city" +
		" from " + TABLE_PREFIX + "clienti";

	public static String QRY_ANA_MARCHI = 
		"SELECT " +
		" id," +
		" nome," +
		" descrizione," +
		" flagAttivo" +
		" FROM " + TABLE_PREFIX + "marchi";

	public static String QRY_ANA_TIPO_DATI_ACQ = 
		"SELECT " +
		" id," +
		" tipo " +
		" FROM " + TABLE_PREFIX + "tpodatiacquisto"	;
	
	public static String QRY_ANA_TIPO_OGGETTO = 
		"SELECT " +
		" id," +
		" nome," +
		" descrizione," +
		" flagAttivo " +
		" FROM " + TABLE_PREFIX + "tipoapparecchiature"	;
	
//	public static String JASPER_ALTERNATIVO =
//		" SELECT" +
//		" id" +
//		" VERSIONE" +
//		" FROM jasper" ;

	public static String QRY_ANA_TIPO_RIP = 
		"SELECT " +
		" id," +
		" nomeTipoRip," +
		" descTipoRip," +
		" flagAttivo " +
		" FROM " + TABLE_PREFIX + "tiporiparazione"	;
	
	public static String QRY_ANA_MODELLI = 
		"SELECT " +
		" id," +
		" nome," +
		" descModello," +
		" idMarchi," +
		" idTipoApp," +
		" flagAttivo " +
		" FROM " + TABLE_PREFIX + "modelli"	;
	
	public static String QRY_LISTA_SCHEDE_ALL = 
		"SELECT " 
		+ " " + TABLE_PREFIX + "schede.id as \""+Messages.getString("VcIfrListaSchede.qryId")+"\" ," 
		+ " " + TABLE_PREFIX + "anastati.nomeStato as \""+Messages.getString("VcIfrListaSchede.qryStato")+"\" ,"
		+ " " + TABLE_PREFIX + "schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
		+ " " + TABLE_PREFIX + "schede.dataChiusura as \""+Messages.getString("VcIfrListaSchede.qryCloseDate")+"\" ,"
		+ " " + TABLE_PREFIX + "tipoapparecchiature.nome as \""+Messages.getString("VcIfrListaSchede.qryEqpType")+"\" ,"
		+ " " + TABLE_PREFIX + "marchi.nome as \""+Messages.getString("VcIfrListaSchede.qryBrand")+"\" ,"
		+ " " + TABLE_PREFIX + "modelli.nome as \""+Messages.getString("VcIfrListaSchede.qryModel")+"\" ,"
		+ " " + TABLE_PREFIX + "clienti.nome as \""+Messages.getString("VcIfrListaSchede.qryCustomerName")+"\" ,"
		+ " " + TABLE_PREFIX + "clienti.cognome as \""+Messages.getString("VcIfrListaSchede.qryCustomerSur")+"\" "
		+ " FROM " 
		+ " " + TABLE_PREFIX + "schede " 
		+ " LEFT JOIN " + TABLE_PREFIX + "anastati on " + TABLE_PREFIX + "schede.idStato=" + TABLE_PREFIX + "anastati.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "tipoapparecchiature on " + TABLE_PREFIX + "schede.idTipoApparecchiatura=" + TABLE_PREFIX + "tipoapparecchiature.id " //$NON-NLS-1$
		+ " LEFT JOIN " + TABLE_PREFIX + "marchi on " + TABLE_PREFIX + "schede.idMarca=" + TABLE_PREFIX + "marchi.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "modelli on " + TABLE_PREFIX + "schede.idModello=" + TABLE_PREFIX + "modelli.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "clienti on " + TABLE_PREFIX + "schede.idCliente=" + TABLE_PREFIX + "clienti.id "
		+ " WHERE " + TABLE_PREFIX + "schede.deleted is null "; 
	
	public static String QRY_LISTA_SCHEDE_DEL_ALL = 
		"SELECT " 
		+ " " + TABLE_PREFIX + "schede.id as 			  \""+Messages.getString("VcIfrListaSchede.qryId")+"\" ," 
		+ " " + TABLE_PREFIX + "anastati.nomeStato as \""+Messages.getString("VcIfrListaSchede.qryStato")+"\" ,"
		+ " " + TABLE_PREFIX + "schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
		+ " " + TABLE_PREFIX + "schede.dataChiusura as \""+Messages.getString("VcIfrListaSchede.qryCloseDate")+"\" ,"
		+ " " + TABLE_PREFIX + "tipoapparecchiature.nome as \""+Messages.getString("VcIfrListaSchede.qryEqpType")+"\" ,"
		+ " " + TABLE_PREFIX + "marchi.nome as \""+Messages.getString("VcIfrListaSchede.qryBrand")+"\" ,"
		+ " " + TABLE_PREFIX + "modelli.nome as \""+Messages.getString("VcIfrListaSchede.qryModel")+"\" ,"
		+ " " + TABLE_PREFIX + "clienti.nome as \""+Messages.getString("VcIfrListaSchede.qryCustomerName")+"\" ,"
		+ " " + TABLE_PREFIX + "clienti.cognome as \""+Messages.getString("VcIfrListaSchede.qryCustomerSur")+"\" "
		+ " FROM " 
		+ " " + TABLE_PREFIX + "schede " 
		+ " LEFT JOIN " + TABLE_PREFIX + "anastati on " + TABLE_PREFIX + "schede.idStato=" + TABLE_PREFIX + "anastati.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "tipoapparecchiature on " + TABLE_PREFIX + "schede.idTipoApparecchiatura=" + TABLE_PREFIX + "tipoapparecchiature.id " //$NON-NLS-1$
		+ " LEFT JOIN " + TABLE_PREFIX + "marchi on " + TABLE_PREFIX + "schede.idMarca=" + TABLE_PREFIX + "marchi.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "modelli on " + TABLE_PREFIX + "schede.idModello=" + TABLE_PREFIX + "modelli.id " 
		+ " LEFT JOIN " + TABLE_PREFIX + "clienti on " + TABLE_PREFIX + "schede.idCliente=" + TABLE_PREFIX + "clienti.id "
		+ " WHERE " + TABLE_PREFIX + "schede.deleted = 'S' "; 
	
	public static String QRY_REPORT = 
		"SELECT " +
		TABLE_PREFIX + "schede.id, " +
		TABLE_PREFIX + "clienti.nome||' '||" + TABLE_PREFIX + "clienti.cognome as nome_cognome, " +
		TABLE_PREFIX + "clienti.indirizzo, " +
		TABLE_PREFIX + "clienti.city, " +
		TABLE_PREFIX + "clienti.phone||' - '||" + TABLE_PREFIX + "clienti.mobilePhone as phone, " +
		TABLE_PREFIX + "schede.dataInserimento, " +
		TABLE_PREFIX + "tipoapparecchiature.nome as tipi, " +
		TABLE_PREFIX + "marchi.nome||' / '||" + TABLE_PREFIX + "modelli.nome as modelli, " +
		TABLE_PREFIX + "schede.serial, " +
		TABLE_PREFIX + "schede.accessoriConsegnati, " +
		TABLE_PREFIX + "schede.statoGenerale, " +
		TABLE_PREFIX + "schede.difettoSegnalato, " +
		TABLE_PREFIX + "tiporiparazione.nomeTipoRip, " +
		TABLE_PREFIX + "tpodatiacquisto.tipo as tpda, " +
		TABLE_PREFIX + "schede.numeroDatiAcq, " +
		TABLE_PREFIX + "schede.dataDatiAcq, " +
		TABLE_PREFIX + "schede.costoPreventivato, " +
		TABLE_PREFIX + "schede.descrizioneRiparazione, " +
		TABLE_PREFIX + "schede.nonConform, " +
		TABLE_PREFIX + "schede.noteStampa, " +
		TABLE_PREFIX + "schede.dataChiusura, " +
		TABLE_PREFIX + "schede.pagatoDalCliente " +
		"FROM " +
		TABLE_PREFIX + "schede " +
		"LEFT JOIN " + TABLE_PREFIX + "tipoapparecchiature on idTipoApparecchiatura=" + TABLE_PREFIX + "tipoapparecchiature.id " +
		"LEFT JOIN " + TABLE_PREFIX + "marchi on idMarca=" + TABLE_PREFIX + "marchi.id " +
		"LEFT JOIN " + TABLE_PREFIX + "modelli on idModello=" + TABLE_PREFIX + "modelli.id " +
		"LEFT JOIN " + TABLE_PREFIX + "clienti on idCliente=" + TABLE_PREFIX + "clienti.id " +
		"LEFT JOIN " + TABLE_PREFIX + "tiporiparazione on idTiporip=" + TABLE_PREFIX + "tiporiparazione.id " +
		"LEFT JOIN " + TABLE_PREFIX + "tpodatiacquisto on idTipoDatiAcq=" + TABLE_PREFIX + "tpodatiacquisto.id " +
		"WHERE " + TABLE_PREFIX + "schede.id = ";
	
//	public static String QRY_STATISTIC =
//		"SELECT "
//		+ " id as 			  \""+Messages.getString("VcDlgStatistic.qryId")+"\" ," 
//		+ " schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
//		+ " pagatoDalCliente as \""+Messages.getString("VcDlgStatistic.qrypagatoDalCliente")+"\" ,"
//		+ " costoInterno as \""+Messages.getString("VcDlgStatistic.qrycostoInterno")+"\",  "
//		+ " pagatoDalCliente-costoInterno as \"Ricavo\" "
//		+ " FROM " + TABLE_PREFIX + "schede";

//	public static String QRY_STATISTIC2 =
//		"SELECT "
//		+ "'Calcolo Totale' as \"Riepilogo\", " 
//		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
//		+ " SUM(costoInterno) as \"Incasso\", "
//		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
//		+ " FROM schede "
//		+ "UNION "
//		+ "SELECT"
//		+ "'Mese Corrente' as \"Riepilogo\", " 
//		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
//		+ " SUM(costoInterno) as \"Incasso\", "
//		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
//		+ " FROM " 
//		+ " schede "
////		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())"
//		+ "UNION "
//		+ "SELECT"
//		+ "'Mese Precedente' as \"Riepilogo\", " 
//		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
//		+ " SUM(costoInterno) as \"Incasso\", "
//		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
//		+ " FROM " 
//		+ " schede ";
////		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())-1 ";

//	public static String QRY_STATISTIC3 =
//		"SELECT"
//		+ " SUM(costoInterno) as \""+Messages.getString("VcDlgStatistic.qrycostoInternoTM")+"\", "
//		+ " SUM(pagatoDalCliente) as \""+Messages.getString("VcDlgStatistic.qrypagatoDalClienteTM")+"\" "
//		+ " FROM " 
//		+ " schede " ;
////		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())";
//	
//	public static String QRY_STATISTIC4 =
//		"SELECT"
//		+ " SUM(costoInterno) as \""+Messages.getString("VcDlgStatistic.qrycostoInternoLM")+"\", "
//		+ " SUM(pagatoDalCliente) as \""+Messages.getString("VcDlgStatistic.qrypagatoDalClienteLM")+"\" "
//		+ " FROM " 
//		+ " schede ";
////		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())-1 ";
	
	
	
	
	
}