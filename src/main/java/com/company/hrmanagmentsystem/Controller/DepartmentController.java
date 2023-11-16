package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.DepartmentService;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiDepartment")
public class DepartmentController {

    @Autowired
    private DepartmentService depService ;

    @GetMapping("/getall")
    public List<DepartmentDTO> getalldepartment() // get all departments
    {
        return depService.getallDepartments();
    }

    @GetMapping("/getbyid")
    public DepartmentDTO getalldepartment(@RequestParam Integer id) // get departments by id
    {
        return depService.getDepartmentById(id);
    }

    @PostMapping("/createDep")
    public DepartmentDTO createDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        return depService.createDepartment(departmentDTO);
    }




}
