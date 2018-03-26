package com.api.studentapi.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.studentapi.exceptions.ClassesNotFoundException;
import com.api.studentapi.model.ClassModel;
import com.api.studentapi.model.ResponseModel;
import com.api.studentapi.service.ClassesService;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ClassesService classesService;
	
	@RequestMapping(value = "/classes/", method = RequestMethod.GET)
	public Iterable<ClassModel> list(Model model){
        Iterable<ClassModel> classesList = classesService.listAllClasses();
        return classesList;
    }
	
	@RequestMapping(value = "/classes/{id}", method= RequestMethod.GET, produces = "application/json")
    public ClassModel showClasses(@PathVariable Integer id, Model model){
		logger.info("showClasses("+id+")");
		ClassModel classes = classesService.getClassesById(id);
		
		if (classes==null)
			throw new ClassesNotFoundException("id-" + id);
		
        return classes;
    }


	@RequestMapping(value = "/classes/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseModel> save(@RequestBody ClassModel classes){
		logger.info("save("+classes+")");
		ResponseModel respuestaBus = new ResponseModel();
		classesService.saveClasses(classes);
		respuestaBus.setStatus(HttpStatus.CREATED.value());
		respuestaBus.setMessage("Classes saved successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
    }

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseModel> update(@PathVariable Integer id, @RequestBody ClassModel classes) {
		logger.info("update("+classes+")");
		ClassModel storedClasses = classesService.getClassesById(id);
		
		if (storedClasses==null)
			throw new ClassesNotFoundException("id-" + id);
		
		storedClasses.setDescription(classes.getDescription());
		storedClasses.setTittle(classes.getTittle());
		classesService.saveClasses(storedClasses);
		ResponseModel respuestaBus = new ResponseModel();
		respuestaBus.setMessage("Classes updated successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
	}

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseModel> delete(@PathVariable Integer id){
		logger.info("delete("+id+")");
		ClassModel storedClasses = classesService.getClassesById(id);
		if (storedClasses==null)
			throw new ClassesNotFoundException("id-" + id);
		
		classesService.deleteClasses(id);
        ResponseModel respuestaBus = new ResponseModel();
        respuestaBus.setMessage("Classes deleted successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
	}
}
