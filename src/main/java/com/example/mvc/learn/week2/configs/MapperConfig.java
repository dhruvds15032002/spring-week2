package com.example.mvc.learn.week2.configs;

import com.example.mvc.learn.week2.dto.EmployeeDTO;
import com.example.mvc.learn.week2.entities.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

}
