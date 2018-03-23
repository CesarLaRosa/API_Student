package com.api.studentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.api.studentapi.modelo.Student;

@RepositoryRestResource
public interface  StudentRepository  extends CrudRepository<Student, Integer>{


}
