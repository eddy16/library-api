package com.edcg

import com.edcg.model.Account
import com.edcg.repository.AccountRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Created by Edgar on 24/02/2017.
 */
@SpringBootApplication
class SpringBootConfig {

    static void main(String ...args){
        SpringApplication.run(SpringBootConfig.class,args)
    }

    @Bean
    CommandLineRunner init(final AccountRepository repository, final BCryptPasswordEncoder encoder){
        return new CommandLineRunner() {
            @Override
            void run(String... strings) throws Exception {
                repository.save(new Account("goku",encoder.encode("kakaroto")))
            }
        }
    }
}
