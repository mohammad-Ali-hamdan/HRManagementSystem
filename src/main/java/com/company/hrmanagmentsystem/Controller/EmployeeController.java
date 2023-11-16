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
    public List<EmployeeDTO> getAll()
    {
        return service.getAllEmployees();
    }

    @GetMapping("/getbyid")
    public EmployeeDTO getByID(@RequestParam Integer id)
    {
        return service.getByID(id);
    }

    @GetMapping("/getempbydep")
    public List<EmployeeDTO> ListEmployeesByDepartment(@RequestParam String name)
    {
        return service.ListEmployeesByDepartment(name);
    }

    @GetMapping("/getempbydep3")
    public ResponseEntity<List<EmployeeDTO>> ListEmployeesByDepartment3(@RequestParam String name)
    {
        List<EmployeeDTO> employeeDTOS =  service.ListEmployeesByDepartment3(name);
        if (employeeDTOS.isEmpty())
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS ,HttpStatus.NOT_FOUND );
        else
            return new ResponseEntity<List<EmployeeDTO>>(employeeDTOS ,HttpStatus.OK);
    }

    @PostMapping("/createEmp")
    public EmployeeDTO createEmp(@RequestBody EmployeeDTO employeeDTOInput)
    {
        return service.CreateEmployee(employeeDTOInput);
    }
    @PostMapping("/createEmp2")
    public ResponseEntity<EmployeeDTO> createEmp2(@RequestBody EmployeeDTO employeeDTOInput)
    {
        EmployeeDTO dto = service.CreateEmployee(employeeDTOInput);
        if(dto == null)
            return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(dto , HttpStatus.OK);

    }

    @DeleteMapping("/deleteEmp")
    public void deleteEmp(@RequestParam Integer id)
    {
        service.deleteEmp(id);
    }

    @PatchMapping("/updateEmp")
    public EmployeeDTO updateEmp(@RequestBody Map<String , Object> dto)
    {
        Integer id = (Integer) dto.get("id");
        return service.updateEmp(dto , id);
    }


}
