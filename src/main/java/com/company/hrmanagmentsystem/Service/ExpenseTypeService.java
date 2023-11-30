package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.model.ExpenseTypeDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseTypeService {
    public List<ExpenseTypeDTO> getAll();
    public ExpenseTypeDTO getById(Integer id);
    public String deleteById(Integer id);
    public ExpenseTypeDTO createExpanseType(ExpenseTypeDTO DTO);
    public ExpenseTypeDTO updateExpenseType(Map<String, Object> DtOToUpdate);


}
