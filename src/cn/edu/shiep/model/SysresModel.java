package cn.edu.shiep.model;

public class SysresModel {
	
	private String errorCode;//	错误代码
	private String errorMsg; //	错误描述文字信息
	private String sig; //数字签名
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSig() {
		return sig;
	}
	public void setSig(String sig) {
		this.sig = sig;
	}
}
