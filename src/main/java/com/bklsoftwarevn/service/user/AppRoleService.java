package com.bklsoftwarevn.service.user;

import com.bklsoftwarevn.entities.user.AppRole;

import java.util.List;

public interface AppRoleService {

    List<AppRole> findAll();

    AppRole findByName(String name);

}
