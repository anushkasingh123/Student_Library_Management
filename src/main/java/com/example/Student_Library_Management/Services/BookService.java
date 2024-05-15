package com.example.Student_Library_Management.Services;

import com.example.Student_Library_Management.DTOs.BookRequestDto;
import com.example.Student_Library_Management.Models.Author;
import com.example.Student_Library_Management.Models.Book;
import com.example.Student_Library_Management.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public  String addBook(BookRequestDto bookRequestDto)
    {
        int authorId=bookRequestDto.getAuthorId();
        Author author=authorRepository.findById(authorId).get();

        Book book=new Book();
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());
        book.setAuthor(author);

        List<Book> currentBooksWritten=author.getBookWritten();
        currentBooksWritten.add(book);
        authorRepository.save(author);
        return "book added successfully";

    }

}
