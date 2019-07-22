package com.bklsoftwarevn.service_impl.login_social;

import com.bklsoftwarevn.dao.login_social.LoginGoogleDao;
import com.bklsoftwarevn.service.login_social.LoginGoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginGoogleService_Impl implements LoginGoogleService {

    @Autowired
    private LoginGoogleDao googleDao;

    @Override
    public String creatGoogleAuthorizationURL() {
        return googleDao.creatGoogleAuthorizationURL();
    }

    @Override
    public void creatGoogleAccessToken(String code) {
        googleDao.creatGoogleAccessToken(code);
    }

    @Override
    public String getData() {
        return googleDao.getData();
    }
}
