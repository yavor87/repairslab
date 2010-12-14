package it.f2.gestRip.util;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;


/**
 * JDBCTableModel provides a simple way to display a table from a JDBC
 * connection. An additional feature is that this model is also editable.
 *
 * @author Holger Antelmann
 *
 * @see javax.swing.JTable
 * @see org.jscience.util.JDBC
 * @since 10/24/2002
 */
public class JDBCTableModel extends AbstractTableModel {

    static final long serialVersionUID = 2487661888487685951L;

    private Connection con = null;
    private String query = null;
    private ResultSet set = null;
    private int size = 0;
    private boolean updateable = false;
    private Component parent = null;
    private NamedParameterStatement statement = null;
    private String[] updatableColumn = null;
    private ArrayList<Object[]> params = null;

    /**
     * Creates a new JDBCTableModel object.
     *
     * @param con DOCUMENT ME!
     * @param tableName DOCUMENT ME!
     * @param updateable DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public JDBCTableModel(Connection con, String query, boolean updateable, String[] updatableColumn, ArrayList<Object[]> params) throws SQLException {
        this.con = con;
        this.query = query;
        this.updateable = updateable;
        this.updatableColumn = updatableColumn;
        this.params = params;
        refresh();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ResultSet getResultSet() {
        return set;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Statement getStatement() {
        return statement.getStatement();
    }

    /**
     * DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public void refresh() throws SQLException {

    	if (statement != null) {
            statement.close();
        }

        if (set != null) {
        	set.close();
        }

        size = 0;

        statement = new NamedParameterStatement(con, query, updateable);

        if(params!=null){
	    	Iterator<Object[]> it = params.iterator();
			while(it.hasNext()){
				Object[] param = it.next();
				if (param[1] != null && param[1].getClass() == java.util.Date.class)
					param[1] = new java.sql.Date(((java.util.Date)param[1]).getTime());
				statement.setObject((String)param[0], param[1]);
			}
        }

        set = statement.executeQuery();

        while(set.next())
        	size++;

        set.beforeFirst();

        fireTableDataChanged();
        
    }

    public void setParameters(ArrayList<Object[]> params){
    	this.params = params;
    }

    public void setQuery(String query){
    	this.query = query;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isEditable() {
        return updateable;
    }

    /**
     * DOCUMENT ME!
     *
     * @param flag DOCUMENT ME!
     */
    public void setEditable(boolean flag) {
        updateable = flag;
        fireTableDataChanged();
    }

    /**
     * DOCUMENT ME!
     *
     * @param parent DOCUMENT ME!
     */
    public void setParentComponent(Component parent) {
        this.parent = parent;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Component getParentComponent() {
        return parent;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getRowCount() {
        return updateable ? (size + 1) : size;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws RuntimeException DOCUMENT ME!
     */
    public int getColumnCount() {
        try {
            return set.getMetaData().getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param row DOCUMENT ME!
     * @param column DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws RuntimeException DOCUMENT ME!
     */
    public Object getValueAt(int row, int column) {
        if (row == size) {
            return null;
        }

        try {
        	//Logger.getRootLogger().debug("getValueAt...");
            set.absolute(row + 1);
            return set.getObject(column + 1);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        	Logger.getRootLogger().error("Exception in getValueAt \n"+e+"\n");
        	e.printStackTrace();
        	return null;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param column DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String getColumnName(int column) {
        try {
        	//Logger.getRootLogger().debug("getColumnName...");
            return set.getMetaData().getColumnLabel(column + 1);
        } catch (SQLException ex) {
        	Logger.getRootLogger().error("Exception in getColumnName \n"+ex+"\n");
            return super.getColumnName(column);
        }
    }

    public String getColumnNameOrig(int column) {
        try {
        	//Logger.getRootLogger().debug("getColumnNameOrig...");
            return set.getMetaData().getColumnName(column + 1);
        } catch (SQLException ex) {
        	Logger.getRootLogger().error("Exception in getColumnNameOrig \n"+ex+"\n");
            return super.getColumnName(column);
        }
    }

    public int getColumnPrecision(int column){
    	try {
    		//Logger.getRootLogger().debug("getColumnPrecision...");
            return set.getMetaData().getColumnDisplaySize(column + 1);
        } catch (SQLException ex) {
        	Logger.getRootLogger().error("Exception in getColumnPrecision \n"+ex+"\n");
        	return 0;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param column DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws RuntimeException DOCUMENT ME!
     */
    @Override
    public Class<?> getColumnClass(int column) {
        try {
            return Class.forName(set.getMetaData().getColumnClassName(column + 1));
        } catch (SQLException ex) {
        	ex.printStackTrace();
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param row DOCUMENT ME!
     * @param column DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public boolean isCellEditable(int row, int column) {
    	if (updatableColumn == null){
    		return updateable;
    	} else {
    		for (String element : updatableColumn) {
    			if(element.equalsIgnoreCase(getColumnName(column))) {
    				return updateable;
    			} else if (element.equalsIgnoreCase(getColumnNameOrig(column))) {
    				return updateable;
    			}
        	}
    	}
		return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     * @param row DOCUMENT ME!
     * @param column DOCUMENT ME!
     *
     * @throws RuntimeException DOCUMENT ME!
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
//    	System.out.println("row:"+row+"__column:"+column);
//    	System.out.println(getColumnName(column));
//    	System.out.println(getColumnNameOrig(column));
        if (updateable) {
            try {
            	//Logger.getRootLogger().debug("setValueAt...");
                if (row == size) {
                    set.moveToInsertRow();
                    set.updateObject(column + 1, value);
                    set.insertRow();
                    set.moveToCurrentRow();
                    size += 1;

                    fireTableRowsInserted(row, row + 1);
                } else {
                    set.absolute(row + 1);
                    set.updateObject(column + 1, value);
                    set.updateRow();

                    fireTableRowsUpdated(row, row+1);
                }

                //con.commit();
            } catch (SQLException ex) {
            	Logger.getRootLogger().error("Exception in setValueAt \n"+ex+"\n");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
            } catch (Exception ex) {
            	Logger.getRootLogger().error("Exception in setValueAt \n"+ex+"\n");
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
            } finally {
                try {
                    refresh();
                } catch (SQLException ex) {
                	Logger.getRootLogger().error("Exception in setValueAt \n"+ex+"\n");
                    //ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void deleteRow(int row){
    	try {
    		//Logger.getRootLogger().debug("deleteRow...");
			set.absolute(row + 1);
		    set.deleteRow();
    	} catch (SQLException ex) {
    		Logger.getRootLogger().error("Exception in deleteRow \n"+ex+"\n");
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
        	Logger.getRootLogger().error("Exception in deleteRow \n"+ex+"\n");
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
        } finally {
            try {
                refresh();
            } catch (SQLException ex) {
            	Logger.getRootLogger().error("Exception in deleteRow \n"+ex+"\n");
                //ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public void close() throws SQLException {
        statement.close();
        con.close();
    }

    /**
     * DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    @Override
    protected void finalize() throws Exception {
        close();
    }
}
