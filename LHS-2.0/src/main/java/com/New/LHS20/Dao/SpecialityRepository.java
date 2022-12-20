package com.New.LHS20.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
	
	
    List<Speciality> findByName(String name);
}
