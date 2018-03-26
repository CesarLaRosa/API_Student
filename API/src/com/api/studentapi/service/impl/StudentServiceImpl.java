package com.api.studentapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.Student;
import com.api.studentapi.repository.StudentRepository;
import com.api.studentapi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Iterable<Student> listAllStudents() {
		 return studentRepository.findAll();
	}

	@Override
	public Student getStudentsById(Integer id) {
		return studentRepository.findOne(id);
	}

	@Override
	public Student saveStudent(Student student) {
		 return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepository.delete(id);
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		return studentRepository.findByLastName(lastName);
	}

	@Override
	public List<Student> findByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	@Override
	public List<Student> findByClass(Classes searchable) {
		return studentRepository.findByClass(searchable);
	}
}