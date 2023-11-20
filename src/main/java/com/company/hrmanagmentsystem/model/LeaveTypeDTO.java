package com.company.hrmanagmentsystem.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LeaveTypeDTO {
    private int id;
    private String name;
}
