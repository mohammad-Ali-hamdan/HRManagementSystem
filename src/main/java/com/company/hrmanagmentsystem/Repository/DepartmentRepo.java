package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {

    @Query(nativeQuery = true , value = "Select name From managementsystem.department")
    public List<String> departmentNames();
    @Query(nativeQuery = true , value = "SELECT MAX(id) FROM managementsystem.department")
    public Integer getMaxdepID();
    @Query(nativeQuery = true , value = "Select name From managementsystem.department")
    public List<String> getAllDepartments();


}
