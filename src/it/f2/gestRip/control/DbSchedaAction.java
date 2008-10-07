package it.f2.gestRip.control;

import it.f2.gestRip.model.BinCliente;
import it.f2.gestRip.model.BinScheda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DbSchedaAction {
	
	public DbSchedaAction(){
	}
	
	public BinScheda getScheda(int nrScheda) throws SQLException{
		BinScheda binScheda = null;
		Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
		String qry = "select id,idCliente,idStato,idTipoRip,accessoriConsegnati,statoGenerale," +
				"difettoSegnalato,nonConform,descrizioneRiparazione," +
				"noteStampa,noteUsoInterno,costoPreventivato,costoInterno," +
				"pagatoDalCliente,dataInserimento,dataChiusura,imei,serial," +
				"idTipoApparecchiatura,idModello,idMarca,numeroDatiAcq,dataDatiAcq,idTipoDatiAcq " +
				"from gestrip.schede where id = " + nrScheda;
		ResultSet rs = smtp.executeQuery(qry);
		while (rs.next()) {
			binScheda = new BinScheda();
			int id = rs.getInt("id");
			binScheda.setOrigId(id);
			binScheda.setId(id);
			binScheda.setBinCliente(getCliente(rs.getInt("idCliente")));
			binScheda.setIdStato(rs.getInt("idStato"));
			binScheda.setIdTipoRiparazione(rs.getInt("idTipoRip"));
			binScheda.setAccessoriConsegnati(rs.getString("accessoriConsegnati"));
			binScheda.setStatoGenerale(rs.getString("statoGenerale"));
			binScheda.setDifettoSegnalato(rs.getString("difettoSegnalato"));
			binScheda.setNonConformita(rs.getString("nonConform"));
			binScheda.setDescrizioneRiparazione(rs.getString("descrizioneRiparazione"));
			binScheda.setNoteStampa(rs.getString("noteStampa"));
			binScheda.setNoteUsoInterno(rs.getString("noteUsoInterno"));
			binScheda.setCostoPreventivo(rs.getString("costoPreventivato"));
			binScheda.setCostoInterno(rs.getString("costoInterno"));
			binScheda.setPagatoDalCliente(rs.getString("pagatoDalCliente"));
			binScheda.setDataInserimento(rs.getDate("dataInserimento"));
			binScheda.setDataChiusura(rs.getDate("dataChiusura"));
			binScheda.setImei(rs.getString("imei"));
			binScheda.setSerial(rs.getString("serial"));
			binScheda.setIdTipoApparecchiature(rs.getInt("idTipoApparecchiatura"));
			binScheda.setIdModelli(rs.getInt("idModello"));
			binScheda.setIdMarchi(rs.getInt("idMarca"));
			binScheda.setNumDatiAcq(rs.getString("numeroDatiAcq"));
			binScheda.setDataDatiAcq(rs.getDate("dataDatiAcq"));
			binScheda.setIdTipoDatiAcq(rs.getInt("idTipoDatiAcq"));
		}
		rs.close();
		smtp.close();
		return binScheda;
	}
	
	public BinCliente getCliente(int idCliente) throws SQLException{
		BinCliente binCliente = null;
		Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
		String qry = "select id,nome,cognome,pIva,azienda,phone,mobilePhone," +
				"email,indirizzo,city " +
				"from gestrip.clienti where id = " + idCliente;
		ResultSet rs = smtp.executeQuery(qry);
		while (rs.next()) {
			binCliente = new BinCliente();
			binCliente.setId(rs.getInt("id"));
			String nome = rs.getString("nome");
			if (nome == null) nome = "";
			binCliente.setNome(nome);
			String cognome = rs.getString("cognome");
			if (cognome == null) cognome = "";
			binCliente.setCognome(cognome);
			String pIva = rs.getString("pIva");
			if (pIva == null) pIva = "";
			binCliente.setPIva(pIva);
			String azienda = rs.getString("azienda");
			if (azienda == null) azienda = "";
			binCliente.setAzienda(azienda);
			String phone = rs.getString("phone");
			if (phone == null) phone = "";
			binCliente.setPhone(phone);
			String mobilePhone = rs.getString("mobilePhone");
			if (mobilePhone == null) mobilePhone = "";
			binCliente.setMobilePhone(mobilePhone);
			String email = rs.getString("email");
			if (email == null) email = "";
			binCliente.setEmail(email);
			String indirizzo = rs.getString("indirizzo");
			if (indirizzo == null) indirizzo = "";
			binCliente.setIndirizzo(indirizzo);
			String city = rs.getString("city");
			if (city == null) city = "";
			binCliente.setCity(city);
		}
		rs.close();
		smtp.close();
		if(binCliente==null){
			binCliente = addVoidCliente();
		}
		return binCliente;
	}
	
	public static void saveScheda(BinScheda scheda) throws SQLException{
		Statement smtpScheda = CommonMetodBin.getInstance().openConn().createStatement();
		
		String updDataInsScheda = "";
		if (scheda.getDataInserimento()==null)
			updDataInsScheda = "dataInserimento="+scheda.getDataInserimento()+",";
		else 
			updDataInsScheda = "dataInserimento='"+scheda.getDataInserimento()+"',";
		
		String updDataChiuScheda = "";
		if (scheda.getDataChiusura() == null)
			updDataChiuScheda = "dataChiusura="+scheda.getDataChiusura()+",";
		else
			updDataChiuScheda = "dataChiusura='"+scheda.getDataChiusura()+"',";
		
		String updDataDAScheda = "";
		if (scheda.getDataDatiAcq()==null)
			updDataDAScheda = "dataDatiAcq="+scheda.getDataDatiAcq()+",";
		else
			updDataDAScheda = "dataDatiAcq='"+scheda.getDataDatiAcq()+"',";
		
		String updScheda = "update gestrip.schede set " +
			"id="+scheda.getId()+"," +
			"idCliente="+scheda.getBinCliente().getId()+"," +
			"idStato="+scheda.getIdStato()+"," +
			"idTipoRip="+scheda.getIdTipoRiparazione()+"," +
			"accessoriConsegnati='"+getParsedString(scheda.getAccessoriConsegnati())+"'," +
			"statoGenerale='"+getParsedString(scheda.getStatoGenerale())+"'," +
			"difettoSegnalato='"+getParsedString(scheda.getDifettoSegnalato())+"'," +
			"nonConform='"+getParsedString(scheda.getNonConformita())+"'," +
			"descrizioneRiparazione='"+getParsedString(scheda.getDescrizioneRiparazione())+"'," +
			"noteStampa='"+getParsedString(scheda.getNoteStampa())+"'," +
			"noteUsoInterno='"+scheda.getNoteUsoInterno()+"'," +
			"costoPreventivato="+scheda.getCostoPreventivo()+"," +
			"costoInterno="+scheda.getCostoInterno()+"," +
			"pagatoDalCliente="+scheda.getPagatoDalCliente()+"," +
			updDataInsScheda +
			updDataChiuScheda +
			"imei='"+getParsedString(scheda.getImei())+"'," +
			"serial='"+getParsedString(scheda.getSerial())+"'," +
			"idTipoApparecchiatura="+scheda.getIdTipoApparecchiature()+"," +
			"idModello="+scheda.getIdModelli()+"," +
			"idMarca="+scheda.getIdMarchi()+"," +
			"numeroDatiAcq='"+getParsedString(scheda.getNumDatiAcq())+"'," +
			updDataDAScheda +
			"idTipoDatiAcq="+scheda.getIdTipoDatiAcq()+" " +
			"where id = " + scheda.getOrigId();
		//System.out.println(updScheda);
		smtpScheda.executeUpdate(updScheda);
		smtpScheda.close();
		CommonMetodBin.getInstance().openConn().commit();
	}
	
	public BinScheda addScheda() throws SQLException{
		BinScheda binScheda = new BinScheda();
		
		int id = 0;
		Statement smtpMaxId = CommonMetodBin.getInstance().openConn().createStatement();
		String qryMaxId = "select max(id) from gestrip.schede ";
		ResultSet rsMaxId = smtpMaxId.executeQuery(qryMaxId);
		while (rsMaxId.next()) {
			id = rsMaxId.getInt(1);
		}
		rsMaxId.close();
		smtpMaxId.close();
		
		id++;
		binScheda.setOrigId(id);
		binScheda.setId(id);
		binScheda.setBinCliente(addVoidCliente());
		binScheda.setIdStato(0);
		binScheda.setIdTipoRiparazione(0);
		binScheda.setAccessoriConsegnati("");
		binScheda.setStatoGenerale("");
		binScheda.setDifettoSegnalato("");
		binScheda.setNonConformita("");
		binScheda.setDescrizioneRiparazione("");
		binScheda.setNoteStampa("");
		binScheda.setNoteUsoInterno("");
		binScheda.setCostoPreventivo("0");
		binScheda.setCostoInterno("0");
		binScheda.setPagatoDalCliente("0");
		binScheda.setDataInserimento(new java.sql.Date(new Date().getTime()));
		binScheda.setDataChiusura(null);
		binScheda.setImei("");
		binScheda.setSerial("");
		binScheda.setIdTipoApparecchiature(0);
		binScheda.setIdModelli(0);
		binScheda.setIdMarchi(0);
		binScheda.setNumDatiAcq("");
		binScheda.setDataDatiAcq(null);
		binScheda.setIdTipoDatiAcq(0);
		
		Statement smtpIns = CommonMetodBin.getInstance().openConn().createStatement();
		String ins = "insert into gestrip.schede " +
				"(id,idCliente,idStato," +
				"idTipoRip,accessoriConsegnati,statoGenerale," +
				"difettoSegnalato,nonConform,descrizioneRiparazione," +
				"noteStampa,noteUsoInterno,costoPreventivato," +
				"costoInterno,pagatoDalCliente,dataInserimento," +
				"dataChiusura,imei,serial," +
				"idTipoApparecchiatura,idModello,idMarca," +
				"numeroDatiAcq,dataDatiAcq,idTipoDatiAcq) " +
				"values ("+binScheda.getId()+","+binScheda.getBinCliente().getId()+","+binScheda.getIdStato()+"," +
						binScheda.getIdTipoRiparazione()+",'"+binScheda.getAccessoriConsegnati()+"','"+binScheda.getStatoGenerale()+"'," +
						"'"+binScheda.getDifettoSegnalato()+"','"+binScheda.getNonConformita()+"','"+binScheda.getDescrizioneRiparazione()+"'," +
						"'"+binScheda.getNoteStampa()+"','"+binScheda.getNoteUsoInterno()+"','"+binScheda.getCostoPreventivo()+"'," +
						"'"+binScheda.getCostoInterno()+"','"+binScheda.getPagatoDalCliente()+"','"+binScheda.getDataInserimento()+"'," +
						binScheda.getDataChiusura()+",'"+binScheda.getImei()+"','"+binScheda.getSerial()+"'," +
						binScheda.getIdTipoApparecchiature()+","+binScheda.getIdModelli()+","+binScheda.getIdMarchi()+"," +
						"'"+binScheda.getNumDatiAcq()+"',"+binScheda.getDataDatiAcq()+","+binScheda.getIdTipoDatiAcq()+")";
		//System.out.println(ins);
		smtpIns.executeUpdate(ins);
		smtpIns.close();
		
		return binScheda;
	}
	
	public static void removeScheda(int idScheda) throws SQLException{
		Statement smtpDel = CommonMetodBin.getInstance().openConn().createStatement();
		String del = "delete from gestrip.schede " +
				"where id = "+idScheda;
		//System.out.println(del);
		smtpDel.executeUpdate(del);
		smtpDel.close();
	}
	
	public static boolean existScheda(int idScheda) throws SQLException{
		Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
		String qry = "select count(id) " +
				"from gestrip.schede where id = " + idScheda;
		ResultSet rs = smtp.executeQuery(qry);
		int c = 0;
		while (rs.next()) {
			c = rs.getInt(1);
		}
		rs.close();
		if(c>0)
			return true;
		else
			return false;
	}
	
	public static BinCliente addVoidCliente() throws SQLException{
		BinCliente binCliente = new BinCliente();
		binCliente.setId(0);
		binCliente.setNome("");
		binCliente.setCognome("");
		binCliente.setPIva("");
		binCliente.setAzienda("");
		binCliente.setPhone("");
		binCliente.setMobilePhone("");
		binCliente.setEmail("");
		binCliente.setIndirizzo("");
		binCliente.setCity("");
		return binCliente;
	}
	
	public static BinCliente addCliente() throws SQLException{
		BinCliente binCliente = new BinCliente();
		
		int id = 0;
		Statement smtpMaxId = CommonMetodBin.getInstance().openConn().createStatement();
		String qryMaxId = "select max(id) from gestrip.clienti ";
		ResultSet rsMaxId = smtpMaxId.executeQuery(qryMaxId);
		while (rsMaxId.next()) {
			id = rsMaxId.getInt(1);
		}
		rsMaxId.close();
		smtpMaxId.close();
		id++;
		
		binCliente.setId(id);
		binCliente.setNome("");
		binCliente.setCognome("");
		binCliente.setPIva("");
		binCliente.setAzienda("");
		binCliente.setPhone("");
		binCliente.setMobilePhone("");
		binCliente.setEmail("");
		binCliente.setIndirizzo("");
		binCliente.setCity("");
		
		return binCliente;
	}
	
	public static void insCliente(BinCliente binCliente) throws SQLException{
		Statement smtpIns = CommonMetodBin.getInstance().openConn().createStatement();
		String ins = "insert into gestrip.clienti " +
				"(id,nome,cognome," +
				"pIva,azienda,phone," +
				"mobilePhone,email," +
				"indirizzo,city) " +
				"values ("+binCliente.getId()+",'"+binCliente.getNome()+"','"+binCliente.getCognome()+"'," +
						"'"+binCliente.getPIva()+"','"+binCliente.getAzienda()+"','"+binCliente.getPhone()+"'," +
						"'"+binCliente.getMobilePhone()+"','"+binCliente.getEmail()+"'," +
						"'"+binCliente.getIndirizzo()+"','"+binCliente.getCity()+"')";
		//System.out.println(ins);
		smtpIns.executeUpdate(ins);
		smtpIns.close();
	}
	
	public static void saveCliente(BinCliente binCliente) throws SQLException{
		Statement smtpIns = CommonMetodBin.getInstance().openConn().createStatement();
		String ins = "update gestrip.clienti set " +
				"pIva='"+getParsedString(binCliente.getPIva())+"'," +
				"azienda='"+getParsedString(binCliente.getAzienda())+"'," +
				"phone='"+getParsedString(binCliente.getPhone())+"'," +
				"mobilePhone='"+getParsedString(binCliente.getMobilePhone())+"'," +
				"email='"+getParsedString(binCliente.getEmail())+"'," +
				"indirizzo='"+getParsedString(binCliente.getIndirizzo())+"'," +
				"city='"+getParsedString(binCliente.getCity())+"' " +
				"where id = "+binCliente.getId();
		//System.out.println(ins);
		smtpIns.executeUpdate(ins);
		smtpIns.close();
	}
	
	public static int existCliente(String nome,String cognome) throws SQLException{
		Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
		String qry = "select id " +
				"from gestrip.clienti " +
				"where nome = '"+nome+"' " +
				"and cognome = '"+cognome+"' ";
		ResultSet rs = smtp.executeQuery(qry);
		int c = 0;
		while (rs.next()) {
			c = rs.getInt(1);
		}
		rs.close();
		return c;
	}

	private static String getParsedString(String stringToParse){
		String result = "";
		result = stringToParse.replace("'", "''");
		return result;
	}
}
