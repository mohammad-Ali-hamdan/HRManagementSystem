package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO empDTO (EmployeeEntity employeeEntity);
    EmployeeEntity empEntity (EmployeeDTO employeeDTO);

}
