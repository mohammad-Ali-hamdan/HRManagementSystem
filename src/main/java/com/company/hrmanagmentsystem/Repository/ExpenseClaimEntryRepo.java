package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseClaimEntryRepo extends JpaRepository<ExpenseClaimEntryEntity , Integer> {

    @Query(nativeQuery = true , value = "Select Max(id) From managementsystem.expenseclaimentry")
    public Integer MaxId();


}
