package it.f2.gestRip.ui;


import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.QryUtil;

import it.f2.gestRip.ui.messages.Messages;

import it.f2.gestRip.util.VcJDBCTablePanel;


import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;


/**
 * Questa classe rappresenta il JDialog Statistic
 * creata con Eclipse Visual Editor.
 * 
 * @author ferraf01
 *
 */

public class VcDlgStatistic extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlStatic = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcDlgStatistic(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcDlgStatistic constructor..."); //$NON-NLS-1$
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
		this.setSize(300, 200);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcDlgStatistic.titleStatistic")); //$NON-NLS-1$
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						Logger.getRootLogger().debug("Closing..."); //$NON-NLS-1$
						close();
					}catch(Exception e1){
						Logger.getRootLogger().error("Exception in Closing \n"+e1+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						//e1.printStackTrace();
					}
				}
			});
	}
	
	private void close(){
		CommonMetodBin.closeConn(con);
		if(checkCompleteUpdate())
			parent.closeTab(this);
	}
	
	public boolean checkCompleteUpdate(){
		boolean result = false;
		if(getPnlStatistic().getModality() == VcJDBCTablePanel.mode.update){
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					Messages.getString("VcDlgStatistic.msgSave"), //$NON-NLS-1$
					Messages.getString("VcDlgStatistic.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
			if (confirm == JOptionPane.OK_OPTION){
				getPnlStatistic().commit();
				result = true;
			} else if (confirm == JOptionPane.NO_OPTION){
				getPnlStatistic().rollback();
				result = true;
			} else if (confirm == JOptionPane.CANCEL_OPTION){
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
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlStatistic() {
		if (pnlStatic == null) {
			
			pnlStatic = new VcJDBCTablePanel(con,QryUtil.QRY_STATISTIC,true){

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
						
				protected void onDelete(){
					boolean referenziato = false;
					try {
						Logger.getRootLogger().debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from schede " + //$NON-NLS-1$
								"where idCliente = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								referenziato = true;
							}
						}
						rs.close();
						smtp.close();
						if(referenziato){
							JOptionPane.showMessageDialog(getParent(),
									Messages.getString("VcDlgStatistic.msgReferenced"), //$NON-NLS-1$
									Messages.getString("VcDlgStatistic.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
						}else{
							deleteRow(currentRow());
						}
					} catch (SQLException e) {
						Logger.getRootLogger().error("Exception in Deleting \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						e.printStackTrace();
					}
				}
				
			};
			pnlStatic.setColumnLabel(0, Messages.getString("VcDlgStatistic.qryId"));
			pnlStatic.setColumnLabel(1, Messages.getString("VcIfrListaSchede.qryInsDate"));
			pnlStatic.setColumnLabel(2, Messages.getString("VcDlgStatistic.qrypagatoDalCliente"));
			pnlStatic.setColumnLabel(3, Messages.getString("VcDlgStatistic.qrycostoInterno"));
			//pnlStatic.setColumnLabel(4, Messages.getString("VcDlgStatistic.qryincasso"));
			//pnlStatic.setColumnLabel(5, Messages.getString("VcDlgStatistic.qryspese"));
			pnlStatic.createControlPanel();
			
		}
		return pnlStatic;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"
