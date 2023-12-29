package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntity;
import com.company.hrmanagmentsystem.model.ExpenseClaimWithEmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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




    @Query(nativeQuery = true, value = "SELECT * FROM managementsystem.expenseclaim " +
            "WHERE (  employee_Id IN (:empId))")
    public Page<ExpenseClaimEntity> paginationExpenseClaimWithFilter(@Param("empId") List<Integer> empId, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT * FROM managementsystem.expenseclaim ")
    public Page<ExpenseClaimEntity> paginationExpenseClaim(Pageable pageable);

//    @Query(nativeQuery = true, value = "SELECT e.id, e.date, e.description, e.total, e.status, emp.name " +
//            "FROM managementsystem.expenseclaim e " +
//            "JOIN managementsystem.employee emp ON e.employee_Id = emp.id " +
//            "WHERE LOWER(emp.name) LIKE LOWER(CONCAT('%', :searchString, '%'))")
//    Page<ExpenseClaimWithEmployeeDTO> paginationWithNamesEmployee(
//            @Param("searchString") String searchString,
//            Pageable pageable
//    );
}
