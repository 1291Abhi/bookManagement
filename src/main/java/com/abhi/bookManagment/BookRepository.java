package com.abhi.bookManagment;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class BookRepository {
    private Map<Integer,Book> bookMap=new HashMap<>();

    public Boolean add(Book book) {

        bookMap.put(book.getBookID(), book);
        return true;

    }

    public Optional<Book> getById(int bookID) {
        if(bookMap.containsKey(bookID))
            return Optional.of(bookMap.get(bookID));
        return Optional.empty();
    }

    public void updateBook(Book book) {
        bookMap.put(book.getBookID(), book);
    }

    public void deleteById(int id) {
        bookMap.remove(id);
    }

    public Optional<List<Book>> getAllBooks() {
        if(bookMap.size()==0)
            return Optional.empty();
        else
            return Optional.of(bookMap.values().stream().toList());
    }
}
