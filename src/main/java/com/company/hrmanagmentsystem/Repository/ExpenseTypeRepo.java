package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseTypeRepo extends JpaRepository<ExpenseTypeEntity , Integer> {

//    @Procedure(name = "MaxId")
//    public Integer MaxId();
//    @Procedure(name = "CheckExist")
//    public List<ExpenseTypeEntity> CheckExist(@Param("TypeName") String name);
    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.expensetype ")
    public Integer MaxId();
    @Query(nativeQuery = true , value = "Select * From managementsystem.expensetype Where name = :TypeName ")
    public List<ExpenseTypeEntity> CheckExist(@Param("TypeName") String name);
}
