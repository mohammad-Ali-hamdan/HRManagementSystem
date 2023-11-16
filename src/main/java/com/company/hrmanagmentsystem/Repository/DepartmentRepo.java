package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<DepartmentEntity, Integer> {
    public DepartmentEntity findByName (String name);
    @Procedure(name = "departmentNames")
    public List<String> departmentNames();
    @Procedure(name = "getMaxdepID")
    public Integer getMaxdepID();
    @Procedure(name = "getAllDepartments")
    public List<String> getAllDepartments();

}
