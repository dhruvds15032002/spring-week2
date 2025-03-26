package com.example.mvc.learn.week2.controllers;

import com.example.mvc.learn.week2.dto.EmployeeDTO;
import com.example.mvc.learn.week2.entities.EmployeeEntity;
import com.example.mvc.learn.week2.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
//        return new EmployeeDTO(id,"Dhruv","dhruvds2002@gmail.com", 23, true, LocalDate.of(2023,07,10));
        return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy)
    {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee)
    {
       return employeeRepository.save(inputEmployee);
    }

}
