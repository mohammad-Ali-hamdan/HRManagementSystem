package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import com.company.hrmanagmentsystem.model.EmployeeExpenseClaimsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO empDTO (EmployeeEntity employeeEntity);
    EmployeeEntity empEntity (EmployeeDTO employeeDTO);
    //another DTO
    EmployeeEntity empExpenseEntity(EmployeeExpenseClaimsDTO empExpenseDTOForEntity);
    EmployeeExpenseClaimsDTO empExpenseDTO(EmployeeEntity employeeEntityForExpense);

}
