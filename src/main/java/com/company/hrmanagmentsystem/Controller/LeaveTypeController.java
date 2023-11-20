package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.LeaveTypeService;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.LeaveTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apileaveType")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService ;

    @GetMapping("/getall")
    public ResponseEntity<List<LeaveTypeDTO>> getallleaveType()
    {
        List<LeaveTypeDTO> leaveTypeDTOS =  leaveTypeService.getall();
        if(!leaveTypeDTOS.isEmpty())
            return new ResponseEntity<List<LeaveTypeDTO>>(leaveTypeDTOS , HttpStatus.OK);
        else
            return new ResponseEntity<List<LeaveTypeDTO>>(leaveTypeDTOS , HttpStatus.NOT_FOUND);


    }

    @GetMapping("/getbyid/{id}") // get department by Id
    public ResponseEntity<LeaveTypeDTO> getbyid(@PathVariable Integer id) // get departments by id
    {
        LeaveTypeDTO leaveTypeDTO  =  leaveTypeService.getbyID(id);
        if(leaveTypeDTO != null)
            return new ResponseEntity<LeaveTypeDTO>(leaveTypeDTO , HttpStatus.OK);
        else
            return new ResponseEntity<LeaveTypeDTO>(leaveTypeDTO , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteleavetype/{id}") // Delete department
    public ResponseEntity<String> deleteleaveType(@PathVariable Integer id)
    {
        boolean isDeleted =  leaveTypeService.deleteLeaveType(id);
        if(isDeleted)
            return new ResponseEntity<String>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<String>("Not exist , Failed to Delete" , HttpStatus.NOT_FOUND);

    }

    @PostMapping("/createleavetype")
    public ResponseEntity<?> createleaveType(@RequestBody LeaveTypeDTO leaveTypeDTO)
    {
        LeaveTypeDTO leaveTypeDTO1 =  leaveTypeService.CreateLeaveType(leaveTypeDTO);
        if(leaveTypeDTO1 != null)
            return new ResponseEntity<LeaveTypeDTO>(leaveTypeDTO1 , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);

    }

    @PatchMapping("/updateleavetype")
    public ResponseEntity<LeaveTypeDTO> updateLeaveType(@RequestBody Map<String , Object> leaveTypeDTO)
    {
        LeaveTypeDTO DTO =  leaveTypeService.Update(leaveTypeDTO);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
    }

}
