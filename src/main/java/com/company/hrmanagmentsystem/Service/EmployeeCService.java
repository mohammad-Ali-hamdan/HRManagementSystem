package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.Mapper.EmployeeMapper;
import com.company.hrmanagmentsystem.Repository.DepartmentRepo;
import com.company.hrmanagmentsystem.Repository.EmployeeRepo;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeCService implements EmployeeService {

    @Autowired
    private EmployeeRepo empRepo;
    @Autowired
    private EmployeeMapper empMap;
    @Autowired
    private DepartmentRepo depRepo ;



    @Override
    public List<EmployeeDTO> getAllEmployees () // Display all employess
    {

        List<EmployeeEntity> employeeList = empRepo.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity entity : employeeList)
        {
            employeeDTOS.add(empMap.empDTO(entity));
        }
        return employeeDTOS;

    }
    @Override
    public EmployeeDTO getByID(Integer id) // display employee by Id
    {
       Optional<EmployeeEntity> optionalEmployeeEntity =  empRepo.findById(id);
       if(optionalEmployeeEntity.isPresent())
       {
           EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
           EmployeeDTO employeeDTO = empMap.empDTO(employeeEntity);
           return employeeDTO;
       }
       else
           return null;
    }

    @Override
    public EmployeeDTO CreateEmployee(EmployeeDTO employeeDTOInput) // Create a new Employee
    {
        employeeDTOInput.setId(empRepo.GetMaxEmpId() + 1);
        boolean IsExistDepartment =  depRepo.existsById(employeeDTOInput.getDepartmentId());
        if (IsExistDepartment)
        {
            EmployeeEntity employeeEntity = empMap.empEntity(employeeDTOInput);
            empRepo.save(employeeEntity);
            EmployeeDTO employeeDTO = empMap.empDTO(employeeEntity);
            return employeeDTO ;
        }
        else

            return null;



    }

    @Override
    public boolean deleteEmp(Integer id) // Delete existence employee
    {
        boolean isDeleted = false;
        Optional<EmployeeEntity> employeeEntityOptional = empRepo.findById(id);
        if(employeeEntityOptional.isPresent())
        {
            empRepo.deleteById(id);
            isDeleted = true;

        }
        return isDeleted;

    }

    @Override
    public EmployeeDTO updateEmp(Map<String , Object> dtoObject , Integer id ) // Update Employee using ReflectionUtils
    {
        Integer depId = (Integer) dtoObject.get("departmentId");
        boolean IsExistDepartment =  depRepo.existsById(depId);
        if(IsExistDepartment)
        {
            Optional<EmployeeEntity> employeeEntityOptional =  empRepo.findById(id);
            if(employeeEntityOptional.isPresent())
            {
                EmployeeEntity employeeEntity = employeeEntityOptional.get();
                Class employeeEntityClass = EmployeeEntity.class;
                //Reflection
                dtoObject.forEach( (k,v) -> {
                    Field field = ReflectionUtils.findField(employeeEntityClass , k) ;
                    field.setAccessible(true);
                    ReflectionUtils.setField(field , employeeEntity , v);

                });

                empRepo.saveAndFlush(employeeEntity);
                return empMap.empDTO(employeeEntity);
            }
            else return null;
        }
        else return null;



    }



    @Override
    public List<EmployeeDTO> ListEmployeesByDepartment(Integer id) // List of Employees By Department
    {

        List<EmployeeEntity> entityList = empRepo.employeesByDepartmentID(id);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity entity : entityList)
        {
            employeeDTOS.add(empMap.empDTO(entity));
        }
        return employeeDTOS;



    }





}
