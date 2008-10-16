package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.control.CommonMetodBin;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

public class VcDlgAdvancedOptions extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField txpJdbcDriver = null;
	private JTextField txpJdbcUrl = null;
	private JTextField txpJdbcUser = null;
	private JPasswordField txpJdbcPsw = null;
	private JLabel lblGenerali = null;
	private JLabel lblJdbcDriver = null;
	private JLabel lblJdbcUrl = null;
	private JLabel lblJdbcUser = null;
	private JLabel LblJdbcPsw = null;
	private JLabel lblDBOptions = null;
	private JButton btnCanc = null;
	private JButton btnOk = null;
	private JLabel lblStartCmd = null;
	private JTextField txfStartCmd = null;
	private JTextField txfServeProc = null;
	private JLabel lblServerProc = null;
	private JCheckBox ckbEmbedded = null;
	private JButton btnTestConn = null;
	/**
	 * This is the xxx default constructor
	 */
	public VcDlgAdvancedOptions(Frame owner) {
		super(owner, true);
		Logger.getRootLogger().debug("VcDlgAdvancedOptions constructor...");
		initialize();
		if(EnvProperties.getInstance().getProperty(
				EnvProperties.DB_ISEMBEDDED).equalsIgnoreCase("S")){
			setEmbedded();
		}else{
			setNonEmbedded();
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(524, 398);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblServerProc = new JLabel();
			lblServerProc.setBounds(new Rectangle(7, 63, 132, 15));
			lblServerProc.setText("Processo Server");
			lblStartCmd = new JLabel();
			lblStartCmd.setBounds(new Rectangle(8, 13, 126, 16));
			lblStartCmd.setText("Comando di avvio");
			lblDBOptions = new JLabel();
			lblDBOptions.setBounds(new Rectangle(46, 126, 119, 16));
			lblDBOptions.setText("COnfigurazione DB");
			LblJdbcPsw = new JLabel();
			LblJdbcPsw.setBounds(new Rectangle(7, 249, 90, 16));
			LblJdbcPsw.setText("JDBC Passw");
			lblJdbcUser = new JLabel();
			lblJdbcUser.setBounds(new Rectangle(7, 219, 90, 16));
			lblJdbcUser.setText("JDBC User");
			lblJdbcUrl = new JLabel();
			lblJdbcUrl.setBounds(new Rectangle(7, 189, 90, 16));
			lblJdbcUrl.setText("JDBC Url");
			lblJdbcDriver = new JLabel();
			lblJdbcDriver.setBounds(new Rectangle(7, 159, 90, 16));
			lblJdbcDriver.setText("JDBC Driver");
			lblGenerali = new JLabel();
			lblGenerali.setBounds(new Rectangle(8, 120, 30, 30));
			lblGenerali.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/Options30.png")));
			lblGenerali.setText("");
			lblGenerali.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getTxpJdbcDriver(), null);
			jContentPane.add(getTxpJdbcUrl(), null);
			jContentPane.add(getTxpJdbcUser(), null);
			jContentPane.add(getTxpJdbcPsw(), null);
			jContentPane.add(lblGenerali, null);
			jContentPane.add(lblJdbcDriver, null);
			jContentPane.add(lblJdbcUrl, null);
			jContentPane.add(lblJdbcUser, null);
			jContentPane.add(LblJdbcPsw, null);
			jContentPane.add(lblDBOptions, null);
			jContentPane.add(getBtnCanc(), null);
			jContentPane.add(getBtnOk(), null);
			jContentPane.add(lblStartCmd, null);
			jContentPane.add(getTxfStartCmd(), null);
			jContentPane.add(getTxfServeProc(), null);
			jContentPane.add(lblServerProc, null);
			jContentPane.add(getCkbEmbedded(), null);
			jContentPane.add(getBtnTestConn(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes txpJdbcDriver	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxpJdbcDriver() {
		if (txpJdbcDriver == null) {
			txpJdbcDriver = new JTextField();
			txpJdbcDriver.setBounds(new Rectangle(100, 158, 409, 25));
			txpJdbcDriver.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.DB_DRIVER));
		}
		return txpJdbcDriver;
	}

	/**
	 * This method initializes txpJdbcUrl	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxpJdbcUrl() {
		if (txpJdbcUrl == null) {
			txpJdbcUrl = new JTextField();
			txpJdbcUrl.setBounds(new Rectangle(100, 188, 409, 25));
			txpJdbcUrl.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.DB_URL));
		}
		return txpJdbcUrl;
	}

	/**
	 * This method initializes txpJdbcUser	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxpJdbcUser() {
		if (txpJdbcUser == null) {
			txpJdbcUser = new JTextField();
			txpJdbcUser.setBounds(new Rectangle(100, 218, 191, 25));
			txpJdbcUser.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.DB_USER));
		}
		return txpJdbcUser;
	}

	/**
	 * This method initializes txpJdbcPsw	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxpJdbcPsw() {
		if (txpJdbcPsw == null) {
			txpJdbcPsw = new JPasswordField();
			txpJdbcPsw.setBounds(new Rectangle(100, 248, 191, 25));
			txpJdbcPsw.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.DB_PASSW));
		}
		return txpJdbcPsw;
	}

	/**
	 * This method initializes btnCanc	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCanc() {
		if (btnCanc == null) {
			btnCanc = new JButton();
			btnCanc.setBounds(new Rectangle(210, 328, 96, 25));
			btnCanc.setText("Annulla");
			btnCanc.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/button_cancel.png")));
			btnCanc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
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
			btnOk.setBounds(new Rectangle(310, 328, 85, 25));
			btnOk.setText("Salva");
			btnOk.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/filesave.png")));
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					save();
				}
			});
		}
		return btnOk;
	}
	
	
	private void save(){
		updateConf();
		dispose();
	}
	
	@SuppressWarnings("deprecation")
	private void test(){
		String testConnection = "";
		if(getCkbEmbedded().isSelected()){
			testConnection = CommonMetodBin.getInstance().testEmbConn();
		}else{
			//System.out.println("getTxpJdbcPsw(): "+getTxpJdbcPsw().getText());
			testConnection = CommonMetodBin.getInstance().testServerConn(
				getTxpJdbcDriver().getText(), getTxpJdbcUrl().getText(), 
				getTxpJdbcUser().getText(), getTxpJdbcPsw().getText());
		}
		
		String msg = "";
		int confirm = 0;
		if (testConnection.equals("Ok")){
			msg = "Test Connessione Avvenuto con successo. "+
				" \n Vuoi salvare la modifica?";
			confirm = JOptionPane.showConfirmDialog(getParent(),
					msg,"Info", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		}else{
			msg = "Test Connessione Fallito: "+testConnection+
				" \n Vuoi salvare la modifica lo stesso?";
			confirm = JOptionPane.showConfirmDialog(getParent(),
					msg,"Warning", 
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
		}
		
		
		if (confirm == JOptionPane.YES_OPTION){
			updateConf();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void updateConf(){
		EnvProperties.getInstance().setProperty(
				EnvProperties.START_CMD,getTxfStartCmd().getText());
		EnvProperties.getInstance().setProperty(
				EnvProperties.SERVER_PROCESS,getTxfServeProc().getText());
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_DRIVER,getTxpJdbcDriver().getText());
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_URL,getTxpJdbcUrl().getText());
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_USER,getTxpJdbcUser().getText());
		String psw = getTxpJdbcPsw().getText();
		//System.out.println("updateConf:"+psw);
		if (psw == null) psw = "";
		//System.out.println("updateConf:"+psw);
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_PASSW,psw);
				
		String emb = "N";
		if(getCkbEmbedded().isSelected()) emb = "S";
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_ISEMBEDDED,emb);
		
		EnvProperties.getInstance().saveFileProperty();
		JOptionPane.showMessageDialog(getParent(),
				"Le modifiche saranno attive dal prossimo riavvio. ",
				"Info", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method initializes txfStartCmd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfStartCmd() {
		if (txfStartCmd == null) {
			txfStartCmd = new JTextField();
			txfStartCmd.setBounds(new Rectangle(8, 32, 500, 25));
			txfStartCmd.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.START_CMD));
		}
		return txfStartCmd;
	}

	/**
	 * This method initializes txfServeProc	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxfServeProc() {
		if (txfServeProc == null) {
			txfServeProc = new JTextField();
			txfServeProc.setBounds(new Rectangle(8, 83, 500, 25));
			txfServeProc.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.SERVER_PROCESS));
		}
		return txfServeProc;
	}

	/**
	 * This method initializes ckbEmbedded	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCkbEmbedded() {
		if (ckbEmbedded == null) {
			ckbEmbedded = new JCheckBox();
			ckbEmbedded.setBounds(new Rectangle(6, 285, 178, 24));
			ckbEmbedded.setText("Embedded Server");
			ckbEmbedded.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if(ckbEmbedded.isSelected()){
						setEmbedded();
					}else{
						setNonEmbedded();
					}
				}
			});
		}
		return ckbEmbedded;
	}

	/**
	 * This method initializes btnTestConn	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnTestConn() {
		if (btnTestConn == null) {
			btnTestConn = new JButton();
			btnTestConn.setBounds(new Rectangle(91, 328, 87, 25));
			btnTestConn.setText("Test");
			btnTestConn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					test();
				}
			});
		}
		return btnTestConn;
	}

	private void setEmbedded(){
		this.getTxpJdbcDriver().setEnabled(false);
		this.getTxpJdbcUrl().setEnabled(false);
		this.getTxpJdbcPsw().setEnabled(false);
		this.getTxpJdbcUser().setEnabled(false);
		this.getCkbEmbedded().setSelected(true);
	}
	
	private void setNonEmbedded(){
		this.getTxpJdbcDriver().setEnabled(true);
		this.getTxpJdbcUrl().setEnabled(true);
		this.getTxpJdbcPsw().setEnabled(true);
		this.getTxpJdbcUser().setEnabled(true);
		this.getCkbEmbedded().setSelected(false);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
