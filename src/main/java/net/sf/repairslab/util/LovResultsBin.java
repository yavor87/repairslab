package net.sf.repairslab.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;

import org.apache.log4j.Logger;

public class LovResultsBin {
	
	static private Logger  logger = Logger.getLogger(LovResultsBin.class.getName());
	
	public LovResultsBin(){
	}
	
	private ArrayList<Object[]> resultList = null;
	
	public void load(JTable table,int col,String queryRender,String colValue,Connection con){
		//if (resultList == null){
			resultList = new ArrayList<Object[]>();
			for(int i=0;i<table.getRowCount();i++){
				Object value = table.getValueAt(i, col);// + "";
				if(value == null) value = "0";
				try {
					logger.debug("Loading...");
					Statement smtp = con.createStatement();
					String queryParsed = "";
					try{
						int numVal = Integer.parseInt(value+"");
						queryParsed = queryRender + numVal;
					}catch(ClassCastException e){
						queryParsed = queryRender + "'" + value + "'";
					}
					ResultSet rs = smtp.executeQuery(queryParsed) ;
					Object[] lovRow = new Object[2];
					lovRow[0] = value;
					while(rs.next()){
						lovRow[1] = rs.getObject(1)+"";
					}
					resultList.add(lovRow);
					rs.close();
					smtp.close();
				} catch (SQLException e) {
					logger.error("Exception in Loading \n"+e+"\n");
					e.printStackTrace();
				} 
			}
			
		//}
	}
	
	public Object getLableAt(int row){
		Object[] result = resultList.get(row);
		Object label = result[1];
		if(label == null) label = "";
		return label;
	}
	
	public Object getValueAt(int row){
		Object[] result = resultList.get(row);
		return result[0];
	}
	
	public void setValueAt(int row,Object value,Object label){
		Object[] result = resultList.get(row);
		result[0] = value;
		result[1] = label;
	}
	
}
