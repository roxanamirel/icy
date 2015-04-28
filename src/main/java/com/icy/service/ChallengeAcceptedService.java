package com.icy.service;


import java.util.List;

import com.icy.entity.ChallengeAccepted;

public interface ChallengeAcceptedService {
	ChallengeAccepted insert(ChallengeAccepted challenge);
	List<ChallengeAccepted> findByUserId(int accountId);

}
