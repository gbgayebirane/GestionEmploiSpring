package com.gb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gb.demo.dao.OffreRepo;
import com.gb.demo.dao.PostuleRepo;
import com.gb.demo.dao.UserRepo;
import com.gb.demo.entities.Offre;
import com.gb.demo.entities.Postule;
import com.gb.demo.entities.User;


@Controller
public class PostuleController {
	@Autowired
	private OffreRepo offredao;
	@Autowired
	private PostuleRepo postdao;
	@Autowired
	private UserRepo userdao;
	@RequestMapping(value="/Postule/liste")
	 public String liste(ModelMap modelmap)
   {
		List<Offre> offre=offredao.findAll();
		modelmap.put("liste_offres",  offre);
		
       return "postule/liste";
   }
	@RequestMapping(value="/Postule" , method=RequestMethod.GET)
	public String post(int id ,ModelMap  model)
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		Postule p=new Postule();
		Offre f=offredao.getOne(id);
		p.setOffre(f);
		User u=userdao.findUserByStatusAndName(auth.getName());
		p.setUser(u);
		
		try 
		{
			postdao.save(p);
			
			postdao.flush();
			
			model.put("message", "v");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Offre> offre=offredao.findAll();
		model.put("liste_offres",  offre);
		
		 return "postule/liste";
		 
		
		
	}
	@RequestMapping(value="/Postule/details" , method=RequestMethod.GET)
	public String edit(int id ,ModelMap  model)
	{
		
		 model.put("description",offredao.getOne(id).getDescription());
		 model.put("poste",offredao.getOne(id).getPoste());
		 model.put("titre",offredao.getOne(id).getTitre());
		 model.put("type",offredao.getOne(id).getType());
		 model.put("user",offredao.getOne(id).getUser().getUsername());
		 
		return "Offre/details";
		
	}
	
	@RequestMapping(value="/Postule/offre" , method=RequestMethod.GET)
	public String offrepostuler(int id ,ModelMap  model)
	{
		//Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		model.put("offres", postdao.mesoffrespostule(id));
		 
		  
		  //model.put("offres", postdao.offrespostule(id,auth.getName()));
	     return "Offre/postule";
		
	}
	 @RequestMapping(value="/Postule/search", method=RequestMethod.POST)
	 public String add(String search,ModelMap model)
		{
			
			//System.out.println(offredao.recherches(nom));
		 List<Offre> offre=offredao.recherches(search);
			model.put("liste_offres",  offre);
	        return "postule/liste";
			
			
		}
	
}
