package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntity;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseClaimMapper {
    ExpenseClaimMapper INSTANCE = Mappers.getMapper(ExpenseClaimMapper.class);
    ExpenseClaimEntity entity(ExpenseClaimDTO expDTO);
    ExpenseClaimDTO DTO(ExpenseClaimEntity expEntity);

}
