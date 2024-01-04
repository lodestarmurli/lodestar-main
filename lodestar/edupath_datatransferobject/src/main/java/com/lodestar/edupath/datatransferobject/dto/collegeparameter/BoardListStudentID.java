package com.lodestar.edupath.datatransferobject.dto.collegeparameter;

import java.util.ArrayList;

import com.lodestar.edupath.datatransferobject.dto.IModel;

public class BoardListStudentID implements IModel {
	
	private int StudentID;
	private ArrayList boardId;
	public int getStudentID() {
		return StudentID;
	}
	public void setStudentID(int studentID) {
		StudentID = studentID;
	}
	public ArrayList getBoardId() {
		return boardId;
	}
	public void setBoardId(ArrayList boardId) {
		this.boardId = boardId;
	}

}
