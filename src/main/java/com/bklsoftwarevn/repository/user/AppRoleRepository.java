package com.bklsoftwarevn.repository.user;

import com.bklsoftwarevn.entities.user.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    List<AppRole> findAll();

    AppRole findByName(String name);

}
