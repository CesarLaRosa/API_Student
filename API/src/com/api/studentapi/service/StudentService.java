package com.api.studentapi.service;

import com.api.studentapi.modelo.Student;

public interface StudentService {
	Iterable<Student> listAllStudents();

	Student getStudentsById(Integer id);

	Student saveStudent(Student product);

    void deleteStudent(Integer id);
}
