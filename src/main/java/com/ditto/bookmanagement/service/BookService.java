package com.ditto.bookmanagement.service;

import com.ditto.bookmanagement.dto.BookDTO;
import com.ditto.bookmanagement.model.Book;
import com.ditto.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setDescription(bookDTO.getDescription());
        book.setPublishedDate(bookDTO.getPublishedDate());

        Book newBook = bookRepository.save(book);

        BookDTO newBookDTO = new BookDTO();
        newBookDTO.setTitle(newBook.getTitle());
        newBookDTO.setAuthor(newBook.getAuthor());
        newBookDTO.setDescription(newBook.getDescription());
        newBookDTO.setPublishedDate(newBook.getPublishedDate());

        return newBookDTO;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(book.getTitle());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setDescription(book.getDescription());
            bookDTO.setPublishedDate(book.getPublishedDate());
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPublishedDate(book.getPublishedDate());
        return bookDTO;
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setPublishedDate(bookDTO.getPublishedDate());

        bookRepository.save(existingBook);

        BookDTO updateBookDTO = new BookDTO();
        updateBookDTO.setTitle(existingBook.getTitle());
        updateBookDTO.setAuthor(existingBook.getAuthor());
        updateBookDTO.setDescription(existingBook.getDescription());
        updateBookDTO.setPublishedDate(existingBook.getPublishedDate());

        return updateBookDTO;
    }

    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.delete(existingBook);
    }
}