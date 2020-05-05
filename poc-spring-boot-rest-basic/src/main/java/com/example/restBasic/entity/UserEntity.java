package com.example.restBasic.entity;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.example.restBasic.entity.enumeration.RolesEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login", unique = true)
    @NotEmpty
    private String login;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "active")
    @NotEmpty
    private Boolean active;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = RolesEnum.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "user_roles",
            uniqueConstraints= @UniqueConstraint(columnNames = {"user_id", "role"})
    )
    @Column(name = "role", nullable = false)
    private Collection<RolesEnum> Roles;

}
