package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.DepartmentService;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.DepartmentEmployeeDTO;
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
    public ResponseEntity<?> getalldepartment() // get all departments
    {
        List<DepartmentDTO> departmentDTOList =  depService.getallDepartments();
        if(!departmentDTOList.isEmpty())
            return new ResponseEntity<List<DepartmentDTO>>(departmentDTOList , HttpStatus.OK);
        else
            return new ResponseEntity<String>("No content", HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}") // get department by Id
    public ResponseEntity<?> getalldepartment(@PathVariable Integer id) // get departments by id
    {
        DepartmentDTO departmentDTO =  depService.getDepartmentById(id);
        if(departmentDTO != null)
            return new ResponseEntity<DepartmentDTO>(departmentDTO , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.OK);
    }

    @PostMapping("/createDep") // Create department
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO createdDepartmentDTO =  depService.createDepartment(departmentDTO);
        if(createdDepartmentDTO != null)
            return new ResponseEntity<DepartmentDTO>(createdDepartmentDTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("failed to create" , HttpStatus.OK);
    }

    @DeleteMapping("/deleteDep/{id}") // Delete department
    public ResponseEntity<String> deleteDepartment(@PathVariable Integer id)
    {
        boolean isDeleted =  depService.deleteDep(id);
        if(isDeleted)
            return new ResponseEntity<String>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<String>("Invalid Id" , HttpStatus.OK);

    }



    @GetMapping("/getemployeesByDepartment")
    public ResponseEntity<List<DepartmentDTO>> getemployeesByAllDepartment()  // Get list of departments with All list pf Employess
    {
        return new ResponseEntity<List<DepartmentDTO>>( depService.getemployeesByAllDepartment(),  HttpStatus.OK) ;
    }

    @GetMapping("/getAllEmployeeInDepartment/{id}")
    public ResponseEntity<DepartmentEmployeeDTO> getAllEmployeeNamesInSpecificDepartment(@PathVariable Integer id)
    {

        return new ResponseEntity<DepartmentEmployeeDTO>(depService.getAllEmployeeNamesInSpecificDepartment(id) ,  HttpStatus.OK) ;
    }

    @GetMapping("/getAllEmployeesEntityInSpecificDepartment/{id}")
    public ResponseEntity<DepartmentDTO> getAllEmployeesEntityInSpecificDepartment(@PathVariable Integer id)
    {
        return new ResponseEntity<>(depService.getAllEmployeesEntityInSpecificDepartment(id) , HttpStatus.OK);
    }


}
