package cn.edu.shiep.model.res;

import java.io.Serializable;

public class TodayClassModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String className;
	private String classTime;
	private String classRoom;
	private String teacherName;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	

}
