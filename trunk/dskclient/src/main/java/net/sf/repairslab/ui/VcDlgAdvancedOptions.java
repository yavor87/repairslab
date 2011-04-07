package net.sf.repairslab.ui;

import java.awt.Frame;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.sf.repairslab.EnvProperties;
import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.ui.messages.Messages;

import org.apache.log4j.Logger;

public class VcDlgAdvancedOptions extends JDialog {
	
	static private Logger  logger = Logger.getLogger(VcDlgAdvancedOptions.class.getName());

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
	private JCheckBox ckbEmbedded = null;
	private JButton btnTestConn = null;

	private JLabel LblJdbcClasspath = null;

	private JButton btnAddClasspath = null;

	private JTextField txpJdbcClasspath = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcDlgAdvancedOptions(Frame owner) {
		super(owner, true);
		logger.debug("VcDlgAdvancedOptions constructor..."); //$NON-NLS-1$
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
		this.setSize(535, 322);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			LblJdbcClasspath = new JLabel();
			LblJdbcClasspath.setBounds(new Rectangle(4, 170, 90, 16));
			LblJdbcClasspath.setText(Messages.getString("VcDlgAdvancedOptions.lblDbClasspath"));
			lblDBOptions = new JLabel();
			lblDBOptions.setBounds(new Rectangle(43, 11, 119, 16));
			lblDBOptions.setText(Messages.getString("VcDlgAdvancedOptions.lblDbConfig")); //$NON-NLS-1$
			LblJdbcPsw = new JLabel();
			LblJdbcPsw.setBounds(new Rectangle(4, 134, 90, 16));
			LblJdbcPsw.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCPassw")); //$NON-NLS-1$
			lblJdbcUser = new JLabel();
			lblJdbcUser.setBounds(new Rectangle(4, 104, 90, 16));
			lblJdbcUser.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCUser")); //$NON-NLS-1$
			lblJdbcUrl = new JLabel();
			lblJdbcUrl.setBounds(new Rectangle(4, 74, 90, 16));
			lblJdbcUrl.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCUrl")); //$NON-NLS-1$
			lblJdbcDriver = new JLabel();
			lblJdbcDriver.setBounds(new Rectangle(4, 44, 90, 16));
			lblJdbcDriver.setText(Messages.getString("VcDlgAdvancedOptions.lblJDBCDriver")); //$NON-NLS-1$
			lblGenerali = new JLabel();
			lblGenerali.setBounds(new Rectangle(5, 5, 30, 30));
			lblGenerali.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/Options30.png"))); //$NON-NLS-1$
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
			jContentPane.add(getCkbEmbedded(), null);
			jContentPane.add(getBtnTestConn(), null);
			jContentPane.add(LblJdbcClasspath, null);
			jContentPane.add(getBtnAddClasspath(), null);
			jContentPane.add(getTxpJdbcClasspath(), null);
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
			txpJdbcDriver.setBounds(new Rectangle(97, 43, 409, 25));
			txpJdbcDriver.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_DRIVER));
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
			txpJdbcUrl.setBounds(new Rectangle(97, 73, 409, 25));
			txpJdbcUrl.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_URL));
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
			txpJdbcUser.setBounds(new Rectangle(97, 103, 191, 25));
			txpJdbcUser.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_USER));
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
			txpJdbcPsw.setBounds(new Rectangle(97, 133, 191, 25));
			txpJdbcPsw.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_PASSW));
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
			btnCanc.setBounds(new Rectangle(206, 241, 96, 25));
			btnCanc.setText(Messages.getString("VcDlgAdvancedOptions.btnCanc")); //$NON-NLS-1$
			btnCanc.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/button_cancel.png"))); //$NON-NLS-1$
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
			btnOk.setBounds(new Rectangle(306, 241, 85, 25));
			btnOk.setText(Messages.getString("VcDlgAdvancedOptions.btnSave")); //$NON-NLS-1$
			btnOk.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/filesave.png"))); //$NON-NLS-1$
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
		} else {
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
		EnvProperties.getInstance().setProperty(EnvProperties.DB_DRIVER, getTxpJdbcDriver().getText());
		EnvProperties.getInstance().setProperty(EnvProperties.DB_URL, getTxpJdbcUrl().getText());
		EnvProperties.getInstance().setProperty(EnvProperties.DB_USER, getTxpJdbcUser().getText());
		EnvProperties.getInstance().setProperty(EnvProperties.DB_CLASSPATH, getTxpJdbcClasspath().getText());
		String psw = getTxpJdbcPsw().getText();
		//System.out.println("updateConf:"+psw);
		if (psw == null) psw = ""; //$NON-NLS-1$
		//System.out.println("updateConf:"+psw);
		EnvProperties.getInstance().setProperty(EnvProperties.DB_PASSW,psw);
				
		String emb = "N"; //$NON-NLS-1$
		if(getCkbEmbedded().isSelected()) emb = "S"; //$NON-NLS-1$
		EnvProperties.getInstance().setProperty(EnvProperties.DB_ISEMBEDDED,emb);
		
		EnvProperties.getInstance().saveFileProperty();
		JOptionPane.showMessageDialog(getParent(),
				Messages.getString("VcDlgAdvancedOptions.msgRestart"), //$NON-NLS-1$
				Messages.getString("VcDlgAdvancedOptions.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
	}

	/**
	 * This method initializes ckbEmbedded	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCkbEmbedded() {
		if (ckbEmbedded == null) {
			ckbEmbedded = new JCheckBox();
			ckbEmbedded.setBounds(new Rectangle(2, 211, 178, 24));
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
			btnTestConn.setBounds(new Rectangle(87, 241, 87, 25));
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

	/**
     * This method initializes btnAddClasspath	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getBtnAddClasspath() {
    	if (btnAddClasspath == null) {
    		btnAddClasspath = new JButton();
    		btnAddClasspath.setBounds(new Rectangle(478, 165, 28, 26));
    		btnAddClasspath.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/edit_add.png"))); //$NON-NLS-1$
    		btnAddClasspath.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent e) {
    				openClassPathSelection();
    			}
    		});
    	}
    	return btnAddClasspath;
    }
    
    private void openClassPathSelection() {
    	JFileChooser fc = new JFileChooser();
		fc.setApproveButtonText("Select");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Jar file", "jar");
		fc.setFileFilter(filter);
		File f = new File(getTxpJdbcClasspath().getText());
		fc.setCurrentDirectory(f.getParentFile());
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
            	getTxpJdbcClasspath().setText(file.getPath());
            } catch (Exception ex) {
            	JOptionPane.showMessageDialog(this, Messages.getString("VcMainFrame.importErr") + ex.getMessage(), Messages.getString("VcMainFrame.importAction"), JOptionPane.ERROR_MESSAGE);
            	ex.printStackTrace();
			}
        }
    }

	/**
     * This method initializes txpJdbcClasspath	
     * 	
     * @return javax.swing.JTextField	
     */
    private JTextField getTxpJdbcClasspath() {
    	if (txpJdbcClasspath == null) {
    		txpJdbcClasspath = new JTextField();
    		txpJdbcClasspath.setBounds(new Rectangle(97, 167, 380, 20));
    		txpJdbcClasspath.setEditable(false);
    		txpJdbcClasspath.setText(EnvProperties.getInstance().getProperty(EnvProperties.DB_CLASSPATH));
    	}
    	return txpJdbcClasspath;
    }

}  //  @jve:decl-index=0:visual-constraint="10,6"
