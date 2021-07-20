package com.example.core;


import javax.persistence.*;

@Entity
@Table(name="Book")
@NamedQueries({
        @NamedQuery(
                name="com.example.core.Book.findAll",
                query="SELECT p FROM Book p"
        ),
        @NamedQuery(
                name="com.example.core.Book.authorBooks",
                query="Select b from Book b , Author a WHERE a.id = b.authorId and a.id= :id"
        )
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name",nullable = false)
    String name;

    @Column(name="authorId",nullable = false)
    long authorId;
    public Book() {

    }
    public Book(String name, long authorId) {
        this.name = name;
        this.authorId = authorId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
