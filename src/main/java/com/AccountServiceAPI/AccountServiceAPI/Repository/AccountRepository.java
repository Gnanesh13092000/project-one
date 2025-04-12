package com.AccountServiceAPI.AccountServiceAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AccountServiceAPI.AccountServiceAPI.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(String accountId);

}
