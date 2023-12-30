package com.company.hrmanagmentsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@Data
public class ExpenseClaimWithEmployeeDTO {
    private int id;
    private Date date;
    private String description;
    private Double total;
    private String status;
    private String employeeName;
}
