package com.gb.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gb.demo.entities.Cv;
import com.gb.demo.entities.Offre;


@Repository
public interface CvRepo  extends JpaRepository<Cv, Integer>{
	@Query("select fichier from Cv o where user_username=:us")
	String moncv(@Param("us") String username);
	
}
