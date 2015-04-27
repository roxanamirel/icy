package com.icy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "challengeaccepted")
public class ChallengeAccepted {
	
	
	private int id;
	private Challenge challenge;
	private Account user;
	
	public ChallengeAccepted() {
	}
	
	public ChallengeAccepted(Challenge challenge, Account user) {
		this.challenge = challenge;
		this.user = user;
		

	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="challenge_id")
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Challenge [id=" + id + ", challenge_id="
				+ challenge + ", user_id=" + user
				+ "]";
	}

}
