package it.f2.gestRip.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;

public class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JCheckBox checkBox;
	private Object value ;
	private Object chechedValue ;
	private Object unchechedValue ;
    
    public CheckBoxCellEditor() {
    	new CheckBoxCellEditor(Boolean.TRUE,Boolean.FALSE);
    }
    
    public CheckBoxCellEditor(Object chechedValue,Object unchechedValue) {
        checkBox = new JCheckBox();
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox.setBackground( Color.white);
        checkBox.setBorder(new LineBorder(Color.black, 2));
        this.chechedValue = chechedValue;
        this.unchechedValue = unchechedValue;
    }
    
    public Component getTableCellEditorComponent(
            JTable table, 
            Object value, 
            boolean isSelected, 
            int row, 
            int column) {

    	this.value = value;
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
    
    public Object getCellEditorValue() {
		if(checkBox.isSelected()){
			value = chechedValue;
		}else{
			value = unchechedValue;
		}
		return value;
    }
}