package com.edcg.config.security

import com.edcg.model.Account
import com.edcg.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * Created by Edgar on 03/05/2017.
 */
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter{

    @Autowired
    AccountRepository accountRepository

    @Override
    void init(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService())
    }

    @Bean
    UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<Account> account = accountRepository.findByUsername(username)

                if (!account.isPresent())
                    throw  new UsernameNotFoundException("could not find the user '"+ username + "'")

                return new User(account.get().getUsername(), account.get().getPassword(),
                        true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER"))
            }
        }
    }

}
