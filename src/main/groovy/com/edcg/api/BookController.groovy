package com.edcg.api

import com.edcg.model.Book
import com.edcg.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Controller
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

    @RequestMapping("/create")
    def create(String name, String author) {
        Book book = null;
        try {
            book = new Book(name, author);
            bookRepository.save(book);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Book succesfully created! (id = " + book.getId() + ")";
    }

    @RequestMapping(method = RequestMethod.GET)
    def getAll(){
        return bookRepository.findAll()
    }

}
