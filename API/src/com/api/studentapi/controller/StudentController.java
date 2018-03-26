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

import com.api.studentapi.exceptions.StudentNotFoundException;
import com.api.studentapi.model.ResponseModel;
import com.api.studentapi.model.StudentModel;
import com.api.studentapi.service.StudentService;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
	private Logger logger = Logger.getLogger(getClass());
	private ResponseModel responseModel;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/", method= RequestMethod.GET, produces = "application/json", consumes= "application/json")
    public ResponseEntity<ResponseModel> list(Model model){
		responseModel = new ResponseModel();
        Iterable<StudentModel> productList = studentService.listAllStudents();
        responseModel.setStatus(HttpStatus.OK.value());
        responseModel.setResult(productList);
        return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
    }
	
	@RequestMapping(value = "/getStudent/{id}", method= RequestMethod.GET, produces = "application/json", consumes= "application/json")
    public ResponseEntity<ResponseModel> getStudent(@PathVariable Integer id, Model model){
		logger.info("showStudent("+id+")");
		responseModel = new ResponseModel();
		
		StudentModel student = studentService.getStudentsById(id);
		
		if (student==null)
		{
			throw new StudentNotFoundException("id-" + id);
		}
		
		responseModel.setStatus(HttpStatus.OK.value());
        responseModel.setResult(student);
        return new ResponseEntity<ResponseModel>(responseModel,	HttpStatus.OK);
    }
	
	@RequestMapping(value = "/saveStudent/", method = RequestMethod.POST, produces = "application/json", consumes= "application/json")
    public ResponseEntity<ResponseModel> saveStudent(@RequestBody StudentModel student){
		logger.info("save("+student+")");
		ResponseModel respuestaBus = new ResponseModel();
		studentService.saveStudent(student);
		respuestaBus.setStatus(HttpStatus.CREATED.value());
		respuestaBus.setMessage("Student saved successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
    }

	@RequestMapping(value = "/updateStudent/{id}", method = RequestMethod.PUT, produces = "application/json", consumes= "application/json")
	public ResponseEntity<ResponseModel> updateStudent(@PathVariable Integer id, @RequestBody StudentModel student) {
		logger.info("update("+id+","+student+")");
		
		StudentModel storedStudent = studentService.getStudentsById(id);
		
		if (storedStudent==null)
		{
			throw new StudentNotFoundException("id-" + id);
		}
		    
		storedStudent.setFirstName(student.getFirstName());
		storedStudent.setLastName(student.getLastName());
		studentService.saveStudent(storedStudent);
		ResponseModel respuestaBus = new ResponseModel();
		respuestaBus.setMessage("Student updated successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteStudent/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes= "application/json")
	public ResponseEntity<ResponseModel> deleteStudent(@PathVariable Integer id){
		logger.info("delete("+id+")");
		StudentModel storedStudent = studentService.getStudentsById(id);
		
		if (storedStudent==null)
	    {
			throw new StudentNotFoundException("id-" + id);
	    }
		
		studentService.deleteStudent(id);
        ResponseModel respuestaBus = new ResponseModel();
        respuestaBus.setMessage("Student deleted successfully");
		return new ResponseEntity<ResponseModel>(respuestaBus,	HttpStatus.OK);
	}
}
