package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.StudentUpdateMobRequestDto;
import com.example.Student_Library_Management.Enums.CardStatus;
import com.example.Student_Library_Management.Models.Card;
import com.example.Student_Library_Management.Models.Student;
import com.example.Student_Library_Management.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student)
    {
        Card card=new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudentVariableName(student);

        student.setCard(card);

        studentRepository.save(student);

        return "Student and Card added";
    }
    public String findNameByEmail(String email)
    {
        Student student =studentRepository.findByEmail(email);
        return student.getName();
    }
    public String updateMobNo(StudentUpdateMobRequestDto studentReq)
    {
        Student originalStudent=studentRepository.findById(studentReq.getId()).get();
        originalStudent.setMobNo((studentReq.getMobNo()));
        studentRepository.save(originalStudent);
        return  "student has been update successfully";

    }


}
