package cn.edu.shiep.model.res;

import java.util.List;

import cn.edu.shiep.model.record.Teacher;

public class TeacherResModel {
	private int recordamount;
	private List<Teacher> rclist;
	public int getRecordamount() {
		return recordamount;
	}
	public void setRecordamount(int recordamount) {
		this.recordamount = recordamount;
	}
	public List<Teacher> getRclist() {
		return rclist;
	}
	public void setRclist(List<Teacher> rclist) {
		this.rclist = rclist;
	}
	

}
