package com.backendcarritoDeComprasApp.backend.security.service;

import com.backendcarritoDeComprasApp.backend.security.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account findByUsername(String username);
    List<Account> getAccounts();
}
