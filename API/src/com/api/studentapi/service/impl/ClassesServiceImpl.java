package com.api.studentapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;
import com.api.studentapi.repository.ClassesRepository;
import com.api.studentapi.service.ClassesService;

@Service
public class ClassesServiceImpl implements ClassesService {
	@Autowired
	private ClassesRepository classesRepository;
	
	@Override
	public Iterable<ClassModel> listAllClasses() {
		 return classesRepository.findAll();
	}

	@Override
	public ClassModel getClassesById(Integer id) {
		return classesRepository.findOne(id);
	}

	@Override
	public ClassModel saveClasses(ClassModel classes) {
		return classesRepository.save(classes);
	}

	@Override
	public void deleteClasses(Integer id) {
		classesRepository.delete(id);
	}

	@Override
	public List<ClassModel> findByTitle(String title) {
		return classesRepository.findByTitle(title);
	}

	@Override
	public List<ClassModel> findByDescription(String description) {
		return classesRepository.findByDescription(description);
	}

	@Override
	public List<ClassModel> findByStudent(StudentModel searchable) {
		return classesRepository.findByStudent(searchable);
	}
}