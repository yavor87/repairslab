package net.sf.repairslab.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

import net.sf.repairslab.util.ui.cmb.TypeCmb;

import org.apache.log4j.Logger;

public class JDBCComboBoxModel extends DefaultComboBoxModel{
	
	static private Logger  logger = Logger.getLogger(JDBCComboBoxModel.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private String query = null;
	private String selectedValue = null;
	private String flagAttivo = null;
	
	public JDBCComboBoxModel(Connection con,String query,String selectedValue){
		super();
		this.con = con;
		this.query = query;
		this.selectedValue = selectedValue;
		this.flagAttivo = null;
		initialize();
	}

	public JDBCComboBoxModel(Connection con,String query,String selectedValue,String flagAttivo){
		super();
		this.con = con;
		this.query = query;
		this.selectedValue = selectedValue;
		this.flagAttivo = flagAttivo;
		initialize();
	}
	
	private void initialize() {
		
		TypeCmb selected = null;
		TypeCmb e1 = new TypeCmb();
		e1.setValue(0+"");
		e1.setDesc("");
		this.addElement(e1);
		
		try {
			logger.debug("Inizializing...");
			Statement smtp = con.createStatement();
			ResultSet rs = smtp.executeQuery(this.query);
			while(rs.next()){
				TypeCmb el = new TypeCmb();
				String obj = rs.getInt(1)+"";
				el.setValue(obj+"");
				el.setDesc(rs.getString(2));
				if(obj.equals(this.selectedValue)){
					selected = el;
				}
				if (flagAttivo != null){
					String flag = rs.getString(3);
					if(flag!=null){
						if(flag.equals(this.flagAttivo)||obj.equals(this.selectedValue)){
							this.addElement(el);
						}
					}
				}else{
					this.addElement(el);
				}
				
				
			}
			rs.close();
			smtp.close();
		} catch (SQLException e) {
			logger.error("Exception in Inizializing \n"+e+"\n", e);
			e.printStackTrace();
		}
		
		this.setSelectedItem(selected);
		
	}
	
	
}
