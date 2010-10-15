package it.f2.gestRip.util;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;


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
public class JDBCTableModel_old extends AbstractTableModel {
    
    static final long serialVersionUID = 2487661888487685951L;

    private Connection con = null;
    private String query = null;
    private ResultSet set = null;
    private int size = 0;
    private boolean updateable = false;
    private Component parent = null;
    private Statement statement = null;
    private String[] updatableColumn = null;

    /**
     * Creates a new JDBCTableModel object.
     *
     * @param con DOCUMENT ME!
     * @param tableName DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public JDBCTableModel_old(Connection con, String query)
        throws SQLException {
        this(con, query, false, null);
    }
    
    public JDBCTableModel_old(Connection con, String query, boolean updateable)
	    throws SQLException {
	    this(con, query, updateable, null);
	}

    /**
     * Creates a new JDBCTableModel object.
     *
     * @param con DOCUMENT ME!
     * @param tableName DOCUMENT ME!
     * @param updateable DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public JDBCTableModel_old(Connection con, String query, boolean updateable,
    		String[] updatableColumn)
        throws SQLException {
        this.con = con;
        this.query = query;
        this.updateable = updateable;
        this.updatableColumn = updatableColumn;
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
        return statement;
    }
    
    public void refresh() throws SQLException {
    	refresh(new ArrayList<Object[]>(),null);
    }

    /**
     * DOCUMENT ME!
     *
     * @throws SQLException DOCUMENT ME!
     */
    public void refresh(ArrayList<Object[]> params, String queryParameter) throws SQLException {
        //Statement tmpst = con.createStatement();
        //ResultSet sizeSet = tmpst.executeQuery("select count(*) from " + tableName);
        //sizeSet.next();
        //size = sizeSet.getInt(1);
        //tmpst.close();
    	
    	if (statement != null) {
            statement.close();
        }
        
        if (set != null) {
        	set.close();
        }
        
        size = 0;
        
        if (false /*params == null && queryParameter == null*/){
	        statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                (updateable ? ResultSet.CONCUR_UPDATABLE
	                            : ResultSet.CONCUR_READ_ONLY));
	        set = statement.executeQuery(query);
        } else {
        	NamedParameterStatement npStatement = 
        		new NamedParameterStatement(con, query, updateable);
        	
        	Iterator<Object[]> it = params.iterator();
			while(it.hasNext()){
				Object[] param = it.next();
				if (param[1] != null && param[1].getClass() == java.util.Date.class)
					param[1] = new java.sql.Date(((java.util.Date)param[1]).getTime());
				npStatement.setObject((String)param[0], param[1]);
			}
			statement = npStatement.getStatement();
			set = npStatement.executeQuery();
        	
        }
        
        while(set.next())
        	size++;
        
        set.beforeFirst();

        fireTableDataChanged();
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
            set.absolute(row + 1);
            return set.getObject(column + 1);
        } catch (SQLException e) {
            //throw new RuntimeException(e);
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
    public String getColumnName(int column) {
        try {
            return set.getMetaData().getColumnLabel(column + 1);
        } catch (SQLException ex) {
            return super.getColumnName(column);
        }
    }
    
    public String getColumnNameOrig(int column) {
        try {
            return set.getMetaData().getColumnName(column + 1);
        } catch (SQLException ex) {
            return super.getColumnName(column);
        }
    }
    
    public int getColumnPrecision(int column){
    	try {
            return set.getMetaData().getColumnDisplaySize(column + 1);
        } catch (SQLException ex) {
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
    public Class<?> getColumnClass(int column) {
        try {
            return Class.forName(set.getMetaData().getColumnClassName(column + 1));
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
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
    public boolean isCellEditable(int row, int column) {
    	if (updatableColumn == null){
    		return updateable;
    	} else {
    		for (int i=0;i<updatableColumn.length;i++){
    			if(updatableColumn[i].equalsIgnoreCase(getColumnName(column))) {
    				return updateable;
    			} else if (updatableColumn[i].equalsIgnoreCase(getColumnNameOrig(column))) {
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
    public void setValueAt(Object value, int row, int column) {
        if (updateable) {
            try {
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
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
            } catch (Exception ex) {
                System.err.println("debug: " + value);
                ex.printStackTrace();
                JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
            } finally {
                try {
                    refresh();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    
    public void deleteRow(int row){
    	try {
			set.absolute(row + 1);
		    set.deleteRow();
    	} catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                ex.getClass().getName(), JOptionPane.ERROR_MESSAGE, null);
        } finally {
            try {
                refresh();
            } catch (SQLException ex) {
                ex.printStackTrace();
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
    protected void finalize() throws Exception {
        close();
    }
}
