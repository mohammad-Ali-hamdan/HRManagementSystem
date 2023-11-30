package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.EmployeeEntity;
import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntity;
import com.company.hrmanagmentsystem.Entity.ExpenseClaimEntryEntity;
import com.company.hrmanagmentsystem.Mapper.EmployeeMapper;
import com.company.hrmanagmentsystem.Mapper.ExpenseClaimEntryMapper;
import com.company.hrmanagmentsystem.Mapper.ExpenseClaimMapper;
import com.company.hrmanagmentsystem.Repository.EmployeeRepo;
import com.company.hrmanagmentsystem.Repository.ExpenseClaimEntryRepo;
import com.company.hrmanagmentsystem.Repository.ExpenseClaimRepo;
import com.company.hrmanagmentsystem.model.EmployeeDTO;
import com.company.hrmanagmentsystem.model.EmployeeExpenseClaimsDTO;
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
public class ExpenseClaimCService implements ExpenseClaimService{
    @Autowired private ExpenseClaimRepo repo;
    @Autowired private ExpenseClaimMapper mapper;
    @Autowired private ExpenseClaimEntryRepo expenseClaimEntryRepo;
    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private EmployeeMapper empMapper;
    @Autowired private ExpenseClaimEntryMapper expenseClaimEntryMapper;


    @Override
    public List<ExpenseClaimDTO> getAll()
    {
        List<ExpenseClaimEntity> listOptional = repo.findAll();
        List<ExpenseClaimDTO> DTOS = new ArrayList<>();
        if(!listOptional.isEmpty())
        {
            for(ExpenseClaimEntity entity : listOptional )
            {
                DTOS.add(mapper.DTO(entity));
            }
            return DTOS;
        }
        else return null;

    }

    @Override
    public ExpenseClaimDTO getById(Integer id)
    {
        if(repo.existsById(id))
        {
            return mapper.DTO(repo.findById(id).get());
        }
        else return null;
    }

    @Override
    public boolean delete(Integer id)
    {
        boolean isDeleted = false ;
        if(repo.existsById(id))
        {
            repo.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public ExpenseClaimDTO update (Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        if(repo.existsById(id))
        {
            ExpenseClaimEntity entity = repo.findById(id).get();
            Class ClassEntity = ExpenseClaimEntity.class;
            DTO.forEach((k,v) ->
            {
                if(v != null)
                {
                    Field field = ReflectionUtils.findField(ClassEntity , k);
                    field.setAccessible(true);

                    if(field.getType() == java.sql.Date.class)
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
                        ReflectionUtils.setField(field , entity , v);
                    }
                }

            });
            repo.saveAndFlush(entity);
            return mapper.DTO(entity);
        }
        else return null;

    }


    @Override
    public ExpenseClaimDTO createWithDateSQLFormat(ExpenseClaimDTO DTO)
    {
        if(!repo.existsById(DTO.getId()))
        {
           Integer id =  repo.MaxId() + 1;
           DTO.setId(id);
           ExpenseClaimEntity entity = mapper.entity(DTO);
           repo.save(entity);
           return mapper.DTO(entity);
        }
        else return null;
    }

    @Override
    public ExpenseClaimDTO create(Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        if(!repo.existsById(id))
        {
            Integer newId = repo.MaxId() + 1;;
            DTO.put("id" , newId);
            ExpenseClaimEntity entity = new ExpenseClaimEntity();
            Class entityClass = ExpenseClaimEntity.class;
            DTO.forEach((k,v) ->
            {
                if(v !=null)
                {
                    Field field = ReflectionUtils.findField(entityClass , k);
                    field.setAccessible(true);
                    if(field.getType() == java.sql.Date.class)
                    {
                        String dateString = (String) v;
                        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat outputFormat= new SimpleDateFormat("yyyy-MM-dd");
                        Date utilDate = null;
                        try {
                            utilDate = inputFormat.parse(dateString);
                        }
                        catch (ParseException e)
                        {}
                        String formattedDate = outputFormat.format(utilDate);
                        java.sql.Date date = java.sql.Date.valueOf(formattedDate);
                        ReflectionUtils.setField(field , entity , date);

                    }
                    else
                    {
                        ReflectionUtils.setField(field , entity , v);
                    }
                }

            });
            repo.saveAndFlush(entity);
            return mapper.DTO(entity);
        }
        else return null;

    }


    @Override
    public ExpenseClaimDTO Submit(Map<String  , Object> Entry) // Entry : expenseClaimId and empId and description  and Status and date
    {
        Integer empId = (Integer) Entry.get("empId");
        Integer expenseClaimId = (Integer) Entry.get("expenseClaimId");
        String description = (String) Entry.get("description");
        String status = (String) Entry.get("status");
        String dateString = (String) Entry.get("date");
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat outputFormat= new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = inputFormat.parse(dateString);
        }
        catch (ParseException e)
        {}
        String formattedDate = outputFormat.format(utilDate);
        java.sql.Date date = java.sql.Date.valueOf(formattedDate);
        List<Integer> entryIds = expenseClaimEntryRepo.CheckExist(expenseClaimId);
        if(!entryIds.isEmpty() && employeeRepo.existsById(empId)) //exist
        {
            Double total =  expenseClaimEntryRepo.CalculateTotalClaim(expenseClaimId);
            ExpenseClaimEntity entitySubmit = new ExpenseClaimEntity();
            entitySubmit.setId(expenseClaimId);
            entitySubmit.setDate(date);
            entitySubmit.setDescription(description);
            entitySubmit.setStatus(status);
            entitySubmit.setTotal(total);
            entitySubmit.setEmployeeId(empId);
            repo.save(entitySubmit);
            return getById(expenseClaimId);

        }
        else return null;

    }


