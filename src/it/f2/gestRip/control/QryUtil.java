package it.f2.gestRip.control;

import it.f2.gestRip.ui.messages.Messages;

public class QryUtil {
	
	public static String QRY_ANA_STATI = 
		"select " +
		" id," +
		" nomeStato," +
		" descStato," +
		" flagAttivo" +
		" from anastati";
	
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
		" from clienti";

	public static String QRY_ANA_MARCHI = 
		"SELECT " +
		" id," +
		" nome," +
		" descrizione," +
		" flagAttivo" +
		" FROM marchi";

	public static String QRY_ANA_TIPO_DATI_ACQ = 
		"SELECT " +
		" id," +
		" tipo " +
		" FROM tpodatiacquisto"	;
	
	public static String QRY_ANA_TIPO_OGGETTO = 
		"SELECT " +
		" id," +
		" nome," +
		" descrizione," +
		" flagAttivo " +
		" FROM tipoapparecchiature"	;
	
	public static String JASPER_ALTERNATIVO =
		" SELECT" +
		" id" +
		" VERSIONE" +
		" FROM jasper" ;

	public static String QRY_ANA_TIPO_RIP = 
		"SELECT " +
		" id," +
		" nomeTipoRip," +
		" descTipoRip," +
		" flagAttivo " +
		" FROM tiporiparazione"	;
	
	public static String QRY_ANA_MODELLI = 
		"SELECT " +
		" id," +
		" nome," +
		" descModello," +
		" idMarchi," +
		" idTipoApp," +
		" flagAttivo " +
		" FROM modelli"	;
	
	public static String QRY_LISTA_SCHEDE_ALL = 
		"SELECT " 
		+ " schede.id as \""+Messages.getString("VcIfrListaSchede.qryId")+"\" ," 
		+ " anastati.nomeStato as \""+Messages.getString("VcIfrListaSchede.qryStato")+"\" ,"
		+ " schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
		+ " schede.dataChiusura as \""+Messages.getString("VcIfrListaSchede.qryCloseDate")+"\" ,"
		+ " tipoapparecchiature.nome as \""+Messages.getString("VcIfrListaSchede.qryEqpType")+"\" ,"
		+ " marchi.nome as \""+Messages.getString("VcIfrListaSchede.qryBrand")+"\" ,"
		+ " modelli.nome as \""+Messages.getString("VcIfrListaSchede.qryModel")+"\" ,"
		+ " clienti.nome as \""+Messages.getString("VcIfrListaSchede.qryCustomerName")+"\" ,"
		+ " clienti.cognome as \""+Messages.getString("VcIfrListaSchede.qryCustomerSur")+"\" "
		+ " FROM " 
		+ " schede " 
		+ " LEFT JOIN anastati on schede.idStato=anastati.id " 
		+ " LEFT JOIN tipoapparecchiature on schede.idTipoApparecchiatura=tipoapparecchiature.id " //$NON-NLS-1$
		+ " LEFT JOIN marchi on schede.idMarca=marchi.id " 
		+ " LEFT JOIN modelli on schede.idModello=modelli.id " 
		+ " LEFT JOIN clienti on schede.idCliente=clienti.id "
		+ " WHERE schede.deleted is null "; 
	
	public static String QRY_LISTA_SCHEDE_DEL_ALL = 
		"SELECT " 
		+ " schede.id as 			  \""+Messages.getString("VcIfrListaSchede.qryId")+"\" ," 
		+ " anastati.nomeStato as \""+Messages.getString("VcIfrListaSchede.qryStato")+"\" ,"
		+ " schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
		+ " schede.dataChiusura as \""+Messages.getString("VcIfrListaSchede.qryCloseDate")+"\" ,"
		+ " tipoapparecchiature.nome as \""+Messages.getString("VcIfrListaSchede.qryEqpType")+"\" ,"
		+ " marchi.nome as \""+Messages.getString("VcIfrListaSchede.qryBrand")+"\" ,"
		+ " modelli.nome as \""+Messages.getString("VcIfrListaSchede.qryModel")+"\" ,"
		+ " clienti.nome as \""+Messages.getString("VcIfrListaSchede.qryCustomerName")+"\" ,"
		+ " clienti.cognome as \""+Messages.getString("VcIfrListaSchede.qryCustomerSur")+"\" "
		+ " FROM " 
		+ " schede " 
		+ " LEFT JOIN anastati on schede.idStato=anastati.id " 
		+ " LEFT JOIN tipoapparecchiature on schede.idTipoApparecchiatura=tipoapparecchiature.id " //$NON-NLS-1$
		+ " LEFT JOIN marchi on schede.idMarca=marchi.id " 
		+ " LEFT JOIN modelli on schede.idModello=modelli.id " 
		+ " LEFT JOIN clienti on schede.idCliente=clienti.id "
		+ " WHERE schede.deleted = 'S' "; 
	
	public static String QRY_STATISTIC =
		"SELECT "
		+ " id as 			  \""+Messages.getString("VcDlgStatistic.qryId")+"\" ," 
		+ " schede.dataInserimento as \""+Messages.getString("VcIfrListaSchede.qryInsDate")+"\" ,"
		+ " pagatoDalCliente as \""+Messages.getString("VcDlgStatistic.qrypagatoDalCliente")+"\" ,"
		+ " costoInterno as \""+Messages.getString("VcDlgStatistic.qrycostoInterno")+"\",  "
		+ " pagatoDalCliente-costoInterno as \"Ricavo\" "
		+ " FROM schede";

	public static String QRY_STATISTIC2 =
		"SELECT "
		+ "'Calcolo Totale' as \"Riepilogo\", " 
		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
		+ " SUM(costoInterno) as \"Incasso\", "
		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
		+ " FROM schede "
		+ "UNION "
		+ "SELECT"
		+ "'Mese Corrente' as \"Riepilogo\", " 
		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
		+ " SUM(costoInterno) as \"Incasso\", "
		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
		+ " FROM " 
		+ " schede "
//		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())"
		+ "UNION "
		+ "SELECT"
		+ "'Mese Precedente' as \"Riepilogo\", " 
		+ " SUM(pagatoDalCliente) as \"Costo Interno\", "
		+ " SUM(costoInterno) as \"Incasso\", "
		+ " SUM(pagatoDalCliente)-SUM(costoInterno) as \"Ricavo\" "
		+ " FROM " 
		+ " schede ";
//		+ "	WHERE MONTH(dataChiusura) = MONTH(CURRENT_DATE())-1 ";

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