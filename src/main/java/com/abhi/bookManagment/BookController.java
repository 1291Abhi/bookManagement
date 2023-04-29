package com.abhi.bookManagment;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController

public class BookController {
    private BookService bookService=new BookService();
    @PostMapping("/add-book")
    public ResponseEntity addBook(@RequestBody Book book){
        try{
            bookService.addBook(book);
            return new ResponseEntity("Book with book id:"+book.getBookID()+" added successfully", HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            return new ResponseEntity("Book is already present",HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }

    }

    @GetMapping("/find-book")
    public ResponseEntity findBook(@RequestParam Integer id){
        try{
            Book book=bookService.getBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        }catch(BookNotFoundException e){
            return new ResponseEntity("Book not found",HttpStatus.valueOf(404));
        }catch(Exception e){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }

    @GetMapping("/find-books/{id}")
    public ResponseEntity findBookByPath(@PathVariable Integer id){
        try{
            Book book=bookService.getBook(id);
            return new ResponseEntity(book,HttpStatus.OK);
        }catch(BookNotFoundException e){
            return new ResponseEntity("Book not found",HttpStatus.valueOf(404));
        }catch(Exception e){
            return new ResponseEntity("Something went wrong",HttpStatus.valueOf(500));
        }
    }
    @GetMapping("/find-all-books")
    public ResponseEntity findAllBooks(){
        Optional<List<Book>> optionalBookList=bookService.getAllBooks();
        if(optionalBookList.isEmpty())
            return new ResponseEntity("No book found",HttpStatus.NOT_FOUND);
        List<Book> bookList=optionalBookList.get();
        return new ResponseEntity(bookList,HttpStatus.OK);

    }

    @PutMapping("/update-book/{id}")
    public String updateBook(@PathVariable int id,@RequestParam(required = false) String title,@RequestParam(required = false) String author, @RequestParam(required = false) Integer page){
       try{
           String response=bookService.updateBook(id,title,author,page);
             return response;
       }catch (Exception ex){
           return "Exception Occurred";
       }

    }

    @DeleteMapping("/remove-book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity("Book is removed successfully",HttpStatus.OK);

        }catch(BookNotFoundException e){
            return new ResponseEntity("Book not found",HttpStatus.NOT_FOUND);
        }
    }
}
