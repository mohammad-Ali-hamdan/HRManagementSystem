package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.Mapper.DepartmentMapper;
import com.company.hrmanagmentsystem.Mapper.EmployeeMapper;
import com.company.hrmanagmentsystem.Repository.DepartmentRepo;
import com.company.hrmanagmentsystem.Repository.EmployeeRepo;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
import com.company.hrmanagmentsystem.model.DepartmentEmployeeDTO;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentCService implements DepartmentService{
    @Autowired private DepartmentRepo depRepo;
    @Autowired private DepartmentMapper depMap ;
    @Autowired private EmployeeRepo emprepo;
    @Autowired private EmployeeMapper empMap;

    @Override
    public List<DepartmentDTO> getallDepartments()
    {
        List<DepartmentEntity> departmentEntities = depRepo.findAll();
        if(!departmentEntities.isEmpty())
        {
            List<DepartmentDTO> departmentDTOS = new ArrayList<>();
            for(DepartmentEntity entity : departmentEntities )
            {
                departmentDTOS.add(depMap.depDTO(entity));
            }
            return departmentDTOS;
        }
        else return null;



    }

    @Override
    public DepartmentDTO getDepartmentById(Integer id)
    {
        Optional<DepartmentEntity> departmentEntity = depRepo.findById(id);
        if(departmentEntity.isPresent())
        {
            return depMap.depDTO(departmentEntity.get());
        }
        else return null;
    }


    @Override
    @Transactional
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO)
    {
        List<String> departmentNames = depRepo.departmentNames();// check if department exist
        String name = departmentDTO.getName();
        boolean IsExistDepartment = false;
        for (String depname : departmentNames)
        {
            if(depname.equalsIgnoreCase(name))
            {
                IsExistDepartment = true;
                break;
            }
        }
        if(!IsExistDepartment)
        {
            Integer id = depRepo.getMaxdepID() + 1 ;
            DepartmentEntity departmentEntity =  depMap.depEntity(departmentDTO);
            departmentEntity.setId(id);
            depRepo.save(departmentEntity);
            return depMap.depDTO(departmentEntity);

        }
        else return null;



    }

    @Override
    public boolean deleteDep(Integer id) // Delete existence department
    {
        boolean isDeleted = false;
        Optional<DepartmentEntity> departmentEntityOptional = depRepo.findById(id);
        if(departmentEntityOptional.isPresent())
        {
            depRepo.deleteById(id);
            isDeleted = true;

        }
        return isDeleted;

    }

//    @Override
//    public List<DepartmentDTO> getemployeesByAllDepartment() // Get All employess in All Department
//    {
//        List<DepartmentEntity> listDepartments = depRepo.findAll();
//        List<DepartmentDTO> DTOs = new ArrayList<>();
//        for(DepartmentEntity entity:listDepartments)
//        {
//            DepartmentDTO DTO = new DepartmentDTO();
//            DTO.setId(entity.getId());
//            DTO.setName(entity.getName());
//            List<EmployeeEntity> employeeEntityList =  emprepo.listEmployeeByDepId(entity.getId());
//            List<EmployeeDTO> EmpDTOS = new ArrayList<>();
//            for(EmployeeEntity entity1 :employeeEntityList )
//            {
//             EmpDTOS.add(empMap.empDTO(entity1))  ;
//            }
//            DTO.setEmpList(EmpDTOS);
//            DTOs.add(DTO);
//        }
//        return DTOs;
//
//
//
//    }

    @Override
    public List<DepartmentDTO> getemployeesByAllDepartment() // Get All employess in All Department
    {
        List<DepartmentEntity> departmentEntityList = depRepo.findAll();
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for(DepartmentEntity entity:departmentEntityList)
        {
            DepartmentDTO DTO = depMap.depDTO(entity);
            Integer id = DTO.getId();
            List<EmployeeEntity> employeeEntityList = emprepo.listEmployeeByDepId(id);
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            for(EmployeeEntity employeeEntity :employeeEntityList)
            {
                employeeDTOList.add(empMap.empDTO(employeeEntity));
            }
            DTO.setEmpList(employeeDTOList);
            departmentDTOList.add(DTO);
        }
        return departmentDTOList;
    }

    @Override
    public DepartmentEmployeeDTO getAllEmployeeNamesInSpecificDepartment(Integer id)
    {
        DepartmentEntity departmentEntity = depRepo.findById(id).get();
        DepartmentEmployeeDTO DTO =  depMap.depEmpDTO(departmentEntity);
        List<String> names = emprepo.namesOfEmployeeInDepartment(id);
        DTO.setNamesOfEmployees(names);
        return DTO;
    }

    @Override
    public DepartmentDTO getAllEmployeesEntityInSpecificDepartment(Integer id)
    {
        List<EmployeeEntity> listOfEmployeeInDepartment = emprepo.listEmployeeByDepId(id);
        List<EmployeeDTO> listOfEmployeeDTOInDepartment = new ArrayList<>();
        for(EmployeeEntity entity: listOfEmployeeInDepartment)
        {
            listOfEmployeeDTOInDepartment.add(empMap.empDTO(entity));
        }
        Optional<DepartmentEntity> optionalDepartmentEntity = depRepo.findById(id);
        if(optionalDepartmentEntity.isPresent())
        {
            DepartmentDTO DTO = depMap.depDTO(optionalDepartmentEntity.get());
            DTO.setEmpList(listOfEmployeeDTOInDepartment);
            return DTO;
        }
        return null;
    }

}
