package it.f2.gestRip.ui;

import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.ui.messages.Messages;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

/**
 * Questa classe rappresenta il JDialog About
 * creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */
public class VcDlgDataImport extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lblName = null;
	private JLabel lblAutore = null;
	private JLabel lblVersion = null;
	private JButton btnOk = null;
	/**
	 * Costruttore della classe
	 * 
	 * @param parent
	 * @throws HeadlessException
	 */
	public VcDlgDataImport(Frame parent) throws HeadlessException {
		super(parent, true);
		Logger.getRootLogger().debug("VcDlgDataImport constructor..."); //$NON-NLS-1$
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
			lblName.setIcon(new ImageIcon(getClass().getResource("/it/f2/gestRip/ui/img/RepairsLab_tr.png")));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblName, null);
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
