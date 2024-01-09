package com.api.apibackend.shared.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMappersConvertion<T, U> {
    private ModelMapper modelMapper;

    @Autowired
    public ModelMappersConvertion(ModelMapper modelMapper) {
        this.modelMapper = new ModelMapper();
    }

    public U toDTOFromEntity(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public T toEntityFromDTO(U source, Class<T> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
