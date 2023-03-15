package com.lancesoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancesoft.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
