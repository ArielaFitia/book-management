package com.ditto.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Schema(description = "Represents a book in the system")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @Schema(description = "Unique identifier of the book", example = "1", required = true)
    private Long id;

    @Schema(description = "The title of the book", example = "Alice's Adventures in Wonderland", required = true)
    private String title;

    @Schema(description = "The author of the book", example = "Lewis Carroll", required = true)
    private String author;

    @Schema(description = "The description of the book", example = " It is a fantastical tale about a young girl named Alice who falls down a rabbit hole into a whimsical world filled with peculiar characters and nonsensical adventures. " +
            "Alice follows a White Rabbit down a rabbit hole and finds herself in a fantastical world where she encounters a series of strange and memorable characters"
            , required = true)
    private String description;

    @Schema(description = "The date of publication of the book ", example = "1865-11-06", required = true)
    private Date publishedDate;
}
