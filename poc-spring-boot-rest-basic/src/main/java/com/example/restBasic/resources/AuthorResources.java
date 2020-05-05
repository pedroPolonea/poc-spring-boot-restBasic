package com.example.restBasic.resources;

import javax.validation.Valid;

import com.example.restBasic.dto.AuthorDTO;
import com.example.restBasic.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(
        tags = "Author",
        description="This class is not covered by Spring security.",
        value="/v1"
        )
@RestController
@RequestMapping(value = "v1", produces = "application/json")
public class AuthorResources {

    @Autowired
    private AuthorService authorService;

    @ApiOperation(
            value = "Get all Author.",
            response = AuthorDTO.class)
    @GetMapping(path = "authors")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(authorService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get for Author.")
    @GetMapping(path = "authors/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id){
        return new ResponseEntity<>(authorService.getById(id), HttpStatus.OK);
    }

    @GetMapping(path = "authors/pageable")
    @ApiOperation(value = "Return a list with all author", response = AuthorDTO[].class)
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAll(Pageable pageable){
        return new ResponseEntity<>(authorService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get save Author.", response = AuthorDTO.class)
    @PostMapping(path = "authors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.create(authorDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Get update Author.")
    @PutMapping(path = "authors")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?> update(@Valid @RequestBody AuthorDTO authorDTO){
        return new ResponseEntity<>(authorService.update(authorDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Author.", hidden = true)
    @DeleteMapping(path = "authors/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
