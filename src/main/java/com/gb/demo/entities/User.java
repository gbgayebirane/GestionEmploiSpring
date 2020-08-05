package com.gb.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class User 
{
	@Id
	private String username;
	@Column(length=200)
	private String nom;
	@Column(length=200)
	private String prenom;
	@Column(length=200)
	private String email;
	@Column(length=200)
	private String password;
	@Column(length=200)
	private int etat;
	@Column(length=200)
	private String adresse;
	@Column(length=200)
	private String telephone;
	
	@ManyToMany
	private List<Profil> roles =new ArrayList<Profil>();
	
	@OneToMany(mappedBy="user")
    private List<Offre> offre =new ArrayList<Offre>();
	@OneToMany(mappedBy="user")
    private List<Cv> cv =new ArrayList<Cv>();
	@OneToMany(mappedBy="user")
    private List<Postule> postule =new ArrayList<Postule>();
	public User() {
		super();
	}
	
	public User(String username, String nom, String prenom, String email, String password, int etat, String adresse,
			String telephone, List<Profil> roles, List<Offre> offre, List<Cv> cv, List<Postule> postule) {
		super();
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.etat = etat;
		this.adresse = adresse;
		this.telephone = telephone;
		this.roles = roles;
		this.offre = offre;
		this.cv = cv;
		this.postule = postule;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Profil> getRoles() {
		return roles;
	}

	public void setRoles(List<Profil> roles) {
		this.roles = roles;
	}

	public List<Offre> getOffre() {
		return offre;
	}
	public void setOffre(List<Offre> offre) {
		this.offre = offre;
	}
	public List<Cv> getCv() {
		return cv;
	}
	public void setCv(List<Cv> cv) {
		this.cv = cv;
	}
	public List<Postule> getPostule() {
		return postule;
	}
	public void setPostule(List<Postule> postule) {
		this.postule = postule;
	}
	
}
