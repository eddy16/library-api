package com.edcg

import com.edcg.model.Account
import com.edcg.repository.AccountRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * Created by Edgar on 24/02/2017.
 */
@SpringBootApplication
class SpringBootConfig {

    static void main(String ...args){
        SpringApplication.run(SpringBootConfig.class,args)
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                accountRepository.save(new Account("edgar", "1234"))
            }
        }
    }
}
