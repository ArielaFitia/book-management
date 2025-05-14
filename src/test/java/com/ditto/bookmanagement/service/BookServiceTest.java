package com.ditto.bookmanagement.service;

import com.ditto.bookmanagement.dto.BookDTO;
import com.ditto.bookmanagement.model.Book;
import com.ditto.bookmanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setDescription("Description");
        book.setPublishedDate(LocalDate.of(2020, 1, 1));

        bookDTO = new BookDTO();
        bookDTO.setTitle("Title");
        bookDTO.setAuthor("Author");
        bookDTO.setDescription("Description");
        bookDTO.setPublishedDate(LocalDate.of(2020, 1, 1));
    }

    @Test
    void addBook_ShouldReturnSavedBookDTO(){
        //Arrange
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        //Act
        BookDTO result = bookService.addBook(bookDTO);
        //Assert
        assertNotNull(result);
        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void getAllBooks_ShouldReturnListOfBookDTOs(){
        Book book1 = new Book();
        book1.setId(2L);
        book1.setTitle("Title1");

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book, book1));

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        assertEquals(book1.getId(), result.get(1).getId());
        assertEquals(book1.getTitle(), result.get(1).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_WithValidId_ShouldReturnBookDTO(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(book.getId(), result.getId());
        assertEquals(book.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_WithInvalidId_ShouldReturnBookDTO(){
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.getBookById(3L));
        verify(bookRepository, times(1)).findById(3L);
    }

    @Test
    void updateBook_WithValidId_ShouldUpdateBookDTO(){
        BookDTO updatedBookDTO = new BookDTO();
        updatedBookDTO.setTitle("UpdatedTitle");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO result = bookService.updateBook(1L, updatedBookDTO);

        assertNotNull(result);
        assertEquals(updatedBookDTO.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void deleteBook_WithValidId_ShouldDeleteBookDTO(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(book);
    }
}
