package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.ExpenseClaimEntryService;
import com.company.hrmanagmentsystem.model.ExpenseClaimEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiExpenseClaimEntity")
public class ExpenseClaimEntryController {
    @Autowired
    private ExpenseClaimEntryService service;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll()
    {
        List<ExpenseClaimEntryDTO> DTOS =  service.getAll();
        if(!DTOS.isEmpty())
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
            return new ResponseEntity<>("Null List" , HttpStatus.OK );
    }

    @GetMapping("/getByid/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        ExpenseClaimEntryDTO DTO = service.getById(id);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("invalid id" , HttpStatus.OK );
    }

    @PostMapping("/craete")
    public ResponseEntity<?> create(@RequestBody Map<String , Object> DTO)
    {
        ExpenseClaimEntryDTO DTOCreated = service.Create(DTO);
        if(DTOCreated != null)
            return new ResponseEntity<>(DTOCreated , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Create!" , HttpStatus.OK );

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  delete(@PathVariable Integer id)
    {
        boolean isDeleted =  service.delete(id);
        if(isDeleted)
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Delete!" , HttpStatus.OK );
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String , Object> DTO)
    {
        ExpenseClaimEntryDTO UpdatedDTO =  service.update(DTO);
        if(UpdatedDTO != null)
            return new ResponseEntity<>(UpdatedDTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update!" , HttpStatus.OK );
    }



}
