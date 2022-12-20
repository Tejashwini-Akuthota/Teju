package com.New.LHS20.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Doctor;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.Speciality;

import java.util.Collection;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
//    Collection<Doctor> findBySpecialityId(Integer specialityId);

	String save(RegistrationForm regform);

	 

	Doctor findById(long Id);

	List<Doctor> findBySpeciality(String name);

	Doctor findByEmail(String doctor);
}
