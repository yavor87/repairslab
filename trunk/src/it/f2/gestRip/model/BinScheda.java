package it.f2.gestRip.model;

import java.util.Date;

public class BinScheda implements Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int origId;
	public int getOrigId(){
		return origId;
	}
	public void setOrigId(int value){
		this.origId = value;
	}
	
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int value){
		this.id = value;
	}
	
	private int idStato;
	public int getIdStato(){
		return idStato;
	}
	public void setIdStato(int value){
		this.idStato = value;
	}
	
	private BinCliente binCliente;
	public BinCliente getBinCliente(){
		return binCliente;
	}
	public void setBinCliente(BinCliente value){
		this.binCliente = value;
	}
	
	private int idTipoRiparazione;
	public int getIdTipoRiparazione(){
		return idTipoRiparazione;
	}
	public void setIdTipoRiparazione(int value){
		this.idTipoRiparazione = value;
	}
	
	private int idTipoApparecchiature;
	public int getIdTipoApparecchiature(){
		return idTipoApparecchiature;
	}
	public void setIdTipoApparecchiature(int value){
		this.idTipoApparecchiature = value;
	}
	
	private String accessoriConsegnati;
	public String getAccessoriConsegnati(){
		return accessoriConsegnati;
	}
	public void setAccessoriConsegnati(String value){
		this.accessoriConsegnati = value;
	}
	
	private String statoGenerale;
	public String getStatoGenerale(){
		return statoGenerale;
	}
	public void setStatoGenerale(String value){
		this.statoGenerale = value;
	}
	
	private String difettoSegnalato;
	public String getDifettoSegnalato(){
		return difettoSegnalato;
	}
	public void setDifettoSegnalato(String value){
		this.difettoSegnalato = value;
	}
	
	private String nonConformita;
	public String getNonConformita(){
		return nonConformita;
	}
	public void setNonConformita(String value){
		this.nonConformita = value;
	}
	
	private String descrizioneRiparazione;
	public String getDescrizioneRiparazione(){
		return descrizioneRiparazione;
	}
	public void setDescrizioneRiparazione(String value){
		this.descrizioneRiparazione = value;
	}
	
	private String noteStampa;
	public String getNoteStampa(){
		return noteStampa;
	}
	public void setNoteStampa(String value){
		this.noteStampa = value;
	}
	
	private String noteUsoInterno;
	public String getNoteUsoInterno(){
		return noteUsoInterno;
	}
	public void setNoteUsoInterno(String value){
		this.noteUsoInterno = value;
	}
	
	private String costoPreventivo;
	public String getCostoPreventivo(){
		return costoPreventivo;
	}
	public void setCostoPreventivo(String value){
		this.costoPreventivo = value;
	}
	
	private String costoInterno;
	public String getCostoInterno(){
		return costoInterno;
	}
	public void setCostoInterno(String value){
		this.costoInterno = value;
	}
	
	private String pagatoDalCliente;
	public String getPagatoDalCliente(){
		return pagatoDalCliente;
	}
	public void setPagatoDalCliente(String value){
		this.pagatoDalCliente = value;
	}
	
	private Date dataInserimento;
	public Date getDataInserimento(){
		return dataInserimento;
	}
	public void setDataInserimento(Date date){
		this.dataInserimento = date;
	}
	
	private java.sql.Date dataChiusura;
	public java.sql.Date getDataChiusura(){
		return dataChiusura;
	}
	public void setDataChiusura(java.sql.Date value){
		this.dataChiusura = value;
	}
	
	private String imei;
	public String getImei(){
		return imei;
	}
	public void setImei(String value){
		this.imei = value;
	}
	
	private String serial;
	public String getSerial(){
		return serial;
	}
	public void setSerial(String value){
		this.serial = value;
	}
	
	private int idModelli;
	public int getIdModelli(){
		return idModelli;
	}
	public void setIdModelli(int value){
		this.idModelli = value;
	}
	
	private int idMarchi;
	public int getIdMarchi(){
		return idMarchi;
	}
	public void setIdMarchi(int value){
		this.idMarchi = value;
	}
	
	private String numDatiAcq;
	public String getNumDatiAcq(){
		return numDatiAcq;
	}
	public void setNumDatiAcq(String value){
		this.numDatiAcq = value;
	}
	
	private java.sql.Date dataDatiAcq;
	public java.sql.Date getDataDatiAcq(){
		return dataDatiAcq;
	}
	public void setDataDatiAcq(java.sql.Date value){
		this.dataDatiAcq = value;
	}
	
	private int idTipoDatiAcq;
	public int getIdTipoDatiAcq(){
		return idTipoDatiAcq;
	}
	public void setIdTipoDatiAcq(int value){
		this.idTipoDatiAcq = value;
	}
	
	public BinScheda clone(){
		try {
			BinScheda bs = new BinScheda();
			bs = (BinScheda) super.clone();
			bs.setBinCliente(this.getBinCliente().clone());
			return bs;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean sameData(BinScheda bs) {
		if (this.getOrigId() == bs.getOrigId()
				&& this.getId() == bs.getId()
				&& this.getAccessoriConsegnati().equals(
						bs.getAccessoriConsegnati())
				&& this.getBinCliente().sameData(bs.getBinCliente())
				&& this.getCostoInterno().equals(bs.getCostoInterno())
				&& this.getDataChiusura() == bs.getDataChiusura()
				&& this.getDataDatiAcq() == bs.getDataDatiAcq()
				&& this.getDataInserimento() == bs.getDataInserimento()
				&& this.getDescrizioneRiparazione().equals(
						bs.getDescrizioneRiparazione())
				&& this.getDifettoSegnalato().equals(bs.getDifettoSegnalato())
				&& this.getIdMarchi() == bs.getIdMarchi()
				&& this.getIdModelli() == bs.getIdModelli()
				&& this.getIdStato() == bs.getIdStato()
				&& this.getIdTipoApparecchiature() == bs
						.getIdTipoApparecchiature()
				&& this.getIdTipoDatiAcq() == bs.getIdTipoDatiAcq()
				&& this.getIdTipoRiparazione() == bs.getIdTipoRiparazione()
				&& this.getImei().equals(bs.getImei())
				&& this.getNonConformita().equals(bs.getNonConformita())
				&& this.getNoteStampa().equals(bs.getNoteStampa())
				&& this.getNoteUsoInterno().equals(bs.getNoteUsoInterno())
				&& this.getNumDatiAcq().equals(bs.getNumDatiAcq())
				&& this.getPagatoDalCliente().equals(bs.getPagatoDalCliente())
				&& this.getSerial().equals(bs.getSerial())
				&& this.getStatoGenerale().equals(bs.getStatoGenerale())) {
			System.out.print(this.getBinCliente().getEmail()+"-"+bs.getBinCliente().getEmail());
			return true;
		} else {
			return false;
		}
	}

}
