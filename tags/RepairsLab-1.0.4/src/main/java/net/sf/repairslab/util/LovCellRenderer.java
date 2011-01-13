package net.sf.repairslab.util;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class LovCellRenderer extends JLabel implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField label;
	
	private String query;
	private String colValue;
	private LovResultsBin lrb;
	private Connection con = null;
	
	public LovCellRenderer(Color background,LovResultsBin lrb,String query,String colValue,Connection con){
		this.query = query;
		this.colValue = colValue;
		this.lrb = lrb;
		this.con = con;
		label = new JTextField();
	}

	public Component getTableCellRendererComponent(			
			JTable table, 
			Object value,
			boolean isSelected, 
			boolean hasFocus, 
			int row, 
			int column) {	
		
		lrb.load(table, column, query, colValue,con);
		
		if(value!=null){
			label.setText(lrb.getLableAt(row)+"");
		}else{
			label.setText("");
		}
		Component c = table.getDefaultRenderer(String.class).
			getTableCellRendererComponent(table, value, isSelected, false, row, column);
	    if (c != null) {
	    	label.setBackground(c.getBackground());
	    	label.setForeground(c.getForeground());
	    }
	    
	    label.setBorder(null);
		
		return label;
	}

}
