package com.omnetric.transmissionoutage.outage;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Outage {

	@Id
	private String MRID;
	private String outageStatus;
	private String outageDescription;
	private Date outageStart;
	private Date outageEnd;

	public Outage() {

	}

	public Outage(String mRID, String outageStatus, String outageDescription, Date outageStart, Date outageEnd ) {
		super();
		this.MRID = mRID;
		this.outageStatus = outageStatus;
		this.outageDescription = outageDescription;
		this.outageStart = outageStart;
		this.outageEnd = outageEnd;
	}

	public String getMRID() {
		return MRID;
	}

	public void setMRID(String mRID) {
		this.MRID = mRID;
	}

	public String getOutageStatus() {
		return outageStatus;
	}

	public void setOutageStatus(String outageStatus) {
		this.outageStatus = outageStatus;
	}

	public String getOutageDescription() {
		return outageDescription;
	}

	public void setOutageDescription(String outageDescription) {
		this.outageDescription = outageDescription;
	}

	public Date getOutageStart() {
		return outageStart;
	}

	public void setOutageStart(Date outageStart) {
		this.outageStart = outageStart;
	}

	public Date getOutageEnd() {
		return outageEnd;
	}

	public void setOutageEnd(Date outageEnd) {
		this.outageEnd = outageEnd;
	}


}
