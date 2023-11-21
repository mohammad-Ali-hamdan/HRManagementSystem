package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.ExpenseTypeEntity;
import com.company.hrmanagmentsystem.model.ExpenseTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseTypeMapper {
    ExpenseTypeMapper INSTANCE = Mappers.getMapper(ExpenseTypeMapper.class);
    ExpenseTypeDTO expenseTypeDTO(ExpenseTypeEntity entity);
    ExpenseTypeEntity expenseTypeEntity(ExpenseTypeDTO DTO);
}
