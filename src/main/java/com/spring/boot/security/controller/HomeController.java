package com.spring.boot.security.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.security.utils.SecurityUtils;

@Controller
public class HomeController {
	
	@Autowired
	DataSource datasource;
	
	@RequestMapping(value="/")
	public String defaultHome(Model model)
	{
		
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
		System.out.println(username);
		List roles=securityUtils.getRoles(username,datasource);
		System.out.println(roles.size());
		if(roles.contains("ADMIN")) {

			return "redirect:admin";
		}
		if(roles.contains("USER"))
		{
		    return "redirect:user";
		}
		
		return "redirect:home";
		
		
	}
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home(Model model) throws SQLException
	{
		
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
        
		model.addAttribute("username", username);
		model.addAttribute("lastLogin", securityUtils.getUserLastLogin(username,datasource));		
		
		securityUtils.logUserEvent(username,datasource);
		return "home";
		
	}
	
	@PreAuthorize("hasAnyRole('USER')")
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user(Model model) throws SQLException
	{
		System.out.println("USER HOME");
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
        
		model.addAttribute("username", username);
		model.addAttribute("lastLogin", securityUtils.getUserLastLogin(username,datasource));		
		
		securityUtils.logUserEvent(username,datasource);
		return "userHome";
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	
	public String admin(Model model) throws SQLException
	{
		System.out.println("ADMIN");
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
        
		model.addAttribute("username", username);
		model.addAttribute("lastLogin", securityUtils.getUserLastLogin(username,datasource));		
		
		securityUtils.logUserEvent(username,datasource);
		return "adminHome";
		
	}
	
	@RequestMapping(value="/login")
	public String loginPage()
	{
		
		return "login";	
		
	}
	
	@RequestMapping(value="/logout-success")
	public String logoutPage()
	{
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
		securityUtils.logUserLogout(username, datasource);
		return "logout";
		
		
	}
	
	@RequestMapping(value="/test")
	public String testPage(Model model)
	{
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
        
		model.addAttribute("username", username);

		return "test";
		
		
	}
	
}
