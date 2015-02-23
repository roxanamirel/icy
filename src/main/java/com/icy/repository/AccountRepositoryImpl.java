package com.icy.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.icy.entity.Account;
import com.icy.service.impl.EmailSender;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

	private List<String> exceptions = new ArrayList<String>();
	@Autowired
	ServletContext servletContext;
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;


	@Override
	public boolean checkUser(Account user) {
		exceptions.clear();
		boolean check = true;
		if (user.getUsername() == null || user.getUsername().equals("")) {
			exceptions.add("Username is needed ");
			check = false;
		} else {
			if (!checkUsername(user.getUsername())) {
				check = false;
			}

		}
		if (user.getPassword() == null || user.getPassword().equals("")) {
			exceptions.add("Password is needed ");
			check = false;
		}
		return check;
	}

	@Override
	public boolean checkUsername(String username) {
		exceptions.clear();
		boolean check = true;
		Pattern pattern;
		Matcher matcher;

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(username);
		if (!matcher.matches()) {
			exceptions.add("Email is not valid " + username);
			check = false;

		}
		return check;
	}

	@Override
	public List<String> exceptions() {
		return exceptions;
	}

	@Override
	public String sendMailAndEncodePassword(String username, String password) {
		sendMail(username, password);

		return messageDigestPasswordEncoder.encodePassword(password, null);
	}

	private void sendMail(String username, String password) {
		exceptions.clear();
		String mail = username;
		String mesaj = "You account for ICY is:  "
				+ "\n Username: " + "" + mail + "\n Password: " + password;

		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		EmailSender mm = (EmailSender) ctx.getBean("mailMail");
		try {
			mm.sendMail("ichallengeyu@gmail.com", mail,
					"Credentials ICY", mesaj);
		} catch (Throwable ex) {
			ex.printStackTrace();
			exceptions.add("Email has not been sent.");
		}
	}

}
