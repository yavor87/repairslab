package net.sf.repairslab.util;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class LovDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcJDBCTablePanel pnlQuery = null;
	private String query;
	private JPanel pnlControls = null;
	private JButton btnOk = null;
	private JButton btnCancel = null;
	private LovChooser lovChoser = null;
	private String colValue = null;
	private String colLabel = null;
	private Connection con = null;
	
	/**
	 * @param owner
	 */
	public LovDialog(Frame owner,LovChooser lovChoser,String query,String colValue,String colLabel,Connection con) {
		super(owner,true);
		this.query = query;
		this.lovChoser = lovChoser;
		this.colValue = colValue;
		this.colLabel = colLabel;
		this.con = con;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 300);
		this.setTitle("LOV...");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPnlQuery(),BorderLayout.CENTER);
			jContentPane.add(getPnlControls(),BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlQuery	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcJDBCTablePanel getPnlQuery() {
		if (pnlQuery == null) {
			pnlQuery = new VcJDBCTablePanel(con,query,false);
			pnlQuery.createControlPanel();
		}
		return pnlQuery;
	}

	/**
	 * This method initializes pnlControls	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlControls() {
		if (pnlControls == null) {
			pnlControls = new JPanel();
			pnlControls.setLayout(new GridBagLayout());
			pnlControls.add(getBtnOk(), new GridBagConstraints());
			pnlControls.add(getBtnCancel(), new GridBagConstraints());
		}
		return pnlControls;
	}

	/**
	 * This method initializes btnOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setText("Ok");
			btnOk.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/button_ok.png")));
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int iColValue = getPnlQuery().getIdxFromColName(colValue);
					int iColLabel = getPnlQuery().getIdxFromColName(colLabel);
					Object value = getPnlQuery().getValueAt(getPnlQuery().currentRow(), iColValue);
					Object label = getPnlQuery().getValueAt(getPnlQuery().currentRow(), iColLabel);
					lovChoser.setResults(value, label);
					setVisible(false);
				}
			});
		}
		return btnOk;
	}

	/**
	 * This method initializes btnCancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.setText("Cancel");
			btnCancel.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/button_cancel.png")));
			btnCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return btnCancel;
	}

}
