package com.icy.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.icy.entity.Authority;
import com.icy.repository.AuthorityRepository;
import com.icy.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Inject
	protected AuthorityRepository authorityRepository;

	@Override
	public Authority findByAuthorityName(String authName) {
		Authority authority = authorityRepository.findByAuthority(authName);

		return authority;
	}

	@Override
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	@Override
	public Authority insert(Authority authority) {
		return authorityRepository.save(authority);
	}

}
