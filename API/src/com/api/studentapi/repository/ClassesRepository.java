package com.api.studentapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.studentapi.modelo.Classes;

@RepositoryRestResource
public interface  ClassesRepository  extends CrudRepository<Classes, Integer>{


}
