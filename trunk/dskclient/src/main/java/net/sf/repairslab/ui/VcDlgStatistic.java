package net.sf.repairslab.ui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sf.repairslab.control.CommonMetodBin;
import net.sf.repairslab.ui.messages.Messages;
import net.sf.repairslab.util.VcJDBCTablePanel2;

import org.apache.log4j.Logger;

/**
 * Questa classe rappresenta il JInternalFrame Statistic
 * creata con Eclipse Visual Editor.
 */

public class VcDlgStatistic extends JInternalFrame {
	
	static private Logger  logger = Logger.getLogger(CommonMetodBin.class.getName());

	private static final long	serialVersionUID	= 1L;
	private JPanel	          jContentPane	     = null;
	private VcMainFrame	      parent	         = null;
	private VcJDBCTablePanel2	pnlStatic	     = null;
	private VcJDBCTablePanel2	pnlStatic2	     = null;
	private VcJDBCTablePanel2	pnlStatic3	     = null;
	private VcJDBCTablePanel2	pnlStatic4	     = null;
	private Connection	      con	             = null;
	

	/**
	 * Costruttore della classe VcDlgStatistic.java
	 * @author Fabrizio Ferraiuolo
	 * 14/dic/2010
	 * 15.52.08
	 * @param parent
	 */
	public VcDlgStatistic(VcMainFrame parent) {
		super();
		logger.debug("VcDlgStatistic constructor..."); //$NON-NLS-1$
		this.parent = parent;
		this.con = CommonMetodBin.getConn();
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(901, 571);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcDlgStatistic.titleStatistic")); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
				try {
					logger.debug("Closing..."); //$NON-NLS-1$
					close();
				} catch (Exception e1) {
					logger.error("Exception in Closing \n" + e1 + "\n", e1); //$NON-NLS-1$ //$NON-NLS-2$
					//e1.printStackTrace();
				}
			}
		});
	}
	
	private void close() {
		CommonMetodBin.closeConn(con);
		if (checkCompleteUpdate())
			parent.closeTab(this);
	}
	
	public boolean checkCompleteUpdate() {
		boolean result = false;
		if (getPnlStatistic().getModality() == VcJDBCTablePanel2.mode.update) {
			int confirm = JOptionPane.showConfirmDialog(getParent(), Messages.getString("VcDlgStatistic.msgSave"), //$NON-NLS-1$
			        Messages.getString("VcDlgStatistic.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
			if (confirm == JOptionPane.OK_OPTION) {
				getPnlStatistic().commit();
				result = true;
			} else if (confirm == JOptionPane.NO_OPTION) {
				getPnlStatistic().rollback();
				result = true;
			} else if (confirm == JOptionPane.CANCEL_OPTION) {
				result = false;
			}
		} else {
			result = true;
		}
		return result;
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
			jContentPane.add(getPnlStatistic(), BorderLayout.CENTER);
			jContentPane.add(getPnlStatistic2(), BorderLayout.SOUTH);
//			jContentPane.add(getPnlStatistic3(), BorderLayout.EAST);
//			jContentPane.add(getPnlStatistic4(), BorderLayout.WEST);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel2 getPnlStatistic() {
		if (pnlStatic == null) {
			
//			pnlStatic = new VcJDBCTablePanel2(con, QryUtil.QRY_STATISTIC) {
//				
//				/**
//				 * 
//				 */
//				private static final long	serialVersionUID	= 1L;
//				
//				protected void onDelete() {
//					boolean referenziato = false;
//					try {
//						logger.debug("Deleting..."); //$NON-NLS-1$
//						Statement smtp = con.createStatement();
//						String query = "select count(*) from schede " + //$NON-NLS-1$
//						        "where idCliente = " + getValueAt(currentRow(), 0); //$NON-NLS-1$
//						ResultSet rs = smtp.executeQuery(query);
//						while (rs.next()) {
//							int fk = rs.getInt(1);
//							if (fk > 0) {
//								referenziato = true;
//							}
//						}
//						rs.close();
//						smtp.close();
//						if (referenziato) {
//							JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgStatistic.msgReferenced"), //$NON-NLS-1$
//							        Messages.getString("VcDlgStatistic.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
//						} else {
//							deleteRow(currentRow());
//						}
//					} catch (SQLException e) {
//						logger.error("Exception in Deleting \n" + e + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$
//						e.printStackTrace();
//					}
//				}
//				
//			};
//			pnlStatic.setColumnLabel(0, Messages.getString("VcDlgStatistic.qryId"));
//			pnlStatic.setColumnLabel(1, Messages.getString("VcIfrListaSchede.qryInsDate"));
//			pnlStatic.setColumnLabel(2, Messages.getString("VcDlgStatistic.qrypagatoDalCliente"));
//			pnlStatic.setColumnLabel(3, Messages.getString("VcDlgStatistic.qrycostoInterno"));
//			pnlStatic.createControlPanel();
			
		}
		return pnlStatic;
	}
	
	private VcJDBCTablePanel2 getPnlStatistic2() {
		if (pnlStatic2 == null) {
			
//			pnlStatic2 = new VcJDBCTablePanel2(con, QryUtil.QRY_STATISTIC2) {
//				
//				/**
//				 * 
//				 */
//				private static final long	serialVersionUID	= 1L;
//				
//				protected void onDelete() {
//					boolean referenziato = false;
//					try {
//						logger.debug("Deleting..."); //$NON-NLS-1$
//						Statement smtp = con.createStatement();
//						String query = "select count(*) from schede " + //$NON-NLS-1$
//						        "where idCliente = " + getValueAt(currentRow(), 0); //$NON-NLS-1$
//						ResultSet rs = smtp.executeQuery(query);
//						while (rs.next()) {
//							int fk = rs.getInt(1);
//							if (fk > 0) {
//								referenziato = true;
//							}
//						}
//						rs.close();
//						smtp.close();
//						if (referenziato) {
//							JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgStatistic.msgReferenced"), //$NON-NLS-1$
//							        Messages.getString("VcDlgStatistic.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
//						} else {
//							deleteRow(currentRow());
//						}
//					} catch (SQLException e) {
//						logger.error("Exception in Deleting \n" + e + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$
//						e.printStackTrace();
//					}
//				}
				
//			};
			
//			pnlStatic2.setColumnLabel(0, Messages.getString("VcDlgStatistic.qryincasso"));
//			pnlStatic2.setColumnLabel(1, Messages.getString("VcDlgStatistic.qryspese"));
			pnlStatic2.createControlPanel();
			
		}
		return pnlStatic2;
	}
	
//	private VcJDBCTablePanel2 getPnlStatistic3() {
//		if (pnlStatic3 == null) {
//			
//			System.out.println(QryUtil.QRY_STATISTIC3);
//			pnlStatic3 = new VcJDBCTablePanel2(con, QryUtil.QRY_STATISTIC3) {
//				
//				/**
//				 * 
//				 */
//				private static final long	serialVersionUID	= 1L;
//				
//				protected void onDelete() {
//					boolean referenziato = false;
//					try {
//						logger.debug("Deleting..."); //$NON-NLS-1$
//						Statement smtp = con.createStatement();
//						String query = "select count(*) from schede " + //$NON-NLS-1$
//						        "where idCliente = " + getValueAt(currentRow(), 0); //$NON-NLS-1$
//						ResultSet rs = smtp.executeQuery(query);
//						while (rs.next()) {
//							int fk = rs.getInt(1);
//							if (fk > 0) {
//								referenziato = true;
//							}
//						}
//						rs.close();
//						smtp.close();
//						if (referenziato) {
//							JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgStatistic.msgReferenced"), //$NON-NLS-1$
//							        Messages.getString("VcDlgStatistic.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
//						} else {
//							deleteRow(currentRow());
//						}
//					} catch (SQLException e) {
//						logger.error("Exception in Deleting \n" + e + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$
//						e.printStackTrace();
//					}
//				}
//				
//			};
//			pnlStatic3.setColumnLabel(0, Messages.getString("VcDlgStatistic.qrycostoInternoTM"));
//			pnlStatic3.setColumnLabel(1, Messages.getString("VcDlgStatistic.qrypagatoDalClienteTM"));
//			pnlStatic3.createControlPanel();
//			
//		}
//		return pnlStatic3;
//	}
//	
//	private VcJDBCTablePanel2 getPnlStatistic4() {
//		if (pnlStatic4 == null) {
//			
//			pnlStatic4 = new VcJDBCTablePanel2(con, QryUtil.QRY_STATISTIC4) {
//				
//				/**
//				 * 
//				 */
//				private static final long	serialVersionUID	= 1L;
//				
//				protected void onDelete() {
//					boolean referenziato = false;
//					try {
//						logger.debug("Deleting..."); //$NON-NLS-1$
//						Statement smtp = con.createStatement();
//						String query = "select count(*) from schede " + //$NON-NLS-1$
//						        "where idCliente = " + getValueAt(currentRow(), 0); //$NON-NLS-1$
//						ResultSet rs = smtp.executeQuery(query);
//						while (rs.next()) {
//							int fk = rs.getInt(1);
//							if (fk > 0) {
//								referenziato = true;
//							}
//						}
//						rs.close();
//						smtp.close();
//						if (referenziato) {
//							JOptionPane.showMessageDialog(getParent(), Messages.getString("VcDlgStatistic.msgReferenced"), //$NON-NLS-1$
//							        Messages.getString("VcDlgStatistic.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
//						} else {
//							deleteRow(currentRow());
//						}
//					} catch (SQLException e) {
//						logger.error("Exception in Deleting \n" + e + "\n", e); //$NON-NLS-1$ //$NON-NLS-2$
//						e.printStackTrace();
//					}
//				}
//				
//			};
//			pnlStatic4.setColumnLabel(0, Messages.getString("VcDlgStatistic.qrycostoInternoLM"));
//			pnlStatic4.setColumnLabel(1, Messages.getString("VcDlgStatistic.qrypagatoDalClienteLM"));
//			pnlStatic4.createControlPanel();
//			
//		}
//		return pnlStatic4;
//	}
	
} //  @jve:decl-index=0:visual-constraint="10,10"
