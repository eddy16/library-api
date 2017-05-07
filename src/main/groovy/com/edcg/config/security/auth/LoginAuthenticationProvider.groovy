package com.edcg.config.security.auth

import com.edcg.model.Account
import com.edcg.repository.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

/**
 * Created by Edgar on 04/05/2017.
 */
@Component
class LoginAuthenticationProvider implements AuthenticationProvider{

    private final Logger logger = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

    @Autowired
    private final BCryptPasswordEncoder encoder
    @Autowired
    private final AccountRepository accountRepository

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        def username = (String) authentication.getPrincipal()
        def password = (String) authentication.getCredentials()

        def account = accountRepository.findByUsername(username)
                .orElseThrow({new UsernameNotFoundException("User not found: " + username)})

        if (!encoder.matches(password, account.getPassword()))
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.")

        def authorities = [new SimpleGrantedAuthority("ROLE_USER")]

        return new UsernamePasswordAuthenticationToken(username, password, authorities)
    }

    @Override
    boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass))
    }
}
