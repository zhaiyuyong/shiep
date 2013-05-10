package cn.edu.shiep.model.res;

import cn.edu.shiep.model.SysresModel;

public class LoginResModel {

	private SysresModel sysres;
	private String  paduserid;
	private String  padusername;
	private String  logintime;
	
	public String getPaduserid() {
		return paduserid;
	}
	public void setPaduserid(String paduserid) {
		this.paduserid = paduserid;
	}
	public SysresModel getSysres() {
		return sysres;
	}
	public void setSysres(SysresModel sysres) {
		this.sysres = sysres;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getPadusername() {
		return padusername;
	}
	public void setPadusername(String padusername) {
		this.padusername = padusername;
	}
}
