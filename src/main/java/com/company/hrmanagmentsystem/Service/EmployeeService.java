package com.company.hrmanagmentsystem.Service;


import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.model.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    public List<EmployeeDTO> getAllEmployees ();
    public EmployeeDTO getByID(Integer id);
    public EmployeeDTO CreateEmployee(EmployeeDTO employeeDTOInput);
    public String deleteEmp(Integer id);
    public EmployeeDTO updateEmp(Map<String , Object> dtoObject , Integer id );
    //public List<EmployeeDTO> ListEmployeesByDepartment(String name);
    //public List<EmployeeDTO> ListEmployeesByDepartment2(String name);
    public List<EmployeeDTO> ListEmployeesByDepartment(Integer id);


}
