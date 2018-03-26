/*package com.api.studentapi.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.studentapi.config.WebConfig;
import com.api.studentapi.controller.StudentController;
import com.api.studentapi.model.StudentModel;
import com.api.studentapi.repository.StudentRepository;
import com.api.studentapi.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thetransactioncompany.cors.CORSFilter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class StudentControllerUnitTest {
	
//	@Autowired
//	private StudentRepository studentRepository;
//	
//	@Test
//	public void testFindById() {
//		StudentModel studentModel = studentRepository.findById(this.book.getId());
//		assertEquals(this.book.getAuthor(), studentModel.getAuthor());
//		assertEquals(this.book.getDescription(), studentModel.getDescription());
//		assertEquals(this.book.getIsbn(), studentModel.getIsbn());
//	}
	
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
                new StudentModel(1, "Daenerys","Cesar"),
                new StudentModel(2, "Snow","Jhon"));

        when(studentService.listAllStudents()).thenReturn(students);

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].lastName", is("Daenerys")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Snow")));

        verify(studentService, times(1)).listAllStudents();
        verifyNoMoreInteractions(studentService);
    }

//    // =========================================== Get User By ID =========================================
//
//    @Test
//    public void test_get_by_id_success() throws Exception {
//        StudentModel studentModel = new StudentModel(1, "Daenerys","Cesar");
//
//        when(studentService.getStudentsById(1)).thenReturn(studentModel);
//
//        mockMvc.perform(get("/student/{id}", 1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.firstName", is("Cesar")));
//
//        verify(studentService, times(1)).getStudentsById(1);
//        verifyNoMoreInteractions(studentService);
//    }
//
//    @Test
//    public void test_get_by_id_fail_404_not_found() throws Exception {
//
//        when(studentService.getStudentsById(1)).thenReturn(null);
//
//        mockMvc.perform(get("/student/{id}", 1))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).getStudentsById(1);
//        verifyNoMoreInteractions(studentService);
//    }
//
//    // =========================================== Create New User ========================================
//
//    @Test
//    public void test_create_user_success() throws Exception {
//        User user = new User("Arya Stark");
//
//        when(studentService.exists(user)).thenReturn(false);
//        doNothing().when(studentService).create(user);
//
//        mockMvc.perform(
//                post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(user)))
//                .andExpect(status().isCreated())
//                .andExpect(header().string("location", containsString("http://localhost/users/")));
//
//        verify(studentService, times(1)).exists(user);
//        verify(studentService, times(1)).create(user);
//        verifyNoMoreInteractions(studentService);
//    }
//
//    @Test
//    public void test_create_user_fail_409_conflict() throws Exception {
//        User user = new User("username exists");
//
//        when(studentService.exists(user)).thenReturn(true);
//
//        mockMvc.perform(
//                post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(user)))
//                .andExpect(status().isConflict());
//
//        verify(studentService, times(1)).exists(user);
//        verifyNoMoreInteractions(studentService);
//    }
//
//    // =========================================== Update Existing User ===================================
//
//    @Test
//    public void test_update_user_success() throws Exception {
//        User user = new User(1, "Arya Stark");
//
//        when(studentService.findById(user.getId())).thenReturn(user);
//        doNothing().when(studentService).update(user);
//
//        mockMvc.perform(
//                put("/users/{id}", user.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(user)))
//                .andExpect(status().isOk());
//
//        verify(studentService, times(1)).findById(user.getId());
//        verify(studentService, times(1)).update(user);
//        verifyNoMoreInteractions(studentService);
//    }
//
//    @Test
//    public void test_update_user_fail_404_not_found() throws Exception {
//        User user = new User(UNKNOWN_ID, "user not found");
//
//        when(studentService.findById(user.getId())).thenReturn(null);
//
//        mockMvc.perform(
//                put("/users/{id}", user.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(user)))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).findById(user.getId());
//        verifyNoMoreInteractions(studentService);
//    }
//
//    // =========================================== Delete User ============================================
//
//    @Test
//    public void test_delete_user_success() throws Exception {
//        User user = new User(1, "Arya Stark");
//
//        when(studentService.findById(user.getId())).thenReturn(user);
//        doNothing().when(studentService).delete(user.getId());
//
//        mockMvc.perform(
//                delete("/users/{id}", user.getId()))
//                .andExpect(status().isOk());
//
//        verify(studentService, times(1)).findById(user.getId());
//        verify(studentService, times(1)).delete(user.getId());
//        verifyNoMoreInteractions(studentService);
//    }
//
//    @Test
//    public void test_delete_user_fail_404_not_found() throws Exception {
//        User user = new User(UNKNOWN_ID, "user not found");
//
//        when(studentService.findById(user.getId())).thenReturn(null);
//
//        mockMvc.perform(
//                delete("/users/{id}", user.getId()))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).findById(user.getId());
//        verifyNoMoreInteractions(studentService);
//    }
//
//    // =========================================== CORS Headers ===========================================
//
    @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    
     * converts a Java object into JSON representation
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
*/