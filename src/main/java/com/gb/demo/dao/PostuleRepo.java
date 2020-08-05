package com.gb.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gb.demo.entities.Postule;


@Repository
public interface PostuleRepo extends JpaRepository<Postule, Integer> {
	@Query("select p from Postule p where offre_id=:us ")
	List<Postule> mesoffrespostule(@Param("us") int id);
	//@Query("select o.titre,o.type,o.description from Offre o,Postule p  where p.offre_id=o.id and  o.user_username=:u offre_id=:us")
	//Postule offrespostule(@Param("us") int id,@Param("u") String user);
}
