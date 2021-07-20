package com.example.core;

import javax.persistence.*;

@Entity
@Table(name="Author")
@NamedQueries({
        @NamedQuery(
                name="com.example.core.Author.findAll",
                query="SELECT p FROM Author p"
        ),
        @NamedQuery(
                name="com.example.core.Author.updateName",
                query="Update Author a SET a.name= :name Where a.id= :id"
        )
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name",nullable = false)
    String name;

    public Author() {

    }

    public Author(String name) {
        this.name = name;
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
