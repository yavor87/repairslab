package it.f2.gestRip.model;

public class BinCliente implements Cloneable{
	
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int value){
		this.id = value;
	}
	
	private String nome;
	public String getNome(){
		return nome;
	}
	public void setNome(String value){
		this.nome = value;
	}
	
	private String cognome;
	public String getCognome(){
		return cognome;
	}
	public void setCognome(String value){
		this.cognome = value;
	}
	
	private String pIva;
	public String getPIva(){
		return pIva;
	}
	public void setPIva(String value){
		this.pIva = value;
	}
	
	private String azienda;
	public String getAzienda(){
		return azienda;
	}
	public void setAzienda(String value){
		this.azienda = value;
	}
	
	private String phone;
	public String getPhone(){
		return phone;
	}
	public void setPhone(String value){
		this.phone = value;
	}
	
	private String mobilePhone;
	public String getMobilePhone(){
		return mobilePhone;
	}
	public void setMobilePhone(String value){
		this.mobilePhone = value;
	}
	
	private String email;
	public String getEmail(){
		return email;
	}
	public void setEmail(String value){
		this.email = value;
	}
	
	private String indirizzo;
	public String getIndirizzo(){
		return indirizzo;
	}
	public void setIndirizzo(String value){
		this.indirizzo = value;
	}
	
	private String city;
	public String getCity(){
		return city;
	}
	public void setCity(String value){
		this.city = value;
	}
	
	public BinCliente clone(){
		try {
			return (BinCliente) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean sameData(BinCliente bc) {
		if(this.getId() != bc.getId())
			return false;
		if(!this.getAzienda().equals(bc.getAzienda()))
			return false;
		if(!this.getCity().equals(bc.getCity()))
			return false;
		if(!this.getCognome().equals(bc.getCognome()))
			return false;
		if(!this.getEmail().equals(bc.getEmail()))
			return false;
		if(!this.getIndirizzo().equals(bc.getIndirizzo()))
			return false;
		if(!this.getMobilePhone().equals(bc.getMobilePhone()))
			return false;
		if(!this.getNome().equals(bc.getNome()))
			return false;
		if(!this.getPhone().equals(bc.getPhone()))
			return false;
		if(!this.getPIva().equals(bc.getPIva()))
			return false;
		return true;
	}

}
