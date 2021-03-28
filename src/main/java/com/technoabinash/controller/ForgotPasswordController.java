package com.technoabinash.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.technoabinash.helper.MessageManager;
import com.technoabinash.model.User;
import com.technoabinash.repository.UserRepository;
import com.technoabinash.services.EmailService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private EmailService mailService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/forgotPassword")
	public String forgotPassword() {

		return "resetPassword";
	}

	@PostMapping("/sendOTP")
	public String sendOTP(@RequestParam("forgotEmail") String email, HttpSession session) {
		System.out.println(email);
		String otpcode = getRandomNumberString();
		String subject = "OTP from SMARTCONTACT";
		String message = "submit this code for verify otp" + (otpcode);
		String to = email;
		boolean sending = mailService.sendEmail(subject, message, to);
		if (sending) {
			session.setAttribute("myotpCode", otpcode);
			session.setAttribute("otpEmail", to);
			System.out.println(otpcode);
			return "verifyOTP";
		} else {
			session.setAttribute("message", new MessageManager("Check your email address", "alert-danger"));
			return "resetPassword";
		}

	}

	@PostMapping("/verifyOTP")
	public String verifyOTP(@RequestParam("otpCode") String otpCode, HttpSession session) {
		String myotp = (String) session.getAttribute("myotpCode");
		String email = (String) session.getAttribute("otpEmail");

		if (myotp.equals(otpCode)) {
			User user = userRepository.getUserByUserName(email);
			if (user == null) {
				session.setAttribute("message",
						new MessageManager("User doesnot exist with this Email", "alert-danger"));
				return "resetPassword";
			} else {
				return "changePassword";

			}
		} else {
			session.setAttribute("message",
					new MessageManager("incorrect OTP please check and Process", "alert-danger"));
			return "verifyOTP";
		}
	}

	@PostMapping("/resetpassword")
	public String resetpassword(@RequestParam("newpassword") String newpassword, HttpSession session) {
		String email = (String) session.getAttribute("otpEmail");
		User user = this.userRepository.getUserByUserName(email);
		user.setPassword(this.encoder.encode(newpassword));
		this.userRepository.save(user);
		session.setAttribute("message", new MessageManager("your password is changed successfully", "alert-success"));
		return "login";
	}

	// generate 6 digit random number for otp
	public static String getRandomNumberString() {
		// It will generate 6 digit random Number.
		// from 0 to 999999
		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%06d", number);
	}
}
