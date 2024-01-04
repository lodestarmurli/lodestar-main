package com.lodestar.edupath.datatransferobject.dto.programTest.CareerDegreeDiscovery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CDDStreamDTO {
	private int studentId;
	private int classId;
	private int stream;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getStream() {
		return stream;
	}
	public void setStream(int stream) {
		this.stream = stream;
	}
	@Override
	public String toString() {
		return "CDDStreamDTO [studentId=" + studentId + ", classId=" + classId + ", stream=" + stream + "]";
	}
	
	
}
