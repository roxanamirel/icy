package com.icy.controller;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.icy.service.impl.EmailSender;
import com.icy.utility.ContactData;

@Controller
@SessionAttributes({"status"})
public class LoginController {
	@Autowired
	ServletContext servletContext;
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String name = user.getUsername();
		
		model.addAttribute("username", name);
		model.addAttribute("message",
				"Spring Security login + database example");
		return "hello";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "/";

	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(Model model) {
		ContactData contactData = new ContactData();
		model.addAttribute("contactData", contactData);
		
		return "contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contactPost(@Valid ContactData contactData,BindingResult bindingResult, Model model
			) {
		if (bindingResult.hasErrors()) {
			model.addAllAttributes(bindingResult.getModel());
			return "contact";
		}
		String emailAdr = contactData.getEmail();
		String message = contactData.getMessage();
		message = message +"\n NUME: "+ contactData.getName();
		String subject = contactData.getSubject();
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		EmailSender mm = (EmailSender) ctx.getBean("mailMail");
		
		try {
			mm.sendMail(emailAdr, "rroxanaioana@gmail.com", subject,
					message);
			model.addAttribute("status","Mesajul a fost trimis cu succes.Incercam sa va raspundem cat mai repede.");
		} catch (Throwable ex) {
			model.addAttribute("status","Mesajul nu a putut fi trimis. Verificati conexiunea la internet.");
			ex.printStackTrace();

		}
		
		return "contact";
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String help() {
		return "help";
	}

}