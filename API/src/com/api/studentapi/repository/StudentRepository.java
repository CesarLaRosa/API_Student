package com.api.studentapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;

@RepositoryRestResource
public interface  StudentRepository  extends CrudRepository<StudentModel, Integer>{
	List<StudentModel> findByLastName(String lastName);
	List<StudentModel> findByFirstName(String firstName);
	List<StudentModel> findByClass(ClassModel searchable);
}
