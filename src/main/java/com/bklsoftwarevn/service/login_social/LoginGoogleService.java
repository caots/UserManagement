package com.bklsoftwarevn.service.login_social;

public interface LoginGoogleService {

    String creatGoogleAuthorizationURL();

    void creatGoogleAccessToken(String code);

    String getData();

}
