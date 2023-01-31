package com.distribuida.servicios;

import com.distribuida.modelos.Book;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BookServicio {
    List<Book> findAll() throws ExecutionException, InterruptedException;
    Book findOne(long id) throws ExecutionException, InterruptedException;
    long save(Book book);
    long update(long id, Book book);
    long delete(long id);
}