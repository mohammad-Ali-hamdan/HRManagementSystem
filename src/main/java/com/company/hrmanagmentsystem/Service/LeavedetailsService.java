package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import com.company.hrmanagmentsystem.model.LeavedetailsDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeavedetailsService {
    public List<LeavedetailsDTO> getall();
    public LeavedetailsDTO getbyId(Integer id);
    public boolean deleteleavedetail(Integer id);
    public LeavedetailsDTO updateLeaveDetails(Map<String , Object> DTO);
    public LeavedetailsDTO SubmitLeave(LeavedetailsDTO leavedetailsDTO);
    public List<LeavedetailsDTO> getLeaveEmployeeWithinRange(Integer employeeId , Date FromDate , Date ToDate);

    public List<LeavedetailsDTO> getLeavePagination(Map<String , Object> pageable );
    public java.sql.Date test(Integer id);

}
