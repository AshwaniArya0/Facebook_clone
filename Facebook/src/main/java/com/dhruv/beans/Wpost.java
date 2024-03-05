package com.dhruv.beans;

public class Wpost {
	
	int wid;
	String sender,msg,dop;
	public Wpost(int wid, String sender, String msg, String dop) {
		this.wid = wid;
		this.sender = sender;
		this.msg = msg;
		this.dop = dop;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDop() {
		return dop;
	}
	public void setDop(String dop) {
		this.dop = dop;
	}
	

}
