package net.sf.repairslab.util;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.util.ui.WindowUtil;

public class LovChooser extends JPanel implements ActionListener,PropertyChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String query = null;
	private String colValue = null;
	private String colLabel = null;
	private LovResultsBin lrb = null;
	private int row = 0;
	
	private JButton button = null;
	private JLabel label = null;
	private Connection con = null;
	
	public LovChooser(LovResultsBin lrb,String query,
			String colValue,String colLabel,int row,Connection con){
		this.query = query;
		this.colValue = colValue;
		this.colLabel = colLabel;
		this.lrb = lrb;
		this.row = row;
		this.con = con;
		
		setLayout(new BorderLayout());
		add(getButton(), BorderLayout.EAST);
		add(getLabel(), BorderLayout.WEST);
	}
	
	private JLabel getLabel(){
		if (label == null) {
			label = new JLabel();
			System.out.println(row);
			label.setText(lrb.getLableAt(row)+"");
		}
		return label;
	}
	
	private JButton getButton(){
		if (button == null){
			button = new JButton(){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean isFocusable() {
					return false;
				}
			};
			button.setMargin(new Insets(0, 0, 0, 0));
			button.addActionListener(this);
			// Alt + 'C' selects the calendar.
			button.setMnemonic(KeyEvent.VK_C);
			button.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/list.png")));
		}
		return button;
	}

	public void actionPerformed(ActionEvent arg0) {
		LovDialog lovDialog = new LovDialog(
				CommonMetodBin.getInstance().getMainFrame(),this,
				query,colValue,colLabel,con);
		WindowUtil.centerWindow(lovDialog);
		lovDialog.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("propertyChange"+evt.getPropertyName());
	}
	
	public Object getValue(){
		return lrb.getValueAt(row);
	}
	
	public void setResults(Object value,Object label){
		lrb.setValueAt(row, value, label);
		this.getLabel().setText(label+"");
	}
	

}
