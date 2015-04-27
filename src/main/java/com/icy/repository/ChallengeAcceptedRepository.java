package com.icy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icy.entity.ChallengeAccepted;

@Repository
public interface ChallengeAcceptedRepository extends JpaRepository<ChallengeAccepted, Integer> {

}
