package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity , Integer> {

    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.employee")
    public Integer GetMaxEmpId();

    @Procedure(name = "departmentIDs")
    public List<Integer> departmentIDs();

    @Procedure(name = "employeeIDs")
    public List<Integer> employeeIDs();

    @Procedure(name = "ListEmployeesInDepartment")
    public List<Integer> ListEmployeesInDepartment(@Param("depname") String name);

    @Procedure(name = "entitybyDepartment")
    public List<EmployeeEntity> ListEmployeesEntityInDepartment(@Param("depname") String name);
}
