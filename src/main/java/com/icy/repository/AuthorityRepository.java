package com.icy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icy.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	List<Authority> findAll();

	Authority findByAuthority(String name);
	


}
