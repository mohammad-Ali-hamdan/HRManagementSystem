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
            return new ResponseEntity<>(null , HttpStatus.OK);
    }
    @GetMapping("/getbyId/{id}")
    public ResponseEntity<LeavedetailsDTO> getbyId(@PathVariable Integer id)
    {
     LeavedetailsDTO DTO =  service.getbyId(id);
     if(DTO != null)
         return new ResponseEntity<>(DTO , HttpStatus.OK);
     else
         return new ResponseEntity<>(null , HttpStatus.OK);
    }

    @DeleteMapping("/deletebyId/{id}")
    public ResponseEntity<String> deleteleavedetail(@PathVariable Integer id)
    {
       boolean isDeleted =  service.deleteleavedetail(id);
       if(isDeleted)
           return new ResponseEntity<>("Deleted" , HttpStatus.OK);
       else
           return new ResponseEntity<>("Id not exist. Failed to Delete" , HttpStatus.OK);
    }
    @PatchMapping("/updateLeavedetails")
    public ResponseEntity<?> updateLeaveDetails(@RequestBody Map<String , Object> DtOToUpdate)
    {
        LeavedetailsDTO DTO = service.updateLeaveDetails(DtOToUpdate);
        if(DTO !=null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed To Update" , HttpStatus.OK);
    }

    @PostMapping("/SubmitLeave")
    public ResponseEntity<?> SubmitLeave(@RequestBody LeavedetailsDTO leavedetailsDTO)
    {
        LeavedetailsDTO DTO =  service.SubmitLeave(leavedetailsDTO);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Duplicate id" , HttpStatus.OK);
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
             return new ResponseEntity<>(null , HttpStatus.OK);
    }

//    @GetMapping("/listpagination")
//    public ResponseEntity<List<LeavedetailsDTO>> Pagination(Integer  pageSize  , Integer pageNumber,
//    Integer employee , Integer leave)
//    {
//        List<LeavedetailsDTO> DTOS = service.getLeavePagination(pageNumber , pageSize , employee ,leave);
//        return new ResponseEntity<>(DTOS , HttpStatus.OK);
//    }

    @GetMapping("/listpagination")
    public ResponseEntity<List<LeavedetailsDTO>> Pagination(@RequestBody Map<String , Object> pageable    )
    {
        Integer  pageSize = (Integer) pageable.get("pageSize");
        Integer  pageNumber = (Integer) pageable.get("pageNumber");
        Integer  employee = (Integer) pageable.get("employee");
        Integer  leave = (Integer) pageable.get("leave");
        List<LeavedetailsDTO> DTOS = service.getLeavePagination(pageNumber , pageSize , employee ,leave);
        return new ResponseEntity<>(DTOS , HttpStatus.OK);
    }

    @GetMapping("/listpagination2")
    public ResponseEntity<List<LeavedetailsDTO>> Pagination2(@RequestBody Map<String , Object> pageable    )
    {
        Integer  pageSize = (Integer) pageable.get("pageSize");
        Integer  pageNumber = (Integer) pageable.get("pageNumber");
        Integer  employee = (Integer) pageable.get("employee");
        Integer  leave = (Integer) pageable.get("leave");
        List<LeavedetailsDTO> DTOS = service.getLeavePagination2(pageNumber , pageSize , employee ,leave);
        return new ResponseEntity<>(DTOS , HttpStatus.OK);
    }



}
