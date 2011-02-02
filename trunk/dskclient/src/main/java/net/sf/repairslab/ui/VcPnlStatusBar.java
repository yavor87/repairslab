package net.sf.repairslab.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.ui.WindowUtil;

public class VcPnlStatusBar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton btnUpgrade = null;
	private Frame parent;
	private JLabel lblStatus = null;
	
	public VcPnlStatusBar(Frame parent) {
		this.parent = parent;
		initialize();
	}
	
	private void initialize() {
		this.setSize(300, 16);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(getLableStatus());
	    add(getBtnUpgrade());
	}
	
	private JLabel getLableStatus() {
		if (lblStatus == null) {
			lblStatus = new JLabel();
			lblStatus.setText(CommonMetodBin.getInstance().getStatusUpdate().name());
			lblStatus.setHorizontalTextPosition(JLabel.RIGHT);
		}
		return lblStatus;
	}
	
	/**
	 * TODO Comment for method "getBtnUpgrade" must be completed
	 * @author Fabrizio Ferraiuolo 03/nov/2010 15.15.16
	 * @return 
	 */
	private JButton getBtnUpgrade() {
		if (btnUpgrade == null) {
			btnUpgrade = new JButton();
			btnUpgrade.setPreferredSize(new Dimension(22,22));
			setStatusLastUpdate();
			btnUpgrade.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					VcDlgCheckUpdate dialog = new VcDlgCheckUpdate(parent);
					WindowUtil.centerWindow(dialog);
					dialog.setVisible(true);
				}
			});
		}
		return btnUpgrade;
	}
	
	public void setStatusNewUpdate() {
		getBtnUpgrade().setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/software-update-available.png")));
		getBtnUpgrade().setToolTipText(Messages.getString("VcPnlStatusBar.tltNewVersion"));
		getLableStatus().setText(Messages.getString("VcPnlStatusBar.tltNewVersion"));
	}
	
	private void setStatusLastUpdate() {
		getBtnUpgrade().setIcon(new ImageIcon(getClass().getResource("/net/sf/repairslab/ui/img/system-software-update.png")));
		getBtnUpgrade().setToolTipText(Messages.getString("VcPnlStatusBar.tltCheckUpdate"));
		getLableStatus().setText("");
	}
}
