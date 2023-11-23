package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import com.company.hrmanagmentsystem.Mapper.ExpenseClaimEntryMapper;
import com.company.hrmanagmentsystem.Repository.ExpenseClaimEntryRepo;
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

//    @Override
//    public ExpenseClaimEntryDTO Create(ExpenseClaimEntryDTO DTO)
//    {
//        if(!repo.existsById(DTO.getId()))
//        {
//            DTO.setId(repo.MaxId() + 1);
//
//            // Error
//            Field[] fields = DTO.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                if (field.getType() == java.sql.Date.class) {
//                    SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
//                    java.sql.Date dateValue = null;
//                    try {
//                        // Parse the incoming date string
//                        Date utilDate = inputFormat.parse(DTO.getDate().toString());
//
//                        // Convert java.util.Date to java.sql.Date
//                        dateValue = new java.sql.Date(utilDate.getTime());
//                    } catch (ParseException e) {
//                        e.printStackTrace(); // Handle the parsing exception appropriately
//                    }
//                    DTO.setDate(dateValue);
//                }
//            }
//
//            ExpenseClaimEntryEntity entity =  mapper.expClaimEntryEntity(DTO);
//            return mapper.expClaimEntryDTO(repo.save(entity)) ;
//        }
//        else
//            return null;
//    }

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
        ExpenseClaimEntryEntity entity = repo.findById(id).get();
        Class entityClass = ExpenseClaimEntryEntity.class;
        DTO.forEach((k,v) ->
        {
            Field field = ReflectionUtils.findField(entityClass , k);
            field.setAccessible(true);
            //ReflectionUtils.setField(field , entity , v);
            // I get error as field  Date.Sql so convert it to Date.Sql
            //method found on net
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





}
