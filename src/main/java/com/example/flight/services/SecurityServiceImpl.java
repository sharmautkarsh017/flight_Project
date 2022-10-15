package com.example.flight.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SecurityServiceImpl implements SecurityService{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public boolean login(String username, String password) {
       UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        boolean result=token.isAuthenticated();
        if(result)
        {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return result;
    }
}
