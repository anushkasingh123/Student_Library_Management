package com.example.Student_Library_Management.Controllers;

import com.example.Student_Library_Management.DTOs.BookRequestDto;
import com.example.Student_Library_Management.Models.Book;
import com.example.Student_Library_Management.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/addBook")
    public String addBook(@RequestBody BookRequestDto bookRequestDto)
    {
        return bookService.addBook(bookRequestDto);
    }



}
