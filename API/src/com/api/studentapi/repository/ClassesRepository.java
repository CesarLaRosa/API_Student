package com.api.studentapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;

@RepositoryRestResource
public interface  ClassesRepository  extends CrudRepository<ClassModel, Integer>{
	List<ClassModel> findByTitle(String title);
	List<ClassModel> findByDescription(String description);
	List<ClassModel> findByStudent(StudentModel searchable);
}
