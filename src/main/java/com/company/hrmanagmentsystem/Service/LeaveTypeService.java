package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.LeaveTypeDTO;

import java.util.List;
import java.util.Map;

public interface LeaveTypeService {
    public LeaveTypeDTO getbyID(Integer id);
    public List<LeaveTypeDTO> getall();
    public LeaveTypeDTO CreateLeaveType(LeaveTypeDTO leaveTypeDTO);
    public boolean deleteLeaveType(Integer id);
    public LeaveTypeDTO Update(Map<String , Object> leaveType);
}
