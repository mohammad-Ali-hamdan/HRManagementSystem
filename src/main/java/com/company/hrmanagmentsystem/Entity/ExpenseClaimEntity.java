package com.company.hrmanagmentsystem.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "expenseclaim", schema = "managementsystem", catalog = "")
public class ExpenseClaimEntity {
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "date")
    private Date date;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private Double total;
    @Basic@Column(name = "status")
    private String status;
    @Basic@Column(name = "employee_Id")
    private int employeeId;


}
