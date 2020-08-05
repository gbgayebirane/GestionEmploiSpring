package com.gb.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cv 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	@Column(length=200)
	private String formation;
	@Column(length=200)
	private String anneeformation;
	@Column(length=200)
	private String experience;
	@Column(length=200)
	private String duree;
	@Column(length=200)
	private String poste;
	@Column(length=200)
	private String language;
	@Column(length=200)
	private String fichier;
	@ManyToOne
    private User user =new User();
	public Cv() {
		super();
	}
	
	public Cv(int id, String formation, String anneeformation, String experience, String duree, String poste,
			String language, String fichier, User user) {
		super();
		this.id = id;
		this.formation = formation;
		this.anneeformation = anneeformation;
		this.experience = experience;
		this.duree = duree;
		this.poste = poste;
		this.language = language;
		this.fichier = fichier;
		this.user = user;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormation() {
		return formation;
	}
	public void setFormation(String formation) {
		this.formation = formation;
	}
	public String getAnneeformation() {
		return anneeformation;
	}
	public void setAnneeformation(String anneeformation) {
		this.anneeformation = anneeformation;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}
	
}
