package com.icy.service.impl;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;


import com.icy.entity.Challenge;
import com.icy.repository.ChallengeRepository;
import com.icy.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Inject
	protected ChallengeRepository challengeRepository;
	
	
	@Override
	public Challenge insert(Challenge challenge) {
		// TODO Auto-generated method stub
		return challengeRepository.save(challenge);
	}

	@Override
	public java.util.List<Challenge> getChallenge() {
		// TODO Auto-generated method stub
		return challengeRepository.findAll();
	}
	
	

}
