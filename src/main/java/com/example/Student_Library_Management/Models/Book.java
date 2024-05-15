package com.example.Student_Library_Management.Models;

import com.example.Student_Library_Management.Enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int pages;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Card card;

    private boolean issued;

    @OneToMany(mappedBy ="book",cascade = CascadeType.ALL)
    List<Transactions> listOfTransaction=new ArrayList<>();

    public List<Transactions> getListOfTransaction() {
        return listOfTransaction;
    }

    public void setListOfTransaction(List<Transactions> listOfTransaction) {
        this.listOfTransaction = listOfTransaction;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
