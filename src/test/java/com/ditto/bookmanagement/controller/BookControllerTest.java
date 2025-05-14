package com.ditto.bookmanagement.controller;

import com.ditto.bookmanagement.dto.BookDTO;
import com.ditto.bookmanagement.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO1;
    private BookDTO bookDTO2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();

        bookDTO1 = new BookDTO();
        bookDTO1.setId(1L);
        bookDTO1.setTitle("Book 1");
        bookDTO1.setAuthor("Author 1");
        bookDTO1.setDescription("Description 1");
        bookDTO1.setPublishedDate(LocalDate.of(2000, 1, 1));

        bookDTO2 = new BookDTO();
        bookDTO2.setId(2L);
        bookDTO2.setTitle("Book 2");
        bookDTO2.setAuthor("Author 2");
        bookDTO2.setDescription("Description 2");
        bookDTO2.setPublishedDate(LocalDate.of(2000, 1, 2));
    }

    @Test
    void createBook_ShouldReturnCreatedBook() throws Exception {
        when(bookService.addBook(any(BookDTO.class))).thenReturn(bookDTO1);

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "Book 1",
                            "author": "Author 1",
                            "description": "Description 1",
                            "publishedDate": "2001-01-01"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Book 1"));

        verify(bookService, times(1)).addBook(any(BookDTO.class));
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() throws Exception {
        List<BookDTO> books = Arrays.asList(bookDTO1, bookDTO2);
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Book 1")))
                .andExpect(jsonPath("$[1].title", is("Book 2")));

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void getBookById_WithValidId_ShouldReturnBook() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookDTO1);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Book 1")));

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void updateBook_ShouldReturnUpdatedBook() throws Exception {
        when(bookService.updateBook(eq(1L), any(BookDTO.class))).thenReturn(bookDTO1);

        mockMvc.perform(MockMvcRequestBuilders.put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "Effective Java",
                            "author": "Joshua Bloch",
                            "description": "Updated content",
                            "publishedDate": "2001-01-01"
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

        verify(bookService, times(1)).updateBook(eq(1L), any(BookDTO.class));
    }

    @Test
    void deleteBook_ShouldReturnDeletedBook() throws Exception {
        doNothing().when(bookService).deleteBook(eq(1L));

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/1"))
                .andExpect(status().isNoContent());

        verify(bookService, times(1)).deleteBook(eq(1L));
    }
}
