package net.sf.repairslab.ui.installwizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.ui.txf.NumericTextField;

public class VcDlgSettingStartValue extends JDialog {
	
	private static final long	serialVersionUID	= 1L;
	private JPanel	          jContentPane	     = null;
	private String 			  currentPanel;
	private JPanel            descriptorPanel = null;
	private CardLayout        cardLayout;
	private JButton 		  btnBack;
	private JButton 		  btnNext;
	private JButton			  btnInstall;
	
	private static String 	  INITIAL_PANEL = "INITIAL_PANEL";
	private JPanel            initialPanel = null;
	
	private static String 	  TYPEMETADATA_PANEL = "TYPEMETADATA_PANEL";
	private JPanel            typeMetadataPanel = null;
	
	private static String 	  CONFIRM_PANEL = "CONFIRM_PANEL";
	private JPanel 			  confirmPanel = null;
	
	private JPanel mysqlOptionPanel = null;
	private JPanel derbyOptionPanel = null;
	private JTextField txfHost;
	private JTextField txfPort;
	private JTextField txfDbName;
	private JTextField txfUrer;
	private JPasswordField txpPsw;
	private JTextField txfTablrPrefix;
	private JButton btnTestConnection;
	private JRadioButton rdbtnDefaultSo;
	private JRadioButton rdbtnPortable;
	private JRadioButton rdbtnCustom;
	
