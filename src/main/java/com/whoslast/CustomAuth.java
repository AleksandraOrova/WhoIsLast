package com.whoslast;

import com.whoslast.authorization.SignInManager;
import com.whoslast.controllers.UserRepository;
import com.whoslast.entities.User;
import com.whoslast.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuth implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication){
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findUserByEmail(name);

        if (user != null) {
            ServerResponse authResponse = new SignInManager(userRepository).signIn(
                    new SignInManager.UserSignInData(name, password));

            List<GrantedAuthority> grantedAuth = new ArrayList<>();
            grantedAuth.add(new SimpleGrantedAuthority("ROLE_USER"));
            if(authResponse.isSuccess())
                return new UsernamePasswordAuthenticationToken(name, password, grantedAuth);
            else
                return null;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}