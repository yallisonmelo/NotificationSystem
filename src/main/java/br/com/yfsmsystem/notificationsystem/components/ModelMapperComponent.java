package com.yfsmsystem.theintegrations.components;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperComponent {

    public ModelMapper maping() {
        return new ModelMapper();
    }

    public ModelMapper mappingAndReturnFieldNotEmpity() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return modelMapper;
    }
}
