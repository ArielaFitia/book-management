package com.ditto.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class bookDTO {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Date publishedDate;
}
