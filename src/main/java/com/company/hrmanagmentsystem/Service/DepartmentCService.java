package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.DepartmentEntity;
import com.company.hrmanagmentsystem.Mapper.DepartmentMapper;
import com.company.hrmanagmentsystem.Repository.DepartmentRepo;
import com.company.hrmanagmentsystem.model.DepartmentDTO;
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

}
