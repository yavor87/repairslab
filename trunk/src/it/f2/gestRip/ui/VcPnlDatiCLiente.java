package it.f2.gestRip.ui;

import it.f2.gestRip.control.DbSchedaAction;
import it.f2.gestRip.model.BinCliente;
import it.f2.gestRip.model.BinScheda;
import it.f2.gestRip.ui.VcDlgDetailScheda.mode;
import it.f2.util.ui.WindowUtil;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import org.apache.log4j.Logger;

public class VcPnlDatiCLiente extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblMail = null;
	private JLabel lblCognome = null;
	private JLabel lblNome = null;
	private JTextField txfAzienda = null;
	private JTextField txfCitta = null;
	private JLabel lblCitta = null;
	private JTextField txfTelefono = null;
	private JTextField txfCognome = null;
	private JTextField txfNome = null;
	private JLabel lblIndirizzo = null;
	private JLabel lblAzienda = null;
	private JTextField txfEmail = null;
	private JTextField txfPiva = null;
	private JLabel lblPiva = null;
	private JTextField txfIndirizzo = null;
	private JLabel lblTelefono = null;
	private JTextField txfMobile = null;
	private JLabel lblMobile = null;
	
	private mode modality = null;
	private BinScheda scheda = null;
	private JDialog dialog = null;
	private JButton btnSelezionaCliente = null;
	private JButton btnInserisciCliente = null;
	private JButton btnEditCliente = null;
	private JButton btnOk = null;
	private JButton btnCanc = null;
	private int newIdClienteAppo = 0;
	private modeCliente modalityCliente = modeCliente.view;  //  @jve:decl-index=0:
	private Connection con = null;
	
	public static enum modeCliente{
		insert,update,view;
	};

	/**
	 * This is the xxx default constructor
	 */
	public VcPnlDatiCLiente(mode modality,BinScheda scheda,JDialog dialog,Connection con) {
		super();
		Logger.getRootLogger().debug("VcPnlDatiCLiente constructor...");
		this.modality = modality;
		this.scheda = scheda;
		this.dialog = dialog;
		this.con = con;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setSize(759, 511);
		setLayout(null);
		int aPoint = 100;
		lblMobile = new JLabel();
		lblMobile.setBounds(new Rectangle(aPoint, 160, aPoint, 16));
		lblMobile.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMobile.setText("Mobile");
		lblTelefono = new JLabel();
		lblTelefono.setBounds(new Rectangle(aPoint, 130, aPoint, 16));
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setText("Telefono");
		lblPiva = new JLabel();
		lblPiva.setBounds(new Rectangle(aPoint, 310, aPoint, 16));
		lblPiva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPiva.setText("Partita IVA");
		lblAzienda = new JLabel();
		lblAzienda.setBounds(new Rectangle(aPoint, 280, aPoint, 16));
		lblAzienda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAzienda.setText("Azienda");
		lblIndirizzo = new JLabel();
		lblIndirizzo.setBounds(new Rectangle(aPoint, 190, aPoint, 16));
		lblIndirizzo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIndirizzo.setText("Indirizzo");
		lblCitta = new JLabel();
		lblCitta.setBounds(new Rectangle(aPoint, 220, aPoint, 16));
		lblCitta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCitta.setText("Citt�");
		lblNome = new JLabel();
		lblNome.setBounds(new Rectangle(aPoint, 70, aPoint, 16));
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setText("Nome");
		lblCognome = new JLabel();
		lblCognome.setBounds(new Rectangle(aPoint, 100, aPoint, 16));
		lblCognome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCognome.setText("Cognome");
		lblMail = new JLabel();
		lblMail.setBounds(new Rectangle(aPoint, 250, aPoint, 16));
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setText("Email");
		this.add(getTxfNome(), null);
		this.add(getTxfCognome(), null);
		add(lblMail, null);
		add(lblCognome, null);
		add(lblNome, null);
		add(getTxfAzienda(), null);
		add(getTxfCitta(), null);
		add(lblCitta, null);
		add(getTxfTelefono(), null);
		add(lblIndirizzo, null);
		add(lblAzienda, null);
		add(getTxfEmail(), null);
		add(getTxfPiva(), null);
		add(lblPiva, null);
		add(getTxfIndirizzo(), null);
		add(lblTelefono, null);
		add(getTxfMobile(), null);
		add(lblMobile, null);
		add(getBtnSelezionaCliente(), null);
		add(getBtnInserisciCliente(), null);
		this.add(getBtnEditCliente(), null);
		this.add(getBtnOk(), null);
		this.add(getBtnCanc(), null);
	}


	/**
	 * This method initializes txfAzienda	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfAzienda() {
		if (txfAzienda == null) {
			txfAzienda = new JTextField();
			txfAzienda.setText(scheda.getBinCliente().getAzienda());
			txfAzienda.setBounds(new Rectangle(220, 275, 300, 25));
			txfAzienda.setEditable(false);
		}
		return txfAzienda;
	}

	/**
	 * This method initializes txfCitta	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfCitta() {
		if (txfCitta == null) {
			txfCitta = new JTextField();
			txfCitta.setText(scheda.getBinCliente().getCity());
			txfCitta.setBounds(new Rectangle(220, 215, 300, 25));
			txfCitta.setEditable(false);
		}
		return txfCitta;
	}

	/**
	 * This method initializes txfTelefono	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfTelefono() {
		if (txfTelefono == null) {
			txfTelefono = new JTextField();
			txfTelefono.setText(scheda.getBinCliente().getPhone());
			txfTelefono.setBounds(new Rectangle(220, 125, 300, 25));
			txfTelefono.setEditable(false);
		}
		return txfTelefono;
	}

	/**
	 * This method initializes txfCognome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfCognome() {
		if (txfCognome == null) {
			txfCognome = new JTextField();
			txfCognome.setText(scheda.getBinCliente().getCognome());
			txfCognome.setBounds(new Rectangle(220, 95, 300, 25));
			txfCognome.setEditable(false);
			txfCognome.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					existCliente();
				}
			});
		}
		return txfCognome;
	}

	/**
	 * This method initializes txfNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfNome() {
		if (txfNome == null) {
			txfNome = new JTextField();
			txfNome.setText(scheda.getBinCliente().getNome());
			txfNome.setBounds(new Rectangle(220, 65, 300, 25));
			txfNome.setEditable(false);
			txfNome.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					existCliente();
				}
			});
		}
		return txfNome;
	}
	
	private void existCliente(){
		if(modalityCliente != modeCliente.view){
			try {
				Logger.getRootLogger().debug("existCliente...");
				int idCliente = DbSchedaAction.existCliente(con,
						getTxfNome().getText(), getTxfCognome().getText());
				if(idCliente>0){
					
					int confirm = JOptionPane.showConfirmDialog(getParent(),
							"Esiste gi� un cliente con queste caratteristiche. Vuoi selezionarlo?",
							"Info", JOptionPane.YES_NO_OPTION);
					if (confirm == JOptionPane.OK_OPTION){
						selezionaCliente(idCliente);
					}else if (confirm == JOptionPane.NO_OPTION){
						getTxfNome().setText("");
						getTxfCognome().setText("");
					}
					
				}
			} catch (SQLException e) {
				Logger.getRootLogger().error("Exception existCliente \n"+e+"\n");
				//e.printStackTrace();
			}
		}
	}

	/**
	 * This method initializes txfEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfEmail() {
		if (txfEmail == null) {
			txfEmail = new JTextField();
			txfEmail.setText(scheda.getBinCliente().getEmail());
			txfEmail.setBounds(new Rectangle(220, 245, 300, 25));
			txfEmail.setEditable(false);
		}
		return txfEmail;
	}

	/**
	 * This method initializes txfPiva	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfPiva() {
		if (txfPiva == null) {
			txfPiva = new JTextField();
			txfPiva.setText(scheda.getBinCliente().getPIva());
			txfPiva.setBounds(new Rectangle(220, 305, 300, 25));
			txfPiva.setEditable(false);
		}
		return txfPiva;
	}

	/**
	 * This method initializes txfIndirizzo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfIndirizzo() {
		if (txfIndirizzo == null) {
			txfIndirizzo = new JTextField();
			txfIndirizzo.setText(scheda.getBinCliente().getIndirizzo());
			txfIndirizzo.setBounds(new Rectangle(220, 185, 300, 25));
			txfIndirizzo.setEditable(false);
		}
		return txfIndirizzo;
	}

	/**
	 * This method initializes txfMobile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfMobile() {
		if (txfMobile == null) {
			txfMobile = new JTextField();
			txfMobile.setText(scheda.getBinCliente().getMobilePhone());
			txfMobile.setBounds(new Rectangle(220, 155, 300, 25));
			txfMobile.setEditable(false);
		}
		return txfMobile;
	}

	/**
	 * This method initializes btnSelezionaCliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnSelezionaCliente() {
		if (btnSelezionaCliente == null) {
			btnSelezionaCliente = new JButton();
			btnSelezionaCliente.setBounds(new Rectangle(560, 120, 160, 30));
			btnSelezionaCliente.setText("Seleziona Cliente");
			btnSelezionaCliente.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/openterm.png")));
			if(modality == mode.view){
				btnSelezionaCliente.setEnabled(false);
			}
			btnSelezionaCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					openDlgSelezionaCliente();
				}
			});
		}
		return btnSelezionaCliente;
	}
	
	private void openDlgSelezionaCliente(){
		VcDlgSelezionaCliente dlg = new VcDlgSelezionaCliente(dialog,this,con);
		WindowUtil.centerWindow(dlg);
		dlg.setVisible(true);
	}
	
	public void selezionaCliente(int idCliente){
		DbSchedaAction dbSchedaAction = new DbSchedaAction();
		try {
			Logger.getRootLogger().debug("Selecting cliente...");
			BinCliente binCliente = dbSchedaAction.getCliente(con,idCliente);
			scheda.setBinCliente(binCliente);
			refreshData(scheda.getBinCliente());
			setViewMode();
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception Selecting cliente \n"+e+"\n");
			//e.printStackTrace();
		}
	}
	
	private void refreshData(BinCliente binCliente){
		getTxfNome().setText(binCliente.getNome());
		getTxfCognome().setText(binCliente.getCognome());
		getTxfTelefono().setText(binCliente.getPhone());
		getTxfMobile().setText(binCliente.getMobilePhone());
		getTxfIndirizzo().setText(binCliente.getIndirizzo());
		getTxfCitta().setText(binCliente.getCity());
		getTxfEmail().setText(binCliente.getEmail());
		getTxfAzienda().setText(binCliente.getAzienda());
		getTxfPiva().setText(binCliente.getPIva());
	}

	/**
	 * This method initializes btnInserisciCliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnInserisciCliente() {
		if (btnInserisciCliente == null) {
			btnInserisciCliente = new JButton();
			btnInserisciCliente.setBounds(new Rectangle(560, 165, 160, 30));
			btnInserisciCliente.setText("Inserisci Cliente");
			btnInserisciCliente.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/edit_add.png")));
			if(modality == mode.view){
				btnInserisciCliente.setEnabled(false);
			}
			btnInserisciCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					inserisciCliente();
				}
			});
		}
		return btnInserisciCliente;
	}
	
	private void setUpdateMode(){
		this.modalityCliente = modeCliente.update;
		getBtnOk().setEnabled(true);
		getBtnCanc().setEnabled(true);
		getBtnSelezionaCliente().setEnabled(false);
		getBtnInserisciCliente().setEnabled(false);
		getBtnEditCliente().setEnabled(false);
		getTxfTelefono().setEditable(true);
		getTxfMobile().setEditable(true);
		getTxfIndirizzo().setEditable(true);
		getTxfCitta().setEditable(true);
		getTxfEmail().setEditable(true);
		getTxfAzienda().setEditable(true);
		getTxfPiva().setEditable(true);
	}
	
	private void setInsertMode(){
		this.modalityCliente = modeCliente.insert;
		getBtnOk().setEnabled(true);
		getBtnCanc().setEnabled(true);
		getBtnSelezionaCliente().setEnabled(false);
		getBtnInserisciCliente().setEnabled(false);
		getBtnEditCliente().setEnabled(false);
		getTxfTelefono().setEditable(true);
		getTxfMobile().setEditable(true);
		getTxfIndirizzo().setEditable(true);
		getTxfCitta().setEditable(true);
		getTxfEmail().setEditable(true);
		getTxfAzienda().setEditable(true);
		getTxfPiva().setEditable(true);
		getTxfNome().setEditable(true);
		getTxfCognome().setEditable(true);
		getTxfNome().requestFocus();
	}
	
	private void setViewMode(){
		this.modalityCliente = modeCliente.view;
		getBtnOk().setEnabled(false);
		getBtnCanc().setEnabled(false);
		getBtnSelezionaCliente().setEnabled(true);
		getBtnInserisciCliente().setEnabled(true);
		if(scheda.getBinCliente().getId() != 0){
			btnEditCliente.setEnabled(true);
		}
		getTxfNome().setEditable(false);
		getTxfCognome().setEditable(false);
		getTxfTelefono().setEditable(false);
		getTxfMobile().setEditable(false);
		getTxfIndirizzo().setEditable(false);
		getTxfCitta().setEditable(false);
		getTxfEmail().setEditable(false);
		getTxfAzienda().setEditable(false);
		getTxfPiva().setEditable(false);
	}
	
	private void inserisciCliente(){
		try {
			Logger.getRootLogger().debug("Inserting Cliente...");
			//scheda.setBinCliente(DbSchedaAction.addCliente());
			BinCliente binCliente = DbSchedaAction.addCliente(con);
			newIdClienteAppo = binCliente.getId();
			refreshData(binCliente);
			setInsertMode();
		} catch (SQLException e) {
			Logger.getRootLogger().error("Exception in Inserting Cliente \n"+e+"\n");
			//e.printStackTrace();
		}
	}
	
	private void modificaCliente(){
		setUpdateMode();
	}

	/**
	 * This method initializes btnEditCliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditCliente() {
		if (btnEditCliente == null) {
			btnEditCliente = new JButton();
			btnEditCliente.setBounds(new Rectangle(560, 210, 160, 30));
			btnEditCliente.setText("Modifica dati Cliente");
			btnEditCliente.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/edit.png")));
			if(modality == mode.view || scheda.getBinCliente().getId() == 0){
				btnEditCliente.setEnabled(false);
			}
			btnEditCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					modificaCliente();
				}
			});
		}
		return btnEditCliente;
	}

	/**
	 * This method initializes btnOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setBounds(new Rectangle(249, 357, 85, 30));
			btnOk.setEnabled(false);
			btnOk.setText("Ok");
			btnOk.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_ok.png")));
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					scheda.getBinCliente().setNome(getTxfNome().getText());
					scheda.getBinCliente().setCognome(getTxfCognome().getText());
					scheda.getBinCliente().setPhone(getTxfTelefono().getText());
					scheda.getBinCliente().setMobilePhone(getTxfMobile().getText());
					scheda.getBinCliente().setIndirizzo(getTxfIndirizzo().getText());
					scheda.getBinCliente().setCity(getTxfCitta().getText());
					scheda.getBinCliente().setEmail(getTxfEmail().getText());
					scheda.getBinCliente().setAzienda(getTxfAzienda().getText());
					scheda.getBinCliente().setPIva(getTxfPiva().getText());
					
					if(modalityCliente == modeCliente.insert){
						//inserimento in bin scheda
						scheda.getBinCliente().setId(newIdClienteAppo);
						try {
							Logger.getRootLogger().debug("getBtnOk ins...");
							DbSchedaAction.insCliente(con,scheda.getBinCliente());
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception getBtnOk ins \n"+e1+"\n");
							//e1.printStackTrace();
						}
					} else if (modalityCliente == modeCliente.update){
						try {
							Logger.getRootLogger().debug("getBtnOk upd...");
							DbSchedaAction.saveCliente(con,scheda.getBinCliente());
						} catch (SQLException e1) {
							Logger.getRootLogger().error("Exception getBtnOk upd \n"+e1+"\n");
							//e1.printStackTrace();
						}
					}
					
					setViewMode();
					refreshData(scheda.getBinCliente());
				}
			});
		}
		return btnOk;
	}

	/**
	 * This method initializes btnCanc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton();
			btnCanc.setBounds(new Rectangle(345, 357, 85, 30));
			btnCanc.setEnabled(false);
			btnCanc.setText("Canc");
			btnCanc.setIcon(new ImageIcon(getClass().getResource(
				"/it/f2/gestRip/ui/img/button_cancel.png")));
			btnCanc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setViewMode();
					refreshData(scheda.getBinCliente());
				}
			});
		}
		return btnCanc;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
