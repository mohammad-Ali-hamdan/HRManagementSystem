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
@CrossOrigin(origins = "http://localhost:4200")
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
            return new ResponseEntity<>( HttpStatus.NOT_FOUND ); //"Null List" ,
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
            return new ResponseEntity<>("Failed to Create! , invalid id" , HttpStatus.NOT_FOUND );

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  delete(@PathVariable Integer id)
    {
        boolean isDeleted =  service.delete(id);
        if(isDeleted)
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Delete! , Invalid id" , HttpStatus.NOT_FOUND );
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String , Object> DTO)
    {
        ExpenseClaimEntryDTO UpdatedDTO =  service.update(DTO);
        if(UpdatedDTO != null)
            return new ResponseEntity<>(UpdatedDTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update! , Invalid id" , HttpStatus.NOT_FOUND );
    }

    @PostMapping("/craeteEntriesToClaim")
    public ResponseEntity<?> craeteEntriesToClaim(@RequestBody List<Map<String , Object>> entries)
    {
        //List<Map<String , Object>> enteredEntries= service.createEntriesToClaim(entries);
        Integer claimId = service.createEntriesToClaim(entries);
        if(claimId != 0)
            return new ResponseEntity<>(claimId , HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid entries" , HttpStatus.OK);
    }


    @GetMapping("/expensesEntryByClaimId/{id}")
    public ResponseEntity<?> expensesEntryByClaimId(@PathVariable Integer id){
        List<ExpenseClaimEntryDTO> DTOS =  service.expensesEntryByClaimId(id);
        if(!DTOS.isEmpty())
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
            return new ResponseEntity<>( HttpStatus.OK ); //"Null List" ,

    }



    @DeleteMapping("/deleteEntryAndSubmitClaim/{id}")
    public ResponseEntity<?> deleteEntryAndSubmitClaim(@PathVariable Integer id)
    {
        boolean isDeleted =  service.deleteEntryAndSubmitClaim(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted and Updated" , HttpStatus.OK );
        }
        else
            return new ResponseEntity<>("Failed to Delete! , Invalid id" , HttpStatus.NOT_FOUND );
    }


}
