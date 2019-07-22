package com.bklsoftwarevn.service_impl.user;

import com.bklsoftwarevn.common.MD5;
import com.bklsoftwarevn.entities.json_payload.RegisterForm;
import com.bklsoftwarevn.entities.user.AppRole;
import com.bklsoftwarevn.entities.user.AppUser;
import com.bklsoftwarevn.repository.user.AppRoleRepository;
import com.bklsoftwarevn.repository.user.AppUserRepository;
import com.bklsoftwarevn.service.user.AppUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppUserService_Impl implements AppUserService {

    private final static Logger LOGGER = Logger.getLogger(AppUserService_Impl.class.getName());

    private final AppUserRepository appUserRepository;

    private final AppRoleRepository appRoleRepository;


    public AppUserService_Impl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser findByEmail(String email) {
        try {
            return appUserRepository.findByEmail(email);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-user-by-email-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public AppUser findByEmailAndPassword(String email, String password) {
        try {
            AppUser appUser = appUserRepository.findAppUserLogin(email,  MD5.encode(password));
            return appUser;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-user-by-email-and-password-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public AppUser findById(int id) {
        try {
            return appUserRepository.findById(id);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-user-by-id-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public List<AppUser> findAll() {
        try {
            return appUserRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-user-error : {0}", ex.getMessage());
        }
        return null;
    }

    @Override

    public boolean saveAppUser(AppUser appUser) {
        try {
            appUserRepository.save(appUser);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-user-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean saveAppUser(RegisterForm registerForm) {

        List<AppRole> appRoles = new ArrayList<>();
        appRoles.add(appRoleRepository.findByName("ROLE_USER"));
        AppUser appUser = new AppUser();
        appUser.setEmail(registerForm.getUsername());
        appUser.setPassword(MD5.encode(registerForm.getPassword()));
        appUser.setFullName(registerForm.getFullName());
        appUser.setAppRoles(appRoles);
        appUser.setStatus(true);
        try {
            appUserRepository.save(appUser);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-user-error : {0}", ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteAppUser(AppUser appUser) {
        try {
            appUser.setStatus(false);
            appUserRepository.save(appUser);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-user-error : {0}", ex.getMessage());
            return false;
        }
    }

    @Override
    public List<AppUser> findAllPage(Pageable pageable) {

        try {
            Page<AppUser> userPage = appUserRepository.findAllPage(pageable);
            List<AppUser> users = userPage.getContent();
            users.get(1).setStatus(false); // pop admin
            return users;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-user-page-error : {0}", ex.getMessage());
        }
        return null;
    }
}
