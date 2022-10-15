package com.gokul.satsum.repository;

import com.gokul.satsum.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByName(String name);
}
