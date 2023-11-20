package com.company.hrmanagmentsystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "leavedetails", schema = "managementsystem", catalog = "")
public class LeavedetailsEntity1 {
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "leavetype")
    private int leavetype;
    @Basic@Column(name = "fromdate")
    private Date from;
    @Basic@Column(name = "Todate")
    private Date to;
    @Basic@Column(name = "number_of_days")
    private int numberOfDays;
    @Basic@Column(name = "note")
    private String note;
    @Basic@Column(name = "employee")
    private int employee;


}
