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
    public ResponseEntity< List<EmployeeDTO>> getAll()
    {
        List<EmployeeDTO> employeeDTOList =   service.getAllEmployees();
        if(!employeeDTOList.isEmpty())
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOList , HttpStatus.OK);
        else
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOList , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getbyid")
    public ResponseEntity<EmployeeDTO> getByID(@RequestParam Integer id)
    {
        EmployeeDTO employeeDTO =  service.getByID(id);
        if(employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO , HttpStatus.OK);
        else
            return new ResponseEntity<EmployeeDTO>(employeeDTO , HttpStatus.NOT_FOUND);
    }



    @GetMapping("/getempbydep")
    public ResponseEntity<List<EmployeeDTO>> ListEmployeesByDepartment(@RequestParam String name)
    {
        List<EmployeeDTO> employeeDTOS =  service.ListEmployeesByDepartment(name);
        if (employeeDTOS.isEmpty())
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS ,HttpStatus.NOT_FOUND );
        else
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS ,HttpStatus.OK);
    }

    @PostMapping("/createEmp")
    public ResponseEntity<EmployeeDTO> createEmp(@RequestBody EmployeeDTO employeeDTOInput)
    {
        EmployeeDTO employeeDTO =  service.CreateEmployee(employeeDTOInput);
        if (employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.OK );
        else
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.NOT_FOUND);

    }




    @DeleteMapping("/deleteEmp")
    public ResponseEntity<String> deleteEmp(@RequestParam Integer id)
    {
        boolean ISdeleted = service.deleteEmp(id);
        if (ISdeleted)
            return new ResponseEntity<String>("deleted" ,HttpStatus.OK );
        else
            return new ResponseEntity<String>("Failed to delete!" ,HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/updateEmp")
    public ResponseEntity<EmployeeDTO> updateEmp(@RequestBody Map<String , Object> dto)
    {
        Integer id = (Integer) dto.get("id");
        EmployeeDTO employeeDTO =  service.updateEmp(dto , id);
        if (employeeDTO != null)
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.OK );
        else
            return new ResponseEntity<EmployeeDTO>(employeeDTO ,HttpStatus.NOT_FOUND);

    }


}
