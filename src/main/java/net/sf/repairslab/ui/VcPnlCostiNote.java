package net.sf.repairslab.ui;

import java.awt.Rectangle;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatterFactory;

import net.sf.repairslab.model.BinScheda;
import net.sf.repairslab.ui.VcDlgDetailScheda.mode;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.CurrencyFormatter;

import org.apache.log4j.Logger;

public class VcPnlCostiNote extends JPanel {
	
	static private Logger  logger = Logger.getLogger(VcPnlCostiNote.class.getName());

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
	private JFormattedTextField txfCostoSostenuto = null;
	private JFormattedTextField txfCostoDalCliente = null;
	private JFormattedTextField txfCostoPreventivato = null;
	
	private mode modality = null;
	private BinScheda scheda = null;

	/**
	 * This is the default constructor
	 */
	public VcPnlCostiNote(mode modality,BinScheda scheda) {
		super();
		logger.debug("VcPnlCostiNote constructor..."); //$NON-NLS-1$
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
		lblCostoPreventivato.setText(Messages.getString("VcPnlCostiNote.lblExsistimetedCost")); //$NON-NLS-1$
		lblCostoDalCliente = new JLabel();
		lblCostoDalCliente.setBounds(new Rectangle(80, 77, 136, 16));
		lblCostoDalCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoDalCliente.setText(Messages.getString("VcPnlCostiNote.lblChargedToCust")); //$NON-NLS-1$
		lblCostoSostenuto = new JLabel();
		lblCostoSostenuto.setBounds(new Rectangle(72, 44, 142, 16));
		lblCostoSostenuto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCostoSostenuto.setText(Messages.getString("VcPnlCostiNote.lblSupportedCost")); //$NON-NLS-1$
		lblCosti = new JLabel();
		lblCosti.setBounds(new Rectangle(39, 14, 79, 16));
		lblCosti.setText(Messages.getString("VcPnlCostiNote.lblCosts")); //$NON-NLS-1$
		lblNoteInterne = new JLabel();
		lblNoteInterne.setBounds(new Rectangle(74, 285, 309, 16));
		lblNoteInterne.setText(Messages.getString("VcPnlCostiNote.lblInternalNote")); //$NON-NLS-1$
		lblNotePerStampa = new JLabel();
		lblNotePerStampa.setText(Messages.getString("VcPnlCostiNote.PrintNote")); //$NON-NLS-1$
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
	private JFormattedTextField getTxfCostoSostenuto() {
		if (txfCostoSostenuto == null) {
			txfCostoSostenuto = new JFormattedTextField(scheda.getCostoInterno());
			txfCostoSostenuto.setFormatterFactory(new DefaultFormatterFactory(new CurrencyFormatter(Locale.getDefault())));
			txfCostoSostenuto.setBounds(new Rectangle(227, 42, 95, 25));
			if(modality == mode.view){
				txfCostoSostenuto.setEditable(false);
			}
			txfCostoSostenuto.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoSostenuto.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoSostenuto.commitEdit();
						scheda.setCostoInterno((Number)txfCostoSostenuto.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcPnlCostiNote.msgValueError"), //$NON-NLS-1$
								Messages.getString("VcPnlCostiNote.msgTitleWarning"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
						txfCostoSostenuto.setValue(scheda.getCostoInterno());
					}
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
	private JFormattedTextField getTxfCostoDalCliente() {
		if (txfCostoDalCliente == null) {
			txfCostoDalCliente = new JFormattedTextField(scheda.getPagatoDalCliente());
			txfCostoDalCliente.setFormatterFactory(new DefaultFormatterFactory(new CurrencyFormatter(Locale.getDefault())));
		    txfCostoDalCliente.setBounds(new Rectangle(227, 75, 95, 25));
			if(modality == mode.view){
				txfCostoDalCliente.setEditable(false);
			}
			txfCostoDalCliente.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoDalCliente.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoDalCliente.commitEdit();
						scheda.setPagatoDalCliente((Number)txfCostoDalCliente.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcPnlCostiNote.msgValueError"), //$NON-NLS-1$
								Messages.getString("VcPnlCostiNote.msgTitleWarning"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
						txfCostoDalCliente.setValue(scheda.getPagatoDalCliente());
					}
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
	private JFormattedTextField getTxfCostoPreventivato() {
		if (txfCostoPreventivato == null) {
			txfCostoPreventivato = new JFormattedTextField(scheda.getCostoPreventivo());
			txfCostoPreventivato.setFormatterFactory(new DefaultFormatterFactory(new CurrencyFormatter(Locale.getDefault())));
		    txfCostoPreventivato.setBounds(new Rectangle(524, 42, 95, 25));
			if(modality == mode.view){
				txfCostoPreventivato.setEditable(false);
			}
			txfCostoPreventivato.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoPreventivato.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoPreventivato.commitEdit();
						scheda.setCostoPreventivo((Number)txfCostoPreventivato.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								Messages.getString("VcPnlCostiNote.msgValueError"), //$NON-NLS-1$
								Messages.getString("VcPnlCostiNote.msgTitleWarning"), JOptionPane.WARNING_MESSAGE); //$NON-NLS-1$
						txfCostoPreventivato.setValue(scheda.getCostoPreventivo());
					}
				}
			});		
		}
		return txfCostoPreventivato;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
