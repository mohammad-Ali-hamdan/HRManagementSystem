package com.company.hrmanagmentsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class ExpenseClaimDTO {
    private int id;
    private Date date;
    private String description;
    private Double total;
    private String status;
    private int employeeId;

    private List<ExpenseClaimEntryDTO> expenseEntryList;


}
