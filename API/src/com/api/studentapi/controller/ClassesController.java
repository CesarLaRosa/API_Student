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
@RequestMapping("/api/classes/")
public class ClassesController {
	private Logger logger = Logger.getLogger(getClass());
	private ResponseModel responseModel;
	
	@Autowired
	private ClassesService classesService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json", consumes= "application/json")
	public ResponseEntity<ResponseModel> list(Model model){
		responseModel = new ResponseModel();
        Iterable<ClassModel> classesList = classesService.listAllClasses();
        responseModel.setStatus(HttpStatus.OK.value());
        responseModel.setResult(classesList);
        return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getClasses/{id}", method= RequestMethod.GET, produces = "application/json", consumes= "application/json")
    public ResponseEntity<ResponseModel> getClasses(@PathVariable Integer id){
		logger.info("showClasses("+id+")");
		responseModel = new ResponseModel();
		ClassModel classes = classesService.getClassesById(id);
		
		if (classes==null)
		{
			throw new ClassesNotFoundException("id-" + id);
		}
		
		responseModel.setStatus(HttpStatus.OK.value());
        responseModel.setResult(classes);
        return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
    }

	@RequestMapping(value = "/saveClasses/", method = RequestMethod.POST, produces = "application/json", consumes= "application/json")
    public ResponseEntity<ResponseModel> saveClasses(@RequestBody ClassModel classes){
		logger.info("save("+classes+")");
		responseModel = new ResponseModel();
		classesService.saveClasses(classes);
		responseModel.setStatus(HttpStatus.CREATED.value());
		responseModel.setMessage("Classes saved successfully");
		return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
    }

	@RequestMapping(value = "/updateClasses/{id}", method = RequestMethod.PUT, produces = "application/json", consumes= "application/json")
	public ResponseEntity<ResponseModel> updateClasses(@PathVariable Integer id, @RequestBody ClassModel classes) {
		logger.info("update("+classes+")");
		responseModel = new ResponseModel();
		ClassModel storedClasses = classesService.getClassesById(id);
		
		if (storedClasses==null)
		{
			throw new ClassesNotFoundException("id-" + id);
		}
		
		storedClasses.setDescription(classes.getDescription());
		storedClasses.setTitle(classes.getTitle());
		classesService.saveClasses(storedClasses);
		responseModel.setMessage("Classes updated successfully");
		return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteClasses/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes= "application/json")
	public ResponseEntity<ResponseModel> deleteClasses(@PathVariable Integer id){
		logger.info("delete("+id+")");
		ClassModel storedClasses = classesService.getClassesById(id);
		if (storedClasses==null)
		{
			throw new ClassesNotFoundException("id-" + id);
		}
		
		classesService.deleteClasses(id);
        ResponseModel respuestaBus = new ResponseModel();
        respuestaBus.setMessage("Classes deleted successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
	}
}
