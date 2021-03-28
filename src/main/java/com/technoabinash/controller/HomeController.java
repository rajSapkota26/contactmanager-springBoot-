package com.technoabinash.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technoabinash.helper.MessageManager;
import com.technoabinash.model.User;
import com.technoabinash.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@PostMapping("/do_register")
	public String createAccount(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreed", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			System.out.println(result);
			System.out.println(user.toString());

			if (!agreement) {
				throw new Exception("you dont accept our terms and condition");
			}
			if (result.hasErrors()) {
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setActivity(true);
			user.setImageUrl("pf_pic.png");
			user.setPassword(PasswordEncoder.encode(user.getPassword()));
			
			
			
			
	           this.userRepository.save(user);
			model.addAttribute("title", "Login-smartContactManager");
			model.addAttribute("user", new User());
			session.setAttribute("message", new MessageManager("Registration success", "alert-success"));
			return "login";

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message",
					new MessageManager("Something Went Wrong !! " + e.getMessage(), "alert-danger"));
			return "signup";

		}

	}

	@GetMapping("/signin")
	public String signUp(Model model) {
		model.addAttribute("title", "Register-smartContactManager");
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("title", "Signup-smartContactManager");
		model.addAttribute("user", new User());

		return "signup";
	}

	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About-smartContactManager");
		return "about";
	}

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home-smartContactManager");
		return "home";
	}
}
