package com.abhi.bookManagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public Boolean addBook(Book book) throws BookAlreadyExistsException{
        Optional<Book> bookOptional=bookRepository.getById(book.getBookID());
        if(bookOptional.isPresent()) {
            throw new BookAlreadyExistsException(book.getBookID());
        }
        return bookRepository.add(book);

    }

    public Book getBook(Integer id) throws BookNotFoundException{
        Optional<Book> bookOptional=bookRepository.getById(id);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException(id);
        }
        return bookOptional.get();
    }

    public String updateBook(int id, String title, String author, Integer page) {
//        Optional<Book> bookOptional=bookRepository.getById(id);
        try {
            Book book = getBook(id);

            if (Objects.nonNull(title))
                book.setTitle(title);
            if (Objects.nonNull(author))
                book.setAuthor(author);
            if (Objects.nonNull(page))
                book.setPages(page);
            bookRepository.add(book);
            return "Book updated";
        }catch (BookNotFoundException e){
            Book book=new Book(id,title,author,page);
            bookRepository.add(book);
            return "Book created";
        }

    }

    public void deleteBook(int id) throws BookNotFoundException{
        Optional<Book> bookOptional=bookRepository.getById(id);
        if(bookOptional.isEmpty()){
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }

    public Optional<List<Book>> getAllBooks() {
        return bookRepository.getAllBooks();
    }
}
