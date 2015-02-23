package com.icy.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.icy.entity.Account;


@NoRepositoryBean
public interface AccountRepositoryCustom {

	boolean checkUser(Account user);

	List<String> exceptions();

	boolean checkUsername(String username);

	String sendMailAndEncodePassword(String username,String password);

}
