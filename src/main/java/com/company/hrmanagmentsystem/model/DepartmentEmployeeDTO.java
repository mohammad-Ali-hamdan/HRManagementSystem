package com.company.hrmanagmentsystem.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
public class DepartmentEmployeeDTO {
    private int id;
    private String name;
    private List<String> namesOfEmployees;
}
