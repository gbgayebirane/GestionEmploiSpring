package com.gb.demo.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gb.demo.dao.OffreRepo;
import com.gb.demo.dao.UserRepo;
import com.gb.demo.entities.Offre;
import com.gb.demo.entities.Postule;
import com.gb.demo.entities.User;


@Controller
public class OffreController
{
	@Autowired
	private OffreRepo offredao;
	@Autowired
	private UserRepo userdao;
	@RequestMapping(value="/Offre/liste")
	 public String liste(ModelMap modelmap)
    {
		List<Offre> offre=offredao.findAll();
		modelmap.put("liste_offres",  offre);
        return "Offre/liste";
    }
	@RequestMapping(value="/Offre/publier")
	 public String publier(ModelMap modelmap)
	    {
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User u=userdao.findUserByStatusAndName(auth.getName());
		
		
		List<Offre> offre=offredao.mesoffres(u.getUsername());
		modelmap.put("liste_offres",  offre);
		 modelmap.put("offre",new Offre());
	        return "Offre/add";
	    }
	@RequestMapping(value="/Offre/mesoffres")
	 public String mesoffre(ModelMap model)
   {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		User u=userdao.findUserByStatusAndName(auth.getName());
		
		List<Offre> offre=offredao.mesoffres(u.getUsername());
		model.put("liste_offres",  offre);
       return "Offre/mesoffres";
   }
	@RequestMapping(value="/Offre/details" , method=RequestMethod.GET)
	public String edit(int id ,ModelMap  model)
	{
		
		 model.put("description",offredao.getOne(id).getDescription());
		 model.put("poste",offredao.getOne(id).getPoste());
		 model.put("titre",offredao.getOne(id).getTitre());
		 model.put("type",offredao.getOne(id).getType());
		 model.put("user",offredao.getOne(id).getUser().getUsername());
		 
		return "Offre/details";
		
	}

	 @RequestMapping(value="/Offre/search", method=RequestMethod.POST)
	 public String add(String search,ModelMap model)
		{
			
			//System.out.println(offredao.recherches(nom));
		 List<Offre> offre=offredao.recherches(search);
			model.put("liste_offres",  offre);
	        return "Offre/liste";
			
			
		}
	 @RequestMapping(value="/Offre/add", method=RequestMethod.POST)
		public String add(int id,String titre,String type,String description,String poste)
		{
		 Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			User u=userdao.findUserByStatusAndName(auth.getName());
			
			Offre f=new Offre();
			f.setId(id);
			f.setDescription(description);
			f.setPoste(poste);
			f.setTitre(titre);
			f.setType(type);
			f.setUser(u);
			try 
			{
				offredao.save(f);
				offredao.flush();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 return  "redirect:/Offre/liste";
		}
	 @RequestMapping(value="/Offre/edit" , method=RequestMethod.GET)
		public String editoffre(int id,String titre,String type,String description,String poste,ModelMap  modelmap)
		{
		          List<Offre> offre=offredao.findAll();
			     modelmap.put("liste_offres",  offre);
		        Offre o=offredao.getOne(id);
		        modelmap.put("offre",o);
		        return "Offre/add";
			
		}
	 @RequestMapping(value="/Offre/delete",method=RequestMethod.GET)
		public String delete(int id)
		{
			try
			{
				offredao.delete(offredao.getOne(id));
				offredao.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return  "redirect:/Offre/publier";
		}
}
