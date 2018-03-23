package com.api.studentapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.studentapi.modelo.Classes;
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
}