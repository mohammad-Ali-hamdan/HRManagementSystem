package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import com.company.hrmanagmentsystem.model.ExpenseClaimEntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseClaimEntryMapper {
    ExpenseClaimEntryMapper INSTANCE = Mappers.getMapper(ExpenseClaimEntryMapper.class);
    //@Mapping(target = "date" , source = "date" , dateFormat = "YYYY-MM-DD") // matching Data Entry dd/MM/yyyy
    ExpenseClaimEntryDTO expClaimEntryDTO (ExpenseClaimEntryEntity entity);
    //@Mapping(target = "date" , source = "date" , dateFormat = "YYYY-MM-DD") // matching the dataBase YYYY-MM-DD
    ExpenseClaimEntryEntity expClaimEntryEntity (ExpenseClaimEntryDTO DTO);

}
