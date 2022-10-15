package com.gokul.satsum.service;

import com.gokul.satsum.dto.EmployeeRequest;
import com.gokul.satsum.exception.UserNotFoundException;
import com.gokul.satsum.model.Employee;
import com.gokul.satsum.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.build(employeeRequest.getId(),employeeRequest.getName(),employeeRequest.getSalary(),
                      employeeRequest.isOn_contract(),employeeRequest.getCurrency(),employeeRequest.getDepartment(),employeeRequest.getSub_department());
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> saveEmployee(List<EmployeeRequest> employeeRequestList) {

        List<Employee> employees = employeeRequestList.stream().map(employeeRequest-> Employee.build(employeeRequest.getId(),employeeRequest.getName(),employeeRequest.getSalary(),
                employeeRequest.isOn_contract(),employeeRequest.getCurrency(),employeeRequest.getDepartment(),employeeRequest.getSub_department())).collect(Collectors.toList());

        return employeeRepository.saveAll(employees);
    }

    @Override
    public Employee updateEmployee(EmployeeRequest employeeRequest) throws UserNotFoundException {
        Optional<Employee> employeeDb = this.employeeRepository.findById(employeeRequest.getId());

        if (employeeDb.isPresent()) {
            Employee employee1 = employeeDb.get();
            employee1.setId(employeeRequest.getId());
            employee1.setName(employeeRequest.getName());
            employee1.setDepartment(employeeRequest.getDepartment());
            employee1.setSalary(employeeRequest.getSalary());
            employee1.setSub_department(employeeRequest.getSub_department());
            employeeRepository.save(employee1);
            return employee1;
        } else {
            throw new UserNotFoundException("Record not found with id : " + employeeRequest.getId());
        }
    }

    @Override
    public List<Employee> getAllExployees() {
        return this.employeeRepository.findAll();
    }

    //3
    @Override
    public DoubleSummaryStatistics getStatisticalSummary() {

        List<Employee> employeeList = this.employeeRepository.findAll();
        DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream().mapToDouble(Employee::getSalary).summaryStatistics();

//        StatisticalSummary statisticalSummary = new StatisticalSummary();
//
//        DecimalFormat numberFormat = new DecimalFormat("#.00");
//
//        statisticalSummary.setMax(numberFormat.format(employeeSalaryStatistics.getMax()));
//        statisticalSummary.setMin(numberFormat.format(employeeSalaryStatistics.getMin()));
//        statisticalSummary.setMean(numberFormat.format(employeeSalaryStatistics.getAverage()));

        return employeeSalaryStatistics;
    }


    //4
    @Override
    public DoubleSummaryStatistics getStatisticalSummaryOnContract() {

        List<Employee> employeeList = this.employeeRepository.findAll();
        DoubleSummaryStatistics employeeSalaryStatistics = employeeList.stream().filter(emp -> emp.isOn_contract())
                .mapToDouble(Employee::getSalary).summaryStatistics();

//        StatisticalSummary statisticalSummary = new StatisticalSummary();
//
//        DecimalFormat numberFormat = new DecimalFormat("#.00");
//
//        statisticalSummary.setMax(numberFormat.format(employeeSalaryStatistics.getMax()));
//        statisticalSummary.setMin(numberFormat.format(employeeSalaryStatistics.getMin()));
//        statisticalSummary.setMean(numberFormat.format(employeeSalaryStatistics.getAverage()));

        return employeeSalaryStatistics;
    }

    //5
    @Override
    public Map<String, DoubleSummaryStatistics> getStatisticalSummaryDept() {

        List<Employee> employeeList = this.employeeRepository.findAll();

        Map<String, List<Employee>> employeeListByDepartment =
                employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        Map<String, DoubleSummaryStatistics> mapSat = employeeListByDepartment.entrySet().stream().collect(
                Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().stream().mapToDouble(Employee::getSalary).summaryStatistics())
        );

        System.out.println("mapSat:" + mapSat);

        return mapSat;
    }

    //6
    @Override
    public Map<String, Map<String, DoubleSummaryStatistics>> getStatisticalSummarySubDept() {

        List<Employee> employeeList = this.employeeRepository.findAll();

        Map<String, Map<String, List<Employee>>> employeeListByDepartment  =
                employeeList.stream().collect(
                        Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getSub_department)));


       Map<String, Map<String, DoubleSummaryStatistics>> mapSat =  new HashMap<>();
//                employeeList.stream().collect(
//                        Collectors.groupingBy(Employee::getDepartment,
//                                Collectors.groupingBy(Employee::getSub_department), Collectors.toMap(Employee emp ->));
//
//        }

        System.out.println("mapSat:" + mapSat);

        return mapSat;
    }

    @Override
    public Employee getEmployeeById(int id) throws UserNotFoundException {
        Optional<Employee> employee = this.employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new UserNotFoundException("Record not found with id : " + id);
        }
    }

    @Override
    public void deleteEmployee(String name) throws UserNotFoundException {
        Optional<Employee> employee = employeeRepository.findByName(name);

        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
        } else {
            throw new UserNotFoundException("Record not found with name : " + name);
        }
    }
}
