package com.New.LHS20.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.New.LHS20.Entity.Bill;
import com.New.LHS20.Entity.Policies;

@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long > {
}
