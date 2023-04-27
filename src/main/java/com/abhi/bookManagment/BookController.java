package com.abhi.bookManagment;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController

public class BookController {
    Map<Integer,Book> bookMap=new HashMap<>();
    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book){
        if(bookMap.containsKey(book.getBookID())){
            return "Book with book id :"+book.getBookID()+"is already present in the database";
        }else{
            bookMap.put(book.getBookID(), book);
            return "Book with book id:"+book.getBookID()+" added successfully";
        }
    }

    @GetMapping("/find-book")
    public Book findBook(@RequestParam Integer id){
        return bookMap.get(id);
    }

    @GetMapping("/find-books/{id}")
    public Book findBookByPath(@PathVariable Integer id){
        return bookMap.get(id);
    }
    @GetMapping("/find-all-books")
    public List<Book> findAllBooks(){
        return bookMap.values().stream().toList();

    }

    @PutMapping("/update-book/{id}")
    public String updateBook(@PathVariable int id,@RequestParam(required = false) String title,@RequestParam(required = false) String author, @RequestParam(required = false) Integer page){
        Book book=bookMap.get(id);
        if(Objects.nonNull(title))
            book.setTitle(title);
        if(Objects.nonNull(author))
            book.setAuthor(author);
        if(Objects.nonNull(page))
            book.setPages(page);
        bookMap.put(id,book);
        return "Book Updated Successfully";
    }

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable int id){
        if(bookMap.containsKey(id)){
            bookMap.remove(id);
            return "book removed successfully!!";
        }else{
            return "Book not present ";
        }
    }
}
