package com.example.db;

import com.example.core.Author;
import com.example.core.Book;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.SQLUpdate;

import javax.xml.ws.BindingType;
import java.util.List;
import java.util.Optional;

public class AuthorDAO extends AbstractDAO<Author> {

    public AuthorDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public void create(Author author){
        persist(author);
    }
    public Optional<Author> findById(Long id){
        return Optional.ofNullable(get(id));
    }
    public List<Author> findAll(){
        return list(namedTypedQuery("com.example.core.Author.findAll"));
//        return findAll();
    }
    public void updateName(Author author, String name){
           author.setName(name);
          currentSession().update(author);

        //        namedTypedQuery("com.example.core.Author.updateName").setParameter("name",name).setParameter("id",id);
    }


}
