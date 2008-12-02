package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.ui.messages.Messages;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.apache.log4j.Logger;

import java.awt.Rectangle;

/**
 * Questa classe rappresenta il JDialog About
 * creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */
public class VcDlgAbout extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblName = null;
	private JLabel lblImg2 = null;
	private JLabel lblAutore = null;
	private JLabel lblVersion = null;
	private JButton btnOk = null;
	/**
	 * Costruttore della classe
	 * 
	 * @param parent
	 * @throws HeadlessException
	 */
	public VcDlgAbout(Frame parent) throws HeadlessException {
		super(parent, true);
		Logger.getRootLogger().debug("VcDlgAbout constructor..."); //$NON-NLS-1$
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
			lblVersion.setBounds(new java.awt.Rectangle(192, 146, 174, 22));
			lblVersion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			lblVersion.setText(Messages.getString("VcDlgAbout.lblVersion") //$NON-NLS-1$
					+ EnvProperties.getInstance().getProperty(
							EnvProperties.VERSION));
			lblAutore = new JLabel();
			lblAutore.setBounds(new java.awt.Rectangle(192, 120, 174, 27));
			lblAutore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
			lblAutore.setText(Messages.getString("VcDlgAbout.lblAutor")); //$NON-NLS-1$
			lblImg2 = new JLabel();
			lblImg2.setBounds(new java.awt.Rectangle(28, 105, 141, 129));
			lblImg2.setIcon(new ImageIcon(getClass().getResource(
					"/it/f2/gestRip/ui/img/logo64.png"))); //$NON-NLS-1$
			lblImg2.setText(""); //$NON-NLS-1$
			lblName = new JLabel();
			lblName.setBounds(new Rectangle(5, 14, 401, 80));
			lblName.setFont(new java.awt.Font("Courier New", //$NON-NLS-1$
					java.awt.Font.BOLD, 26));
			lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			lblName.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.APPNAME));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblName, null);
			jContentPane.add(lblImg2, null);
			jContentPane.add(lblAutore, null);
			jContentPane.add(lblVersion, null);
			jContentPane.add(getBtnOk(), null);
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

} //  @jve:decl-index=0:visual-constraint="10,10"
