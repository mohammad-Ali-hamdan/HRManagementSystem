package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.ExpenseClaimService;
import com.company.hrmanagmentsystem.model.EmployeeExpenseClaimsDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            return new ResponseEntity<>("Failed to Update , Invalid id" , HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id)
    {
        boolean isDeleted = service.delete(id);
        if(isDeleted)
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to delete , Invalid id" , HttpStatus.OK);
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
            return new ResponseEntity<>("Failed to create , Invalid Id" , HttpStatus.OK);
    }


    @PostMapping("/Submit") //// Entry : expenseClaimId and empId and description  and status and date
    public ResponseEntity<?> Submit(@RequestBody Map<String , Object> entry)
    {
        ExpenseClaimDTO DTO =  service.Submit(entry);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Submit Failed , Expense claim Id and Employee Id must be valid " , HttpStatus.OK);
    }

    @GetMapping("/getClaimPerEmployee/{id}") // All Claims per employee
    public ResponseEntity<?> getClaimPerEmployee(@PathVariable Integer id)
    {
        List<ExpenseClaimDTO> DTOS = service.getAllClaimPerEmployee(id);
        if(DTOS !=null)
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
        return new ResponseEntity<>("No claims for this employee" , HttpStatus.OK);
    }

    @GetMapping("/getAllClaimsPerEmployee/{id}") // All Claims with entries per employee
    public ResponseEntity<?> getAllClaimsWithDetailsPerEmployee(@PathVariable Integer id)
    {
        EmployeeExpenseClaimsDTO DTO = service.getAllClaimsWithDetailsPerEmployee(id);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("Invalid id " , HttpStatus.OK);
    }




    @GetMapping("/getTotalPerEmployeePerType") // Total per Employee and per claim type
    public ResponseEntity<?> getTotalPerEmployeePerType(@RequestBody Map<String , Object> body) // body : empId , claimType
    {
        Map<String,Object> result = service.getTotalPerEmployeePerType(body);
        if(result.get("total") !=null)
            return new ResponseEntity<>(result , HttpStatus.OK);
        else
            return new ResponseEntity<>("No claims of this claim type for this employee" , HttpStatus.OK);
    }
}
