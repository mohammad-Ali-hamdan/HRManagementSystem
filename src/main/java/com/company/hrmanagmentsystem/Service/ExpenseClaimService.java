package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.EmployeeExpenseClaimsDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimService {
    public List<ExpenseClaimDTO> getAll();
    public ExpenseClaimDTO getById(Integer id);
    public boolean delete(Integer id);
    public ExpenseClaimDTO update (Map<String , Object> DTO);
    public ExpenseClaimDTO createWithDateSQLFormat(ExpenseClaimDTO DTO);
    public ExpenseClaimDTO create(Map<String , Object> DTO);
    public ExpenseClaimDTO Submit(Map<String  , Object> Entry) ;// Entry : expenseClaimId and EmployeeId and description  and Status and date
    public List<ExpenseClaimDTO> getAllClaimPerEmployee (Integer empId);
    public EmployeeExpenseClaimsDTO getAllClaimsWithDetailsPerEmployee(Integer id);

}
