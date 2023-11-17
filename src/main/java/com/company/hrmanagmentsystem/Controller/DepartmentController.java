package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.DepartmentService;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiDepartment")
public class DepartmentController {

    @Autowired
    private DepartmentService depService ;

    @GetMapping("/getall") // Get all department
    public ResponseEntity<List<DepartmentDTO>> getalldepartment() // get all departments
    {
        List<DepartmentDTO> departmentDTOList =  depService.getallDepartments();
        if(!departmentDTOList.isEmpty())
            return new ResponseEntity<List<DepartmentDTO>>(departmentDTOList , HttpStatus.OK);
        else
            return new ResponseEntity<List<DepartmentDTO>>(departmentDTOList , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getbyid") // get department by Id
    public ResponseEntity<DepartmentDTO> getalldepartment(@RequestParam Integer id) // get departments by id
    {
        DepartmentDTO departmentDTO =  depService.getDepartmentById(id);
        if(departmentDTO != null)
            return new ResponseEntity<DepartmentDTO>(departmentDTO , HttpStatus.OK);
        else
            return new ResponseEntity<DepartmentDTO>(departmentDTO , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/createDep") // Create department
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO CreatedDepartmentDTO =  depService.createDepartment(departmentDTO);
        if(CreatedDepartmentDTO != null)
            return new ResponseEntity<DepartmentDTO>(CreatedDepartmentDTO , HttpStatus.OK);
        else
            return new ResponseEntity<DepartmentDTO>(CreatedDepartmentDTO , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteDep") // Delete department
    public ResponseEntity<String> deleteDepartment(@RequestParam Integer id)
    {
        boolean isDeleted =  depService.deleteDep(id);
        if(isDeleted)
            return new ResponseEntity<String>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<String>("Failed to Delete" , HttpStatus.NOT_FOUND);

    }






}
