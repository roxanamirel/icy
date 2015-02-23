package com.icy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icy.entity.Account;

@Repository
public interface AccountRepository extends AccountRepositoryCustom, JpaRepository<Account, Integer> {

	Account findByUsername(String username);

	List<Account> findAll();

	Account findById(int id);
	
}
