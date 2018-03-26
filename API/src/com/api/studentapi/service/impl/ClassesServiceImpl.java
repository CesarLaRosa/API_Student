package com.api.studentapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.Student;
import com.api.studentapi.repository.ClassesRepository;
import com.api.studentapi.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesRepository classesRepository;
	
	@Override
	public Iterable<Classes> listAllClasses() {
		 return classesRepository.findAll();
	}

	@Override
	public Classes getClassesById(Integer id) {
		return classesRepository.findOne(id);
	}

	@Override
	public Classes saveClasses(Classes classes) {
		return classesRepository.save(classes);
	}

	@Override
	public void deleteClasses(Integer id) {
		classesRepository.delete(id);
	}

	@Override
	public List<Classes> findByTitle(String title) {
		return classesRepository.findByTitle(title);
	}

	@Override
	public List<Classes> findByDescription(String description) {
		return classesRepository.findByDescription(description);
	}

	@Override
	public List<Classes> findByStudent(Student searchable) {
		return classesRepository.findByStudent(searchable);
	}
}