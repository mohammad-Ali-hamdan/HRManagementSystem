package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.ExpenseTypeEntity;
import com.company.hrmanagmentsystem.Mapper.ExpenseTypeMapper;
import com.company.hrmanagmentsystem.Repository.ExpenseTypeRepo;
import com.company.hrmanagmentsystem.model.ExpenseTypeDTO;
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
public class ExpenseTypeCService implements ExpenseTypeService{
    @Autowired private ExpenseTypeRepo expTypeRepo;
    @Autowired private ExpenseTypeMapper expTypeMap;

    @Override
    public List<ExpenseTypeDTO> getAll()
    {
        List<ExpenseTypeEntity> listOfExpType =  expTypeRepo.findAll();
        List<ExpenseTypeDTO> DTOS = new ArrayList<>();
        for(ExpenseTypeEntity entity:listOfExpType)
        {
            DTOS.add(expTypeMap.expenseTypeDTO(entity));
        }
        return DTOS;
    }

    @Override
    public ExpenseTypeDTO getById(Integer id)
    {
        Optional<ExpenseTypeEntity> expenseTypeEntityOptional = expTypeRepo.findById(id);
        if(expenseTypeEntityOptional.isPresent())
        {
            ExpenseTypeDTO DTO = expTypeMap.expenseTypeDTO(expenseTypeEntityOptional.get());
            return DTO;
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id)
    {
        boolean isDeleted = false;
        if(expTypeRepo.existsById(id))
        {
            expTypeRepo.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public ExpenseTypeDTO createExpanseType(ExpenseTypeDTO DTO)
    {
        if(expTypeRepo.CheckExist(DTO.getName()).isEmpty()) // not exist before
        {
            Integer id = expTypeRepo.MaxId() + 1;
            ExpenseTypeEntity entity = expTypeMap.expenseTypeEntity(DTO);
            entity.setId(id);
            expTypeRepo.save(entity);
            ExpenseTypeDTO DTOCreated = expTypeMap.expenseTypeDTO(entity);
            return DTOCreated;

        }
        return null;
    }

    @Override
    public ExpenseTypeDTO updateExpenseType(Map<String, Object> DtOToUpdate)
    {
        Integer id = (Integer) DtOToUpdate.get("id");
        if(expTypeRepo.existsById(id))
        {
            ExpenseTypeEntity entity = expTypeRepo.findById(id).get();
            Class entityClass = ExpenseTypeEntity.class;
            DtOToUpdate.forEach((k,v) ->
            {
                Field field = ReflectionUtils.findField(entityClass , k);
                field.setAccessible(true);
                ReflectionUtils.setField(field , entity , v);
            });
            expTypeRepo.saveAndFlush(entity);
            ExpenseTypeDTO DTO = expTypeMap.expenseTypeDTO(entity);
            return DTO;
        }
        else return null;

    }

}
