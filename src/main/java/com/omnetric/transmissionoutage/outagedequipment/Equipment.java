package com.omnetric.transmissionoutage.outagedequipment;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.omnetric.transmissionoutage.outage.Outage;

@Entity
public class Equipment {

	@Id
	private String MRID;
	private String name;
	private String psrtype;
	@ManyToOne
	private Outage outage;

	public Equipment() {

	}

	public Equipment(String mRID, String name, String psrtype, String outageId) {
		super();
		this.MRID = mRID;
		this.name = name;
		this.psrtype = psrtype;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		this.outage = new Outage(outageId, "", "",sqlDate,sqlDate);
	}

	
	public String getMRID() {
		return MRID;
	}

	public void setMRID(String mRID) {
		this.MRID = mRID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsrtype() {
		return psrtype;
	}

	public void setPsrtype(String psrtype) {
		this.psrtype = psrtype;
	}

	public Outage getOutage() {
		return outage;
	}

	public void setOutage(Outage outage) {
		this.outage = outage;
	}


}
