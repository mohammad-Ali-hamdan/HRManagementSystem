package com.company.hrmanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;


@Data
public class ExpenseClaimEntryDTO {
    private int id;

    //@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd/MM/yyyy")
    private Date date;  // DD/MM/YYYY
    private int expenseType;
    private int expenseClaim;
    private String description;
    private Double total;



}
