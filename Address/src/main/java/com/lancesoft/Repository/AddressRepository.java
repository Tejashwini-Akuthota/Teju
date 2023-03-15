package com.lancesoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lancesoft.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	// address based on employee id
	@Query(nativeQuery = true, value = "SELECT ea.id, ea.lane1, ea.lane2, ea.state, ea.zip FROM seleniumexpree.address ea JOIN seleniumexpree.employee e ON e.id = ea.employee_id WHERE ea.employee_id = :employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
