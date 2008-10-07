package it.f2.gestRip.util;

import java.awt.Color;
import java.awt.Component;

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
	
	String query;
	String colValue;
	String colLabel;
	LovResultsBin lrb;
	
	public LovCellRenderer(Color background,LovResultsBin lrb,String query,String colValue,String colLabel){
		this.query = query;
		this.colValue = colValue;
		this.colLabel = colLabel;
		this.lrb = lrb;
		label = new JTextField();
	}

	@Override
	public Component getTableCellRendererComponent(			
			JTable table, 
			Object value,
			boolean isSelected, 
			boolean hasFocus, 
			int row, 
			int column) {	
		
		lrb.load(table, column, query, colValue);
		
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
