package it.f2.gestRip.ui.anagraf;

import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.control.QryUtil;
import it.f2.gestRip.ui.VcMainFrame;
import it.f2.gestRip.ui.messages.Messages;
import it.f2.gestRip.util.VcJDBCTablePanel;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;

public class VcIfrAnaTipoRip extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaTipoRip = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaTipoRip(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcIfrAnaTipoRip constructor..."); //$NON-NLS-1$
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
		this.setSize(517, 388);
		this.setClosable(true);
		this.setTitle(Messages.getString("VcIfrAnaTipoRip.titleRepairType")); //$NON-NLS-1$
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
		//CommonMetodBin.getInstance().closeConn();
		parent.closeTab(this);
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
			jContentPane.add(getPnlTableAnaTipoOggetto(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaTipoOggetto() {
		if (pnlTableAnaTipoRip == null) {
			
			pnlTableAnaTipoRip = new VcJDBCTablePanel(
					con,QryUtil.QRY_ANA_TIPO_RIP,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					try {
						Logger.getRootLogger().debug("Deleting..."); //$NON-NLS-1$
						Statement smtp = con.createStatement();
						String query = "select count(*) from schede " + //$NON-NLS-1$
								"where idTipoRip = "+getValueAt(currentRow(), 0); //$NON-NLS-1$
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								System.out.println(fk);
								JOptionPane.showMessageDialog(getParent(),
										Messages.getString("VcIfrAnaTipoRip.msgReferenced"), //$NON-NLS-1$
										Messages.getString("VcIfrAnaTipoRip.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
							}else{
								deleteRow(currentRow());
							}
						}
						rs.close();
						smtp.close();
					} catch (SQLException e) {
						Logger.getRootLogger().error("Exception in Deleting \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						e.printStackTrace();
					}					
				}
			};
			pnlTableAnaTipoRip.setColumnLabel(0, Messages.getString("VcIfrAnaTipoRip.qryId"));
			pnlTableAnaTipoRip.setColumnLabel(1, Messages.getString("VcIfrAnaTipoRip.qryName"));
			pnlTableAnaTipoRip.setColumnLabel(2, Messages.getString("VcIfrAnaTipoRip.qryDesc"));
			pnlTableAnaTipoRip.setColumnLabel(3, Messages.getString("VcIfrAnaTipoRip.qryFlag"));
			pnlTableAnaTipoRip.createControlPanel();
			pnlTableAnaTipoRip.setCheckBoxColumn(3,"S","N"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return pnlTableAnaTipoRip;
	}

}
