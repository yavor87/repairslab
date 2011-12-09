package net.sf.repairslab.ui.installwizard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.repairslab.EnvConstants;
import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.control.install.MySqlFileScriptExec;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.ui.txf.NumericTextField;

import org.apache.log4j.Logger;

public class VcDlgMetadataSetting extends JDialog {
	
	static private Logger  logger = Logger.getLogger(VcDlgMetadataSetting.class.getName());
	
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
	
	private static String 	  LOG_PANEL = "LOG_PANEL";
	private static JPanel 	  logPanel = null;
	
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
	private JTable confirmTableOptions;
	private JLabel lblCurrentAction = new JLabel("Starting...");
	private JProgressBar progressBar = new JProgressBar();
	private JTextArea textAreaLog = new JTextArea();
	
	
	private boolean isDbEmbedded = true;
	private String metadataLocation = EnvConstants.USER_HOME_DIR;
	private JTextField txfLocation;
	private JButton btnSelectLocation;
	
	public VcDlgMetadataSetting() {
		super();
		logger.debug("init");
		initialize();
	}
	
	/**
	 * @param owner
	 */
	public VcDlgMetadataSetting(Frame owner) {
		super(owner, true);
		logger.debug("init");
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
		this.setResizable(false);
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
			
			JLabel lblConfigurazionWizard = new JLabel(Messages.getString("VcDlgMetadataSetting.title") + "          \t                          -");
			lblConfigurazionWizard.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblConfigurazionWizard.setHorizontalAlignment(SwingConstants.CENTER);
			headerPanel.add(lblConfigurazionWizard, BorderLayout.EAST);
			
			jContentPane.add(getDesctiptorPanel(), BorderLayout.CENTER);
			
			JPanel navigationPanel = new JPanel();
			jContentPane.add(navigationPanel, BorderLayout.SOUTH);
			
			btnBack = new JButton(Messages.getString("VcDlgMetadataSetting.back"));
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setBackPanel();
				}
			});
			navigationPanel.add(btnBack);
			
			btnNext = new JButton(Messages.getString("VcDlgMetadataSetting.next"));
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setNextPanel();
				}
			});
			navigationPanel.add(btnNext);
			
			JButton btnCancel = new JButton(Messages.getString("VcDlgMetadataSetting.cancel"));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmCancel();
				}
			});
			navigationPanel.add(btnCancel);
			
			btnInstall = new JButton(Messages.getString("VcDlgMetadataSetting.install"));
			btnInstall.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					install();
				}
			});
			btnInstall.setEnabled(false);
			navigationPanel.add(btnInstall);
			
			setNextPanel();
		}
		return jContentPane;
	}
	
	private void confirmCancel() {
		int confirm = JOptionPane.showConfirmDialog(this, Messages.getString("VcDlgMetadataSetting.confirmCancel"), Messages.getString("VcDlgMetadataSetting.warn"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (confirm == JOptionPane.OK_OPTION)
			dispose();
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
			setConfirmOptions();
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
		this.repaint();
	}
	
	private JPanel getDesctiptorPanel() {
		if (descriptorPanel == null) {
			descriptorPanel = new JPanel();
			cardLayout = new CardLayout(); 
			descriptorPanel.setLayout(cardLayout);
			descriptorPanel.add(getInitialPanel(), INITIAL_PANEL);
			descriptorPanel.add(getTypeMetadataPanel(), TYPEMETADATA_PANEL);
			descriptorPanel.add(getConfirmPanel(), CONFIRM_PANEL);
			descriptorPanel.add(getLogPanel(), LOG_PANEL);
		}
		return descriptorPanel;
	}
	
	private JPanel getInitialPanel() {
		if (initialPanel == null) {
			initialPanel = new JPanel();
			
			initialPanel.setLayout(null);
			JLabel lblRow1 = new JLabel(Messages.getString("VcDlgMetadataSetting.welcome1"));
			lblRow1.setBounds(26, 11, 537, 14);
			initialPanel.add(lblRow1);
			
			JLabel lblRow1_1 = new JLabel(Messages.getString("VcDlgMetadataSetting.welcome2"));
			lblRow1_1.setBounds(47, 36, 409, 14);
			initialPanel.add(lblRow1_1);
			
			JLabel lblRow2 = new JLabel(Messages.getString("VcDlgMetadataSetting.welcome3"));
			lblRow2.setBounds(26, 276, 537, 14);
			initialPanel.add(lblRow2);
			
			JLabel lblRow3 = new JLabel(Messages.getString("VcDlgMetadataSetting.welcome4"));
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

		    JLabel lblInstallType = new JLabel(Messages.getString("VcDlgMetadataSetting.metaPanel1"));
			lblInstallType.setBounds(26, 11, 385, 14);
			typeMetadataPanel.add(lblInstallType);
			
			String isEmbParam = EnvProperties.getInstance().getProperty(EnvProperties.DB_ISEMBEDDED);
			if (isEmbParam != null && isEmbParam.equals("N"))
				isDbEmbedded = false;
			
			setEnableMysqlOptionPanel(!isDbEmbedded);
		    setEnableDerbyOptionPanel(isDbEmbedded);

			JRadioButton rdbtnLocal = new JRadioButton(Messages.getString("VcDlgMetadataSetting.metaPanel2"));
			rdbtnLocal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEnableMysqlOptionPanel(false);
					setEnableDerbyOptionPanel(true);
					isDbEmbedded = true;
				}
			});
			rdbtnLocal.setSelected(isDbEmbedded);
			rdbtnLocal.setBounds(51, 32, 418, 23);
			typeMetadataPanel.add(rdbtnLocal);
			
			JRadioButton rdbtnMySql = new JRadioButton(Messages.getString("VcDlgMetadataSetting.metaPanel3"));
			rdbtnMySql.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setEnableMysqlOptionPanel(true);
					setEnableDerbyOptionPanel(false);
					isDbEmbedded = false;
				}
			});
			rdbtnMySql.setSelected(!isDbEmbedded);
			rdbtnMySql.setBounds(51, 58, 418, 23);
			typeMetadataPanel.add(rdbtnMySql);
			
			ButtonGroup group = new ButtonGroup();
		    group.add(rdbtnLocal);
		    group.add(rdbtnMySql);
		    
		    JLabel label = new JLabel(Messages.getString("VcDlgMetadataSetting.metaPanel3"));
		    label.setBounds(26, 294, 537, 14);
		    typeMetadataPanel.add(label);
		}
		return typeMetadataPanel;
	}
	
	private JPanel getLogPanel() {

		if (logPanel == null) {
			logPanel = new JPanel();
			
			logPanel.setLayout(null);
			
			progressBar.setBounds(12, 62, 571, 20);
			logPanel.add(progressBar);
			
			JLabel lblInstallationInProgress = new JLabel("Installation in progress...");
			lblInstallationInProgress.setBounds(12, 13, 571, 16);
			logPanel.add(lblInstallationInProgress);
			
			lblCurrentAction.setBounds(12, 42, 571, 16);
			logPanel.add(lblCurrentAction);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 94, 571, 220);
			logPanel.add(scrollPane);
			scrollPane.setViewportView(textAreaLog);
		}
		return logPanel;

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
		    
		    JLabel lblInserireLaLocazione = new JLabel(Messages.getString("VcDlgMetadataSetting.derbyOpt1"));
		    lblInserireLaLocazione.setBounds(10, 0, 252, 14);
		    derbyOptionPanel.add(lblInserireLaLocazione);
		    
		    metadataLocation = EnvProperties.getInstance().getProperty(EnvProperties.DB_DERBYDIR);
		    if (metadataLocation == null || metadataLocation.equals(""))
		    	metadataLocation = EnvConstants.USER_HOME_DIR;
		    
		    rdbtnDefaultSo = new JRadioButton(Messages.getString("VcDlgMetadataSetting.derbyOpt2"));
		    rdbtnDefaultSo.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		metadataLocation = EnvConstants.USER_HOME_DIR;
		    		txfLocation.setEnabled(false);
		    		btnSelectLocation.setEnabled(false);
		    	}
		    });
		    rdbtnDefaultSo.setBounds(22, 25, 212, 23);
		    rdbtnDefaultSo.setSelected(metadataLocation.equals(EnvConstants.USER_HOME_DIR));
		    derbyOptionPanel.add(rdbtnDefaultSo);
		    
		    rdbtnPortable = new JRadioButton(Messages.getString("VcDlgMetadataSetting.derbyOpt3"));
		    rdbtnPortable.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		metadataLocation = EnvConstants.PORTABLE_HOME_DIR;
		    		txfLocation.setEnabled(false);
		    		btnSelectLocation.setEnabled(false);
		    	}
		    });
		    rdbtnPortable.setBounds(22, 51, 212, 23);
		    rdbtnPortable.setSelected(metadataLocation.equals(EnvConstants.PORTABLE_HOME_DIR));
		    derbyOptionPanel.add(rdbtnPortable);
		    
		    boolean isCustomLocation = !metadataLocation.equals(EnvConstants.USER_HOME_DIR) && !metadataLocation.equals(EnvConstants.PORTABLE_HOME_DIR);
		    rdbtnCustom = new JRadioButton(Messages.getString("VcDlgMetadataSetting.derbyOpt4"));
		    rdbtnCustom.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		txfLocation.setEnabled(true);
		    		btnSelectLocation.setEnabled(true);
		    	}
		    });
		    rdbtnCustom.setBounds(22, 77, 115, 23);
		    rdbtnCustom.setSelected(isCustomLocation);
		    derbyOptionPanel.add(rdbtnCustom);
		    
		    ButtonGroup groupLocation = new ButtonGroup();
		    groupLocation.add(rdbtnDefaultSo);
		    groupLocation.add(rdbtnPortable);
		    groupLocation.add(rdbtnCustom);
		    
		    txfLocation = new JTextField();
		    txfLocation.setEnabled(isCustomLocation);
		    txfLocation.setBounds(22, 107, 212, 20);
		    derbyOptionPanel.add(txfLocation);
		    txfLocation.setColumns(10);
		    
		    txfLocation.setText(metadataLocation);
		    
		    btnSelectLocation = new JButton("Select");
		    btnSelectLocation.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		setCustomLocation();
		    	}
		    });
		    btnSelectLocation.setEnabled(isCustomLocation);
		    btnSelectLocation.setBounds(164, 81, 70, 23);
		    derbyOptionPanel.add(btnSelectLocation);
		}
		return derbyOptionPanel;
	}
	
	private void setCustomLocation() {
		JFileChooser chooser = new JFileChooser(EnvConstants.USER_HOME_DIR);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			txfLocation.setText(chooser.getSelectedFile().getAbsolutePath());
			metadataLocation = chooser.getSelectedFile().getAbsolutePath();
		}
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
		    String psw = EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW);
		    if (psw == null)
		    	psw = "";
		    txpPsw.setText(psw);
		    mysqlOptionPanel.add(txpPsw);
		    
		    txfTablrPrefix = new JTextField();
		    txfTablrPrefix.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_TABLE_PREFIX));
		    txfTablrPrefix.setBounds(89, 145, 164, 20);
		    mysqlOptionPanel.add(txfTablrPrefix);
		    txfTablrPrefix.setColumns(10);
		    btnTestConnection.setBounds(72, 170, 128, 23);
		    mysqlOptionPanel.add(btnTestConnection);
		    
		    JLabel lblInserireIParametri = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt1"));
		    lblInserireIParametri.setBounds(10, 0, 243, 14);
		    mysqlOptionPanel.add(lblInserireIParametri);
		    
		    JLabel lblHost = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt2"));
		    lblHost.setBounds(10, 24, 79, 14);
		    mysqlOptionPanel.add(lblHost);
		    
		    JLabel lblPort = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt3"));
		    lblPort.setBounds(10, 48, 79, 14);
		    mysqlOptionPanel.add(lblPort);
		    
		    JLabel lblDbName = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt4"));
		    lblDbName.setBounds(10, 73, 79, 14);
		    mysqlOptionPanel.add(lblDbName);
		    
		    JLabel lblUser = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt5"));
		    lblUser.setBounds(10, 98, 79, 14);
		    mysqlOptionPanel.add(lblUser);
		    
		    JLabel lblPassword = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt6"));
		    lblPassword.setBounds(10, 123, 79, 14);
		    mysqlOptionPanel.add(lblPassword);
		    
		    JLabel lblTablePrefix = new JLabel(Messages.getString("VcDlgMetadataSetting.mysqlOpt7"));
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
			
			JLabel lblTitConfirm = new JLabel(Messages.getString("VcDlgMetadataSetting.confirmPnl1"));
			lblTitConfirm.setBounds(26, 11, 385, 14);
			confirmPanel.add(lblTitConfirm);
			
			JLabel lblFootConfirm = new JLabel(Messages.getString("VcDlgMetadataSetting.confirmPnl2"));
			lblFootConfirm.setBounds(26, 294, 537, 14);
			confirmPanel.add(lblFootConfirm);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 36, 480, 224);
			confirmPanel.add(scrollPane);

			confirmTableOptions = new JTable();
			
			scrollPane.setViewportView(confirmTableOptions);
			
		}
		return confirmPanel;
	}
	
	private void setConfirmOptions() {
		
		Vector confirmOptions = new Vector();
		
		Vector v1_dbEmbedded = new Vector();
		v1_dbEmbedded.add(Messages.getString("VcDlgMetadataSetting.confirmOpt1"));
		v1_dbEmbedded.add(isDbEmbedded);
		confirmOptions.add(v1_dbEmbedded);
		
		Vector v2_metadataLocation = new Vector();
		v2_metadataLocation.add(Messages.getString("VcDlgMetadataSetting.confirmOpt2"));
		v2_metadataLocation.add(metadataLocation);
		confirmOptions.add(v2_metadataLocation);
		
		Vector v3_txfHost = new Vector();
		v3_txfHost.add("MySql Host");
		v3_txfHost.add(txfHost.getText());
		confirmOptions.add(v3_txfHost);
		
		Vector v4_txfPort = new Vector();
		v4_txfPort.add("MySql Port");
		v4_txfPort.add(txfPort.getText());
		confirmOptions.add(v4_txfPort);
		
		Vector v5_txfDbName = new Vector();
		v5_txfDbName.add("MySql Db Name");
		v5_txfDbName.add(txfDbName.getText());
		confirmOptions.add(v5_txfDbName);
		
		Vector v6_txfUrer = new Vector();
		v6_txfUrer.add("MySql User");
		v6_txfUrer.add(txfUrer.getText());
		confirmOptions.add(v6_txfUrer);
		
		Vector v7_txfTablrPrefix = new Vector();
		v7_txfTablrPrefix.add("MySql Table Prefix");
		v7_txfTablrPrefix.add(txfTablrPrefix.getText());
		confirmOptions.add(v7_txfTablrPrefix);
		
		Vector<String> colname = new Vector<String>();
		colname.add(Messages.getString("VcDlgMetadataSetting.confirmOpt3"));
		colname.add(Messages.getString("VcDlgMetadataSetting.confirmOpt4"));
		TableModel dataModel = new DefaultTableModel(confirmOptions, colname);
		
		confirmTableOptions.setModel(dataModel);
	}
	
	private void install() {
		
		setCurrentPanel(LOG_PANEL);
		
		logger.debug("install");
		
		// Test connessione
        String url = "jdbc:mysql://" + txfHost.getText() + ":" + txfPort.getText() + "/" + txfDbName.getText();
        String result = CommonMetodBin.getInstance().testServerConn(CommonMetodBin.MYSQL_DRIVER, url, txfUrer.getText(), txpPsw.getText());
		
		if (isDbEmbedded || result.equals("Ok")) {
			
			logger.debug("is setting properties");
			
			// Set properties parameters
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_ISEMBEDDED, isDbEmbedded ? "S" : "N");
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_DRIVER, isDbEmbedded ? CommonMetodBin.DERBYEMBEDDED_DRIVER : CommonMetodBin.MYSQL_DRIVER);
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_DERBYDIR, metadataLocation);
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_URL, url);
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_USER, txfUrer.getText());
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_PASSW, txpPsw.getText());
	        EnvProperties.getInstance().setProperty(EnvProperties.DB_TABLE_PREFIX, txfTablrPrefix.getText());
			
	        
	        
	        try {
	        	// Installazione database
//		        InstallUtil install = new InstallUtil(isDbEmbedded, progressBar, lblCurrentAction, textAreaLog);
//		        install.run();
	        	if (isDbEmbedded) {
	    			logger.debug("Embedded installazion");
	    			
	    		} else {
	    			logger.debug("Server installazion");
	    			final MySqlFileScriptExec mySqlFileScriptExec = new MySqlFileScriptExec();
	    			progressBar.setStringPainted(true);
	    			setProgressStatus(0);
//	    			Thread t = new Thread(){
//	    				@Override
//	    				public void run() {
	    					try {
	    	    				List<String> instInstrs = mySqlFileScriptExec.getInstallInstructions();
	    	    				float increment = 100 / instInstrs.size();
	    	    				float percent = 0;
	    	    				for (String instr : instInstrs) {
	    	    					try {
	    	    						logger.debug("Script in execution: " + instr);
	    	    						percent = percent + increment;
//	    	    						lblCurrentAction.setText(instr);
//	    	    						textAreaLog.append(instr + "\n\n");
//	    	    						progressBar.setValue(Math.round(percent));
	    	    						mySqlFileScriptExec.executeInstruction(instr);
	    	    						
	    	    						setProgressStatus(Math.round(percent));
	    	    	                } catch (SQLException e) {
	    	    		                logger.error(e+"\n", e); 
//	    	    		                throw e;
	    	    	                }
	    	    				}
	    	    				
	    	    				JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgMetadataSetting.installComplete"), Messages.getString("VcDlgMetadataSetting.info"), JOptionPane.INFORMATION_MESSAGE);
	    	    		        EnvProperties.getInstance().saveFileProperty();
	    	    				
	    	    	        } catch (IOException e) {
	    	    	        	logger.error(e+"\n", e); 
//	    	    	        	throw e;
	    	    	        }
//	    				}
//	    			};
//	    			t.run();
//	    			SwingUtilities.invokeLater(t);
	    			
	    			
	    		}
		        
		        logger.debug("is ok");
//		        dispose();
		        
	        } catch (Exception e) {
	        	logger.error(e+"\n", e); 
	        	EnvProperties.getInstance().loadProperties();
	        	JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgMetadataSetting.installError1") + e.getMessage(), Messages.getString("VcDlgMetadataSetting.error"), JOptionPane.ERROR_MESSAGE);
	        }
	        
		} else {
			logger.error("Db connection error:" + result); 
			JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgMetadataSetting.installError2"), Messages.getString("VcDlgMetadataSetting.error"), JOptionPane.ERROR_MESSAGE);
		}
    }
	
	public void setProgressStatus(int status) {
        final int value = (status > 0 ? status : progressBar.getMaximum());
        Runnable setProgressBar = new Runnable() {
            public void run() {
                progressBar.setValue(value);
            }
        };
        SwingUtilities.invokeLater(setProgressBar);
    }
}
