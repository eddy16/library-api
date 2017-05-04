package com.edcg.model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Edgar on 03/05/2017.
 */
@Entity
@Canonical
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String username
    String password

    Account() {
    }

    Account(String user, String pass) {
        this.username = user
        this.password = pass
    }
}
