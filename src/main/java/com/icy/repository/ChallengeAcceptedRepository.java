package com.icy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icy.entity.Authority;
import com.icy.entity.ChallengeAccepted;

@Repository
public interface ChallengeAcceptedRepository extends JpaRepository<ChallengeAccepted, Integer> {

	List<ChallengeAccepted> findByUserId(int accountId);
}
