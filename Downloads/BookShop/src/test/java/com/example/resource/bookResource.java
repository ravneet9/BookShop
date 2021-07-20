package com.example.resource;


import com.example.core.Book;
import com.example.db.BookDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class bookResource {
    private final BookDAO bookDAO;

    public bookResource(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @POST
    @UnitOfWork
    public void create(Book book){
        bookDAO.create(book);
    }

    @GET
    @Path("/{ id }")
    @UnitOfWork
    public Optional<Book> findById(@PathParam("id") Long id){
        return bookDAO.findById(id);
    }

    @GET
    @UnitOfWork
    public List<Book> findAll(){

        return bookDAO.findAll();
    }


    @GET
    @Path("/authorBooks/{ id }")
    @UnitOfWork
    public List<Book> getAuthorsBooks(@PathParam("id") Long id){
        return bookDAO.getAuthorsBooks(id);
    }

}
