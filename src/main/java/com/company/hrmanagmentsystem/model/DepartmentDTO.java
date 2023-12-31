package com.company.hrmanagmentsystem.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {
    private int id;
    private String name;
    private List<EmployeeDTO> empList;

}
