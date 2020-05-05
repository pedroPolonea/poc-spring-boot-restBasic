package com.example.restBasic.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author s2it_psilva
 * @version $Revision: $<br/>
 * $Id: $
 * @since 11/14/18 23:21 PM
 */
@Data
@Entity(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idBook")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "numberPages")
    private Integer numberPages;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_author",
            joinColumns = { @JoinColumn(name = "idBook") },
            inverseJoinColumns = { @JoinColumn(name = "idAuthor") })
    private List<AuthorEntity> authors;
}
