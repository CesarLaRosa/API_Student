package com.api.studentapi.service;

import com.api.studentapi.modelo.Classes;

public interface ClassesService {
	Iterable<Classes> listAllClasses();

	Classes getClassesById(Integer id);

	Classes saveClasses(Classes classes);

    void deleteClasses(Integer id);
}
