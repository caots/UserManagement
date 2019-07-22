package com.bklsoftwarevn.controller.login_social;

import com.bklsoftwarevn.service.login_social.LoginLinkedinService;
import com.bklsoftwarevn.service_impl.login_social.LoginLinkedinService_Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login-linkedin")
public class LoginLinkedinController {

    @Autowired
    private LoginLinkedinService linkedinService;


    @GetMapping("/create-linkedin-authorization")
    public String creatLinkedinAuthorization() {
        return linkedinService.creatLinkedinAuthorizationURL();

    }

    @GetMapping("/connect-linkedin")
    public void createLinkedinAccessToken(@RequestParam("oauth_token") String code) {
        linkedinService.creatLinkedinAccessToken(code);
    }

    @GetMapping(value = "/get-data-linkedin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() {
        return new ResponseEntity<>(linkedinService.getData(), HttpStatus.OK);
    }
}
