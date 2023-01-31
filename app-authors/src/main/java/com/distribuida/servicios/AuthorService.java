package com.distribuida.servicios;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {

    List<Author> listAllAuthors();

    Author findAuthor(Long id);

    void insertAuthor(Author author);

    void updateAuthor(long id, Author author) throws Exception;

    void deleteAuthor(Long id);

}
