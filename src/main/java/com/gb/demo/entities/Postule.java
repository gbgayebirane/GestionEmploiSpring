package com.gb.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Postule {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	@ManyToOne
    private User user =new User();
	@ManyToOne
    private Offre offre =new Offre();
	public Postule() {
		super();
	}
	public Postule(int id, User user, Offre offre) {
		super();
		this.id = id;
		this.user = user;
		this.offre = offre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	
}
