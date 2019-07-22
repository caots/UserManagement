package com.bklsoftwarevn.controller.login_social;


import com.bklsoftwarevn.entities.AppUserLogin;
import com.bklsoftwarevn.service.login_social.LoginFacebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("login-facebook")
public class LoginFacebookController {

    @Autowired
    private LoginFacebookService facebookService;


    @GetMapping("/create-facebook-authorization")
    public String creatFacebookAuthorization() {
        return facebookService.creatFacebookAuthorizationURL();

    }

    @GetMapping("/connect-facebook")
    public void createFacebookAccessToken(@RequestParam("code") String code) {
        facebookService.creatFacebookAccessToken(code);
    }

    @GetMapping(value = "/get-data-facebook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getData() {

        AppUserLogin userFb = facebookService.getData();

        if (userFb != null) {
            return new ResponseEntity<>(userFb, HttpStatus.OK);
        }
        return new ResponseEntity<>("Empty Data", HttpStatus.NO_CONTENT);
    }
}
