package net.sf.repairslab.control;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe necessaria per caricare dinamicamente un driver nel classpath
 * @author Fabrizio Ferraiuolo
 * 18/feb/2011
 * 14.59.58
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class DriverShim implements Driver {
	
	private Driver driver;
	
	/**
	 * Costruttore della classe DriverShim.java
	 * @author Fabrizio Ferraiuolo
	 * 18/feb/2011
	 * 15.00.39
	 * @param driver
	 */
	public DriverShim(Driver driver) {
		this.driver = driver;
	}


	/**
	 * @see java.sql.Driver#connect(java.lang.String, java.util.Properties)
	 */
	@Override
    public Connection connect(String url, Properties info) throws SQLException {
	    return driver.connect(url, info);
    }

	/**
	 * @see java.sql.Driver#acceptsURL(java.lang.String)
	 */
	@Override
    public boolean acceptsURL(String url) throws SQLException {
		return driver.acceptsURL(url);
    }

	/**
	 * @see java.sql.Driver#getPropertyInfo(java.lang.String, java.util.Properties)
	 */
	@Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
	    return driver.getPropertyInfo(url, info);
    }

	/**
	 * @see java.sql.Driver#getMajorVersion()
	 */
	@Override
    public int getMajorVersion() {
	    return driver.getMajorVersion();
    }

	/**
	 * @see java.sql.Driver#getMinorVersion()
	 */
	@Override
    public int getMinorVersion() {
	    return driver.getMinorVersion();
    }

	/**
	 * @see java.sql.Driver#jdbcCompliant()
	 */
	@Override
    public boolean jdbcCompliant() {
	    return driver.jdbcCompliant();
    }

}
