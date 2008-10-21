package it.f2.gestRip.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class CheckBoxCellRenderer extends JCheckBox implements
		TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JCheckBox checkBox;
	private Object chechedValue ;
	private Object unchechedValue ;
	
	public CheckBoxCellRenderer(Color background){
		new CheckBoxCellRenderer(background,Boolean.TRUE,Boolean.FALSE);
	}
	
	public CheckBoxCellRenderer(Color background,Object chechedValue,Object unchechedValue) {
		checkBox = new JCheckBox();
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBackground(background);
		this.chechedValue = chechedValue;
        this.unchechedValue = unchechedValue;
	}

	public Component getTableCellRendererComponent(
			JTable table, 
			Object value,
			boolean isSelected, 
			boolean hasFocus, 
			int row, 
			int column) {		
		
    	if(value != null){
	    	if (value.getClass() == Boolean.class){
	    		checkBox.setSelected(((Boolean) value).booleanValue());
	    	} else if(value.getClass() == String.class){
	    		if (value.equals(chechedValue))
	    			checkBox.setSelected(true);
	    		else{
	    			checkBox.setSelected(false);
	    			value = unchechedValue;
	    		}
	    	} else if(value.getClass() == Integer.class ||
	    			value.getClass() == Long.class ||
	    			value.getClass() == Number.class){
	    		if (value==chechedValue)
	    			checkBox.setSelected(true);
	    		else{
	    			checkBox.setSelected(false);
	    			value = unchechedValue;
	    		}
	    	}
    	}else{
    		value = unchechedValue;
			checkBox.setSelected(false);
		}
    	
		Component c = table.getDefaultRenderer(String.class).
    		getTableCellRendererComponent(table, value, isSelected, false, row, column);
	    if (c != null) {
	        checkBox.setBackground(c.getBackground());
	    }
		
		return checkBox;
	}
}
