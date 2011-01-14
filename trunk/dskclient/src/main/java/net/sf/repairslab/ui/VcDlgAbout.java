package net.sf.repairslab.ui;

import java.awt.Component;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.repairslab.EnvConstants;
import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.LinksUtils;

import org.apache.log4j.Logger;

/**
 * Questa classe rappresenta il JDialog About
 * creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */
public class VcDlgAbout extends JDialog {
	
	static private Logger  logger = Logger.getLogger(VcDlgAbout.class.getName());

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblName = null;
	private JLabel lblAutore = null;
	private JLabel lblVersion = null;
	private JButton btnOk = null;
	private JButton btnTeam = null;
	/**
	 * Costruttore della classe
	 * 
	 * @param parent
	 * @throws HeadlessException
	 */
	public VcDlgAbout(Frame parent) throws HeadlessException {
		super(parent, true);
		logger.debug("VcDlgAbout constructor..."); //$NON-NLS-1$
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(417, 296);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblVersion = new JLabel();
			lblVersion.setBounds(new Rectangle(8, 174, 174, 22));
			lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			lblVersion.setText(Messages.getString("VcDlgAbout.lblVersion") + CommonMetodBin.getInstance().getCurrentRelease().toString());
			lblAutore = new JLabel();
			lblAutore.setBounds(new Rectangle(9, 145, 254, 27));
			lblAutore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			lblAutore.setText(Messages.getString("VcDlgAbout.lblAutor")); //$NON-NLS-1$
			lblName = new JLabel();
			lblName.setBounds(new Rectangle(5, 6, 401, 112));
			lblName.setFont(new java.awt.Font("Courier New",java.awt.Font.BOLD, 26));
			lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//			lblName.setText(EnvProperties.getInstance().getProperty(
//					EnvProperties.APPNAME));
			lblName.setText("");
			lblName.setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/RepairsLab_tr.png")));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblName, null);
			jContentPane.add(lblAutore, null);
			jContentPane.add(lblVersion, null);
			jContentPane.add(getBtnOk(), null);
			jContentPane.add(getBtnTeam(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnOk
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton();
			btnOk.setBounds(new java.awt.Rectangle(277, 213, 103, 32));
			btnOk.setText(Messages.getString("VcDlgAbout.btnOk")); //$NON-NLS-1$
			btnOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					close();
				}
			});
		}
		return btnOk;
	}

	/**
	 * Effettua la chiusura della dialog.
	 */
	private void close() {
		this.dispose();
	}

	/**
     * This method initializes btnTeam	
     * 	
     * @return javax.swing.JButton	
     */
    private JButton getBtnTeam() {
    	if (btnTeam == null) {
    		btnTeam = new JButton();
    		btnTeam.setBounds(new Rectangle(172, 213, 99, 32));
    		btnTeam.setText("Team");
    		final Component parent = super.getParent();
    		btnTeam.addActionListener(new java.awt.event.ActionListener() {
    			public void actionPerformed(java.awt.event.ActionEvent e) {
    				LinksUtils.openUrl(parent, EnvConstants.LINK_TEAM);
    			}
    		});
    	}
    	return btnTeam;
    }

} //  @jve:decl-index=0:visual-constraint="10,10"
