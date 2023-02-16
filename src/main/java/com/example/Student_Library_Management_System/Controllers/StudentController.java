package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.StudentUpdateMobileRequest;
import com.example.Student_Library_Management_System.Models.Student;
import com.example.Student_Library_Management_System.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add_student")
    public String createStudent(@RequestBody Student student) throws Exception{

        try {
            return studentService.createStudent(student);
        }catch (Throwable e){
            e.printStackTrace(System.out);
        }

        return "Entry not added";
    }

    @GetMapping("/get-student-name--by-email")
    public String getNameByEmail(@RequestParam("email")String email){
        return studentService.findNameByEmail(email);
    }

    @PutMapping("/update-mobile")
    public String updateMobile(@RequestBody StudentUpdateMobileRequest studentUpdateMobileRequest){
        return studentService.updateMobile(studentUpdateMobileRequest);
    }

    @GetMapping("/get-student-by-email")
    public Student getStudentByEmail(@RequestParam("email") String email){
        return studentService.findStudentByEmail(email);
    }
}
