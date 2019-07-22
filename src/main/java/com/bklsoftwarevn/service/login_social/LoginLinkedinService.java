package com.bklsoftwarevn.service.login_social;

public interface LoginLinkedinService {

    String creatLinkedinAuthorizationURL();

    void creatLinkedinAccessToken(String code);

    String getData();
}
