package com.api.studentapi.test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.studentapi.config.WebConfig;
import com.api.studentapi.controller.StudentController;
import com.api.studentapi.model.StudentModel;
import com.api.studentapi.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetransactioncompany.cors.CORSFilter;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class StudentControllerUnitTest {
	
	private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Get All Students ==========================================

    @Test
    public void test_get_all_success() throws Exception {
        List<StudentModel> students = Arrays.asList(
                new StudentModel(1, "La Rosa","Cesar"),
                new StudentModel(2, "Meza","Jhon"));

        when(studentService.listAllStudents()).thenReturn(students);

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                //.andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].lastName", is("La Rosa")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Jhon")));

        verify(studentService, times(1)).listAllStudents();
        verifyNoMoreInteractions(studentService);
    }

    // =========================================== Get User By ID =========================================

    @Test
    public void test_get_by_id_success() throws Exception {
        StudentModel studentModel = new StudentModel(1, "La Rosa","Cesar");

        when(studentService.getStudentsById(1)).thenReturn(studentModel);

        mockMvc.perform(get("/student/getStudent/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Cesar")));

        verify(studentService, times(1)).getStudentsById(1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
    	when(studentService.getStudentsById(1)).thenReturn(null);

        mockMvc.perform(get("/student/getStudent/{id}", 1))
                .andExpect(status().isNotFound());

        verify(studentService, times(1)).getStudentsById(1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    
    // converts a Java object into JSON representation
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
