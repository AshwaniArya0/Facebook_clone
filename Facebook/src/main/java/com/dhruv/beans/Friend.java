package com.dhruv.beans;

public class Friend {
	
	int fid,stat;
	String sender,rec;
	public Friend(int fid, String sender, String rec, int stat) {
		super();
		this.fid = fid;
		this.stat = stat;
		this.sender = sender;
		this.rec = rec;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getRec() {
		return rec;
	}
	public void setRec(String rec) {
		this.rec = rec;
	}
	

}
