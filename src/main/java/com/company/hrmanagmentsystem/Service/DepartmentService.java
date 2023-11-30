package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.DepartmentEmployeeDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public List<DepartmentDTO> getallDepartments();
    public DepartmentDTO getDepartmentById(Integer id);
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO);
    public boolean deleteDep(Integer id);
    public List<DepartmentDTO> getemployeesByAllDepartment();
    public DepartmentEmployeeDTO getAllEmployeeNamesInSpecificDepartment(Integer id);
    public DepartmentDTO getAllEmployeesEntityInSpecificDepartment(Integer id);
    public DepartmentDTO update(Map<String, Object> dto);
}
