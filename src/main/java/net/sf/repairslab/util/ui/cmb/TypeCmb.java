package net.sf.repairslab.util.ui.cmb;

/**
 * Classe che definisce il tipo combo box.
 */
public class TypeCmb {

	private String value;

	private String desc;

	public TypeCmb() {
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String v) {
		this.value = v;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String v) {
		this.desc = v;
	}

	public String toString() {
		return this.desc;
	}
}
