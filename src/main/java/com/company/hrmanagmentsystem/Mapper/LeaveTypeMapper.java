package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import com.company.hrmanagmentsystem.model.LeaveTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveTypeMapper INSTANCE = Mappers.getMapper(LeaveTypeMapper.class);
    LeaveTypeDTO leaveTypeDTO(LeaveTypeEntity entity);
    LeaveTypeEntity leaveTypeEntity(LeaveTypeDTO dto);
}
