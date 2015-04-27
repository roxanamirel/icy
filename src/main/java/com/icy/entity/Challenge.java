package com.icy.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "challenge")
public class Challenge implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int challenge_id;
	private String challengename;
	private String challengedesc;
	private String category;
	private String additionalinfo;
	
	public Challenge() {
	}

	public Challenge(String challengename, String challengedesc, String category,String additionalinfo) {
		this.challengename = challengename;
		this.challengedesc = challengedesc;
		this.category = category;
		this.additionalinfo = additionalinfo;

	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "challenge_id", unique = true, nullable = false)
	public int getId() {
		return this.challenge_id;
	}
	
	public void setId(int id) {
		this.challenge_id = id;
	}

	@Column(name = "challengename", nullable = false, length = 45)
	public String getChallengename() {
		return this.challengename;
	}

	public void setChallengename(String challengename) {
		this.challengename = challengename;
	}
	
	@Column(name = "challengedesc", nullable = false, length = 200)
	public String getChallengedesc() {
		return this.challengedesc;
	}

	public void setChallengedesc(String challengedesc) {
		this.challengedesc = challengedesc;
	}
	
	@Column(name = "Challengecategory", nullable = false, length = 200)
	public String getChallengecategory() {
		return this.category;
	}

	public void setChallengecategory(String category) {
		this.category = category;
	}
	
	@Column(name = "additionalinfo", nullable = true, length = 200)
	public String getChallengeadditionalinfo() {
		return this.additionalinfo;
	}

	public void setChallengeadditionalinfo(String additionalinfo) {
		this.additionalinfo = additionalinfo;
	}
	
	@Override
	public String toString() {
		return "Challenge [challenge_id=" + challenge_id + ", challengename=" + challengename
				+ ", challengedesc=" + challengedesc + ", Challengecategory=" + category
				+ ", additionalinfo=" + additionalinfo + "]";
	}
}
