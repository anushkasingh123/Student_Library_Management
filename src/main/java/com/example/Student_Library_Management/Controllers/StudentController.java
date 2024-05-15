package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management.Models.Student;
import com.example.Student_Library_Management.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add-student")
    public String createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }
    @GetMapping("/get_User")
    public String getNameByEmail(@RequestParam("email") String email)
    {
        return studentService.findNameByEmail(email);

    }
    @PutMapping("/update")
    public String updateMob(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto)
    {
        return studentService.updateMobNo(studentUpdateMobRequestDto);
    }

}
