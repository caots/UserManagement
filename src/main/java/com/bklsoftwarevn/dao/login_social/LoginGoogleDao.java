package com.bklsoftwarevn.dao.login_social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;


import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class LoginGoogleDao {

    private static final Logger LOG = Logger.getLogger(LoginGoogleDao.class.getName());

    @Autowired
    private Environment env;

    private String accessToken;

    public String creatGoogleAuthorizationURL() {
        try {
            GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
                    env.getProperty("google.app.id"), env.getProperty("google.app.secret"));
            OAuth2Operations oAuth2Operations = googleConnectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(env.getProperty("google.redirect.uri"));
            oAuth2Parameters.setScope("https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url google exeption: {0}", ex.getMessage());
        }
        return null;

    }


    public void creatGoogleAccessToken(String code) {
        try {
            GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(
                    env.getProperty("google.app.id"), env.getProperty("google.app.secret"));
            AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(
                    code, env.getProperty("google.redirect.uri"), null);
            accessToken = accessGrant.getAccessToken();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat access token google exeption: {0}", ex.getMessage());
        }
    }

    public String getData() {
        Google google = new GoogleTemplate(accessToken);
        Person googleUser = google.plusOperations().getGoogleProfile();
        String email = googleUser.getAccountEmail();
        String name = googleUser.getDisplayName();
        String imageUrl = googleUser.getImageUrl();
        return "Email: " + email + "____  Name:" + name + "____  ImageUrl: " + imageUrl;
    }
}
