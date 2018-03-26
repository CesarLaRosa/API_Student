package com.api.studentapi.model;

import javax.persistence.*;

@Entity
public class ClassModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer code;
	private StudentModel studentID;
	private String tittle;
	private String description;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StudentModel getStudentID() {
		return studentID;
	}
	public void setStudentID(StudentModel studentID) {
		this.studentID = studentID;
	}
	@Override
	public String toString() {
		return "Classes [code=" + code + ", studentID=" + studentID
				+ ", tittle=" + tittle + ", description=" + description + "]";
	}
}