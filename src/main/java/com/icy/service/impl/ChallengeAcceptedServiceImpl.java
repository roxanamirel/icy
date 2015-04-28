package com.icy.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.icy.entity.ChallengeAccepted;
import com.icy.repository.ChallengeAcceptedRepository;
import com.icy.repository.ChallengeRepository;
import com.icy.service.ChallengeAcceptedService;

@Service
public class ChallengeAcceptedServiceImpl implements ChallengeAcceptedService {
	
	@Inject
	protected ChallengeAcceptedRepository challengeAcceptedRepository;

	@Override
	public ChallengeAccepted insert(ChallengeAccepted challenge) {
		return challengeAcceptedRepository.save(challenge);
	}

	@Override
	public List<ChallengeAccepted> findByUserId(int accountId) {
		return challengeAcceptedRepository.findByUserId(accountId);
	}
	

}
