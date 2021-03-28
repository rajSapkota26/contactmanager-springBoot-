package com.technoabinash.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.technoabinash.model.Contacts;
import com.technoabinash.model.User;
import com.technoabinash.repository.ContactRepository;
import com.technoabinash.repository.UserRepository;

@RestController
public class SearchController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query") String query,Principal principal){
		User user = userRepository.getUserByUserName(principal.getName());
		List<Contacts> list = contactRepository.findByNameContainingAndUser(query, user);
		return ResponseEntity.ok(list);
		
	}
}
