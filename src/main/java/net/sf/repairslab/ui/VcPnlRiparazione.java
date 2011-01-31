package net.sf.repairslab.ui;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.model.BinScheda;
import net.sf.repairslab.ui.VcDlgDetailScheda.mode;
import net.sf.repairslab.ui.messages.Messages;

import org.apache.log4j.Logger;

public class VcPnlRiparazione extends JPanel {
	
	static private Logger  logger = Logger.getLogger(CommonMetodBin.class.getName());

	private static final long serialVersionUID = 1L;
	private JLabel lblRiparazione = null;
	private JScrollPane scpRiparazione = null;
	private JTextPane txpRiparazione = null;
	private mode modality = null;
	private BinScheda scheda = null;

	/**
	 * This is the default constructor
	 */
	public VcPnlRiparazione(mode modality,BinScheda scheda) {
		super();
		logger.debug("VcDlgDetailScheda constructor..."); //$NON-NLS-1$
		this.modality = modality;
		this.scheda = scheda;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(766, 460);
		this.setLayout(null);
		lblRiparazione = new JLabel();
		lblRiparazione.setBounds(new Rectangle(205, 66, 279, 16));
		lblRiparazione.setText(Messages.getString("VcPnlRiparazione.lblDescRepair")); //$NON-NLS-1$
		add(lblRiparazione, null);
		add(getScpRiparazione(), null);
	}

	/**
	 * This method initializes scpRiparazione	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScpRiparazione() {
		if (scpRiparazione == null) {
			scpRiparazione = new JScrollPane();
			scpRiparazione.setBounds(new Rectangle(204, 89, 404, 195));
			scpRiparazione.setViewportView(getTxpRiparazione());
		}
		return scpRiparazione;
	}

	/**
	 * This method initializes txpRiparazione	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getTxpRiparazione() {
		if (txpRiparazione == null) {
			txpRiparazione = new JTextPane();
			txpRiparazione.setText(scheda.getDescrizioneRiparazione());
			if(modality == mode.view){
				txpRiparazione.setEditable(false);
			}
			txpRiparazione.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setDescrizioneRiparazione(txpRiparazione.getText());
				}
			});
		}
		return txpRiparazione;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
