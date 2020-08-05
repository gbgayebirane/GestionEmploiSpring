package com.gb.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Profil
{
	@Id 
	private String nom;
	
	@ManyToMany(mappedBy="roles")
	private List<User> username =new ArrayList<User>();
	
	public Profil() {
		super();
	}

	public Profil(String nom, List<User> username) {
		super();
		this.nom = nom;
		this.username = username;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<User> getUsername() {
		return username;
	}

	public void setUsername(List<User> username) {
		this.username = username;
	}
	

}
