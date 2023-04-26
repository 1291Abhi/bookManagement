package com.abhi.bookManagment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int bookID;
    private String title;
    private String author;
    private int pages;

    public Book(int bookID, String title, String author, int pages) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}
