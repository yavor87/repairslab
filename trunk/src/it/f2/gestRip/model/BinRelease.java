package it.f2.gestRip.model;

public class BinRelease {
	
	private int majorVersion;
	private int minorVersion;
	private int revision;
	private String release;
	public enum Status {
		DEV,
		ALPHA,
		BETA,
		RELEASE_CANDIDATE,
		RELEASE;
	}
	
	public BinRelease(String txtRelease) {
		String[] arr = txtRelease.split("\\.");
		setMajorVersion(Integer.parseInt(arr[0]));
		setMinorVersion(Integer.parseInt(arr[1]));
		setRevision(Integer.parseInt(arr[2]));
		setRelease(arr[3]);
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
		if (getRelease().contains("a"))
			return Status.RELEASE_CANDIDATE;
		if (getRelease().contains("r"))
			return Status.RELEASE;
		else
			return Status.RELEASE;
	}
	
	@Override
	public String toString() {
	    return getMajorVersion() + "." + getMinorVersion() + "." + getRevision() + "." + getRelease();
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
	

}
