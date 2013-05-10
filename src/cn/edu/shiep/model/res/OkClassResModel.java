package cn.edu.shiep.model.res;

import java.util.List;

import cn.edu.shiep.model.record.OkClass;


public class OkClassResModel {
	private int recordamount;
	private List<OkClass> rclist;
	public int getRecordamount() {
		return recordamount;
	}
	public void setRecordamount(int recordamount) {
		this.recordamount = recordamount;
	}
	public List<OkClass> getRclist() {
		return rclist;
	}
	public void setRclist(List<OkClass> rclist) {
		this.rclist = rclist;
	}
	
	
}
