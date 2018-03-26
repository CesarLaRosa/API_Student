package com.api.studentapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;
import com.api.studentapi.repository.StudentRepository;
import com.api.studentapi.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Iterable<StudentModel> listAllStudents() {
		 return studentRepository.findAll();
	}

	@Override
	public StudentModel getStudentsById(Integer id) {
		return studentRepository.findOne(id);
	}

	@Override
	public StudentModel saveStudent(StudentModel student) {
		 return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Integer id) {
		studentRepository.delete(id);
	}

	@Override
	public List<StudentModel> findByLastName(String lastName) {
		return studentRepository.findByLastName(lastName);
	}

	@Override
	public List<StudentModel> findByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	@Override
	public List<StudentModel> findByClass(ClassModel searchable) {
		return studentRepository.findByClass(searchable);
	}
}