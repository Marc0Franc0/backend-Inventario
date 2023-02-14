package com.backendcarritoDeComprasApp.backend.security.service.impl;

import com.backendcarritoDeComprasApp.backend.security.model.Account;
import com.backendcarritoDeComprasApp.backend.security.model.Role;
import com.backendcarritoDeComprasApp.backend.security.repository.AccountRepository;
import com.backendcarritoDeComprasApp.backend.security.repository.RoleRepository;
import com.backendcarritoDeComprasApp.backend.security.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
