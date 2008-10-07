package it.f2.gestRip.ui.anagraf;

import it.f2.gestRip.control.CommonMetodBin;
import it.f2.gestRip.ui.VcMainFrame;
import it.f2.gestRip.util.VcJDBCTablePanel;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;

public class VcIfrAnaModelli extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaModelli = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaModelli(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcIfrAnaModelli constructor...");
		this.parent = parent;
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
		this.setTitle("Anagrafica Modelli");
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						Logger.getRootLogger().debug("Closing...");
						close();
					}catch(Exception e1){
						Logger.getRootLogger().error("Exception in Set modality \n"+e1+"\n");
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
			jContentPane.add(getPnlTableAnaModelli(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private VcJDBCTablePanel getPnlTableAnaModelli() {
		if (pnlTableAnaModelli == null) {
			//String[] updatableCol = {"nomeStato","descrizione","Ultima modifica"};
			
			String query = "SELECT id,nome,descModello,idMarchi,idTipoApp,flagAttivo FROM gestrip.modelli"	;
			
			pnlTableAnaModelli = new VcJDBCTablePanel(
					CommonMetodBin.getInstance().openConn(),query,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					Logger.getRootLogger().debug("Deleting...");
					try {
						Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
						String query = "select count(*) from gestrip.schede " +
								"where idModello = "+getValueAt(currentRow(), 0);
						ResultSet rs = smtp.executeQuery(query);
						while(rs.next()){
							int fk = rs.getInt(1);
							if(fk>0){
								System.out.println(fk);
								JOptionPane.showMessageDialog(getParent(),
										"Stato referenziato. Non è possibile la cancellazione.",
										"Errore", JOptionPane.ERROR_MESSAGE);
							}else{
								deleteRow(currentRow());
							}
						}
						rs.close();
						smtp.close();
					} catch (SQLException e) {
						Logger.getRootLogger().error("Exception in Deleting \n"+e+"\n");
						e.printStackTrace();
					}					
				}
			};
			pnlTableAnaModelli.createControlPanel();
			pnlTableAnaModelli.setCheckBoxColumn(5,"S","N");
			
			String qryLovModelli = "select id,nome,descrizione from gestrip.marchi " +
					"where flagAttivo = 'S'";
			String qryRenderModelli = "select nome from gestrip.marchi " +
					"where id = ";
			pnlTableAnaModelli.setLovColumn(3,qryLovModelli,qryRenderModelli,"id","nome",50);
			
			String qryLovTipoApp = "select id,nome,descrizione from gestrip.tipoapparecchiature " +
					"where flagAttivo = 'S'";
			String qryRenderTipoApp = "select nome from gestrip.tipoapparecchiature " +
					"where id = ";
			pnlTableAnaModelli.setLovColumn(4,qryLovTipoApp,qryRenderTipoApp,"id","nome",50);
		}
		return pnlTableAnaModelli;
	}

}
