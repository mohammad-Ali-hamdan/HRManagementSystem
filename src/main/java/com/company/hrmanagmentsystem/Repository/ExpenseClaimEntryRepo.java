package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseClaimEntryRepo extends JpaRepository<ExpenseClaimEntryEntity , Integer> {

    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.expenseclaimentry")
    public Integer MaxId();
    @Query(nativeQuery = true , value = "Select sum(total) From managementsystem.expenseclaimentry Where expense_claim = :expenseClaimId")
    public Double CalculateTotalClaim(@Param("expenseClaimId") Integer claimId);
    @Query(nativeQuery = true , value = "Select id From managementsystem.expenseclaimentry Where  expense_claim = :expenseClaimId")
    public List<Integer> CheckExist(@Param("expenseClaimId") Integer claimId);
    @Query(nativeQuery = true , value = "Select * from managementsystem.expenseclaimentry Where expense_claim = :expenseClaimId")
    public List<ExpenseClaimEntryEntity> AllExpensesPerClaim(@Param("expenseClaimId") Integer claimId);


}
