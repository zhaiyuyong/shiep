package cn.edu.shiep.model;

public class SysresModel {
	
	private String errorCode;//	�������
	private String errorMsg; //	��������������Ϣ
	private String sig; //����ǩ��
	
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
