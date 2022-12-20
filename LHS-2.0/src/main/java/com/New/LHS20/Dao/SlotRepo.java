package com.New.LHS20.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.RegistrationForm;
import com.New.LHS20.Entity.SlotTime;


@Repository
public interface SlotRepo extends JpaRepository<SlotTime, Integer> {

boolean existsByDoctorId(Long Id);

List<SlotTime> findByDoctorId(Long id);

boolean existsByPatientId(Long Id);

List<SlotTime> findByDate(String date);


@Query(value="from SlotTime where date >=?1 AND date <= ?2")
List<SlotTime> findBySevenDaysSlots( String date, String tillDate);


@Query(value="from SlotTime where date >=?1 AND date <= ?2  AND doctorId=?3")
List<SlotTime>findBySevenDaysSlots1(String today, String tillDate, Long   doctorId );
//
//List<SlotTime2> findByTodaySlots(String today);

List<SlotTime> findByPatientId(long regdNo);


@Query(value="from SlotTime where date =?1 AND startTime = ?2  AND doctorId=?3" )
List<SlotTime> findByStartTimeAndDateAndId(String date, String startTime,Long doctorId);



 


}
