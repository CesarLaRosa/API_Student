package com.api.studentapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.studentapi.modelo.Classes;
import com.api.studentapi.modelo.ResponseController;
import com.api.studentapi.service.ClassesService;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
	@Autowired
	private ClassesService classesService;
	
	@RequestMapping(value = "/classes/", method = RequestMethod.GET)
	public Iterable<Classes> list(Model model){
        Iterable<Classes> classesList = classesService.listAllClasses();
        return classesList;
    }
	
	@RequestMapping(value = "/classes/{id}", method= RequestMethod.GET, produces = "application/json")
    public Classes showClasses(@PathVariable Integer id, Model model){
		Classes classes = classesService.getClassesById(id);
        return classes;
    }


	@RequestMapping(value = "/classes/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseController> saveProduct(@RequestBody Classes classes){
		ResponseController respuestaBus = new ResponseController();
		classesService.saveClasses(classes);
		respuestaBus.setStatus(HttpStatus.CREATED.value());
		respuestaBus.setMessage("Classes saved successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
    }

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseController> update(@PathVariable Integer id, @RequestBody Classes classes) {
		Classes storedClasses = classesService.getClassesById(id);
		storedClasses.setDescription(classes.getDescription());
		storedClasses.setTittle(classes.getTittle());
		classesService.saveClasses(storedClasses);
		ResponseController respuestaBus = new ResponseController();
		respuestaBus.setMessage("Classes updated successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
	}

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseController> delete(@PathVariable Integer id){
		classesService.deleteClasses(id);
        ResponseController respuestaBus = new ResponseController();
        respuestaBus.setMessage("Classes deleted successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
	}
}
