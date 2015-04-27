package com.icy.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icy.entity.Account;
import com.icy.entity.Authority;
import com.icy.entity.Challenge;
import com.icy.service.AccountService;
import com.icy.service.AuthorityService;
import com.icy.service.ChallengeService;
import com.icy.utility.AuthorityEnum;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "id" })
public class AccountController {

	@Inject
	private AccountService userService;
	@Inject
	private AuthorityService authorityService;
	@Inject
	private ChallengeService challengeService;
	
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;
	private static final String AUTHORITY_NAME = "user";
	private static final String AUTHORITY_NOT_FOUND = "Authority was not found";
	protected static final Logger LOGGER = LoggerFactory
			.getLogger(AccountController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerOnSubmit(@Valid Account account,
			BindingResult bindingResult, Model model) {
		LOGGER.debug("create User={}", account);
		userService.exceptions().clear();
		if (bindingResult.hasErrors()) {
			LOGGER.warn("validation error={}", bindingResult.getModel());
			model.addAllAttributes(bindingResult.getModel());
			return "redirect:/login";
		}
		Authority regularUserAuth = authorityService
				.findByAuthorityName(AUTHORITY_NAME);
		if (regularUserAuth != null) {
			account.setAuthority(regularUserAuth);
			userService.insert(account);
		} else {
			userService.exceptions().add(AUTHORITY_NOT_FOUND);
		}
		if (!userService.exceptions().isEmpty()) {
			model.addAttribute("status", setStatus(userService.exceptions()));
			model.addAttribute("register_feedback",
					"There were errors with the data you provided. Click on register to correct.");
			return "redirect:/login";
		} else {
			model.addAttribute("register_feedback",
					"Your account has been created. Login and start challenging!");
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/userRedirect", method = RequestMethod.GET)
	public String redirect(Model model) {

		com.icy.entity.Account user = getLoggedInUser();
		if (user == null) {
			return "redirect:/login";
		}

		if (user.getAuthority() == null) {
			return "redirect:/login";
		}
		if (user.getAuthority().getAuthority()
				.equals(AuthorityEnum.user.toString())) {
			return "redirect:/user/wall";
		} else {
			return "redirect:/login";
		}

	}
	
	
	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public void accountCreated(Model model) {
		model.addAttribute("challenge_id", challengeService.getChallenge());
	}
	
	
	
	@RequestMapping(value = "/challenge", method = RequestMethod.GET)
	public void challenge(Model model) {
		Challenge challenge = new Challenge();
		model.addAttribute("challenge",challenge);
	}
	
	@RequestMapping(value = "/challengecreated", method = RequestMethod.POST)
	public String challengeCreated(@Valid Challenge challenge,Model model) {
		challengeService.insert(challenge);
		model.addAttribute("challenge_feedback",
				"Challenge has been created.");
		return "redirect:/user/wall";
	}

	public String setStatus(List<String> exceptions) {
		StringBuilder modelString = new StringBuilder();
		if (exceptions.isEmpty()) {
			modelString.append("OK");
		} else {
			for (String s : exceptions)
				modelString.append(s);
		}
		return modelString.toString();
	}

	public com.icy.entity.Account getLoggedInUser() {
		User userAut = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		return userService.findByUsername(userAut.getUsername());
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public @ModelAttribute void sendMailGet(Model model) {

		model.addAttribute("username", "");
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestParam("username") String username,
			Model model) {
		com.icy.entity.Account user = userService.findByUsername(username);
		if (user != null) {
			user.setPassword(RandomStringUtils.randomAlphanumeric(8));
			userService.changePassword(user);
			if (userService.exceptions().isEmpty()) {
				model.addAttribute("sendMail_success",
						"An email containing your account  details has been sent!");
				return "/login";
			} else {
				model.addAttribute("status",
						setStatus(userService.exceptions()));
			}
		} else {
			model.addAttribute("status", "Username or password not found");
		}
		return "user/sendMail";

	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public @ModelAttribute String viewer(Model model) {

		return "/user/viewer";

	}

	/*
	 * 
	 * ADMINISTRATIVE
	 */

	/* http://localhost:8080/icy/user/count.json */
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ModelAttribute void countAllUsers(Model model) {
		List<Account> users = userService.findAll();
		int count = users.size();
		model.addAttribute("Number of users:", count);

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ModelAttribute void listAllUsers(Model model) {
		List<Account> users = userService.findAll();
		StringBuilder userModel = new StringBuilder();
		for(Account account:users){
			userModel.append(account.toString());
			userModel.append("********");
		}
		model.addAttribute("Users",userModel);

	}

}
