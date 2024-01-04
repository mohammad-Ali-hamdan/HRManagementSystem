package com.company.hrmanagmentsystem.Service;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import com.company.hrmanagmentsystem.Mapper.LeavedetailsMapper;
import com.company.hrmanagmentsystem.Repository.EmployeeRepo;
import com.company.hrmanagmentsystem.Repository.LeaveTypeRepo;
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
    @Autowired private EmployeeRepo employeeRepo;
    @Autowired private LeaveTypeRepo leaveTypeRepo;

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
            LeavedetailsDTO dto = leavedetailsMapper.leavedetailsDTO(leavedetailsEntity1Optional.get());
            return dto;
        }
        return null;
    }

    @Override
    public java.sql.Date test(Integer id)
    {
        LeavedetailsEntity1 entity = leavedetailsRepo.findById(id).get();
        return entity.getFrom();

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
    public LeavedetailsDTO SubmitLeave(LeavedetailsDTO leavedetailsDTO)
    {


        if (
                employeeRepo.existsById(leavedetailsDTO.getEmployee()) &&
                leaveTypeRepo.existsById(leavedetailsDTO.getLeavetype()))
        {
            LeavedetailsDTO dto = new LeavedetailsDTO();
            LeavedetailsEntity1 leavedetailsEntity1 =  leavedetailsMapper.leavedetailsEntity(leavedetailsDTO);
            Integer id = leavedetailsRepo.getMaxleavedetailsID() + 1;
            leavedetailsEntity1.setId(id);
            leavedetailsRepo.save(leavedetailsEntity1);
            dto =  leavedetailsMapper.leavedetailsDTO(leavedetailsEntity1);
            return dto;
        }
        else return null;


    }

    @Override
    public LeavedetailsDTO updateLeaveDetails(Map<String , Object> DTO)
    {
        Integer id = (Integer) DTO.get("id");
        Integer empId = (Integer) DTO.get("employee");
        Integer leavetypeId = (Integer) DTO.get("leavetype");
        if (leavedetailsRepo.existsById(id) &&
                employeeRepo.existsById(empId) &&
                leaveTypeRepo.existsById(leavetypeId))
        {
            LeavedetailsEntity1 entity = leavedetailsRepo.findById(id).get();
            Class entityClass = LeavedetailsEntity1.class;
            DTO.forEach((k,v) ->
            {
                Field field = ReflectionUtils.findField(entityClass , k);
                field.setAccessible(true);
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
            LeavedetailsDTO dtoUpdated = new LeavedetailsDTO();
            dtoUpdated = leavedetailsMapper.leavedetailsDTO(entity);
            return dtoUpdated;
        }
        else return null;


    }


    @Override
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
    public List<LeavedetailsDTO> getLeavePagination(Map<String , Object> pageable )
    {
        Integer  pageSize = (Integer) pageable.get("pageSize");
        Integer  pageNumber = (Integer) pageable.get("pageNumber");
        Integer  employee = (Integer) pageable.get("employee");
        Integer  leave = (Integer) pageable.get("leave");
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<LeavedetailsEntity1> filteredList = leavedetailsRepo.findByEmpIdLeaveId(employee , leave , page );
        List<LeavedetailsDTO> DTOS = new ArrayList<>();
        for(LeavedetailsEntity1 entity: filteredList)
        {
            DTOS.add(leavedetailsMapper.leavedetailsDTO(entity));
        }
        return DTOS;
    }


    @Override
    public List<LeavedetailsDTO> pageableComponent(Map<String , Object> pageable ){
        Integer  pageSize = (Integer) pageable.get("pageSize");
        Integer  pageNumber = (Integer) pageable.get("pageNumber");
        String  text = (String) pageable.get("text");
        Pageable page = PageRequest.of(pageNumber, pageSize);
        Page<LeavedetailsEntity1> filteredList = leavedetailsRepo.pageableComponent(text , page );
        List<LeavedetailsDTO> DTOS = new ArrayList<>();
        for(LeavedetailsEntity1 entity: filteredList)
        {
            DTOS.add(leavedetailsMapper.leavedetailsDTO(entity));
        }
        return DTOS;
    }



}
