package com.edcg.repository

import com.edcg.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Edgar on 03/05/2017.
 */
@Transactional
interface AccountRepository extends CrudRepository<Account, Long>{

    Optional<Account> findByUsername(String username);
}