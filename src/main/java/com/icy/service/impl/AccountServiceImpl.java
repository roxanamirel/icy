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

@Service
public class AccountServiceImpl implements AccountService {

	@Inject
	protected AccountRepository accountRepository;
	@Autowired
	ServletContext servletContext;
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;

	List<String> exceptions = new ArrayList<String>();

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

	@Override
	public List<String> exceptions() {
		return exceptions;
	}

	
	public void checkUser(Account user, int mode) {
		if (user.getPassword() == null || user.getPassword().equals("")) {
			exceptions.add("Parola nu poate fi goala. ");
		}
		if (user.getUsername() == null || user.getUsername().equals("")) {
			exceptions.add("Username-ul nu poate fi gol. ");
		} else {
			Pattern pattern;
			Matcher matcher;

			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(user.getUsername());
			if (!matcher.matches()) {
				exceptions.add("Emailul nu este valid!!*! "
						+ user.getUsername());
				return;
			}

			Account u = accountRepository.findByUsername(user.getUsername());

			if (mode == 0) {// CREATE USER
				if (u != null) {
					exceptions.add("Acest email este deja folosit. "
							+ u.getUsername());
				}
			}

			if (mode == 1) {// UPDATE USER
				if (u != null && u.getId() != user.getId()) {
					exceptions.add("Acest email este deja folosit. "
							+ u.getUsername());
				}

			}

		}

	}

	@Override
	@Transactional
	public Account changePassword(Account user) {
		exceptions.clear();
		checkUser(user, 1);// 1-UPDATE USER
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
	public Account insert(Account account) {
		
		return accountRepository.save(account);
	}
	

}
