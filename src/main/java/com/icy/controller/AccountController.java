package com.icy.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icy.service.AccountService;
import com.icy.utility.AuthorityEnum;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "id" })
public class AccountController {

	@Inject
	protected AccountService userService;
	@Autowired
	private MessageDigestPasswordEncoder messageDigestPasswordEncoder;


	protected static final Logger LOGGER = LoggerFactory
			.getLogger(AccountController.class);
/*
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public @ModelAttribute
	void register(Model model) {
		User user = new User();
		model.addAttribute(user);

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerOnSubmit(@Valid User User,
			BindingResult bindingResult, Model model) {
		LOGGER.debug("create User={}", User);
		userService.getExceptions().clear();
		if (bindingResult.hasErrors()) {
			LOGGER.warn("validation error={}", bindingResult.getModel());
			model.addAllAttributes(bindingResult.getModel());
			model.addAttribute("status",
					setStatus(UserService.getExceptions()));
			return "/user/register";
		}

		UserService.insert(user);
		if (!userService.getExceptions().isEmpty()) {
			model.addAttribute("status",
					setStatus(parentService.getExceptions()));
			return "redirect:/user/register";
		}
		return "redirect:/user/accountCreated";
	}

	@RequestMapping(value = "/accountCreated", method = RequestMethod.GET)
	public void accountCreated(Model model) {

	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.GET)
	public @ModelAttribute
	void editPassword(Model model) {
		model.addAttribute(new com.icy.entity.User());

	}

	@RequestMapping(value = "/editPassword", method = RequestMethod.POST)
	public String editOnSubmit(@Valid com.icy.entity.User user,
			BindingResult bindingResult, Model model) {
		LOGGER.debug("create user transaction={}", user);
		if (bindingResult.hasErrors()) {
			LOGGER.warn("validation error={}", bindingResult.getModel());
			model.addAllAttributes(bindingResult.getModel());
			return "/user/editPassword";
		}

		if (user.getUsername() == null || user.getConfirmPassword() == null
				|| user.getNewPassword() == null || user.getPassword() == null) {
			model.addAttribute("status", "All fields are mandatory");
			return "/user/editPassword";
		}

		if (user.getUsername().isEmpty() || user.getConfirmPassword().isEmpty()
				|| user.getNewPassword().isEmpty()
				|| user.getPassword().isEmpty()) {
			model.addAttribute("status", "All fields are mandatory");
			return "/user/editPassword";
		}
		com.icy.entity.User dbUser = userService.findByUsername(user
				.getUsername());
		if (dbUser == null) {
			model.addAttribute("status", "User not registered ");
			return "/user/editPassword";
		}
		 String encryptedPassword =
				 messageDigestPasswordEncoder.encodePassword(user.getPassword(), null);
		if (!encryptedPassword.equals(dbUser.getPassword())) {
			model.addAttribute("status",
					"Password not found!");
			return "/user/editPassword";
		}

		if (!user.getConfirmPassword().equals(user.getNewPassword())) {
			model.addAttribute("status", "Password does not match");
			return "/user/editPassword";
		}
		dbUser.setPassword(user.getNewPassword());
		userService.changePassword(dbUser);
		if (!userService.exceptions().isEmpty()) {
			model.addAttribute("status", setStatus(userService.exceptions()));
			return "/user/editPassword";
		} else {
			model.addAttribute("sendMail_success",
					"An email with your account has been sent!");
			return "/login";
		}

	}
*/
	@RequestMapping(value = "/userRedirect", method = RequestMethod.GET)
	public String redirect(Model model) {

		com.icy.entity.Account user = getLoggedInUser();
		if (user == null) {
			return "redirect:/login";
		}

	
		if (user.getAuthority() == null) {
			return "redirect:/login";
		}
		if(user.getAuthority().getAuthority().equals(AuthorityEnum.user.toString())){
			return "redirect:/user/wall";
		}
		else {
			return "redirect:/login";
		}
		
	}
	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public void accountCreated(Model model) {

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
	public @ModelAttribute
	void sendMailGet(Model model) {

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
			model.addAttribute("status",
					"Username or password not found");
		}
		return "user/sendMail";

	}


	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public @ModelAttribute
	String viewer(Model model) {
	
		return "/user/viewer";

	}
}
