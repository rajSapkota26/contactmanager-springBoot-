package com.technoabinash.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.technoabinash.model.Contacts;
import com.technoabinash.model.User;

import java.util.*;

public interface ContactRepository extends JpaRepository<Contacts, Integer>{
	
	@Query("from Contacts as u where u.user.id=:user_id")
	public Page<Contacts>  findContactsByUser(@Param("user_id") int user_id,Pageable pageable);

	public List<Contacts> findByNameContainingAndUser(String keywords,User user);
}
