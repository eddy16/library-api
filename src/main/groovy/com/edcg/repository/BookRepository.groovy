package com.edcg.repository

import com.edcg.model.Book
import org.springframework.data.repository.CrudRepository

import javax.transaction.Transactional

/**
 * Created by Edgar on 25/02/2017.
 */
@Transactional
interface BookRepository extends CrudRepository<Book,Long>{

    List<Book> findAll()

    Book findById(String id)
}