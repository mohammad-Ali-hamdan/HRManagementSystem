package com.company.hrmanagmentsystem.Controller;

import com.company.hrmanagmentsystem.Service.ExpenseTypeService;
import com.company.hrmanagmentsystem.model.ExpenseTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiExpenseType")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseTypeController {
    @Autowired private ExpenseTypeService service;


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll()
    {
        List<ExpenseTypeDTO> DTOS =  service.getAll();
        if(!DTOS.isEmpty())
            return new ResponseEntity<>(DTOS , HttpStatus.OK);
        else
            return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id)
    {
        ExpenseTypeDTO DTO = service.getById(id);
        if(DTO != null)
            return new ResponseEntity<>(DTO , HttpStatus.OK);
        else
            return new ResponseEntity<>("There is no expense Type fo the provided id " , HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id)
    {
        String isDeleted =  service.deleteById(id);
        if(isDeleted.equals("true"))
            return new ResponseEntity<>("Deleted" , HttpStatus.OK);
        else if(isDeleted.equals("false"))
            return new ResponseEntity<>("Failed to Delete . There is no expense Type fo the provided id" , HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<String>("Can't delete the record , foreign key restriction", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createExpenseType(@RequestBody ExpenseTypeDTO DTO)
    {
        ExpenseTypeDTO DTO1 =  service.createExpanseType(DTO);
        if(DTO1 !=null)
            return new ResponseEntity<>(DTO1 , HttpStatus.OK);
        else
            return new ResponseEntity<>("Duplicated expense type " , HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateExpenseType(@RequestBody Map<String , Object> DTO)
    {
        ExpenseTypeDTO DTO1 =  service.updateExpenseType(DTO);
        if(DTO1 !=null)
            return new ResponseEntity<>(DTO1 , HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update ,Invalid id " , HttpStatus.OK);
    }
}
