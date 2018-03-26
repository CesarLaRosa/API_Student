package com.api.studentapi.model;

import java.io.Serializable;

public class StudentModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ClassModel classID;
	private String lastName;
	private String firstName;
	
	public StudentModel(Integer id,String lastName, String firstName){
		this.id=id;
		this.firstName=firstName;
		this.lastName=lastName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public ClassModel getClassID() {
		return classID;
	}

	public void setClassID(ClassModel classID) {
		this.classID = classID;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", classID=" + classID + ", lastName="
				+ lastName + ", firstName=" + firstName + "]";
	}
}
