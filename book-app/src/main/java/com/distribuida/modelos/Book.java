package com.distribuida.modelos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private Author author;
    private String isbn;
    private String title;
    private double price;
}
