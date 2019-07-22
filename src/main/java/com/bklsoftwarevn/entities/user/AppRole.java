package com.bklsoftwarevn.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class AppRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private boolean status;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appRoles")
    private List<AppUser> appUsers = new ArrayList<>();
}