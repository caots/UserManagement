package com.bklsoftwarevn.dao.login_social;


import com.bklsoftwarevn.entities.AppUserLogin;
import com.bklsoftwarevn.service.login_social.LoginFacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Repository;

import java.util.logging.Level;
import java.util.logging.Logger;


@Repository
public class LoginFacebookDao  {

    private static final Logger LOG = Logger.getLogger(LoginFacebookDao.class.getName());

    @Autowired
    private Environment env;


    private String accessToken;

    public String creatFacebookAuthorizationURL() {
        try {

            FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(
                    env.getProperty("facebook.app.id")
                    , env.getProperty("facebook.app.secret"));
            OAuth2Operations oAuth2Operations = connectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(env.getProperty("facebook.redirect.uri"));
            oAuth2Parameters.setScope("public_profile,email");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url facebook exception: {0}", ex.getMessage());
        }
        return null;
    }


    public void creatFacebookAccessToken(String code) {
        try {
            FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(
                    env.getProperty("facebook.app.id"),
                    env.getProperty("facebook.app.secret"));
            AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, env.getProperty("facebook.redirect.uri"), null);
            accessToken = accessGrant.getAccessToken();
            System.out.println(accessToken);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat Token Facebook exception: {0}", ex.getMessage());
        }

    }


    public AppUserLogin getData() {
        try {
            Facebook facebook = new FacebookTemplate(accessToken);
            User user = facebook.fetchObject("me", User.class);
            AppUserLogin appUser = new AppUserLogin();
            appUser.setName(user.getName());
            appUser.setEmail(user.getEmail());
            appUser.setFacebookId(user.getId());
            appUser.setLinkFacebook(user.getLink());
            return appUser;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get data Facebook exeption: {0}", ex.getMessage());
        }
        return null;
    }
}
