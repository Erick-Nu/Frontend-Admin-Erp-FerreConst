package com.esnt.ferreconst.mapper;

import com.esnt.ferreconst.dto.response.configuration.ConfigurationResponseDto;
import com.esnt.ferreconst.model.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConfigurationMapper {

    public Configuration toConfiguration(ConfigurationResponseDto dto) {
        if (dto == null) return null;

        Configuration configuration = new Configuration();
        configuration.setId(dto.getId());
        configuration.setCompanyId(dto.getCompanyId());
        configuration.setKey(dto.getKey());
        configuration.setValue(dto.getValue());
        return configuration;
    }

    public List<Configuration> toConfigurations(ConfigurationResponseDto[] dtos) {
        List<Configuration> configurations = new ArrayList<>();
        if (dtos == null) return configurations;

        for (ConfigurationResponseDto dto : dtos) {
            configurations.add(toConfiguration(dto));
        }
        return configurations;
    }
}
