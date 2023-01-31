package client.servicios;

import client.modelos.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();
    Author findById(String id);
    void save(Author singer);
    void delete(Integer id);
    void update(Integer id, Author singer);

}
