package com.ditto.bookmanagement.service;

import com.ditto.bookmanagement.dto.BookDTO;
import com.ditto.bookmanagement.model.Book;
import com.ditto.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
@Autowired
private BookRepository bookRepository;

public BookDTO getBookById(Long id) {
   Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found") );
    BookDTO bookDTO = new BookDTO();
    bookDTO.setTitle(book.getTitle());
    bookDTO.setAuthor(book.getAuthor());
    return bookDTO;
}

public List<BookDTO> getAllBooks() {
    BookDTO bookDTO = new BookDTO();
   return

}

public BookDTO addBook(BookDTO bookDTO){
    Book book = new Book();
    book.setAuthor(bookDTO.getAuthor());
    book.setTitle(bookDTO.getTitle());

    Book newBook =bookRepository.save(book);

    BookDTO newBookDTO = new BookDTO();
    newBookDTO.setAuthor(newBook.getAuthor());
    newBookDTO.setTitle(newBook.getTitle());

    return newBookDTO;

}

public BookDTO updateBook (Long id ,BookDTO bookDTO){
    Book existingBook= bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    existingBook.setAuthor(bookDTO.getAuthor());
    existingBook.setTitle(bookDTO.getTitle());

    bookRepository.save(existingBook);

    BookDTO updateBookDTO= new BookDTO();
    updateBookDTO.setId(existingBook.getId());
    updateBookDTO.setAuthor(existingBook.getAuthor());
    updateBookDTO.setTitle(existingBook.getTitle());

    return updateBookDTO; }

 public void deleteBook(Long id){
     Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
     bookRepository.delete(existingBook);

 }


}