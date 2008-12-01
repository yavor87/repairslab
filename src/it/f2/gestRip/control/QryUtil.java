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

}