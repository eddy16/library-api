package com.edcg.api

import com.edcg.model.Book
import com.edcg.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Edgar on 25/02/2017.
 */
@RestController
@RequestMapping(value = "/book")
@Scope("request")
class BookController {

    @Autowired
    BookRepository bookRepository

    @RequestMapping(method = RequestMethod.GET)
    def getAll(){
        return bookRepository.findAll()
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    def getById(@PathVariable long id){
        return bookRepository.findById(id)
    }

    @RequestMapping(method = RequestMethod.POST)
    def save(@RequestBody Book book) {
        return bookRepository.save(book)
    }

    @RequestMapping(method =  RequestMethod.POST)
    def save(@RequestBody List<Book> books){
        return bookRepository.save(books)
    }

    @RequestMapping(method = RequestMethod.PUT)
    def update(@RequestBody Book book){
        return bookRepository.save(book)
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    def delete(@PathVariable long id){
        bookRepository.delete(id)
    }
}
