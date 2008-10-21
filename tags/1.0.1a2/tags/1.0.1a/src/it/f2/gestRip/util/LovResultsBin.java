package it.f2.gestRip.util;

import it.f2.gestRip.control.CommonMetodBin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;

import org.apache.log4j.Logger;

public class LovResultsBin {
	
	public LovResultsBin(){
	}
	
	private ArrayList<Object[]> resultList = null;
	
	public void load(JTable table,int col,String queryRender,String colValue){
		//if (resultList == null){
			resultList = new ArrayList<Object[]>();
			for(int i=0;i<table.getRowCount();i++){
				String value = table.getValueAt(i, col) + "";
				try {
					Logger.getRootLogger().debug("Loading...");
					Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
					String queryParsed = queryRender + "'" + value + "'";
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
					Logger.getRootLogger().error("Exception in Loading \n"+e+"\n");
					//e.printStackTrace();
				} 
			}
			
		//}
	}
	
	public Object getLableAt(int row){
		Object[] result = resultList.get(row);
		return result[1];
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
