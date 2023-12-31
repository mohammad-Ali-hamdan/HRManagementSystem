package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.LeavedetailsService;
import com.company.hrmanagmentsystem.model.LeaveEmployeeDTO;
import com.company.hrmanagmentsystem.model.LeavedetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apileavedetails")
@CrossOrigin(origins = "http://localhost:4200")
public class LeavedetailsController {

    @Autowired private LeavedetailsService service;

    @GetMapping("/getall")
    public ResponseEntity<List<LeavedetailsDTO>> getall()
    {
        List<LeavedetailsDTO> dtos = service.getall();
        if(!dtos.isEmpty())
            return new ResponseEntity<>(dtos , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<?> getbyId(@PathVariable Integer id)
    {
     LeavedetailsDTO dto =  service.getbyId(id);
     if(dto != null)
         return new ResponseEntity<>(dto , HttpStatus.OK);
     else
         return new ResponseEntity<>("Invalid id" , HttpStatus.NOT_FOUND);
    }
    @GetMapping("/test/{id}")
    public ResponseEntity<?>test(@PathVariable Integer id)
    {
        return new ResponseEntity<>( service.test(id) , HttpStatus.OK);
    }

    @DeleteMapping("/deletebyId/{id}")
    public ResponseEntity<String> deleteleavedetail(@PathVariable Integer id)
    {
       boolean isDeleted =  service.deleteleavedetail(id);
       if(isDeleted)
           return new ResponseEntity<>("Deleted" , HttpStatus.OK);
       else
           return new ResponseEntity<>("Id not exist. Failed to Delete" , HttpStatus.NOT_FOUND);
    }
    @PatchMapping("/updateLeavedetails")
    public ResponseEntity<?> updateLeaveDetails(@RequestBody Map<String , Object> dtObject)
    {
        LeavedetailsDTO dto = service.updateLeaveDetails(dtObject);
        if(dto !=null)
            return new ResponseEntity<>(dto , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed To Update , leave id , employee id and leave Type should be exist" , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/SubmitLeave")
    public ResponseEntity<?> SubmitLeave(@RequestBody LeavedetailsDTO leavedetailsDTO)
    {
        LeavedetailsDTO dto =  service.SubmitLeave(leavedetailsDTO);
        if(dto != null)
            return new ResponseEntity<>(dto , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to submit , leave id must be unique , employee id and leave Type should be exist" , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getleaveEmployeeRange")
    public ResponseEntity<?> getLeaveEmployeeWithinRange(@RequestBody LeaveEmployeeDTO dto) // employeeId,FromDate,ToDate
    {

         Integer employeeId =  dto.getEmployeeId();
         Date fromDate = dto.getFromDate();
         Date toDate = dto.getToDate();
         List<LeavedetailsDTO> dtos = service.getLeaveEmployeeWithinRange(employeeId , fromDate , toDate);
         if(!dtos.isEmpty())
             return new ResponseEntity<>(dtos , HttpStatus.OK);
         else
             return new ResponseEntity<>("There is no leaves in the provided range for this employee" , HttpStatus.NOT_FOUND);
    }


    @PutMapping("/listpagination")
    public ResponseEntity<List<LeavedetailsDTO>> Pagination(@RequestBody Map<String , Object> pageable    )
    {

        List<LeavedetailsDTO> dtos = service.getLeavePagination(pageable);
        return new ResponseEntity<>(dtos , HttpStatus.OK);
    }

    @PutMapping("/pageableComponent")
    public ResponseEntity<List<LeavedetailsDTO>> pageableComponent(@RequestBody Map<String , Object> pageable    )
    {

        List<LeavedetailsDTO> dtos = service.pageableComponent(pageable);
        return new ResponseEntity<>(dtos , HttpStatus.OK);
    }



}
