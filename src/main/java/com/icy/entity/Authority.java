package com.icy.entity;

import static javax.persistence.GenerationType.IDENTITY;




import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "authority")
public class Authority implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String authority;
	private List<Account> users = new ArrayList<Account>(0);

	public Authority() {
	}

	public Authority(Integer id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "authorityName", unique = true, nullable = false)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "authority")
	public List<Account> getAccounts() {
		return users;
	}

	public void setAccounts(List<Account> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority ;
	}

	

}
