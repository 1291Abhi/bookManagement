package com.abhi.bookManagment;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("Book doesn't exists for id:"+id);
    }
}
