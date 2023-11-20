package com.company.hrmanagmentsystem.Mapper;

import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import com.company.hrmanagmentsystem.model.LeavedetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LeavedetailsMapper {
    LeavedetailsMapper INSTANCE = Mappers.getMapper(LeavedetailsMapper.class);
    LeavedetailsDTO leavedetailsDTO(LeavedetailsEntity1 entity);
    LeavedetailsEntity1 leavedetailsEntity(LeavedetailsDTO dto);


}
