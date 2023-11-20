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
public class LeavedetailsController {

    @Autowired private LeavedetailsService service;

    @GetMapping("/getall")
    public ResponseEntity<List<LeavedetailsDTO>> getall()
    {
        List<LeavedetailsDTO> DTOS = service.getall();
        if(!DTOS.isEmpty())
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<LeavedetailsDTO> getbyId(@PathVariable Integer id)
    {
     LeavedetailsDTO DTO =  service.getbyId(id);
     if(DTO != null)
         return new ResponseEntity<>(DTO , HttpStatus.OK);
     else
         return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
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
    public ResponseEntity<?> updateLeaveDetails(@RequestBody Map<String , Object> DtOToUpdate)
    {
        LeavedetailsDTO DTO = service.updateLeaveDetails(DtOToUpdate);
        if(DTO !=null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed To Update" , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/SubmitLeave")
    public ResponseEntity<LeavedetailsDTO> SubmitLeave(@RequestBody LeavedetailsDTO leavedetailsDTO)
    {
        LeavedetailsDTO DTO =  service.SubmitLeave(leavedetailsDTO);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }

    @PostMapping("/getleaveEmployeeRange")
    public ResponseEntity<List<LeavedetailsDTO>> getLeaveEmployeeWithinRange(@RequestBody LeaveEmployeeDTO DTO)
    {

         Integer employeeId =  DTO.getEmployeeId();
         Date FromDate = DTO.getFromDate();
         Date ToDate = DTO.getToDate();
         List<LeavedetailsDTO> DTOS = service.getLeaveEmployeeWithinRange(employeeId , FromDate , ToDate);
         if(!DTOS.isEmpty())
             return new ResponseEntity<>(DTOS , HttpStatus.OK);
         else
             return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }

    @GetMapping("/listpagination")
    public ResponseEntity<List<LeavedetailsDTO>> Pagination(Integer pageSize  , Integer pageNumber,
    Integer employee , Integer leave)
    {
        List<LeavedetailsDTO> DTOS = service.getLeavePagination(pageNumber , pageSize , employee ,leave);
        return new ResponseEntity<>(DTOS , HttpStatus.OK);
    }



}
