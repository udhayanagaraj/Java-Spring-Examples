package com.SpringEmployeeService.demo.Controller;

import com.SpringEmployeeService.demo.Response.EmployeeResponse;
import com.SpringEmployeeService.demo.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") int id){
        EmployeeResponse employeeResponse = employeeService.getEmployeeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

}
