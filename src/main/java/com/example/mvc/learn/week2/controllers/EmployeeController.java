package com.example.mvc.learn.week2.controllers;

import com.example.mvc.learn.week2.dto.EmployeeDTO;
import com.example.mvc.learn.week2.exceptions.ResourceNotFoundException;
import com.example.mvc.learn.week2.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
         Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not found"));
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String sortBy)
    {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee)
    {
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping("/{employeeId}")

    public EmployeeDTO updateEmployeeById(@PathVariable Long employeeId, @RequestBody EmployeeDTO inputEmployee)
    {
        return employeeService.updateEmployeeById(employeeId,inputEmployee);
    }

    @DeleteMapping("/{employeeId}")

    public boolean deleteEmployeeById(@PathVariable Long employeeId)
    {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping("/{employeeId}")

    public EmployeeDTO updatePartialEmployeeById(@RequestBody @Valid Map<String,Object> updates,
                                                 @PathVariable Long employeeId)
    {
        return employeeService.updatePartialEmployeeById(updates,employeeId);
    }
}
