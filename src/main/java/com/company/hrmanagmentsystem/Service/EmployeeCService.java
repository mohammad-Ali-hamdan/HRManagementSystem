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
        if (employeeDTOS.isEmpty())
        {return null;}
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
    @Transactional
    public EmployeeDTO CreateEmployee(EmployeeDTO employeeDTOInput) // Create a new Employee
    {
        employeeDTOInput.setId(empRepo.GetMaxEmpId() + 1);
        boolean IsExistDepartment = false;
        for(Integer departmentId : empRepo.departmentIDs())
        {
            if(employeeDTOInput.getDepartmentId() == departmentId)
            {
                IsExistDepartment = true;
                break;
            }
        }
        if (IsExistDepartment)
        {
            EmployeeEntity employeeEntity = empMap.empEntity(employeeDTOInput);
            empRepo.save(employeeEntity);
            EmployeeDTO employeeDTO = empMap.empDTO(employeeEntity);
            return employeeDTO ;
        }
        else
            //console.log("Your alert message here");
            return null;



    }

    @Override
    @Transactional
    public void deleteEmp(Integer id) // Delete existence employee
    {
        boolean IsExistEmployee = false;
        List<Integer> listEmpIds = empRepo.employeeIDs();
        for(Integer idi : listEmpIds)
        {
            if(id == idi)
            {
                IsExistEmployee = true;
                break;
            }
        }
        if(IsExistEmployee)
        {
            empRepo.deleteById(id);
        }

    }

    @Override
    @Transactional
    public EmployeeDTO updateEmp(Map<String , Object> dtoObject , Integer id ) // Update Employee using ReflectionUtils
    {
        boolean IsExistDepartment = false;
        for(Integer departmentId : empRepo.departmentIDs())
        {
            if(dtoObject.get("departmentId") == departmentId)
            {
                IsExistDepartment = true;
                break;
            }
        }
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
    @Transactional
    public List<EmployeeDTO> ListEmployeesByDepartment(String name) // List of Employees By Department
    {


       boolean IsDepartmentExist = false;
       for (String depName : depRepo.departmentNames())
       {
           if(depName.equalsIgnoreCase(name))
           {
               IsDepartmentExist = true ;
               break;
           }
       }
       if(IsDepartmentExist)
       {
           DepartmentEntity  departmentEntity = depRepo.findByName(name);
           Integer depId = departmentEntity.getId();
           List<EmployeeEntity> allemployees = empRepo.findAll();
           List<EmployeeDTO> employeeDTOS = new ArrayList<>();
           for(EmployeeEntity employeeEntity : allemployees)
           {
               if(employeeEntity.getDepartmentId() == depId)
               {
                   employeeDTOS.add(empMap.empDTO(employeeEntity));
               }
           }

           return employeeDTOS ;
       }
       else return null;

    }

    @Override
    @Transactional
    public List<EmployeeDTO> ListEmployeesByDepartment2(String name) //Another way of List of Employees By Department
    {
        boolean IsDepartmentExist = false;
        for (String depName : depRepo.departmentNames())
        {
            if(depName.equalsIgnoreCase(name))
            {
                IsDepartmentExist = true ;
                break;
            }
        }
        if(IsDepartmentExist)
        {
            List<Integer> ids = empRepo.ListEmployeesInDepartment(name);
            List<EmployeeDTO> employeeDTOS = new ArrayList<>();
            for(Integer id : ids)
            {
                employeeDTOS.add(empMap.empDTO(empRepo.findById(id).get()));
            }
            return employeeDTOS ;
        }
        else return null;


    }

    @Override
    @Transactional
    public List<EmployeeDTO> ListEmployeesByDepartment3(String name) //Another way of List of Employees By Department
    {

        List<EmployeeEntity> entityList = empRepo.ListEmployeesEntityInDepartment(name);
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(EmployeeEntity entity : entityList)
        {
            employeeDTOS.add(empMap.empDTO(entity));
        }
        return employeeDTOS;



    }





}
