package com.example.resource;

import com.example.core.Author;

import com.example.db.AuthorDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
public class authorResource {

    private final AuthorDAO authorDAO;

    public authorResource(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }



    @POST
    @UnitOfWork
    public void create(Author author){
        authorDAO.create(author);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Author> findById(@PathParam("id") Long id){
        return authorDAO.findById(id);
    }
    @GET
    @UnitOfWork
    public List<Author> findAll(){
        return authorDAO.findAll();
    }


    @PUT
    @Path("/updateName/{ id}")
    @UnitOfWork
    public void updateName(@PathParam("id") Long id,String name){
        Optional<Author> author=findById(id);
        if(author.isPresent()){
            authorDAO.updateName(author.get(),name);
        }
    }
}