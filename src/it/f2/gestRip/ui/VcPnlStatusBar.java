package it.f2.gestRip.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VcPnlStatusBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public VcPnlStatusBar() {
		initialize();
	}
	
	private void initialize() {
		this.setSize(300, 16);
		JLabel lblStatus = new JLabel();
		lblStatus.setText("Prova");
		this.add(lblStatus, null);
	}
}
