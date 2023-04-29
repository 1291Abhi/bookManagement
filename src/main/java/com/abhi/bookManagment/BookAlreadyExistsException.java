package com.abhi.bookManagment;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(int id){
        super("Book with id:"+id+" is already present in database");
    }
}
