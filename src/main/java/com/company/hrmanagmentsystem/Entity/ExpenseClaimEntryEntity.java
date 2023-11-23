package com.company.hrmanagmentsystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "expenseclaimentry", schema = "managementsystem", catalog = "")
public class ExpenseClaimEntryEntity {
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "date")
    private Date date;
    @Basic@Column(name = "expense_type")
    private int expenseType;
    @Basic@Column(name = "expense_claim")
    private int expenseClaim;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private Double total;


}
