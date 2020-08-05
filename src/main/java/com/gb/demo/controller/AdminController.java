package com.gb.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController 
{
	@RequestMapping(value="/connexion")
	 public String login() {
		 
		 return "login";
	 }
	@RequestMapping(value="/login")
	 public String connexion() {
		 
		 return "home";
	 }
	@RequestMapping(value="/")
	 public String home() {
		 
		 return "redirect:/logon";
	}
	
	@RequestMapping(value="")
	 public String homes() {
		 
		 return "redirect:/logon";
	 }
	
	@RequestMapping(value="/logon")
	public String logon(ModelMap map,HttpServletRequest  req,HttpServletResponse res) 
	{
		String user=req.getRemoteUser();

		//String name=SecurityContextHolder.getContext().getAuthentication().getName();
		//System.out.println(name);
	
				//System.out.println();
		 if(user != null)
			
			 return "accueil"; 
		
			   
			 
		 else
			 return "redirect:/login";
			 	
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest  req,HttpServletResponse res) 
	{
		
		
			 	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			 	
			 	if(auth!=null)
			 	{
			 		new SecurityContextLogoutHandler().logout(req, res, auth);
			 	}
			 	return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/Admin/403")
	public String denied()
	{
		return "403";
	}
	
}


