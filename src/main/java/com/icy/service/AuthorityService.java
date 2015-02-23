package com.icy.service;

import java.util.List;

import com.icy.entity.Account;
import com.icy.entity.Authority;

public interface AuthorityService {

	Authority findByAuthorityName(String authName);
	
	List<Authority> findAll();
	
	Authority insert(Authority authority);

}
