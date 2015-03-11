package com.icy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icy.entity.Account;
import com.icy.repository.AccountRepository;
import com.icy.service.AccountService;
import com.icy.utility.Mode;

@Service
public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository accountRepository;
	@Inject
	@Autowired
	ServletContext servletContext;
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	private List<String> exceptions = new ArrayList<String>();

	@Override
	@Transactional(readOnly = true)
	public Account findByUsername(String username) {
		Account user = accountRepository.findByUsername(username);

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Account> findAll() {
		List<Account> users = accountRepository.findAll();
		return users;
	}

	public void checkUser(Account user, Mode mode) {
		if (user.getPassword() == null || user.getPassword().equals("")) {
			exceptions.add("Empty password field. ");
		}
		if (user.getConfirmPassword() == null
				|| user.getConfirmPassword().equals("")) {
			exceptions.add("Empty confirm password field. ");
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			exceptions.add("Passsword fields do not match. ");
		}
		if (user.getUsername() == null || user.getUsername().equals("")) {
			exceptions.add("Empty username field. ");
		}
		if (user.getEmail() == null || ("").equals(user.getEmail())) {
			exceptions.add("Empty email field. ");
		} else {
			Pattern pattern;
			Matcher matcher;

			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getEmail());
			if (!matcher.matches()) {
				exceptions.add("Invalid email!! " + user.getEmail());
				return;
			}

			Account account = accountRepository.findByEmail(user.getEmail());
			if (account != null) {
				exceptions.add("Email " + account.getEmail() + " already used ");
			}
			account = accountRepository.findByUsername(user.getUsername());
			if (account != null) {
				exceptions.add("Username " + account.getUsername()
						+ " already used");
			}

		}

	}

	@Override
	@Transactional
	public Account changePassword(Account user) {
		exceptions.clear();
		checkUser(user, Mode.UPDATE);
		if (exceptions.isEmpty()) {
			String encryptedPassword = accountRepository
					.sendMailAndEncodePassword(user.getUsername(),
							user.getPassword());
			user.setPassword(encryptedPassword);
			Account userRet = accountRepository.save(user);
			return userRet;

		} else {
			return user;
		}
	}

	@Override
	@Transactional
	public void encodePass() {
		List<Account> users = accountRepository.findAll();
		for (Account u : users) {
			String encryptedPassword = messageDigestPasswordEncoder
					.encodePassword(u.getPassword(), null);
			u.setPassword(encryptedPassword);
		}
		accountRepository.save(users);
	}

	@Override
	@Transactional
	public Account insert(Account account) {
		checkUser(account, Mode.INSERT);
		if (exceptions.isEmpty()) {
			return accountRepository.save(account);
		} else {
			return new Account();
		}
	}

	@Override
	public List<String> exceptions() {
		return exceptions;
	}

	

}
