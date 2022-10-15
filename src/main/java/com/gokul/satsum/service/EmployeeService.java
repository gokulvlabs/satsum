package com.gokul.satsum.service;

import com.gokul.satsum.dto.EmployeeRequest;
import com.gokul.satsum.exception.UserNotFoundException;
import com.gokul.satsum.model.Employee;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee saveEmployee(EmployeeRequest employeeRequest);

    List<Employee> saveEmployee(List<EmployeeRequest> employeeRequestList);
    Employee updateEmployee(EmployeeRequest employeeRequest) throws UserNotFoundException;
    List<Employee> getAllExployees();
    Employee getEmployeeById(int id) throws UserNotFoundException;
    void deleteEmployee(String name) throws UserNotFoundException;

    DoubleSummaryStatistics getStatisticalSummary();

    DoubleSummaryStatistics getStatisticalSummaryOnContract();

    Map<String, DoubleSummaryStatistics> getStatisticalSummaryDept();

    Map<String, Map<String, DoubleSummaryStatistics>> getStatisticalSummarySubDept();
}
