package client.servicios;

import client.modelos.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    Book findById(String id);
    void save(Book album);
    void delete(Integer id);
    void update(Integer id, Book album);

}
