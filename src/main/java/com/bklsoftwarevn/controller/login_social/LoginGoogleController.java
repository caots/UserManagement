package com.bklsoftwarevn.controller.login_social;

import com.bklsoftwarevn.service.login_social.LoginGoogleService;
import com.bklsoftwarevn.service_impl.login_social.LoginGoogleService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login-google")
public class LoginGoogleController {

    @Autowired
    private LoginGoogleService googleService;


    @RequestMapping("/create-google-authorization")
    public String creatGoogleAuthorization() {
        return googleService.creatGoogleAuthorizationURL();
    }

    @GetMapping("/connect-google")
    public void creatGoogleAccessToken(@RequestParam("code") String code) {
        googleService.creatGoogleAccessToken(code);
    }

    @GetMapping(value = "/get-data-google", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() {
        String profile = googleService.getData();
        if (profile != null && !profile.isEmpty()) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }
        return new ResponseEntity<>("Empty Data", HttpStatus.NO_CONTENT);
    }
}
