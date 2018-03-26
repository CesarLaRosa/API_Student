package com.api.studentapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.Student;

@RepositoryRestResource
public interface  StudentRepository  extends CrudRepository<Student, Integer>{
	List<Student> findByLastName(String lastName);
	List<Student> findByFirstName(String firstName);
	List<Student> findByClass(Classes searchable);
}
