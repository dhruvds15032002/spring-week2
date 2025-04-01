package com.example.mvc.learn.week2.services;

import com.example.mvc.learn.week2.configs.MapperConfig;
import com.example.mvc.learn.week2.dto.EmployeeDTO;
import com.example.mvc.learn.week2.entities.EmployeeEntity;
import com.example.mvc.learn.week2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//
//        return  employeeEntity.map(employeeEntity1 ->  modelMapper.map(employeeEntity1,EmployeeDTO.class));

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO inputEmployee) {
        EmployeeEntity beforeSaveEmployee = modelMapper.map(inputEmployee,EmployeeEntity.class);
        beforeSaveEmployee.setId(employeeId);
        EmployeeEntity updatedEmployee = employeeRepository.save(beforeSaveEmployee);
        return modelMapper.map(updatedEmployee,EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean employeeExists = employeeRepository.existsById(employeeId);
        if (!employeeExists) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        boolean employeeExists = employeeRepository.existsById(employeeId);
        if(!employeeExists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldsToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class,field);
            fieldsToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldsToBeUpdated,employeeEntity,value);
        });
        EmployeeEntity updatedEmployee = employeeRepository.save(employeeEntity);
        return modelMapper.map(updatedEmployee,EmployeeDTO.class);
    }
}
