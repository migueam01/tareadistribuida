package com.distribuida.modelos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private long id;
    private String firstName;
    private String lastName;
}
