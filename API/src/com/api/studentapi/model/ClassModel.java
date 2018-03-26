package com.api.studentapi.model;

import java.io.Serializable;

public class ClassModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer code;
	private StudentModel studentID;
	private String title;
	private String description;
	
	public ClassModel(Integer code,String title, String description){
		this.code=code;
		this.title=title;
		this.description=description;
	}

	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public StudentModel getStudentID() {
		return studentID;
	}


	public void setStudentID(StudentModel studentID) {
		this.studentID = studentID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Classes [code=" + code + ", studentID=" + studentID
				+ ", tittle=" + title + ", description=" + description + "]";
	}
}
