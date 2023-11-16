package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    DepartmentDTO depDTO (DepartmentEntity departmentEntity);
    DepartmentEntity depEntity (DepartmentDTO departmentDTO);
}

