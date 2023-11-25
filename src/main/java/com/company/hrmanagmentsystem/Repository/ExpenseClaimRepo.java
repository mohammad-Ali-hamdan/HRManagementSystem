package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseClaimRepo extends JpaRepository<ExpenseClaimEntity, Integer> {
    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.expenseclaim")
    public Integer MaxId();
    @Query(nativeQuery = true , value = "Select * From managementsystem.expenseclaim Where employee_Id = :empId" )
    public List<ExpenseClaimEntity> getExpenseClaimPerEmployee(@Param("empId") Integer empId);
}
