package it.f2.gestRip.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class LovCellEditor extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LovChooser component;
	
	private String query;
	private String colValue;
	private String colLabel;
	private LovResultsBin lrb;
	
	public LovCellEditor(LovResultsBin lrb,String query,String colValue,String colLabel){
		this.query = query;
		this.colValue = colValue;
		this.colLabel = colLabel;
		this.lrb = lrb;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, 
            Object value, 
            boolean isSelected, 
            int row, 
            int column) {
		
		/*Component c = table.getDefaultRenderer(String.class).
    		getTableCellRendererComponent(table, value, isSelected, false, row, column);
	    if (c != null) {
	    	getLovChooser(row).setBackground(c.getBackground());
	    }*/
		
		component = new LovChooser(lrb,query,colValue,colLabel,row);
		component.setBackground(Color.WHITE);
		
		if(value==null){
			component.setResults(0, "");
		}
		
		return component;
        
	}

	@Override
	public Object getCellEditorValue() {
		return component.getValue();
	}

}
