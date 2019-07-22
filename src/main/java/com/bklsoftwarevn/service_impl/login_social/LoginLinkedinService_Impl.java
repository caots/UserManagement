package com.bklsoftwarevn.service_impl.login_social;

import com.bklsoftwarevn.dao.login_social.LoginLinkedinDao;
import com.bklsoftwarevn.service.login_social.LoginLinkedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginLinkedinService_Impl implements LoginLinkedinService {

    @Autowired
    private LoginLinkedinDao linkedinDao;

    @Override
    public String creatLinkedinAuthorizationURL() {
        return linkedinDao.creatLinkedinAuthorizationURL();
    }

    @Override
    public void creatLinkedinAccessToken(String code) {
        linkedinDao.creatLinkedinAccessToken(code);
    }

    @Override
    public String getData() {
        return linkedinDao.getData();
    }
}
