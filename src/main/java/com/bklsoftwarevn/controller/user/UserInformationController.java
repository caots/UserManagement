package com.bklsoftwarevn.controller.user;


import com.bklsoftwarevn.common.MD5;
import com.bklsoftwarevn.entities.user.AppUser;
import com.bklsoftwarevn.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@RolesAllowed("ROLE_USER")
public class UserInformationController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/my-profile")
    public ResponseEntity<AppUser> myProfile(HttpServletRequest request,
                                             HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String username = request.getUserPrincipal().getName();
        if (username == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        AppUser appUser = appUserService.findByEmail(username);
        if (appUser == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestParam("old") String oldPassword,
                                                 @RequestParam("new") String newPassword,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String username = request.getUserPrincipal().getName();
        if (username == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        AppUser appUser = appUserService.findByEmail(username);
        if (appUser == null) return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        if (!appUser.getPassword().equals(MD5.encode(oldPassword)))
            return new ResponseEntity<>("password is not correct", HttpStatus.BAD_REQUEST);
        appUser.setPassword(MD5.encode(newPassword));
        if (appUserService.saveAppUser(appUser))
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        else return new ResponseEntity<>("change password error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/change-profile")
    public ResponseEntity<Object> changeProfile(@RequestBody AppUser appUser,
                                                HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (appUserService.saveAppUser(appUser))
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        else return new ResponseEntity<>("change profile error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Object> getProfile(@PathVariable int id,
                                             HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findById(id);
        if (appUser == null) return new ResponseEntity<>("no user found", HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(appUser, HttpStatus.OK);
    }
}
