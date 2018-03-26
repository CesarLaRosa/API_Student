package com.api.studentapi.service;

import java.util.List;

import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.StudentModel;

public interface ClassesService {
	Iterable<ClassModel> listAllClasses();

	ClassModel getClassesById(Integer id);

	ClassModel saveClasses(ClassModel classes);

    void deleteClasses(Integer id);
    
    List<ClassModel> findByTitle(String title);
    
    List<ClassModel> findByDescription(String description);
    
    List<ClassModel> findByStudent(StudentModel searchable);
}
