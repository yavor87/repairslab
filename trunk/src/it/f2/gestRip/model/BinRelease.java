package it.f2.gestRip.model;

public class BinRelease {
	
	private int majorVersion = 0;
	private int minorVersion = 0;
	private int revision = 0;
	private String release = "0";
	public enum Status {
		DEV(0),
		ALPHA(1),
		BETA(2),
		RELEASE_CANDIDATE(3),
		RELEASE(4);
		
		private final int order;
		
		Status(int order) {
			this.order = order;
		}
		
		public int getOrder() {
			return order;
		}

	}
	
	public BinRelease(String txtRelease) {
		if (txtRelease!=null && !"".equals(txtRelease)) {
			String[] arr = txtRelease.split("\\.");
			setMajorVersion(Integer.parseInt(arr[0]));
			setMinorVersion(Integer.parseInt(arr[1]));
			setRevision(Integer.parseInt(arr[2]));
			setRelease(arr[3]);
		}
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
    public String getRelease() {
    	return release;
    }
	/**
     * @param release the release to set
     */
    public void setRelease(String release) {
    	this.release = release;
    }
	
	/**
	 * Stato dello sviluppo
	 * @author Fabrizio Ferraiuolo 04/nov/2010 10.57.24
	 * @return 
	 */
	public Status getStatus() {
		if (getRelease().contains("s"))
			return Status.DEV;
		if (getRelease().contains("a"))
			return Status.ALPHA;
		if (getRelease().contains("b"))
			return Status.BETA;
		if (getRelease().contains("rc"))
			return Status.RELEASE_CANDIDATE;
		if (getRelease().contains("r"))
			return Status.RELEASE;
		else
			return Status.RELEASE;
	}
	
	/**
	 * TODO Comment for method "getReleaseNumber" must be completed
	 * @author Fabrizio Ferraiuolo 04/nov/2010 18.00.48
	 * @return 
	 */
	private int getReleaseNumber() {
		String numericChars = "";
		for (int i=0;i<getRelease().length();i++){
			char c = getRelease().charAt(i);
			int asciCode = c;
			if (asciCode >= 48 && asciCode <= 57)
				numericChars += c;
		}
		return Integer.parseInt(numericChars);
	}
	
	@Override
	public String toString() {
	    return getMajorVersion() + "." + getMinorVersion() + "." + getRevision() + "." + getRelease();
	}
	
	public String getRevisionString() {
		return getMajorVersion() + "." + getMinorVersion() + "." + getRevision();
	}
	
	@Override
	public boolean equals(Object o) {
		BinRelease bo = (BinRelease)o;
	    if (this.getMajorVersion() == bo.getMajorVersion()
	    		&& this.getMinorVersion() == bo.getMinorVersion()
	    		&& this.getRevision() == bo.getRevision()
	    		&& this.getRelease().equals(bo.getRelease())) {
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
	    		&& this.getReleaseNumber()	   >  o.getReleaseNumber()) {
	    	return true;
	    }
		return false;
	}

}
