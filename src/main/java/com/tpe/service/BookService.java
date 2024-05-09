package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.exceptions.BookNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();

    }

    //3-b
    public Book getBookById(Long identity) {

        Book book = bookRepository.findById(identity).
                orElseThrow(() -> new BookNotFoundException("Kitap bulunamadı, ID : " + identity));
        return book;
    }

    public void deleteBookById(Long id) {
       Book book = getBookById(id);
       //bookRepository.delete(book);
        bookRepository.deleteById(id);
    }

    public List<Book> filterBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }


    public void deleteBookByTitle(String title) {
        Book book = getBookByTitle(title);
        bookRepository.delete(book);
    }

    public Book getBookByTitle(String title) {
        Book book = bookRepository.foundByName(title);
        return book;
    }

    public List<Book> filterBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
}
