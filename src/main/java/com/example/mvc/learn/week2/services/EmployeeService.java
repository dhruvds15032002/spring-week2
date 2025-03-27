package com.example.mvc.learn.week2.services;

import com.example.mvc.learn.week2.configs.MapperConfig;
import com.example.mvc.learn.week2.dto.EmployeeDTO;
import com.example.mvc.learn.week2.entities.EmployeeEntity;
import com.example.mvc.learn.week2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity beforeSaveEmployee = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity employee = employeeRepository.save(beforeSaveEmployee);
        return modelMapper.map(employee,EmployeeDTO.class);
    }
}
