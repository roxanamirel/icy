package com.icy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icy.entity.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Integer>  {

	List<Challenge> findAll();
}
