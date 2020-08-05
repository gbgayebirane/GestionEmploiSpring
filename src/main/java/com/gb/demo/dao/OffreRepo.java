package com.gb.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gb.demo.entities.Offre;

@Repository
public interface OffreRepo extends JpaRepository<Offre, Integer> {
	@Query("select o from Offre o where user_username=:us")
	List<Offre> mesoffres(@Param("us") String username);
	
	@Query("select o from Offre o where titre LIKE CONCAT('%',:search,'%')")
	List<Offre> recherches(@Param("search") String nom);
}
