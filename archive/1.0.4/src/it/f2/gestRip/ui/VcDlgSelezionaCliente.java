package it.f2.gestRip.ui;

import it.f2.gestRip.ui.messages.Messages;
import it.f2.gestRip.util.VcJDBCTablePanel;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.sql.Connection;

import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

public class VcDlgSelezionaCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcJDBCTablePanel pnlTblCliente = null;
	private JPanel pnlControls = null;
	private JButton btnCanc = null;
	private JButton btnOk = null;
	private VcPnlDatiCLiente parent = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcDlgSelezionaCliente(JDialog dialog,VcPnlDatiCLiente parent,Connection con) {
		super(dialog);
		Logger.getRootLogger().debug("VcDlgSelezionaCliente constructor..."); //$NON-NLS-1$
		this.parent = parent;
		this.con = con;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(515, 372);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
			jContentPane.add(getPnlTblCliente(), BorderLayout.CENTER);
			jContentPane.add(getPnlControls(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes pnlTblCliente	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcJDBCTablePanel getPnlTblCliente() {
		if (pnlTblCliente == null) {
			pnlTblCliente = new VcJDBCTablePanel(
					con,
					"select id,nome,cognome,azienda,city " + //$NON-NLS-1$
					"from clienti", //$NON-NLS-1$
					false);
			pnlTblCliente.createControlPanel();
		}
		return pnlTblCliente;
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
			pnlControls.add(getBtnCanc(), new GridBagConstraints());
		}
		return pnlControls;
	}

	/**
	 * This method initializes btnCanc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton();
			btnCanc.setText(Messages.getString("VcDlgSelezionaCliente.btnCanc")); //$NON-NLS-1$
			btnCanc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
					dispose();
				}
			});
		}
		return btnCanc;
	}

	/**
	 * This method initializes btnOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setText(Messages.getString("VcDlgSelezionaCliente.btnOk")); //$NON-NLS-1$
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int id = Integer.parseInt(getPnlTblCliente().getValueAt(
							getPnlTblCliente().currentRow(), 0)+""); //$NON-NLS-1$
					parent.selezionaCliente(id);
					setVisible(false);
					dispose();
				}
			});
		}
		return btnOk;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