    @Override
    public List<ExpenseClaimDTO> getAllClaimPerEmployee (Integer empId)
    {
        List<ExpenseClaimEntity> expenseClaimEntityList = repo.getExpenseClaimPerEmployee(empId);
        if(!expenseClaimEntityList.isEmpty())
        {
            List<ExpenseClaimDTO> DTOS = new ArrayList<>();
            for(ExpenseClaimEntity entity : expenseClaimEntityList)
            {
                DTOS.add(mapper.DTO(entity));
            }
            return DTOS;
        }
        else return null;
    }


    @Override
    public EmployeeExpenseClaimsDTO getAllClaimsWithDetailsPerEmployee(Integer id)
    {
        Optional<EmployeeEntity> employeeEntityOptional =  employeeRepo.findById(id);
        if(employeeEntityOptional.isPresent())
        {
            EmployeeExpenseClaimsDTO DTO = empMapper.empExpenseDTO(employeeEntityOptional.get());
            List<ExpenseClaimEntity> expenseClaimEntityList = repo.getExpenseClaimPerEmployee(id);
            List<ExpenseClaimDTO> claimDTOS = new ArrayList<>();
            for(ExpenseClaimEntity expenseClaimEntity : expenseClaimEntityList)
            {
                claimDTOS.add(mapper.DTO(expenseClaimEntity));
            }
            for(ExpenseClaimDTO claimDTO : claimDTOS)
            {
                List<ExpenseClaimEntryEntity> expenseClaimEntryEntityList = expenseClaimEntryRepo.AllExpensesPerClaim(claimDTO.getId());
                List<ExpenseClaimEntryDTO> expenseClaimEntryDTOList = new ArrayList<>();
                for(ExpenseClaimEntryEntity entity : expenseClaimEntryEntityList)
                {
                    expenseClaimEntryDTOList.add(expenseClaimEntryMapper.expClaimEntryDTO(entity));
                }
                claimDTO.setExpenseEntryList(expenseClaimEntryDTOList);
            }
            DTO.setExpenseTotal(claimDTOS);
            return DTO;

        }
        else    return null;

    }


    @Override
    public Map<String,Object>  getTotalPerEmployeePerType(Map<String , Object>  body)
    {
        Integer empId = (Integer) body.get("empId");
        Integer claimType = (Integer) body.get("claimType");
        Optional<Double>  total  = repo.getTotalPerEmployeePerType(empId , claimType);
        Map<String , Object> returnBody = new HashMap<>();
        returnBody.put("emloyeeId" , empId);
        returnBody.put("claimType" , claimType);
        returnBody.put("total" , total);

        return returnBody;
    }


}
