package com.icy.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.icy.entity.Account;

public interface AccountService {

	Account findByUsername(String username);

	List<Account> findAll();

	Account insert(Account account);

	List<String> exceptions();

	void encodePass();

	Account changePassword(Account user);

}