	/**
	 * @param owner
	 */
	public VcDlgSettingStartValue(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(601, 454);
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
			
			JPanel headerPanel = new JPanel();
			jContentPane.add(headerPanel, BorderLayout.NORTH);
			headerPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel lblRepairslabImg = new JLabel("");
			lblRepairslabImg.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/logo64.png")));
			headerPanel.add(lblRepairslabImg);
			
			JLabel lblConfigurazionWizard = new JLabel("Configurazion Wizard          \t                          -");
			lblConfigurazionWizard.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblConfigurazionWizard.setHorizontalAlignment(SwingConstants.CENTER);
			headerPanel.add(lblConfigurazionWizard, BorderLayout.EAST);
			
			jContentPane.add(getDesctiptorPanel(), BorderLayout.CENTER);
			
			JPanel navigationPanel = new JPanel();
			jContentPane.add(navigationPanel, BorderLayout.SOUTH);
			
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setBackPanel();
				}
			});
			navigationPanel.add(btnBack);
			
			btnNext = new JButton("Next");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setNextPanel();
				}
			});
			navigationPanel.add(btnNext);
			
			JButton btnCancel = new JButton("Cancel");
			navigationPanel.add(btnCancel);
			
			btnInstall = new JButton("Install");
			btnInstall.setEnabled(false);
			navigationPanel.add(btnInstall);
			
			setNextPanel();
		}
		return jContentPane;
	}
	
	private void setNextPanel() {
		if (currentPanel == null) {
			btnBack.setEnabled(false);
			btnNext.setEnabled(true);
			btnInstall.setEnabled(false);
			setCurrentPanel(INITIAL_PANEL);
		} else if (currentPanel.equals(INITIAL_PANEL)) {
			btnBack.setEnabled(true);
			btnNext.setEnabled(true);
			btnInstall.setEnabled(false);
			setCurrentPanel(TYPEMETADATA_PANEL);
		} else if (currentPanel.equals(TYPEMETADATA_PANEL)) {
			btnBack.setEnabled(true);
			btnNext.setEnabled(false);
			btnInstall.setEnabled(true);
			setCurrentPanel(CONFIRM_PANEL);
		}
	}
	
	private void setBackPanel() {
		if (currentPanel.equals(TYPEMETADATA_PANEL)) {
			btnBack.setEnabled(false);
			btnNext.setEnabled(true);
			btnInstall.setEnabled(false);
			setCurrentPanel(INITIAL_PANEL);
		} else if (currentPanel.equals(CONFIRM_PANEL)) {
			btnBack.setEnabled(true);
			btnNext.setEnabled(true);
			btnInstall.setEnabled(false);
			setCurrentPanel(TYPEMETADATA_PANEL);
		}
	}
	
	private String getCurrentPanel() {
		return currentPanel;
	}
	
	private void setCurrentPanel(String currentPanelId) {
		cardLayout.show(getDesctiptorPanel(), currentPanelId);
		this.currentPanel = currentPanelId;
	}
	
	private JPanel getDesctiptorPanel() {
		if (descriptorPanel == null) {
			descriptorPanel = new JPanel();
			cardLayout = new CardLayout(); 
			descriptorPanel.setLayout(cardLayout);
			descriptorPanel.add(getInitialPanel(), INITIAL_PANEL);
			descriptorPanel.add(getTypeMetadataPanel(), TYPEMETADATA_PANEL);
			descriptorPanel.add(getConfirmPanel(), CONFIRM_PANEL);
		}
		return descriptorPanel;
	}
	
	private JPanel getInitialPanel() {
		if (initialPanel == null) {
			initialPanel = new JPanel();
			
			initialPanel.setLayout(null);
			JLabel lblRow1 = new JLabel("Welcome to configurazion wizard.");
			lblRow1.setBounds(26, 11, 537, 14);
			initialPanel.add(lblRow1);
			
			JLabel lblRow1_1 = new JLabel("Impostazioni di configurazione iniziali per la gestione dei dati.");
			lblRow1_1.setBounds(47, 36, 409, 14);
			initialPanel.add(lblRow1_1);
			
			JLabel lblRow2 = new JLabel("Seguire le istruzioni per configurare il sistema.");
			lblRow2.setBounds(26, 276, 537, 14);
			initialPanel.add(lblRow2);
			
			JLabel lblRow3 = new JLabel("Premere \"continua\" per proseguire.");
			lblRow3.setBounds(26, 294, 537, 14);
			initialPanel.add(lblRow3);
		}
		return initialPanel;
	}
	
	private JPanel getTypeMetadataPanel() {
		if (typeMetadataPanel == null) {
			typeMetadataPanel = new JPanel();
			typeMetadataPanel.setForeground(SystemColor.desktop);
			typeMetadataPanel.setLayout(null);
			
		    typeMetadataPanel.add(getDerbyOptionPanel());
		    derbyOptionPanel.setLayout(null);
		    
		    typeMetadataPanel.add(getMysqlOptionPanel());
		    setEnableMysqlOptionPanel(false);
			
			JLabel lblInstallType = new JLabel("Selezionare il tipo di installazione:");
			lblInstallType.setBounds(26, 11, 385, 14);
			typeMetadataPanel.add(lblInstallType);
			
			JRadioButton rdbtnLocal = new JRadioButton("Utilizzo utente singolo (database Derby su pc locale)");
			rdbtnLocal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEnableMysqlOptionPanel(false);
					setEnableDerbyOptionPanel(true);
				}
			});
			rdbtnLocal.setSelected(true);
			rdbtnLocal.setBounds(51, 32, 418, 23);
			typeMetadataPanel.add(rdbtnLocal);
			
			JRadioButton rdbtnMySql = new JRadioButton("Multiutente (database MySql)");
			rdbtnMySql.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEnableMysqlOptionPanel(true);
					setEnableDerbyOptionPanel(false);
				}
			});
			rdbtnMySql.setBounds(51, 58, 418, 23);
			typeMetadataPanel.add(rdbtnMySql);
			
			ButtonGroup group = new ButtonGroup();
		    group.add(rdbtnLocal);
		    group.add(rdbtnMySql);
		    
		    JLabel label = new JLabel("Premere \"continua\" per proseguire.");
		    label.setBounds(26, 294, 537, 14);
		    typeMetadataPanel.add(label);
		}
		return typeMetadataPanel;
	}
	
	private void setEnableDerbyOptionPanel(boolean enabled) {
		rdbtnDefaultSo.setEnabled(enabled);
		rdbtnPortable.setEnabled(enabled);
		rdbtnCustom.setEnabled(enabled);
	}
	
	private JPanel getDerbyOptionPanel() {
		if (derbyOptionPanel == null) {
			derbyOptionPanel = new JPanel();
			derbyOptionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		    derbyOptionPanel.setBounds(26, 88, 263, 201);
		    
		    JLabel lblInserireLaLocazione = new JLabel("Inserire la locazione dei metadati");
		    lblInserireLaLocazione.setBounds(10, 0, 252, 14);
		    derbyOptionPanel.add(lblInserireLaLocazione);
		    
		    rdbtnDefaultSo = new JRadioButton("Default (Consigliato)");
		    rdbtnDefaultSo.setBounds(22, 25, 212, 23);
		    rdbtnDefaultSo.setSelected(true);
		    derbyOptionPanel.add(rdbtnDefaultSo);
		    
		    rdbtnPortable = new JRadioButton("Portable");
		    rdbtnPortable.setBounds(22, 51, 212, 23);
		    derbyOptionPanel.add(rdbtnPortable);
		    
		    rdbtnCustom = new JRadioButton("Custom");
		    rdbtnCustom.setBounds(22, 77, 212, 23);
		    derbyOptionPanel.add(rdbtnCustom);
		    
		    ButtonGroup groupLocation = new ButtonGroup();
		    groupLocation.add(rdbtnDefaultSo);
		    groupLocation.add(rdbtnPortable);
		    groupLocation.add(rdbtnCustom);
		}
		return derbyOptionPanel;
	}
	
	private void setEnableMysqlOptionPanel(boolean enabled) {
		txfHost.setEnabled(enabled);
		txfPort.setEnabled(enabled);
		txfDbName.setEnabled(enabled);
		txfUrer.setEnabled(enabled);
		txpPsw.setEnabled(enabled);
		txfTablrPrefix.setEnabled(enabled);
		btnTestConnection.setEnabled(enabled);
	}
	
	private JPanel getMysqlOptionPanel() {
		if (mysqlOptionPanel == null) {
			mysqlOptionPanel = new JPanel();
			
			mysqlOptionPanel.setLayout(null);
		    mysqlOptionPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		    mysqlOptionPanel.setBounds(300, 88, 263, 201);
		    
		    txfHost = new JTextField();
		    txfHost.setText("localhost");
		    txfHost.setBounds(89, 21, 164, 20);
		    mysqlOptionPanel.add(txfHost);
		    txfHost.setColumns(10);
		    
		    txfPort = new NumericTextField();
		    txfPort.setText("3306");
		    txfPort.setBounds(89, 45, 164, 20);
		    mysqlOptionPanel.add(txfPort);
		    txfPort.setColumns(10);
		    
		    txfDbName = new JTextField();
		    txfDbName.setText("repairslab");
		    txfDbName.setBounds(89, 70, 164, 20);
		    mysqlOptionPanel.add(txfDbName);
		    txfDbName.setColumns(10);
		    
		    btnTestConnection = new JButton("Test connection");
		    btnTestConnection.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		testConnection(txfHost.getText(), txfPort.getText(), txfDbName.getText(), txfUrer.getText(), txpPsw.getText());
		    	}
		    });
		    
		    txfUrer = new JTextField();
		    txfUrer.setText("root");
		    txfUrer.setBounds(89, 95, 164, 20);
		    mysqlOptionPanel.add(txfUrer);
		    txfUrer.setColumns(10);
		    
		    txpPsw = new JPasswordField();
		    txpPsw.setBounds(89, 120, 164, 20);
		    mysqlOptionPanel.add(txpPsw);
		    
		    txfTablrPrefix = new JTextField();
		    txfTablrPrefix.setText("_rl");
		    txfTablrPrefix.setBounds(89, 145, 164, 20);
		    mysqlOptionPanel.add(txfTablrPrefix);
		    txfTablrPrefix.setColumns(10);
		    btnTestConnection.setBounds(72, 170, 128, 23);
		    mysqlOptionPanel.add(btnTestConnection);
		    
		    JLabel lblInserireIParametri = new JLabel("Inserire i parametri di connessione MySql");
		    lblInserireIParametri.setBounds(10, 0, 243, 14);
		    mysqlOptionPanel.add(lblInserireIParametri);
		    
		    JLabel lblHost = new JLabel("Host:");
		    lblHost.setBounds(10, 24, 79, 14);
		    mysqlOptionPanel.add(lblHost);
		    
		    JLabel lblPort = new JLabel("Port:");
		    lblPort.setBounds(10, 48, 79, 14);
		    mysqlOptionPanel.add(lblPort);
		    
		    JLabel lblDbName = new JLabel("DB Name:");
		    lblDbName.setBounds(10, 73, 79, 14);
		    mysqlOptionPanel.add(lblDbName);
		    
		    JLabel lblUser = new JLabel("User:");
		    lblUser.setBounds(10, 98, 79, 14);
		    mysqlOptionPanel.add(lblUser);
		    
		    JLabel lblPassword = new JLabel("Password:");
		    lblPassword.setBounds(10, 123, 79, 14);
		    mysqlOptionPanel.add(lblPassword);
		    
		    JLabel lblTablePrefix = new JLabel("Table Prefix:");
		    lblTablePrefix.setBounds(10, 148, 79, 14);
		    mysqlOptionPanel.add(lblTablePrefix);
		    
		}
	    return mysqlOptionPanel;
	}
	
	private void testConnection(String host, String port, String dbName, String user, String psw) {
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		String result = CommonMetodBin.getInstance().testServerConn(CommonMetodBin.MYSQL_DRIVER, url, user, psw);
		
		if (result.equals("Ok")) {
			JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgAdvancedOptions.msgTestOk1"), Messages.getString("VcDlgAdvancedOptions.msgTitleWarning"), JOptionPane.INFORMATION_MESSAGE);
		} else {
			String msg = Messages.getString("VcDlgAdvancedOptions.msgTestFailed1")+result;
			JOptionPane.showMessageDialog(getParent(), msg, Messages.getString("VcDlgAdvancedOptions.msgTitleWarning"), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private JPanel getConfirmPanel() {
		if (confirmPanel == null) {
			confirmPanel = new JPanel();
			confirmPanel.setLayout(null);
			
			JLabel lblTitConfirm = new JLabel("Confermare le ipostazioni selezionate");
			lblTitConfirm.setBounds(26, 11, 385, 14);
			confirmPanel.add(lblTitConfirm);
			
			JLabel lblFootConfirm = new JLabel("Premere \"Installa\" per completare l'operazione.");
			lblFootConfirm.setBounds(26, 294, 537, 14);
			confirmPanel.add(lblFootConfirm);
			
			JTextArea txaOptions = new JTextArea();
			txaOptions.setBounds(26, 36, 526, 88);
			confirmPanel.add(txaOptions);
			
		}
		return confirmPanel;
	}
	
	private void saveSettings() {
		
	}
}
