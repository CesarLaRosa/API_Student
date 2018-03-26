package com.api.studentapi.service;

import java.util.List;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;

public interface StudentService {
	Iterable<StudentModel> listAllStudents();

	StudentModel getStudentsById(Integer id);
	
	StudentModel saveStudent(StudentModel product);

    void deleteStudent(Integer id);
    
    List<StudentModel> findByFirstName(String firstName);
    
    List<StudentModel> findByLastName(String lastName);
    
    List<StudentModel> findByClass(ClassModel searchable);
}
