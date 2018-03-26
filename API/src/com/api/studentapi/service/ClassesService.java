package com.api.studentapi.service;

import java.util.List;
import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.Student;

public interface ClassesService {
	Iterable<Classes> listAllClasses();

	Classes getClassesById(Integer id);

	Classes saveClasses(Classes classes);

    void deleteClasses(Integer id);
    
    List<Classes> findByTitle(String title);
    
    List<Classes> findByDescription(String description);
    
    List<Classes> findByStudent(Student searchable);
}
