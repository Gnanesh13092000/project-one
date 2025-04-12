package com.AccountServiceAPI.AccountServiceAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AccountServiceAPI.AccountServiceAPI.Entity.Account;
import com.AccountServiceAPI.AccountServiceAPI.Repository.AccountRepository;

@Service
public class AccountService {

	private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountDetails(String accountId) {
        return accountRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
    
   

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public List<Account> getAllAccounts() {
		 return accountRepository.findAll();
	}
}
