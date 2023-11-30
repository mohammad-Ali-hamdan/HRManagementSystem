package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import com.company.hrmanagmentsystem.Mapper.ExpenseClaimEntryMapper;
import com.company.hrmanagmentsystem.Repository.ExpenseClaimEntryRepo;
import com.company.hrmanagmentsystem.model.ExpenseClaimDTO;
import com.company.hrmanagmentsystem.model.ExpenseClaimEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExpenseClaimEntryCService implements ExpenseClaimEntryService{

    @Autowired private  ExpenseClaimEntryRepo repo;
    @Autowired private ExpenseClaimEntryMapper mapper;
    @Autowired private ExpenseClaimService expenseClaimService;


    @Override
    public List<ExpenseClaimEntryDTO> getAll()
    {
        List<ExpenseClaimEntryEntity> listOfClaimEntity =  repo.findAll();
        List<ExpenseClaimEntryDTO> DTOS = new ArrayList<>();
        for(ExpenseClaimEntryEntity entity: listOfClaimEntity)
        {
            DTOS.add(mapper.expClaimEntryDTO(entity));
        }
        return DTOS;
    }

    @Override
    public ExpenseClaimEntryDTO getById(Integer id)
    {
        Optional<ExpenseClaimEntryEntity> optionalExpenseClaimEntryEntity = repo.findById(id);
        if(optionalExpenseClaimEntryEntity.isPresent())
        {
            ExpenseClaimEntryEntity entity = optionalExpenseClaimEntryEntity.get();
            ExpenseClaimEntryDTO DTO = mapper.expClaimEntryDTO(entity);
            return DTO;
        }
        return null;
    }


    @Override
    public ExpenseClaimEntryDTO Create(Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        if(!repo.existsById(id))
        {
            Integer newId = repo.MaxId() +1 ;
            DTO.put("id" , newId);
            ExpenseClaimEntryEntity EntityCreated = new ExpenseClaimEntryEntity();
            EntityCreated.setId(newId);
            Class entityClass1 = ExpenseClaimEntryEntity.class;
            DTO.forEach((k,v) ->
            {
                Field field = ReflectionUtils.findField(entityClass1 , k);
                field.setAccessible(true);
                if (field.getType() == java.sql.Date.class)
                {
                    String dateString = (String) v;
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date utilDate = null;
                    try {
                        utilDate = inputFormat.parse(dateString);
                    } catch (ParseException e) {
                        //e.printStackTrace();
                    }
                    String formattedDate = outputFormat.format(utilDate);
                    java.sql.Date dateValue = java.sql.Date.valueOf(formattedDate);
                    ReflectionUtils.setField(field, EntityCreated, dateValue);
                }
                else
                {
                    ReflectionUtils.setField(field, EntityCreated, v);
                }
            });
            repo.saveAndFlush(EntityCreated);
            return  mapper.expClaimEntryDTO(EntityCreated);

        }
        else
            return null;
    }
    @Override
    public boolean delete(Integer id)
    {
        boolean isDeleted = false;
        if(repo.existsById(id))
        {
            repo.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public ExpenseClaimEntryDTO update(Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        if(repo.existsById(id))
        {
            ExpenseClaimEntryEntity entity = repo.findById(id).get();
            Class entityClass = ExpenseClaimEntryEntity.class;
            DTO.forEach((k,v) ->
            {
                Field field = ReflectionUtils.findField(entityClass , k);
                field.setAccessible(true);
                if (field.getType() == java.sql.Date.class)
                {
                    String dateString = (String) v;
                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date utilDate = null;
                    try {
                        utilDate = inputFormat.parse(dateString);
                    } catch (ParseException e) {
                        //e.printStackTrace();
                    }
                    String formattedDate = outputFormat.format(utilDate);
                    java.sql.Date dateValue = java.sql.Date.valueOf(formattedDate);
                    ReflectionUtils.setField(field, entity, dateValue);
                }
                else
                {
                    ReflectionUtils.setField(field, entity, v);
                }
            });
            repo.saveAndFlush(entity);
            return  mapper.expClaimEntryDTO(entity);
        }
        else return null;

    }

    @Override
    public java.sql.Date ConvertDateToSQLDate(Date date)
    {
        //java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    @Override
    public Date ConvertDateToJavaDate(java.sql.Date date)
    {
        java.util.Date javaDate = new Date(date.getTime());
        return javaDate;
    }


    @Override
    public List<Map<String , Object>> createEntriesToClaim(List<Map<String , Object>> entries)
    {
        for(Map<String, Object> entry : entries)
        {
            Create(entry);
        }
        return entries;


    }




}
