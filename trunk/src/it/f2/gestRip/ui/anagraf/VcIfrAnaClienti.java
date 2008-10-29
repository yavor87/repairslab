package it.f2.gestRip.ui.anagraf;

import it.f2.gestRip.control.CommonMetodBin;
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

public class VcIfrAnaClienti extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaMarche = null;
	private Connection con = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaClienti(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcIfrAnaClienti constructor..."); //$NON-NLS-1$
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
		this.setTitle(Messages.getString("VcIfrAnaClienti.titleCustomers")); //$NON-NLS-1$
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
		if(getPnlTableAnaClienti().getModality() == VcJDBCTablePanel.mode.update){
			int confirm = JOptionPane.showConfirmDialog(getParent(),
					Messages.getString("VcIfrAnaClienti.msgSave"), //$NON-NLS-1$
					Messages.getString("VcIfrAnaClienti.msgTitleInfo"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
			if (confirm == JOptionPane.OK_OPTION){
				getPnlTableAnaClienti().commit();
				result = true;
			} else if (confirm == JOptionPane.NO_OPTION){
				getPnlTableAnaClienti().rollback();
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
			jContentPane.add(getPnlTableAnaClienti(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaClienti() {
		if (pnlTableAnaMarche == null) {
			
			String query = "SELECT * FROM clienti"	; //$NON-NLS-1$
			
			pnlTableAnaMarche = new VcJDBCTablePanel(con,query,true){

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
									Messages.getString("VcIfrAnaClienti.msgReferenced"), //$NON-NLS-1$
									Messages.getString("VcIfrAnaClienti.msgTitleError"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
						}else{
							deleteRow(currentRow());
						}
					} catch (SQLException e) {
						Logger.getRootLogger().error("Exception in Deleting \n"+e+"\n"); //$NON-NLS-1$ //$NON-NLS-2$
						//e.printStackTrace();
					}
				}
				
			};
			pnlTableAnaMarche.createControlPanel();
		}
		return pnlTableAnaMarche;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
