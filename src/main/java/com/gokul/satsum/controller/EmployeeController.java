package com.gokul.satsum.controller;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;

import com.gokul.satsum.dto.AuthRequest;
import com.gokul.satsum.dto.EmployeeRequest;
import com.gokul.satsum.exception.UserNotFoundException;
import com.gokul.satsum.model.Employee;
import com.gokul.satsum.service.EmployeeService;
import com.gokul.satsum.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody @Valid AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }


    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employeeRequest));
    }


    @PostMapping("/employeesBatch")
    public ResponseEntity<List<Employee>> saveEmployeeList(@RequestBody @Valid List<EmployeeRequest> employeeRequestList) {
        return ResponseEntity.ok().body(employeeService.saveEmployee(employeeRequestList));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllExployees(),HttpStatus.CREATED);
    }

    @GetMapping("/statisticalSummary")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticalSummary() {
        return ResponseEntity.ok().body(employeeService.getStatisticalSummary());
    }

    @GetMapping("/statisticalSummaryOnContract")
    public ResponseEntity<DoubleSummaryStatistics> getStatisticalSummaryOnContract() {
        return ResponseEntity.ok().body(employeeService.getStatisticalSummary());
    }

    @GetMapping("/statisticalSummaryDept")
    public HttpEntity<Map<String, DoubleSummaryStatistics>> getStatisticalSummaryDept() {
        return ResponseEntity.ok().body(employeeService.getStatisticalSummaryDept());
    }

    @GetMapping("/statisticalSummarySubDept")
    public HttpEntity<Map<String, Map<String, DoubleSummaryStatistics>>> getStatisticalSummarySubDept() {
        return ResponseEntity.ok().body(employeeService.getStatisticalSummarySubDept());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws UserNotFoundException {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest) throws UserNotFoundException {
        employeeRequest.setId(id);
        return ResponseEntity.ok().body(this.employeeService.updateEmployee(employeeRequest));
    }

    @DeleteMapping("/employees/{name}")
    public HttpStatus deleteEmployee(@PathVariable String name) throws UserNotFoundException {
        this.employeeService.deleteEmployee(name);
        return HttpStatus.OK;
    }
}
