package com.bklsoftwarevn.service.user;

import com.bklsoftwarevn.entities.json_payload.RegisterForm;
import com.bklsoftwarevn.entities.user.AppUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppUserService {


    AppUser findByEmail(String email);

    AppUser findByEmailAndPassword(String email, String password);

    AppUser findById(int id);

    List<AppUser> findAll();

    boolean saveAppUser(AppUser appUser);

    boolean saveAppUser(RegisterForm registerForm);

    boolean deleteAppUser(AppUser appUser);

    List<AppUser> findAllPage(Pageable pageable);
}
