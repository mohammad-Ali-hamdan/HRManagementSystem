package com.company.hrmanagmentsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
public class EmployeeExpenseClaimsDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private int departmentId;
    //private List<ExpenseClaimEntryDTO> expenseEntryList;
    private List<ExpenseClaimDTO> expenseTotal ;
}
