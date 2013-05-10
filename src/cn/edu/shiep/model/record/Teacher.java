package cn.edu.shiep.model.record;

import cn.edu.shiep.model.BaseModel;

public class Teacher extends BaseModel{


	private static final long serialVersionUID = 1L;
	
	private String teacherId;
	private String teacherName;
	private String teacherJob;
	private String hireDate;
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherJob() {
		return teacherJob;
	}
	public void setTeacherJob(String teacherJob) {
		this.teacherJob = teacherJob;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	

}
