package com.example.restBasicClient;

import com.example.restBasicClient.dto.AuthorDTO;
import com.example.restBasicClient.exampleclient.AuthorClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorClientTests {

    @Autowired
    private AuthorClient authorClient;

    @Before
    public void setUp(){}

    @Test
    public void shouldGetAuthor() {

    }

    @Test
    public void shouldPostAuthor(){

    }
}
