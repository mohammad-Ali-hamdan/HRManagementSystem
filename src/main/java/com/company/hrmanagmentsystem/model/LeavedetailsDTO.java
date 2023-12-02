package com.company.hrmanagmentsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@Data
public class LeavedetailsDTO {

    private int id;
    private int leavetype;
    //@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date from;
    //@JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private Date to;
    private int numberOfDays;
    private String note;
    private int employee;
}
