package cn.edu.shiep.model.res;

import java.io.Serializable;
import java.util.List;

import cn.edu.shiep.model.record.ClassModel;
import cn.edu.shiep.model.record.Teacher;

public class ClassResModel {
	
	private int recordamount;
	private List<ClassModel> rclist;
	public int getRecordamount() {
		return recordamount;
	}
	public void setRecordamount(int recordamount) {
		this.recordamount = recordamount;
	}
	public List<ClassModel> getRclist() {
		return rclist;
	}
	public void setRclist(List<ClassModel> rclist) {
		this.rclist = rclist;
	}
	


}
