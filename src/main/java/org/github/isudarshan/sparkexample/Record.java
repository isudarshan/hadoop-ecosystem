package org.github.isudarshan.sparkexample;

/**
 * @author sudarsan
 * 
 * 
 */
public class Record implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 859356247326118581L;
	private String stCode;
	private String dtCode;
	private String dtName;
	private String sdtCode;
	private String sdtName;
	private String tvCode;
	private String name;

	public Record(String stCode, String dtCode, String dtName, String sdtCode,
			String sdtName, String tvCode, String name) {
		super();
		this.stCode = stCode;
		this.dtCode = dtCode;
		this.dtName = dtName;
		this.sdtCode = sdtCode;
		this.sdtName = sdtName;
		this.tvCode = tvCode;
		this.name = name;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public String getDtCode() {
		return dtCode;
	}

	public void setDtCode(String dtCode) {
		this.dtCode = dtCode;
	}

	public String getDtName() {
		return dtName;
	}

	public void setDtName(String dtName) {
		this.dtName = dtName;
	}

	public String getSdtCode() {
		return sdtCode;
	}

	public void setSdtCode(String sdtCode) {
		this.sdtCode = sdtCode;
	}

	public String getSdtName() {
		return sdtName;
	}

	public void setSdtName(String sdtName) {
		this.sdtName = sdtName;
	}

	public String getTvCode() {
		return tvCode;
	}

	public void setTvCode(String tvCode) {
		this.tvCode = tvCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Record [stCode=" + stCode + ", dtCode=" + dtCode + ", dtName="
				+ dtName + ", sdtCode=" + sdtCode + ", sdtName=" + sdtName
				+ ", tvCode=" + tvCode + ", name=" + name + "]";
	}

}
