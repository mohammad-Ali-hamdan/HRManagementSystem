package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.EmployeeExpenseClaimsDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimWithEmployeeDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpenseClaimService {
    public List<ExpenseClaimDTO> getAll();
    public ExpenseClaimDTO getById(Integer id);
    public String delete(Integer id);
    public ExpenseClaimDTO update (Map<String , Object> DTO);
    public ExpenseClaimDTO createWithDateSQLFormat(ExpenseClaimDTO DTO);
    public ExpenseClaimDTO create(Map<String , Object> DTO);
    public ExpenseClaimDTO Submit(Map<String  , Object> Entry) ;// Entry : expenseClaimId and EmployeeId and description  and Status and date
    public List<ExpenseClaimDTO> getAllClaimPerEmployee (Integer empId);
    public EmployeeExpenseClaimsDTO getAllClaimsWithDetailsPerEmployee(Integer id);
    public Map<String,Object>  getTotalPerEmployeePerType(Map<String , Object>  body);
    public List<ExpenseClaimDTO> paginationWithFilter(Map<String , Object> pageable);
    public List<ExpenseClaimDTO> paginationWithOutFilter(Map<String , Object> pageable);
//    public List<ExpenseClaimWithEmployeeDTO> paginationWithNamesEmployee (Map<String , Object> pageable);

}
