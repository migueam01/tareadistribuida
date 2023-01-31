package com.distribuida.servicios;

import com.distribuida.db.Author;
import com.distribuida.repository.AuthorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AuthorServiceImpl implements AuthorService  {

    @Inject
    AuthorRepository authorRepository;

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll().stream().toList();
    }

    @Override
    public Author findAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    @Transactional
    public void insertAuthor(Author author) {
        authorRepository.persist(author);
    }

    @Override
    @Transactional
    public void updateAuthor(long id, Author author) {
        Author authorDb = authorRepository.findById(id);
        if (authorDb != null) {
            authorDb.setFirstName(author.getFirstName());
            authorDb.setLastName(author.getLastName());
            authorRepository.persistAndFlush(authorDb);
        }
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        Author authorDb = authorRepository.findById(id);
        if (authorDb != null) {
            authorRepository.delete(authorDb);
        }
    }
}
