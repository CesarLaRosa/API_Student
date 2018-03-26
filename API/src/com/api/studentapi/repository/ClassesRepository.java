package com.api.studentapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.studentapi.model.Classes;
import com.api.studentapi.model.Student;

@RepositoryRestResource
public interface  ClassesRepository  extends CrudRepository<Classes, Integer>{
	List<Classes> findByTitle(String title);
	List<Classes> findByDescription(String description);
	List<Classes> findByStudent(Student searchable);
}
