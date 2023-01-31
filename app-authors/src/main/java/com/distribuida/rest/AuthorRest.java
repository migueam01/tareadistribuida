package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.servicios.AuthorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/author")
public class AuthorRest {

    @Inject
    AuthorService authorService;

    @GET
    @Produces("application/json")
    public List<Author> listAll() {
        return authorService.listAllAuthors();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Author getOne(@PathParam("id") Long id) {
        return authorService.findAuthor(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(Author author) {authorService.insertAuthor(author);
        authorService.insertAuthor(author);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") long id, Author author) throws Exception {
        authorService.updateAuthor(id, author);
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        authorService.deleteAuthor(id);
        return Response.status((Response.Status.OK)).build();
    }

}
