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

import com.api.studentapi.modelo.ResponseController;
import com.api.studentapi.modelo.Student;
import com.api.studentapi.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/students", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Student> list(Model model){
        Iterable<Student> productList = studentService.listAllStudents();
        return productList;
    }
	
	@RequestMapping(value = "/students/{id}", method= RequestMethod.GET, produces = "application/json")
    public Student showStudent(@PathVariable Integer id, Model model){
		Student student = studentService.getStudentsById(id);
        return student;
    }


	@RequestMapping(value = "/students/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResponseController> saveProduct(@RequestBody Student student){
		ResponseController respuestaBus = new ResponseController();
		studentService.saveStudent(student);
		respuestaBus.setStatus(HttpStatus.CREATED.value());
		respuestaBus.setMessage("Student saved successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
    }

	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseController> update(@PathVariable Integer id, @RequestBody Student student) {
		Student storedStudent = studentService.getStudentsById(id);
		storedStudent.setFirstName(student.getFirstName());
		storedStudent.setLastName(student.getLastName());
		studentService.saveStudent(storedStudent);
		ResponseController respuestaBus = new ResponseController();
		respuestaBus.setMessage("Student updated successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
	}

	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseController> delete(@PathVariable Integer id){
		studentService.deleteStudent(id);
        ResponseController respuestaBus = new ResponseController();
        respuestaBus.setMessage("Student deleted successfully");
		return new ResponseEntity<ResponseController>(respuestaBus,	HttpStatus.OK);
	}
}
