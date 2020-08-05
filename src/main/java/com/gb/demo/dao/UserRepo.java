package com.gb.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gb.demo.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where username=:em ")
	User findUserByStatusAndName(@Param("em") String username);
}
