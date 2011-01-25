package net.sf.repairslab.model;

/**
 * Version and Revision bean
 * @author Fabrizio Ferraiuolo
 * 13/gen/2011
 * 15.20.41
 * Copyright (c)2009 Decisyon S.r.l.
 */
public class BinRelease {
	
	private String appname = "RepairsLab";
	private int majorVersion = 0;
	private int minorVersion = 0;
	private int revision = 0;
	
	private Status status = Status.SNAPSHOT;
	
	private int release = 0;
	
	public enum Status {
		SNAPSHOT(0),
		alpha(1),
		beta(2),
		rc(3),
		STABLE(4);
		
		private final int order;
		
		Status(int order) {
			this.order = order;
		}
		
		public int getOrder() {
			return order;
		}

	}
	
	/**
	 * Costruttore della classe BinRelease.java
	 * @author Fabrizio Ferraiuolo
	 * 13/gen/2011
	 * 15.51.27
	 */
	public BinRelease() {
	}
	
	/**
	 * Costruttore della classe BinRelease.java
	 * @author Fabrizio Ferraiuolo
	 * 13/gen/2011
	 * 15.21.10
	 * @param version
	 * @param release
	 */
	public BinRelease(String appname, String version, int release) {
		
		if (appname != null && !"".equals(appname))
			setAppname(appname);
		
		if (version!=null && !"".equals(version)) {
			
			String[] arr1 = version.split("\\.");
			setMajorVersion(Integer.parseInt(arr1[0]));
			setMinorVersion(Integer.parseInt(arr1[1]));
			
			String[] arr2 = arr1[2].split("-");
			setRevision(Integer.parseInt(arr2[0]));
			
			if (arr2.length == 1) {
				setStatus(Status.STABLE);
			} else {
				setStatus(getStatusByString(arr2[1]));
			}
			
		}
		
		setRelease(release);
	}
	
	public BinRelease(String version) {
		if (version!=null && !"".equals(version)) {
			String[] arr1 = version.split("\\.");
			setMajorVersion(Integer.parseInt(arr1[0]));
			setMinorVersion(Integer.parseInt(arr1[1]));
			setRevision(Integer.parseInt(arr1[2]));
			setRelease(Integer.parseInt(arr1[3]));
			setStatus(Status.STABLE);
		}
    }

	private Status getStatusByString(String string) {
		if (string.equalsIgnoreCase(Status.SNAPSHOT.name()))
			return Status.SNAPSHOT;
		if (string.equalsIgnoreCase(Status.alpha.name()))
			return Status.alpha;
		if (string.equalsIgnoreCase(Status.beta.name()))
			return Status.beta;
		if (string.equalsIgnoreCase(Status.rc.name()))
			return Status.rc;
		if (string.equalsIgnoreCase(""))
			return Status.STABLE;
		return null;
	}
	
	
	/**
     * @return the version
     */
    public int getMajorVersion() {
    	return majorVersion;
    }
	/**
     * @param version the version to set
     */
    public void setMajorVersion(int majorVersion) {
    	this.majorVersion = majorVersion;
    }
	/**
     * @return the minorVersion
     */
    public int getMinorVersion() {
    	return minorVersion;
    }
	/**
     * @param minorVersion the minorVersion to set
     */
    public void setMinorVersion(int minorVersion) {
    	this.minorVersion = minorVersion;
    }
	/**
     * @return the revision
     */
    public int getRevision() {
    	return revision;
    }
	/**
     * @param revision the revision to set
     */
    public void setRevision(int revision) {
    	this.revision = revision;
    }
	/**
     * @return the release
     */
    public int getRelease() {
    	return release;
    }
	/**
     * @param release the release to set
     */
    public void setRelease(int release) {
    	this.release = release;
    }
	
	@Override
	public String toString() {
		
		if (getMajorVersion() == 0 && getMinorVersion() == 0 && getRevision() == 0)
			return "Error check...";
		
		String status = (getStatus().equals(Status.STABLE)) ? "" : "-"+getStatus().name();
	    return getMajorVersion() + "." + getMinorVersion() + "." + getRevision() + status + "-r" + getRelease();
	}
	
	public String getVersion() {
		return getMajorVersion() + "." + getMinorVersion() + "." + getRevision();
	}
	
	@Override
	public boolean equals(Object o) {
		BinRelease bo = (BinRelease)o;
	    if (this.getMajorVersion() == bo.getMajorVersion()
	    		&& this.getMinorVersion() == bo.getMinorVersion()
	    		&& this.getRevision() == bo.getRevision()
	    		&& this.getStatus().equals(bo.getStatus())
	    		&& this.getRelease() == bo.getRelease()) {
	    	return true;
	    }
	    return false;
	}
	
	public boolean isMajor(BinRelease o) {
		if (this.getMajorVersion() > o.getMajorVersion()) {
	    	return true;
	    } else if (this.getMajorVersion() == o.getMajorVersion()
	    		&& this.getMinorVersion() >  o.getMinorVersion()) {
	    	return true;
	    } else if (this.getMajorVersion() == o.getMajorVersion()
	    		&& this.getMinorVersion() == o.getMinorVersion()
	    		&& this.getRevision()     >  o.getRevision()) {
	    	return true;
	    } else if (this.getMajorVersion() 	   == o.getMajorVersion()
	    		&& this.getMinorVersion() 	   == o.getMinorVersion()
	    		&& this.getRevision()    	   == o.getRevision()
	    		&& this.getStatus().getOrder() >  o.getStatus().getOrder()) {
	    	return true;
	    } else if (this.getMajorVersion() 	   == o.getMajorVersion()
	    		&& this.getMinorVersion() 	   == o.getMinorVersion()
	    		&& this.getRevision()    	   == o.getRevision()
	    		&& this.getStatus().getOrder() == o.getStatus().getOrder()
	    		&& this.getRelease()	   	   >  o.getRelease()) {
	    	return true;
	    }
		return false;
	}


	/**
     * @return the status
     */
    public Status getStatus() {
    	return status;
    }


	/**
     * @param status the status to set
     */
    public void setStatus(Status status) {
    	this.status = status;
    }

	/**
     * @return the appname
     */
    public String getAppname() {
    	return appname;
    }

	/**
     * @param appname the appname to set
     */
    public void setAppname(String appname) {
    	this.appname = appname;
    }

}
