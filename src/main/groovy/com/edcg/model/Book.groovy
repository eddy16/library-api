package com.edcg.model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Edgar on 24/02/2017.
 */
@Canonical
@Entity
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String name
    String author

    Book(){
    }

    Book(Long id){
        this.id=id
    }

    Book(String name, String author){
        this.name=name;
        this.author=author;
    }

}
