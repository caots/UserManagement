package com.bklsoftwarevn.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AppUserLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String address;
    private Byte sex;
    private int phone;
    private String email;
    private String facebookId;
    private String linkFacebook;

    public AppUserLogin(){}
}
