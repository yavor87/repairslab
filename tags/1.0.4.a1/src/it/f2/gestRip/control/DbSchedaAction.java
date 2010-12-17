package it.f2.gestRip.control;

import it.f2.gestRip.model.BinCliente;
import it.f2.gestRip.model.BinScheda;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DbSchedaAction {
	
	public DbSchedaAction(){
	}
	
	public BinScheda getScheda(Connection con,int nrScheda) throws SQLException{
		BinScheda binScheda = null;
		Statement smtp = con.createStatement();
		String qry = "select id,idCliente,idStato,idTipoRip,accessoriConsegnati,statoGenerale," +
				"difettoSegnalato,nonConform,descrizioneRiparazione," +
				"noteStampa,noteUsoInterno,costoPreventivato,costoInterno," +
				"pagatoDalCliente,dataInserimento,dataChiusura,imei,serial," +
				"idTipoApparecchiatura,idModello,idMarca,numeroDatiAcq,dataDatiAcq,idTipoDatiAcq " +
				"from schede where id = " + nrScheda;
		ResultSet rs = smtp.executeQuery(qry);
		while (rs.next()) {
			binScheda = new BinScheda();
			int id = rs.getInt("id");
			binScheda.setOrigId(id);
			binScheda.setId(id);
			binScheda.setBinCliente(getCliente(con,rs.getInt("idCliente")));
			binScheda.setIdStato(rs.getInt("idStato"));
			binScheda.setIdTipoRiparazione(rs.getInt("idTipoRip"));
			binScheda.setAccessoriConsegnati(rs.getString("accessoriConsegnati"));
			binScheda.setStatoGenerale(rs.getString("statoGenerale"));
			binScheda.setDifettoSegnalato(rs.getString("difettoSegnalato"));
			binScheda.setNonConformita(rs.getString("nonConform"));
			binScheda.setDescrizioneRiparazione(rs.getString("descrizioneRiparazione"));
			binScheda.setNoteStampa(rs.getString("noteStampa"));
			binScheda.setNoteUsoInterno(rs.getString("noteUsoInterno"));
			binScheda.setCostoPreventivo(rs.getFloat("costoPreventivato"));
			binScheda.setCostoInterno(rs.getFloat("costoInterno"));
			binScheda.setPagatoDalCliente(rs.getFloat("pagatoDalCliente"));
			binScheda.setDataInserimento(rs.getDate("dataInserimento"));
			binScheda.setDataChiusura(rs.getDate("dataChiusura"));
			//binScheda.setImei(rs.getString("imei"));
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
	
	public BinCliente getCliente(Connection con,int idCliente) throws SQLException{
		BinCliente binCliente = null;
		Statement smtp = con.createStatement();
		String qry = "select id,nome,cognome,pIva,azienda,phone,mobilePhone," +
				"email,indirizzo,city " +
				"from clienti where id = " + idCliente;
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
	
	public static void saveScheda(Connection con,BinScheda scheda) throws SQLException{
		Statement smtpScheda = con.createStatement();
		
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
		
		String updScheda = "update schede set " +
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
			"noteUsoInterno='"+getParsedString(scheda.getNoteUsoInterno())+"'," +
			"costoPreventivato="+scheda.getCostoPreventivo()+"," +
			"costoInterno="+scheda.getCostoInterno()+"," +
			"pagatoDalCliente="+scheda.getPagatoDalCliente()+"," +
			updDataInsScheda +
			updDataChiuScheda +
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
		con.commit();
	}
	
	public BinScheda addScheda(Connection con) throws SQLException{
		BinScheda binScheda = new BinScheda();
		
		int id = 0;
		Statement smtpMaxId = con.createStatement();
		String qryMaxId = "select max(id) from schede ";
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
		binScheda.setCostoPreventivo(new Float(0));
		binScheda.setCostoInterno(new Float(0));
		binScheda.setPagatoDalCliente(new Float(0));
		binScheda.setDataInserimento(new java.sql.Date(new Date().getTime()));
		binScheda.setDataChiusura(null);
		binScheda.setSerial("");
		binScheda.setIdTipoApparecchiature(0);
		binScheda.setIdModelli(0);
		binScheda.setIdMarchi(0);
		binScheda.setNumDatiAcq("");
		binScheda.setDataDatiAcq(null);
		binScheda.setIdTipoDatiAcq(0);
		
		Statement smtpIns = con.createStatement();
		String ins = "insert into schede " +
				"(id,idCliente,idStato," +
				"idTipoRip,accessoriConsegnati,statoGenerale," +
				"difettoSegnalato,nonConform,descrizioneRiparazione," +
				"noteStampa,noteUsoInterno,costoPreventivato," +
				"costoInterno,pagatoDalCliente,dataInserimento," +
				"dataChiusura,serial," +
				"idTipoApparecchiatura,idModello,idMarca," +
				"numeroDatiAcq,dataDatiAcq,idTipoDatiAcq) " +
				"values ("+binScheda.getId()+","+
						binScheda.getBinCliente().getId()+","+
						binScheda.getIdStato()+"," +
						binScheda.getIdTipoRiparazione()+",'"+
						binScheda.getAccessoriConsegnati()+"'," +
						"'"+binScheda.getStatoGenerale()+"'," +
						"'"+binScheda.getDifettoSegnalato()+"'," +
						"'"+binScheda.getNonConformita()+"'," +
						"'"+binScheda.getDescrizioneRiparazione()+"'," +
						"'"+binScheda.getNoteStampa()+"'," +
						"'"+binScheda.getNoteUsoInterno()+"',"+
						binScheda.getCostoPreventivo()+"," +
						binScheda.getCostoInterno()+","+
						binScheda.getPagatoDalCliente()+"," +
						"'"+binScheda.getDataInserimento()+"'," +
						binScheda.getDataChiusura()+"," +
						"'"+binScheda.getSerial()+"'," +
						binScheda.getIdTipoApparecchiature()+","+
						binScheda.getIdModelli()+","+
						binScheda.getIdMarchi()+"," +
						"'"+binScheda.getNumDatiAcq()+"',"+
						binScheda.getDataDatiAcq()+","+
						binScheda.getIdTipoDatiAcq()+")";
		//System.out.println(ins);
		smtpIns.executeUpdate(ins);
		smtpIns.close();
		
		return binScheda;
	}
	
	public static void removeScheda(Connection con,int idScheda) throws SQLException{
		Statement smtpDel = con.createStatement();
		String del = "delete from schede " +
				"where id = "+idScheda;
		//System.out.println(del);
		smtpDel.executeUpdate(del);
		smtpDel.close();
	}
	
	public static void svuotaCestinoScheda(Connection con) throws SQLException{
		Statement smtpDel = con.createStatement();
		String del = "delete from schede " +
				"where deleted = 'S' ";
		//System.out.println(del);
		smtpDel.executeUpdate(del);
		smtpDel.close();
	}
	
	public static void cestinaScheda(Connection con,int idScheda) throws SQLException{
		Statement smtpDel = con.createStatement();
		String upd = "update schede " +
				"set deleted = 'S' " +
				"where id = "+idScheda;
		//System.out.println(upd);
		smtpDel.executeUpdate(upd);
		smtpDel.close();
	}
	
	public static void ripristinaScheda(Connection con,int idScheda) throws SQLException{
		Statement smtpDel = con.createStatement();
		String upd = "update schede " +
				"set deleted = null " +
				"where id = "+idScheda;
		//System.out.println(upd);
		smtpDel.executeUpdate(upd);
		smtpDel.close();
	}
	
	public static boolean existScheda(Connection con,int idScheda) throws SQLException{
		Statement smtp = con.createStatement();
		String qry = "select count(id) " +
				"from schede where id = " + idScheda;
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
	
	public static BinCliente addCliente(Connection con) throws SQLException{
		BinCliente binCliente = new BinCliente();
		
		int id = 0;
		Statement smtpMaxId = con.createStatement();
		String qryMaxId = "select max(id) from clienti ";
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
	
	public static void insCliente(Connection con,BinCliente binCliente) throws SQLException{
		Statement smtpIns = con.createStatement();
		String ins = "insert into clienti " +
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
	
	public static void saveCliente(Connection con,BinCliente binCliente) throws SQLException{
		Statement smtpIns = con.createStatement();
		String ins = "update clienti set " +
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
	
	public static int existCliente(Connection con,String nome,String cognome) throws SQLException{
		Statement smtp = con.createStatement();
		String qry = "select id " +
				"from clienti " +
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
