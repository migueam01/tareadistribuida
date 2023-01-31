package com.distribuida.controller;

import com.distribuida.modelos.Book;
import com.distribuida.servicios.BookServicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static jakarta.ws.rs.core.Response.Status.ACCEPTED;

@ApplicationScoped
@Path("api/book")
public class BookController {
    @Inject
    private BookServicio bookServicio;

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Book findOneById(@PathParam("id") long id) throws ExecutionException, InterruptedException {
        return this.bookServicio.findOne(id);
    }

    @GET
    @Produces("application/json")
    public Response findAll() throws ExecutionException, InterruptedException {
        return Response.status(ACCEPTED).entity(bookServicio.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Long> update(@PathParam("id") long id, Book book) {
        return Map.of("rowsChanged", this.bookServicio.update(id, book));
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Map<String, Long> insert(Book book) {
        return Map.of("rowsChanged", this.bookServicio.save(book));
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/json")
    public Map<String, Long> delete(@PathParam("id") long id) {
        return Map.of("rowsChanged", this.bookServicio.delete(id));
    }
}
