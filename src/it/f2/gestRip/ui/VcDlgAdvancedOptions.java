package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.ui.messages.Messages;

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
		Logger.getRootLogger().debug("VcDlgAdvancedOptions constructor..."); //$NON-NLS-1$
		initialize();
		if(EnvProperties.getInstance().getProperty(
				EnvProperties.DB_ISEMBEDDED).equalsIgnoreCase("S")){ //$NON-NLS-1$
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
			lblServerProc.setText("Processo Server"); //$NON-NLS-1$
			lblStartCmd = new JLabel();
			lblStartCmd.setBounds(new Rectangle(8, 13, 126, 16));
			lblStartCmd.setText("Comando di avvio"); //$NON-NLS-1$
			lblDBOptions = new JLabel();
			lblDBOptions.setBounds(new Rectangle(46, 126, 119, 16));
			lblDBOptions.setText(Messages.getString("VcDlgAdvancedOptions.lblDbConfig")); //$NON-NLS-1$
			LblJdbcPsw = new JLabel();
			LblJdbcPsw.setBounds(new Rectangle(7, 249, 90, 16));
			LblJdbcPsw.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCPassw")); //$NON-NLS-1$
			lblJdbcUser = new JLabel();
			lblJdbcUser.setBounds(new Rectangle(7, 219, 90, 16));
			lblJdbcUser.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCUser")); //$NON-NLS-1$
			lblJdbcUrl = new JLabel();
			lblJdbcUrl.setBounds(new Rectangle(7, 189, 90, 16));
			lblJdbcUrl.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCUrl")); //$NON-NLS-1$
			lblJdbcDriver = new JLabel();
			lblJdbcDriver.setBounds(new Rectangle(7, 159, 90, 16));
			lblJdbcDriver.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCDriver")); //$NON-NLS-1$
			lblGenerali = new JLabel();
			lblGenerali.setBounds(new Rectangle(8, 120, 30, 30));
			lblGenerali.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/Options30.png"))); //$NON-NLS-1$
			lblGenerali.setText(""); //$NON-NLS-1$
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
			btnCanc.setText(Messages.getString("VcDlgAdvancedOptions.btnCanc")); //$NON-NLS-1$
			btnCanc.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/button_cancel.png"))); //$NON-NLS-1$
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
			btnOk.setText(Messages.getString("VcDlgAdvancedOptions.btnSave")); //$NON-NLS-1$
			btnOk.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/filesave.png"))); //$NON-NLS-1$
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
		String testConnection = ""; //$NON-NLS-1$
		if(getCkbEmbedded().isSelected()){
			testConnection = CommonMetodBin.getInstance().testEmbConn();
		}else{
			//System.out.println("getTxpJdbcPsw(): "+getTxpJdbcPsw().getText());
			testConnection = CommonMetodBin.getInstance().testServerConn(
				getTxpJdbcDriver().getText(), getTxpJdbcUrl().getText(), 
				getTxpJdbcUser().getText(), getTxpJdbcPsw().getText());
		}
		
		String msg = ""; //$NON-NLS-1$
		int confirm = 0;
		if (testConnection.equals(Messages.getString("VcDlgAdvancedOptions.btnOk"))){ //$NON-NLS-1$
			msg = Messages.getString("VcDlgAdvancedOptions.msgTestOk1")+ //$NON-NLS-1$
				Messages.getString("VcDlgAdvancedOptions.msgTestOk2"); //$NON-NLS-1$
			confirm = JOptionPane.showConfirmDialog(getParent(),
					msg,Messages.getString("VcDlgAdvancedOptions.msgTitleInfo"),  //$NON-NLS-1$
					JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE);
		}else{
			msg = Messages.getString("VcDlgAdvancedOptions.msgTestFailed1")+testConnection+ //$NON-NLS-1$
				Messages.getString("VcDlgAdvancedOptions.msgTestFailed2"); //$NON-NLS-1$
			confirm = JOptionPane.showConfirmDialog(getParent(),
					msg,Messages.getString("VcDlgAdvancedOptions.msgTitleWarning"),  //$NON-NLS-1$
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
		if (psw == null) psw = ""; //$NON-NLS-1$
		//System.out.println("updateConf:"+psw);
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_PASSW,psw);
				
		String emb = "N"; //$NON-NLS-1$
		if(getCkbEmbedded().isSelected()) emb = "S"; //$NON-NLS-1$
		EnvProperties.getInstance().setProperty(
				EnvProperties.DB_ISEMBEDDED,emb);
		
		EnvProperties.getInstance().saveFileProperty();
		JOptionPane.showMessageDialog(getParent(),
				Messages.getString("VcDlgAdvancedOptions.msgRestart"), //$NON-NLS-1$
				Messages.getString("VcDlgAdvancedOptions.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
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
			ckbEmbedded.setText(Messages.getString("VcDlgAdvancedOptions.lblEmbeddedServer")); //$NON-NLS-1$
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
			btnTestConn.setText(Messages.getString("VcDlgAdvancedOptions.btnlTest")); //$NON-NLS-1$
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
