package com.icy.service;

import java.util.List;

import com.icy.entity.Challenge;


public interface ChallengeService {
	
	List<Challenge> getChallenge();
	Challenge insert(Challenge challenge);
	Challenge findById(Integer challengeId);
}
