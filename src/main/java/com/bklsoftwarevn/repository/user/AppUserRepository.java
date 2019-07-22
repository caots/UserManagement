package com.bklsoftwarevn.repository.user;

import com.bklsoftwarevn.entities.user.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);

    AppUser findById(int id);

    @Query("select u from AppUser u where u.email= :email and u.password= :password and u.status=true")
    AppUser findAppUserLogin(@Param("email") String email, @Param("password")String password);

    List<AppUser> findAll();

    List<AppUser> findByStatus(boolean status);

    @Query("select u from AppUser u where u.status=true ")
    Page<AppUser> findAllPage(Pageable pageable);

    Page<AppUser> findByFullName(String name, Pageable pageable);

}
