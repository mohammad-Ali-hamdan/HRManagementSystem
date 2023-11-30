package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimEntryDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExpenseClaimEntryService {
    public List<ExpenseClaimEntryDTO> getAll();
    public ExpenseClaimEntryDTO getById(Integer id);
    //public ExpenseClaimEntryDTO Create(ExpenseClaimEntryDTO DTO);
    public ExpenseClaimEntryDTO Create(Map<String , Object> DTO);
    public boolean delete(Integer id);
    public ExpenseClaimEntryDTO update(Map<String , Object> DTO);
    public java.sql.Date ConvertDateToSQLDate(Date date);
    public Date ConvertDateToJavaDate(java.sql.Date date);
    public List<Map<String , Object>> createEntriesToClaim(List<Map<String , Object>> entries);
}
