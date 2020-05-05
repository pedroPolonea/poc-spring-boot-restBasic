package com.example.restBasic.service;

import com.example.restBasic.entity.BookEntity;
import com.example.restBasic.repositiry.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author s2it_psilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 11/14/18 23:21 PM
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }
}
