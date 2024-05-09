package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//Body
//@Controller//Model, View
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;

    //1- Save a Book & Return :Message
    // http://localhost:8080/books + POST + JSON format body
    @PostMapping
    public ResponseEntity<String> saveBook(@Valid @RequestBody Book book){
        service.saveBook(book);
        return new ResponseEntity<>("Book was successfully saved", HttpStatus.CREATED); //201
    }

    //READ
    //2- Get All Books, Return:List<Book>
    // http://localhost:8080/books + GET
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList = service.getAll();
        return ResponseEntity.ok(bookList); //200
    }

    //3 - Get a Book by its ID, Return: Book
    // http://localhost:8080/books/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long identity){
        Book found = service.getBookById(identity);
        return new ResponseEntity<>(found, HttpStatus.OK); //200
    }

    //4- Delete a Book by its ID
    // http://localhost:8080/books/2 + DELETE
    @DeleteMapping("/{no}")
    public ResponseEntity<String> deleteBook(@PathVariable("no") Long id){
        service.deleteBookById(id);
        return ResponseEntity.ok("Book was deleted successfully.. " + id);
    }

    //http://localhost:8080/books/q?id=2 + DELETE
    @DeleteMapping("/q")
    public ResponseEntity<String> deleteBookWithRequestParam(@RequestParam Long id){
        service.deleteBookById(id);
        return ResponseEntity.ok("Book was deleted successfully.. " + id);
    }

    @DeleteMapping("/p")
    public ResponseEntity<String> deleteBookWithTitle(@RequestParam("title") String title){
        service.deleteBookByTitle(title);
        return ResponseEntity.ok("Book was deleted successfully.. " + title);
    }


    //5 - Get a Book by its ID with RequestParam , Return:Book
    // http://localhost:8080/books/q?id=2 + GET
    @GetMapping("/q")
    public ResponseEntity<Book> getBookByIdWithQuery(@RequestParam("id") Long identity){
        Book book = service.getBookById(identity);
        return ResponseEntity.ok(book);//200
    }

    //6 - Get a Book by its Title withRequestParam
    //http://localhost:8080/books/search?title=Atomic Habits + GET
    @GetMapping("/search")
    public ResponseEntity<List<Book>> filterBooksByTitle(@RequestParam("title") String title){
        List<Book> books = service.filterBookByTitle(title);
        return ResponseEntity.ok(books);
    }

    //Get Book By Author
    //http://localhost:8080/books/query?author=George Orwell + GET
    @GetMapping("/query")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
        List<Book> books = service.filterBooksByAuthor(author);
        return ResponseEntity.ok(books);

    }



}
