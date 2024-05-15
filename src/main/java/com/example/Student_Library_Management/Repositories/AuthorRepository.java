package com.example.Student_Library_Management.Repositories;

import com.example.Student_Library_Management.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
