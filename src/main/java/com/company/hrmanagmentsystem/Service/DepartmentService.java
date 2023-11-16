package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    public List<DepartmentDTO> getallDepartments();
    public DepartmentDTO getDepartmentById(Integer id);
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
}
