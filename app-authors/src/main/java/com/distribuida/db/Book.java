package com.distribuida.db;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    public static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String isbn;
    private String title;
    private double price;
}
