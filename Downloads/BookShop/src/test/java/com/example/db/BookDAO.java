package com.example.db;

import com.example.core.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class BookDAO extends AbstractDAO<Book> {



    public BookDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public void create(Book book){
        persist(book);
    }
    public Optional<Book> findById(Long id){
        return Optional.ofNullable(get(id));
    }
    public List<Book> findAll(){
        return list(namedTypedQuery("com.example.core.Book.findAll"));
    }
    public List<Book> getAuthorsBooks(Long id){
        return list(namedTypedQuery("com.example.core.Book.authorBooks").setParameter("id",id));
    }



}
