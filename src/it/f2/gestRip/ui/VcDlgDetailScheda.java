package it.f2.gestRip.ui;


import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.control.PrintAction;
import it.f2.gestRip.model.BinScheda;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JDialog;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.WindowConstants;
import javax.swing.JCheckBox;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.apache.log4j.Logger;

public class VcDlgDetailScheda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane tbpSchedaApp = null;
	private VcPnlApparecchio pnlApparecchio = null;
	private VcPnlDatiCLiente pnlDatiCliente = null;
	private VcPnlCostiNote pnlCostiNote = null;
	private VcPnlRiparazione pnlRiparazione = null;
	private JPanel pnlTesta = null;
	private JLabel lblSchedaN = null;
	private JFormattedTextField txfNumScheda = null;
	private JLabel lblDataApertura = null;
	private JDateChooser txfDataApertura = null;
	
	private mode modality = null;
	private BinScheda scheda = null;
	private BinScheda schedaLastSavepoint = null;
	private JLabel lblDataChiusura = null;
	private JDateChooser txfDataChiusura = null;
	private JPanel pnlNavig = null;
	private JButton btnNext = null;
	private JButton btnSalva = null;
	private JButton btnPrint = null;
	private JButton btnPrev = null;
	private JButton btnCanc = null;
	private VcIfrListaSchede listaSchede = null;
	private JCheckBox chbRiconsegnato = null;
	private JLabel lblRiconsegnato = null;
	private Connection con = null;
	
	public static enum mode{
		insert,update,view;
	};
	
	/**
	 * This is the xxx default constructor
	 */
	public VcDlgDetailScheda(VcMainFrame parent,VcIfrListaSchede listaSchede,mode modality,int idScheda) {
		super(parent,true);
		Logger.getRootLogger().debug("VcDlgDetailScheda constructor...");
		this.modality = modality;
		this.listaSchede = listaSchede;
		this.con = CommonMetodBin.getConn();
		DbSchedaAction lsa = new DbSchedaAction();
		try {
			Logger.getRootLogger().debug("Set modality...");
			if(modality == mode.insert || idScheda==0){
				scheda = lsa.addScheda(con);
			}else{
				scheda = lsa.getScheda(con,idScheda);
			}
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Set modality \n"+e+"\n");
			e.printStackTrace();
		}
		schedaLastSavepoint = scheda.clone();
		initialize();
	}
	
	private void close(){
		boolean disposing = false;
		if(modality != mode.view && !scheda.sameData(schedaLastSavepoint)){
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					"Vuoi salvare le modifiche effettuate.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			if (confirm == JOptionPane.OK_OPTION){
				try {
					Logger.getRootLogger().debug("Closing 1...");
					save();
					con.commit();
					if(listaSchede!=null){
						listaSchede.getTblList().refresh();
					}
				} catch (SQLException e1) {
					Logger.getRootLogger().error("Exception in Closing 1 \n"+e1+"\n");
					//e1.printStackTrace();
				}
				disposing = true;
			}else if (confirm == JOptionPane.NO_OPTION){
				try {
					Logger.getRootLogger().debug("Closing 2...");
					con.rollback();
					if(listaSchede!=null){
						listaSchede.getTblList().refresh();
					}
				} catch (SQLException e1) {
					Logger.getRootLogger().error("Exception in Closing 2 \n"+e1+"\n");
					//e1.printStackTrace();
				}
				setVisible(false);
				dispose();
			}
		}else{
			try {
				Logger.getRootLogger().debug("Closing 3...");
				con.rollback();
				if(listaSchede!=null){
					listaSchede.getTblList().refresh();
				}
			} catch (SQLException e1) {
				Logger.getRootLogger().error("Exception in Closing 3 \n"+e1+"\n");
				//e1.printStackTrace();
			}
			disposing = true;
		}
		CommonMetodBin.closeConn(con);
		if(disposing){
			setVisible(false);
			dispose();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(831, 600);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle("Scheda Apparecchio");
		this.setContentPane(getJContentPane());
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				close();
			}
		});
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
			jContentPane.add(getPnlTesta(), BorderLayout.NORTH);
			jContentPane.add(getTbpSchedaApp(), BorderLayout.CENTER);
			jContentPane.add(getPnlNavig(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes tbpSchedaApp	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTbpSchedaApp() {
		if (tbpSchedaApp == null) {
			tbpSchedaApp = new JTabbedPane();
			tbpSchedaApp.addTab("Apparecchio in riparazione", null, getPnlApparecchio(), null);
			tbpSchedaApp.addTab("Dati Cliente", null, getPnlDatiCliente(), null);
			tbpSchedaApp.addTab("Costi & Note", null, getPnlCostiNote(), null);
			tbpSchedaApp.addTab("Riparazione", null, getPnlRiparazione(), null);
			tbpSchedaApp.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(tbpSchedaApp.getSelectedIndex() < 1){
						getBtnPrev().setEnabled(false);
						getBtnNext().setEnabled(true);
					} else if(tbpSchedaApp.getSelectedIndex() > 2){
						getBtnPrev().setEnabled(true);
						getBtnNext().setEnabled(false);
					} else {
						getBtnPrev().setEnabled(true);
						getBtnNext().setEnabled(true);
					}
				}
			});
		}
		return tbpSchedaApp;
	}

	/**
	 * This method initializes pnlApparecchio	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcPnlApparecchio getPnlApparecchio() {
		if (pnlApparecchio == null) {
			pnlApparecchio = new VcPnlApparecchio(modality,scheda,this,con);
		}
		return pnlApparecchio;
	}

	/**
	 * This method initializes pnlDatiCliente	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcPnlDatiCLiente getPnlDatiCliente() {
		if (pnlDatiCliente == null) {
			pnlDatiCliente = new VcPnlDatiCLiente(modality,scheda,this,con);
		}
		return pnlDatiCliente;
	}

	/**
	 * This method initializes pnlCostiNote	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private VcPnlCostiNote getPnlCostiNote() {
		if (pnlCostiNote == null) {
			pnlCostiNote = new VcPnlCostiNote(modality,scheda);
		}
		return pnlCostiNote;
	}

	/**
	 * This method initializes PnlRiparazione	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlRiparazione() {
		if (pnlRiparazione == null) {
			pnlRiparazione = new VcPnlRiparazione(modality,scheda);
		}
		return pnlRiparazione;
	}

	/**
	 * This method initializes pnlTesta	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlTesta() {
		if (pnlTesta == null) {
			lblRiconsegnato = new JLabel();
			lblRiconsegnato.setBounds(new Rectangle(720, 14, 98, 16));
			lblRiconsegnato.setText("Riconsegnato");
			lblDataChiusura = new JLabel();
			lblDataChiusura.setBounds(new Rectangle(444, 14, 118, 16));
			lblDataChiusura.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataChiusura.setText("Data riconsegna:");
			lblDataApertura = new JLabel();
			lblDataApertura.setBounds(new Rectangle(202, 11, 103, 16));
			lblDataApertura.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDataApertura.setText("Data Ingresso:");
			lblSchedaN = new JLabel();
			lblSchedaN.setText("Scheda Numero: ");
			lblSchedaN.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSchedaN.setBounds(new Rectangle(6, 11, 111, 16));
			pnlTesta = new JPanel();
			pnlTesta.setLayout(null);
			pnlTesta.setSize(800,200);
			pnlTesta.setPreferredSize(new Dimension(800, 40));
			pnlTesta.add(lblSchedaN, null);
			pnlTesta.add(getTxfNumScheda(), null);
			pnlTesta.add(lblDataApertura, null);
			pnlTesta.add(getTxfDataApertura(), null);
			pnlTesta.add(lblDataChiusura, null);
			pnlTesta.add(getTxfDataChiusura(), null);
			pnlTesta.add(getChbRiconsegnato(), null);
			pnlTesta.add(lblRiconsegnato, null);
		}
		return pnlTesta;
	}

	/**
	 * This method initializes txfNumScheda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxfNumScheda() {
		if (txfNumScheda == null) {
			txfNumScheda = new JFormattedTextField(scheda.getId());
			DefaultFormatter fmt = new NumberFormatter(new DecimalFormat("###0"));
		    fmt.setValueClass(Integer.class);
		    DefaultFormatterFactory fmtFactory = new DefaultFormatterFactory(fmt, fmt, fmt);
		    txfNumScheda.setFormatterFactory(fmtFactory);
			txfNumScheda.setBounds(new Rectangle(121, 9, 72, 25));
			txfNumScheda.setText(scheda.getId()+"");
			if (modality == mode.view){
				txfNumScheda.setEditable(false);
			}
			txfNumScheda.setFocusLostBehavior(JFormattedTextField.PERSIST);
			txfNumScheda.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					boolean existScheda;
					try {
						txfNumScheda.commitEdit();
						int val = (Integer)txfNumScheda.getValue();
						if (val!=scheda.getId()){
							Logger.getRootLogger().debug("getTxfNumScheda focus listener...");
							existScheda = DbSchedaAction.existScheda(con,val);
							if(existScheda){
								JOptionPane.showMessageDialog(getParent(), 
										"Esiste già una scheda con il numero inserito.", 
										"Errore", JOptionPane.ERROR_MESSAGE);
								txfNumScheda.setValue(scheda.getId());
							}else{
								scheda.setId(val);
							}
						}
						
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(getParent(),
								"Valore errato. ",
								"Warning", JOptionPane.WARNING_MESSAGE);
						txfNumScheda.setValue(scheda.getId());
					} catch (SQLException e1) {
						Logger.getRootLogger().error("Exception in getTxfNumScheda focus listener \n"+e1+"\n");
						//e1.printStackTrace();
						txfNumScheda.setValue(scheda.getId());
					}
					
				}
			});
		}
		return txfNumScheda;
	}

	/**
	 * This method initializes txfDataApertura	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JDateChooser getTxfDataApertura() {
		if (txfDataApertura == null) {
			txfDataApertura = new JDateChooser();
			txfDataApertura.setBounds(new Rectangle(310, 9, 128, 25));
			txfDataApertura.setDate(scheda.getDataInserimento());
			if (modality == mode.view){
				txfDataApertura.setEnabled(false);
			}
			txfDataApertura.addPropertyChangeListener("date",
				new java.beans.PropertyChangeListener() {
					public void propertyChange(java.beans.PropertyChangeEvent e) {
						scheda.setDataInserimento(new java.sql.Date(txfDataApertura.getDate().getTime()));
					}
				});
		}
		return txfDataApertura;
	}

	/**
	 * This method initializes txfDataChiusura	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JDateChooser getTxfDataChiusura() {
		if (txfDataChiusura == null) {
			txfDataChiusura = new JDateChooser();
			txfDataChiusura.setBounds(new Rectangle(567, 9, 128, 25));
			txfDataChiusura.setDate(scheda.getDataChiusura());
			if (modality == mode.view){
				txfDataChiusura.setEnabled(false);
			}
			txfDataChiusura.addPropertyChangeListener("date",
				new java.beans.PropertyChangeListener() {
					public void propertyChange(java.beans.PropertyChangeEvent e) {
						if (txfDataChiusura.getDate() == null)
							scheda.setDataChiusura(null);
						else
							scheda.setDataChiusura(new java.sql.Date(txfDataChiusura.getDate().getTime()));
					}
				});
		}
		return txfDataChiusura;
	}

	/**
	 * This method initializes pnlNavig	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPnlNavig() {
		if (pnlNavig == null) {
			pnlNavig = new JPanel();
			pnlNavig.setLayout(new FlowLayout());
			pnlNavig.add(getBtnPrev(), null);
			pnlNavig.add(getBtnNext(), null);
			pnlNavig.add(getBtnSalva(), null);
			pnlNavig.add(getBtnPrint(), null);
			pnlNavig.add(getBtnCanc(), null);
		}
		return pnlNavig;
	}

	/**
	 * This method initializes btnNext	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton();
			btnNext.setText("Avanti");
			btnNext.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/forward.png")));
			btnNext.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getTbpSchedaApp().setSelectedIndex(
							getTbpSchedaApp().getSelectedIndex()+1);
				}
			});
			if(getTbpSchedaApp().getSelectedIndex() > 2){
				btnPrev.setEnabled(false);
			}
			
		}
		return btnNext;
	}

	/**
	 * This method initializes btnSalva	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSalva() {
		if (btnSalva == null) {
			btnSalva = new JButton();
			btnSalva.setText("Salva");
			btnSalva.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/filesave.png")));
			if(modality == mode.view){
				btnSalva.setEnabled(false);
			}
			btnSalva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					save();
				}
			});
		}
		return btnSalva;
	}
	
	private void save(){
		try {
			Logger.getRootLogger().debug("Saving...");
			DbSchedaAction.saveScheda(con,scheda);
			schedaLastSavepoint = scheda.clone();
		} catch (SQLException e1) {
			Logger.getRootLogger().error("Exception in Saving \n"+e1+"\n");
			JOptionPane.showMessageDialog(CommonMetodBin.getInstance().getMainFrame(),
                    "Errore: "
                    +e1.getMessage(),"Errore...",JOptionPane.ERROR_MESSAGE);
			//e1.printStackTrace();
		}
	}

	/**
	 * This method initializes btnPrint	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPrint() {
		if (btnPrint == null) {
			btnPrint = new JButton();
			btnPrint.setText("Stampa");
			btnPrint.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/fileprint.png")));
			btnPrint.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					print();
				}
			});
		}
		return btnPrint;
	}
	
	private void print(){
		Logger.getRootLogger().debug("printing");
		PrintAction pa = new PrintAction();
		if(modality != mode.view && !scheda.sameData(schedaLastSavepoint)){
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					"Vuoi salvare le modifiche effettuate.",
					"Info", JOptionPane.INFORMATION_MESSAGE);
			if (confirm == JOptionPane.OK_OPTION){
				try {
					Logger.getRootLogger().debug("Print Save...");
					save();
					con.commit();
					pa.callReportRicevuta(this,scheda.getId(),con);
				} catch (SQLException e1) {
					Logger.getRootLogger().error("Exception in Closing 1 \n"+e1+"\n");
					//e1.printStackTrace();
				}
			}else if (confirm == JOptionPane.NO_OPTION){
				Logger.getRootLogger().debug("Print No Save...");
				pa.callReportRicevuta(this,scheda.getId(),con);
			}
		}else{
			Logger.getRootLogger().debug("Print No Save...");
			pa.callReportRicevuta(this,scheda.getId(),con);
		}
	}

	/**
	 * This method initializes btnPrev	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnPrev() {
		if (btnPrev == null) {
			btnPrev = new JButton();
			btnPrev.setText("Indietro");
			btnPrev.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/back.png")));
			btnPrev.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getTbpSchedaApp().setSelectedIndex(
							getTbpSchedaApp().getSelectedIndex()-1);
				}
			});
			if(getTbpSchedaApp().getSelectedIndex() == 0){
				btnPrev.setEnabled(false);
			}
		}
		return btnPrev;
	}

	/**
	 * This method initializes btnCanc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton();
			btnCanc.setText("Chiudi");
			btnCanc.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_cancel.png")));
			btnCanc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
				}
			});
		}
		return btnCanc;
	}

	/**
	 * This method initializes chbRiconsegnato	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getChbRiconsegnato() {
		if (chbRiconsegnato == null) {
			chbRiconsegnato = new JCheckBox();
			chbRiconsegnato.setBounds(new Rectangle(695, 12, 21, 21));
			if(scheda.getDataChiusura()!=null){
				chbRiconsegnato.setSelected(true);
			}
			if(modality==mode.view){
				chbRiconsegnato.setEnabled(false);
			}
			chbRiconsegnato.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(chbRiconsegnato.isSelected()){
						getTxfDataChiusura().setDate(new java.sql.Date(new Date().getTime()));
					}else{
						getTxfDataChiusura().setDate(null);
					}
				}
			});
		}
		return chbRiconsegnato;
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
