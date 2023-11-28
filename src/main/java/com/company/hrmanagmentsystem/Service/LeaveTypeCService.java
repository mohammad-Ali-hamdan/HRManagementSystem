package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import com.company.hrmanagmentsystem.Mapper.LeaveTypeMapper;
import com.company.hrmanagmentsystem.Repository.LeaveTypeRepo;
import com.company.hrmanagmentsystem.model.LeaveTypeDTO;
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
public class LeaveTypeCService implements LeaveTypeService{

    @Autowired
    private LeaveTypeRepo leaveTypeRepo;
    @Autowired
    private LeaveTypeMapper leaveTypeMapper;

    @Override
    public LeaveTypeDTO getbyID(Integer id)
    {
        Optional<LeaveTypeEntity> leaveTypeEntityOptional = leaveTypeRepo.findById(id);
        if(leaveTypeEntityOptional.isPresent())
        {
            return leaveTypeMapper.leaveTypeDTO(leaveTypeEntityOptional.get());
        }
        else return null;


    }
    @Override
    public List<LeaveTypeDTO> getall()
    {
        List<LeaveTypeEntity> leaveTypeEntityList = leaveTypeRepo.findAll();
        List<LeaveTypeDTO> leaveTypeDTOS = new ArrayList<>();
        if(!leaveTypeEntityList.isEmpty())
        {
            for(LeaveTypeEntity entity : leaveTypeEntityList)
            {
                leaveTypeDTOS.add(leaveTypeMapper.leaveTypeDTO(entity));
            }
        }
        return leaveTypeDTOS;
    }

    @Override
    public LeaveTypeDTO CreateLeaveType(LeaveTypeDTO leaveTypeDTO)
    {

        if (!leaveTypeRepo.existsById(leaveTypeDTO.getId()))
        {
            Integer id = leaveTypeRepo.leaveTypeMaxID() + 1;
            LeaveTypeEntity leaveTypeEntity =  leaveTypeMapper.leaveTypeEntity(leaveTypeDTO);
            leaveTypeEntity.setId(id);
            leaveTypeRepo.save(leaveTypeEntity);
            LeaveTypeDTO dto = leaveTypeMapper.leaveTypeDTO(leaveTypeEntity);
            return dto;
        }
       return null;
    }

    @Override
    public boolean deleteLeaveType(Integer id)
    {

        if(leaveTypeRepo.existsById(id))
        {
            leaveTypeRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public LeaveTypeDTO Update(Map<String , Object> leaveType)
    {
        Integer id = (Integer) leaveType.get("id");
        if(leaveTypeRepo.existsById(id))
        {
            LeaveTypeEntity leaveTypeEntity = leaveTypeRepo.findById(id).get();
            Class leaveTypeEntityClass =LeaveTypeEntity.class;
            leaveType.forEach( (k,v) ->
            {

                Field field = ReflectionUtils.findField(leaveTypeEntityClass , k);
                field.setAccessible(true);
                ReflectionUtils.setField(field , leaveTypeEntity , v);

            });
            leaveTypeRepo.saveAndFlush(leaveTypeEntity);
            LeaveTypeDTO dto = leaveTypeMapper.leaveTypeDTO(leaveTypeEntity);
            return dto;
        }
        else return null;

    }



}
