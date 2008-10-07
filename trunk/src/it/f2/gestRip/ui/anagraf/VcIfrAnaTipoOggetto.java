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

public class VcIfrAnaTipoOggetto extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private VcMainFrame parent = null;
	private VcJDBCTablePanel pnlTableAnaTipoOggetto = null;

	/**
	 * This is the xxx default constructor
	 */
	public VcIfrAnaTipoOggetto(VcMainFrame parent) {
		super();
		Logger.getRootLogger().debug("VcIfrAnaTipoOggetto constructor...");
		this.parent = parent;
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
		this.setTitle("Anagrafica Tipologia Apparecchiatura");
		this.setContentPane(getJContentPane());
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
			public void internalFrameClosed(
				javax.swing.event.InternalFrameEvent e) {
					try{
						Logger.getRootLogger().debug("Closing...");
						close();
					}catch(Exception e1){
						Logger.getRootLogger().error("Exception in Closing \n"+e1+"\n");
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
		if (pnlTableAnaTipoOggetto == null) {
			//String[] updatableCol = {"nomeStato","descrizione","Ultima modifica"};
			
			String query = "SELECT id,nome,descrizione,flagAttivo " +
					"FROM gestrip.tipoapparecchiature"	;
			
			pnlTableAnaTipoOggetto = new VcJDBCTablePanel(
					CommonMetodBin.getInstance().openConn(),query,true){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				protected void onDelete(){
					try {
						Logger.getRootLogger().debug("Deleting...");
						Statement smtp = CommonMetodBin.getInstance().openConn().createStatement();
						String query = "select count(*) from gestrip.schede " +
								"where idTipoApparecchiatura = "+getValueAt(currentRow(), 0);
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
						//e.printStackTrace();
					}					
				}
			};
			pnlTableAnaTipoOggetto.createControlPanel();
			pnlTableAnaTipoOggetto.setCheckBoxColumn(3,"S","N");
		}
		return pnlTableAnaTipoOggetto;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
