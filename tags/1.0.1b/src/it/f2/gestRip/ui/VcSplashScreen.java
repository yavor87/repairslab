package it.f2.gestRip.ui;

import it.f2.gestRip.EnvProperties;
import it.f2.gestRip.ui.messages.Messages;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VcSplashScreen extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="520,10"
	private JProgressBar progressBar = null;
	private JLabel iblIcon = null;
	private JLabel lblGestRip = null;
	private JLabel lblVersion = null;

	public VcSplashScreen() {
		super();
		initialize();
	}


	/**
	 * This method stores all initialization commands for the window.
	 */
	private void initialize() {
		
		setSize(401, 276);
		setContentPane(getJContentPane());
	}
	
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblVersion = new JLabel();
			lblVersion.setBounds(new Rectangle(123, 143, 257, 28));
			lblVersion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblVersion.setText(Messages.getString("VcSplashScreen.version")+ //$NON-NLS-1$
					EnvProperties.getInstance().getProperty(
					EnvProperties.VERSION));
			lblGestRip = new JLabel();
			lblGestRip.setBounds(new Rectangle(123, 75, 256, 65));
			lblGestRip.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24)); //$NON-NLS-1$
			lblGestRip.setHorizontalAlignment(SwingConstants.RIGHT);
			lblGestRip.setText(EnvProperties.getInstance().getProperty(
					EnvProperties.APPNAME));
			iblIcon = new JLabel();
			iblIcon.setBounds(new Rectangle(4, 6, 98, 101));
			iblIcon.setIcon(new ImageIcon(getClass().
					getResource("img/logo64.png"))); //$NON-NLS-1$
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(new Dimension(294, 255));
			jContentPane.add(getProgressBar(), null);
			jContentPane.add(iblIcon, null);
			jContentPane.add(lblGestRip, null);
			jContentPane.add(lblVersion, null);
		}
		return jContentPane;
	}


	/**
	 * Sets the text of the progress bar and its value
	 *
	 * @param msg The message to be displayed in the progress bar
	 * @param theVal An integer value from 0 to 100
	 */
	public void setStatus(String msg, int value) {
		getProgressBar().setString(msg);
		getProgressBar().setValue(value);
	}


	/**
	 * This method initializes progressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setBounds(new Rectangle(1, 246, 399, 28));
			progressBar.setStringPainted(true);
		}
		return progressBar;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
