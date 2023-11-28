package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExpenseClaimRepo extends JpaRepository<ExpenseClaimEntity, Integer> {
    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.expenseclaim")
    public Integer MaxId();
    @Query(nativeQuery = true , value = "Select * From managementsystem.expenseclaim Where employee_Id = :empId" )
    public List<ExpenseClaimEntity> getExpenseClaimPerEmployee(@Param("empId") Integer empId);
    @Query(nativeQuery = true , value = "Select c.id , c.date , c.description , c.total , c.status , c.employee_Id From expenseclaim c join expenseclaimentry e On c.id = e.expense_claim Where e.expense_type = :empId && c.employee_Id = :claimtype")
    public List<ExpenseClaimEntity> getAllClaimsPerEmployeePerType(@Param("empId") Integer empId , @Param("claimtype") Integer claimtype);
    @Query(nativeQuery = true , value = "Select sum(e.total) From expenseclaim c join expenseclaimentry e On c.id = e.expense_claim Where e.expense_type = :claimtype && c.employee_Id = :empId" )
    public Optional<Double> getTotalPerEmployeePerType(@Param("empId") Integer empId , @Param("claimtype") Integer claimtype);
}
