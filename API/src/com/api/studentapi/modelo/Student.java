package com.api.studentapi.modelo;

import javax.persistence.*;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Classes classID;
	private String lastName;
	private String firstName;

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

	public Classes getClassID() {
		return classID;
	}

	public void setClassID(Classes classID) {
		this.classID = classID;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", classID=" + classID + ", lastName="
				+ lastName + ", firstName=" + firstName + "]";
	}
}
