package it.f2.gestRip.ui;

import it.f2.gestRip.model.BinScheda;
import it.f2.gestRip.ui.VcDlgDetailScheda.mode;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

public class VcPnlCostiNote extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel lblNoteInterne = null;
	private JLabel lblNotePerStampa = null;
	private JLabel lblCosti = null;
	private JLabel lblCostoSostenuto = null;
	private JLabel lblCostoDalCliente = null;
	private JLabel lblCostoPreventivato = null;

	private JScrollPane scpNoteStampa = null;
	private JTextPane txpNoteStampa = null;
	private JScrollPane scpNoteInterno = null;
	private JTextPane txpNoteInterno = null;
	private JTextField txfCostoSostenuto = null;
	private JTextField txfCostoDalCliente = null;
	private JTextField txfCostoPreventivato = null;
	
	private mode modality = null;
	private BinScheda scheda = null;

	/**
	 * This is the default constructor
	 */
	public VcPnlCostiNote(mode modality,BinScheda scheda) {
		super();
		Logger.getRootLogger().debug("VcPnlCostiNote constructor...");
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
		this.setSize(722, 542);

		lblCostoPreventivato = new JLabel();
		lblCostoPreventivato.setBounds(new Rectangle(371, 46, 147, 16));
		lblCostoPreventivato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoPreventivato.setText("Costo Preventivato");
		lblCostoDalCliente = new JLabel();
		lblCostoDalCliente.setBounds(new Rectangle(80, 77, 136, 16));
		lblCostoDalCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoDalCliente.setText("Addebitato al Cliente");
		lblCostoSostenuto = new JLabel();
		lblCostoSostenuto.setBounds(new Rectangle(72, 44, 142, 16));
		lblCostoSostenuto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoSostenuto.setText("Sostenuto");
		lblCosti = new JLabel();
		lblCosti.setBounds(new Rectangle(39, 14, 79, 16));
		lblCosti.setText("Costi");
		lblNoteInterne = new JLabel();
		lblNoteInterne.setBounds(new Rectangle(74, 285, 309, 16));
		lblNoteInterne.setText("Note per uso interno");
		lblNotePerStampa = new JLabel();
		lblNotePerStampa.setText("Note per la stampa");
		lblNotePerStampa.setBounds(new Rectangle(74, 138, 300, 16));
		setLayout(null);
		add(lblNotePerStampa, null);
		add(getScpNoteStampa(), null);
		add(lblNoteInterne, null);
		add(getScpNoteInterno(), null);
		add(lblCosti, null);
		add(lblCostoSostenuto, null);
		add(getTxfCostoSostenuto(), null);
		add(lblCostoDalCliente, null);
		add(getTxfCostoDalCliente(), null);
		add(lblCostoPreventivato, null);
		add(getTxfCostoPreventivato(), null);

	}

	/**
	 * This method initializes scpNoteStampa
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScpNoteStampa() {
		if (scpNoteStampa == null) {
			scpNoteStampa = new JScrollPane();
			scpNoteStampa.setBounds(new Rectangle(74, 160, 500, 100));
			scpNoteStampa.setViewportView(getTxpNoteStampa());
		}
		return scpNoteStampa;
	}

	/**
	 * This method initializes txpNoteStampa
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getTxpNoteStampa() {
		if (txpNoteStampa == null) {
			txpNoteStampa = new JTextPane();
			txpNoteStampa.setText(scheda.getNoteStampa());
			if(modality == mode.view){
				txpNoteStampa.setEditable(false);
			}
			txpNoteStampa.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setNoteStampa(txpNoteStampa.getText());
				}
			});
		}
		return txpNoteStampa;
	}

	/**
	 * This method initializes scpNoteInterno
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getScpNoteInterno() {
		if (scpNoteInterno == null) {
			scpNoteInterno = new JScrollPane();
			scpNoteInterno.setBounds(new Rectangle(74, 304, 500, 100));
			scpNoteInterno.setViewportView(getTxpNoteInterno());
		}
		return scpNoteInterno;
	}

	/**
	 * This method initializes txpNoteInterno
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getTxpNoteInterno() {
		if (txpNoteInterno == null) {
			txpNoteInterno = new JTextPane();
			txpNoteInterno.setText(scheda.getNoteUsoInterno());
			if(modality == mode.view){
				txpNoteInterno.setEditable(false);
			}
			txpNoteInterno.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setNoteUsoInterno(txpNoteInterno.getText());
				}
			});
		}
		return txpNoteInterno;
	}

	/**
	 * This method initializes txfCostoSostenuto
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxfCostoSostenuto() {
		if (txfCostoSostenuto == null) {
			txfCostoSostenuto = new JTextField();
			txfCostoSostenuto.setText(""+scheda.getCostoInterno());
			txfCostoSostenuto.setBounds(new Rectangle(227, 42, 95, 25));
			if(modality == mode.view){
				txfCostoSostenuto.setEditable(false);
			}
			txfCostoSostenuto.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setCostoInterno(new Float(txfCostoSostenuto.getText()));
				}
			});
		}
		return txfCostoSostenuto;
	}

	/**
	 * This method initializes txfCostoDalCliente
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxfCostoDalCliente() {
		if (txfCostoDalCliente == null) {
			txfCostoDalCliente = new JTextField();
			txfCostoDalCliente.setText(""+scheda.getPagatoDalCliente());
			txfCostoDalCliente.setBounds(new Rectangle(227, 75, 95, 25));
			if(modality == mode.view){
				txfCostoDalCliente.setEditable(false);
			}
			txfCostoDalCliente.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setPagatoDalCliente(new Float(txfCostoDalCliente.getText()));
				}
			});
		}
		return txfCostoDalCliente;
	}

	/**
	 * This method initializes txfCostoPreventivato
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getTxfCostoPreventivato() {
		if (txfCostoPreventivato == null) {
			txfCostoPreventivato = new JTextField();
			txfCostoPreventivato.setText(""+scheda.getCostoPreventivo());
			txfCostoPreventivato.setBounds(new Rectangle(524, 42, 95, 25));
			if(modality == mode.view){
				txfCostoPreventivato.setEditable(false);
			}
			txfCostoPreventivato.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					scheda.setCostoPreventivo(new Float(txfCostoPreventivato.getText()));
				}
			});
		}
		return txfCostoPreventivato;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
