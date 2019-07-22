package com.bklsoftwarevn.service_impl.login_social;

import com.bklsoftwarevn.dao.login_social.LoginFacebookDao;
import com.bklsoftwarevn.entities.AppUserLogin;
import com.bklsoftwarevn.service.login_social.LoginFacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginFacebookService_Impl implements LoginFacebookService {

    @Autowired
    private LoginFacebookDao loginFacebookDao;

    @Override
    public String creatFacebookAuthorizationURL() {
        return loginFacebookDao.creatFacebookAuthorizationURL();
    }

    @Override
    public void creatFacebookAccessToken(String code) {
        loginFacebookDao.creatFacebookAccessToken(code);
    }

    @Override
    public AppUserLogin getData() {
        return loginFacebookDao.getData();
    }
}
