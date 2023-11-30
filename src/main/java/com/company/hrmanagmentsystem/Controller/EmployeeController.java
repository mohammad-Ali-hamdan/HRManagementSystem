package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.Service.EmployeeService;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiEmployee")
public class EmployeeController {

    @Autowired public EmployeeService service;


    @GetMapping("/getall")
    public ResponseEntity<?> getAll()
    {
        List<EmployeeDTO> employeeDTOList =   service.getAllEmployees();
        if(!employeeDTOList.isEmpty())
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOList , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getByID(@PathVariable Integer id)
    {
        EmployeeDTO employeeDTO =  service.getByID(id);
        if(employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.OK);
    }




    @GetMapping("/getempbydep/{id}") //
    public ResponseEntity<?> ListEmployeesByDepartment(@PathVariable Integer id)
    {
        List<EmployeeDTO> employeeDTOS =  service.ListEmployeesByDepartment(id);
        if (employeeDTOS.isEmpty())
            return new ResponseEntity<>(null ,HttpStatus.OK );
        else
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS ,HttpStatus.OK);
    }

    @PostMapping("/createEmp")
    public ResponseEntity<?> createEmp(@RequestBody EmployeeDTO employeeDTOInput)
    {
        EmployeeDTO employeeDTO =  service.CreateEmployee(employeeDTOInput);
        if (employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.OK );
        else
            return new ResponseEntity<>("The department id must be valid and employee id must be unique " ,HttpStatus.OK);

    }




    @DeleteMapping("/deleteEmp/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Integer id)
    {
        String isDeleted = service.deleteEmp(id);
        if(isDeleted.equals("true"))
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else if(isDeleted.equals("false"))
            return new ResponseEntity<>("Failed to delete , Invalid id" , HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<String>("Can't delete the record , foreign key restriction", HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/updateEmp")
    public ResponseEntity<?> updateEmp(@RequestBody Map<String , Object> dto)
    {
        Integer id = (Integer) dto.get("id");
        EmployeeDTO employeeDTO =  service.updateEmp(dto , id);
        if (employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.OK );
        else
            return new ResponseEntity<>("The department id must be valid and employee id must be valid" ,HttpStatus.OK);

    }



}
