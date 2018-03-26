package com.api.studentapi.service;

import java.util.List;

import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.Student;

public interface StudentService {
	Iterable<Student> listAllStudents();

	Student getStudentsById(Integer id);
	
	Student saveStudent(Student product);

    void deleteStudent(Integer id);
    
    List<Student> findByFirstName(String firstName);
    
    List<Student> findByLastName(String lastName);
    
    List<Student> findByClass(Classes searchable);
}
