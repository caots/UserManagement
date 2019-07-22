package com.bklsoftwarevn.service.login_social;

import com.bklsoftwarevn.entities.AppUserLogin;

public interface LoginFacebookService {

    String creatFacebookAuthorizationURL();

    void creatFacebookAccessToken(String code);

    AppUserLogin getData();

}
