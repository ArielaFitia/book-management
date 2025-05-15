package com.ditto.bookmanagement.controller;

import com.ditto.bookmanagement.dto.BookDTO;
import com.ditto.bookmanagement.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @Operation(summary = "Create a new book",
            description = "Add a new book to the system with the informations provided")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid data")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@Parameter(description = "Created book information") @RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }


    @Operation(summary = "Retrieve all Books",
            description = "Returns the list of the books registered in the system")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "List of books retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }


    @Operation(summary = "Retrieve a book by id ",
            description = "Returns all the details of a specific user based on their identifier")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")})
    @GetMapping("/{id}")
    public BookDTO getBookById(@Parameter(description = "Identifier of the book to retrieve", example = "1")
                               @PathVariable Long id) {
        return bookService.getBookById(id);
    }


    @Operation(summary = "Update a book",
            description = "Updates the details of an existing book based on their identifier with the information provided")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid data")})
    @PutMapping("/{id}")
    public BookDTO updateBook(@Parameter(description = "Identifier of the book to update", example = "1") @PathVariable Long id, @Parameter(description = "Updated book information") @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }


    @Operation(summary = "Delete a book",
            description = "Delete the details of a specific book based on their identifier")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@Parameter(description = "Identifier of the book to delete", example = "1") @PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
