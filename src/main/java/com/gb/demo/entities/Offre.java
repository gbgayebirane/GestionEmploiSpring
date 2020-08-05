package com.gb.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Offre 
{
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private int id;
	@Column(length=200)
	private String titre;
	@Column(length=200)
	private String poste;
	@Column(length=200)
	private String type;
	@Column(length=200)
	private String description;	
	@ManyToOne
    private User user =new User();
	@OneToMany(mappedBy="offre")
    private List<Postule> postule =new ArrayList<Postule>();
	public Offre() {
		super();
	}
	public Offre(int id, String titre, String poste, String type, String description, User user,
			List<Postule> postule) {
		super();
		this.id = id;
		this.titre = titre;
		this.poste = poste;
		this.type = type;
		this.description = description;
		this.user = user;
		this.postule = postule;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Postule> getPostule() {
		return postule;
	}
	public void setPostule(List<Postule> postule) {
		this.postule = postule;
	}
	
}
