package com.bklsoftwarevn.dao.login_social;

import com.bklsoftwarevn.service.login_social.LoginLinkedinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.social.linkedin.api.impl.LinkedInTemplate;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;


import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class LoginLinkedinDao  {

    @Autowired
    private Environment env;

    private static final Logger LOG = Logger.getLogger(LoginLinkedinDao.class.getName());

    private String accessToken;

    public String creatLinkedinAuthorizationURL() {
        try {
            LinkedInConnectionFactory linkedInConnectionFactory = new LinkedInConnectionFactory(
                    env.getProperty("linkedin.app.id"), env.getProperty("linkedin.app.secret"));
            OAuth2Operations oAuth2Operations = linkedInConnectionFactory.getOAuthOperations();
            OAuth2Parameters oAuth2Parameters = new OAuth2Parameters();
            oAuth2Parameters.setRedirectUri(env.getProperty("linkedin.redirect.uri"));
            oAuth2Parameters.setScope("r_fullprofile%20r_emailaddress%20w_share");
            oAuth2Parameters.setState("DCEeFWf45A53sdfKef424");
            return oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat url linkedin exeption: {0}", ex.getMessage());
        }
        return null;
    }

    public void creatLinkedinAccessToken(String code) {

        try {
            LinkedInConnectionFactory linkedInConnectionFactory = new LinkedInConnectionFactory(
                    env.getProperty("linkedin.app.id"), env.getProperty("linkedin.app.secret"));
            AccessGrant grant = linkedInConnectionFactory.getOAuthOperations().exchangeForAccess(code, env.getProperty("linkedin.redirect.uri"), null);
            accessToken = grant.getAccessToken();
            System.out.println(accessToken);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "creat Token Linkedin exeption: {0}", ex.getMessage());
        }

    }

    public String getData() {
        try {
            LinkedIn linkedIn = new LinkedInTemplate(accessToken);
            LinkedInProfileFull linkedInProfileFull = linkedIn.profileOperations().getUserProfileFull();
            String email = linkedInProfileFull.getEmailAddress();
            String name = linkedInProfileFull.getFirstName() + linkedInProfileFull.getLastName();
            String imageUrl = linkedInProfileFull.getProfilePictureUrl();
            return "Email: " + email + "____  Name:" + name + "____  ImageUrl: " + imageUrl;
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "get data linkedin exeption: {0}", ex.getMessage());
        }
        return null;
    }

}
