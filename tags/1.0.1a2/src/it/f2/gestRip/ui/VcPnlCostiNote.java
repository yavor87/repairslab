package it.f2.gestRip.ui;

import it.f2.gestRip.model.BinScheda;
import it.f2.gestRip.ui.VcDlgDetailScheda.mode;

import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

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
	private JFormattedTextField getTxfCostoSostenuto() {
		if (txfCostoSostenuto == null) {
			txfCostoSostenuto = new JFormattedTextField(scheda.getCostoInterno());
			DefaultFormatter fmt = new NumberFormatter(new DecimalFormat("###0.00"));
		    fmt.setValueClass(Float.class);
		    DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);
		    txfCostoSostenuto.setFormatterFactory(fmtFactory);
			txfCostoSostenuto.setBounds(new Rectangle(227, 42, 95, 25));
			if(modality == mode.view){
				txfCostoSostenuto.setEditable(false);
			}
			txfCostoSostenuto.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoSostenuto.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoSostenuto.commitEdit();
						scheda.setCostoInterno((Float)txfCostoSostenuto.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								"Valore errato. ",
								"Warning", JOptionPane.WARNING_MESSAGE);
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
			DefaultFormatter fmt = new NumberFormatter(new DecimalFormat("###0.00"));
		    fmt.setValueClass(Float.class);
		    DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);
		    txfCostoDalCliente.setFormatterFactory(fmtFactory);
		    txfCostoDalCliente.setBounds(new Rectangle(227, 75, 95, 25));
			if(modality == mode.view){
				txfCostoDalCliente.setEditable(false);
			}
			txfCostoDalCliente.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoDalCliente.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoDalCliente.commitEdit();
						scheda.setPagatoDalCliente((Float)txfCostoDalCliente.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								"Valore errato. ",
								"Warning", JOptionPane.WARNING_MESSAGE);
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
			DefaultFormatter fmt = new NumberFormatter(new DecimalFormat("###0.00"));
		    fmt.setValueClass(Float.class);
		    DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);
		    txfCostoPreventivato.setFormatterFactory(fmtFactory);
		    txfCostoPreventivato.setBounds(new Rectangle(524, 42, 95, 25));
			if(modality == mode.view){
				txfCostoPreventivato.setEditable(false);
			}
			txfCostoPreventivato.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfCostoPreventivato.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					try {
						txfCostoPreventivato.commitEdit();
						scheda.setCostoPreventivo((Float)txfCostoPreventivato.getValue());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								"Valore errato. ",
								"Warning", JOptionPane.WARNING_MESSAGE);
						txfCostoPreventivato.setValue(scheda.getCostoPreventivo());
					}
				}
			});		
		}
		return txfCostoPreventivato;
	}

} // @jve:decl-index=0:visual-constraint="10,10"