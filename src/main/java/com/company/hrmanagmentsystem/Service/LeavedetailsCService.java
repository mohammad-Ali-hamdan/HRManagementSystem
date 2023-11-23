package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import com.company.hrmanagmentsystem.Mapper.LeavedetailsMapper;
import com.company.hrmanagmentsystem.Repository.LeavedetailsRepo;
import com.company.hrmanagmentsystem.Repository.PaginationRepository;
import com.company.hrmanagmentsystem.model.LeaveTypeDTO;
import com.company.hrmanagmentsystem.model.LeavedetailsDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeavedetailsCService implements LeavedetailsService{

    @Autowired private LeavedetailsRepo leavedetailsRepo ;
    @Autowired private LeavedetailsMapper leavedetailsMapper;
    @Autowired private PaginationRepository pageRepo;

    @Override
    public List<LeavedetailsDTO> getall()
    {
        List<LeavedetailsEntity1> leavedetailsEntity1List = leavedetailsRepo.findAll();
        List<LeavedetailsDTO> leavedetailsDTOS = new ArrayList<>();

        for(LeavedetailsEntity1 entity : leavedetailsEntity1List)
        {
            leavedetailsDTOS.add(leavedetailsMapper.leavedetailsDTO(entity));
        }
        return leavedetailsDTOS;

    }


    @Override
    public LeavedetailsDTO getbyId(Integer id)
    {
        Optional<LeavedetailsEntity1> leavedetailsEntity1Optional = leavedetailsRepo.findById(id);
        if(leavedetailsEntity1Optional.isPresent())
        {
            LeavedetailsDTO DTO = leavedetailsMapper.leavedetailsDTO(leavedetailsEntity1Optional.get());
            return DTO;
        }
        return null;
    }

    @Override
    public boolean deleteleavedetail(Integer id)
    {
        boolean isDeleted = false;
        if(leavedetailsRepo.findById(id).isPresent())
        {
            leavedetailsRepo.deleteById(id);
            isDeleted = true;
        }
        return isDeleted;

    }

    @Override
    @Transactional
    public LeavedetailsDTO SubmitLeave(LeavedetailsDTO leavedetailsDTO)
    {
        LeavedetailsDTO DTO = new LeavedetailsDTO();
        //checking if already exist:
        if (leavedetailsRepo.CheckLeaveExist(leavedetailsDTO.getId()) == null)
        {
            LeavedetailsEntity1 leavedetailsEntity1 =  leavedetailsMapper.leavedetailsEntity(leavedetailsDTO);
            Integer id = leavedetailsRepo.getMaxleavedetailsID() + 1;
            leavedetailsEntity1.setId(id);
            leavedetailsRepo.save(leavedetailsEntity1);
            DTO =  leavedetailsMapper.leavedetailsDTO(leavedetailsEntity1);

        }
        return DTO;

    }

    @Override
    public LeavedetailsDTO updateLeaveDetails(Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        LeavedetailsEntity1 entity = leavedetailsRepo.findById(id).get();
        Class entityClass = LeavedetailsEntity1.class;
        DTO.forEach((k,v) ->
        {
            Field field = ReflectionUtils.findField(entityClass , k);
            field.setAccessible(true);
            //ReflectionUtils.setField(field , entity , v);
            // I get error as field  Date.Sql so convert it to Date.Sql
            if (field.getType() == java.sql.Date.class)
            {
                java.sql.Date dateValue = java.sql.Date.valueOf((String) v);
                ReflectionUtils.setField(field, entity, dateValue);
            }
            else
            {
                ReflectionUtils.setField(field, entity, v);
            }

        });
        leavedetailsRepo.saveAndFlush(entity);
        LeavedetailsDTO DTOupdated = leavedetailsMapper.leavedetailsDTO(entity);
        return DTOupdated;

    }


    @Override
    @Transactional
    public List<LeavedetailsDTO> getLeaveEmployeeWithinRange(Integer employeeId , Date FromDate , Date ToDate)
    {
        List<LeavedetailsEntity1> leavedetailsEntity1List =  leavedetailsRepo.SearchforleavesFromTo(FromDate , ToDate , employeeId);
        List<LeavedetailsDTO> DTOS = new ArrayList<>();

        for(LeavedetailsEntity1 entity : leavedetailsEntity1List)
        {
            DTOS.add(leavedetailsMapper.leavedetailsDTO(entity));
        }
        return DTOS;


    }
    @Override
    public List<LeavedetailsDTO> getLeavePagination(Integer pageNumber, Integer pageSize,
                                                        Integer employeeId , Integer leavetypeId )
    {
        Pageable pageable = null;
        pageable = PageRequest.of(pageNumber, pageSize);
        //List<LeavedetailsEntity1> pagelist =  pageRepo.findAll(pageable).getContent();
        Page<LeavedetailsEntity1> pagelist =  pageRepo.findAll(pageable);
        List<LeavedetailsDTO> DTOS = new ArrayList<>();
        List<LeavedetailsEntity1> filteredList =  pagelist.stream().filter(x->
                (x.getLeavetype() == leavetypeId && x.getEmployee() == employeeId))
                .collect(Collectors.toList());
        for(LeavedetailsEntity1 entity: filteredList)
        {
            DTOS.add(leavedetailsMapper.leavedetailsDTO(entity));
        }
        //Page<LeavedetailsDTO> result = Page.empty(DTOS);
        return DTOS;

    }



}
