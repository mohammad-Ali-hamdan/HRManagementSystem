package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.ExpenseClaimService;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiExpenseClaim")
public class ExpenseClaimController {
    @Autowired private ExpenseClaimService service;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll()
    {
        List<ExpenseClaimDTO> DTOS =  service.getAll();
        if(!DTOS.isEmpty())
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
            return new ResponseEntity<>("No Content Found" , HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        ExpenseClaimDTO DTO = service.getById(id);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid id" , HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody Map<String , Object> DTO)
    {
        ExpenseClaimDTO DTOUpdated =  service.update(DTO);
        if(DTOUpdated != null)
            return new ResponseEntity<>(DTOUpdated , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to Update" , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id)
    {
        boolean isDeleted = service.delete(id);
        if(isDeleted)
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to delete" , HttpStatus.OK);
    }

    @PostMapping("/createWithDateSQLFormat")
    public ResponseEntity<?> createWithDateSQLFormat(@RequestBody ExpenseClaimDTO DTO)
    {
        ExpenseClaimDTO DTOCreated = service.createWithDateSQLFormat(DTO);
        if(DTOCreated != null)
            return new ResponseEntity<>(DTOCreated , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to create" , HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> craete(@RequestBody Map<String , Object> DTO)
    {
        ExpenseClaimDTO DTOCreated = service.create(DTO);
        if(DTOCreated != null)
            return new ResponseEntity<>(DTOCreated , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to create" , HttpStatus.OK);
    }


    @PostMapping("/Submit") //// Entry : expenseClaimId and empId and description  and Status and date
    public ResponseEntity<?> Submit(@RequestBody Map<String , Object> entry)
    {
        ExpenseClaimDTO DTO =  service.Submit(entry);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Submit Failed , Expense claim Id is invalid " , HttpStatus.OK);
    }

    @GetMapping("/getClaimPerEmployee/{id}")
    public ResponseEntity<?> getClaimPerEmployee(@PathVariable Integer id)
    {
        List<ExpenseClaimDTO> DTOS = service.getAllClaimPerEmployee(id);
        if(DTOS !=null)
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
        return new ResponseEntity<>("No claims for this employee" , HttpStatus.OK);
    }
}
