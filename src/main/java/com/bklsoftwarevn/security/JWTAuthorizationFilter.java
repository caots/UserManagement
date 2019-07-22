package com.bklsoftwarevn.security;

import com.bklsoftwarevn.entities.user.AppUser;
import com.bklsoftwarevn.service_impl.JWTService;
import com.bklsoftwarevn.service_impl.user.AppUserService_Impl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private AppUserService_Impl appUserService;

    private JWTService jwtService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, AppUserService_Impl appUserService, JWTService jwtService) {
        super(authenticationManager);
        this.appUserService = appUserService;
        this.jwtService = jwtService;
    }

    // Xác nhận dua user vao he thong
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if (header != null && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } else {
            System.out.println("no authorization");
            chain.doFilter(request, response);
        }
    }

    //  read token và cấp quyền
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            String username = jwtService.decode(token);
            if (username != null) {
                AppUser appUser = appUserService.findByEmail(username);
                System.out.println("Email Principal: " + appUser.getEmail());
                return new UsernamePasswordAuthenticationToken(username, null, appUser.grantedAuthorities());
            }
            return null;
        }
        return null;
    }


}
