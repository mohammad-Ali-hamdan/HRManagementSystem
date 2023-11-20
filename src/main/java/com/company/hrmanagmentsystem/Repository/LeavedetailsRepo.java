package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LeavedetailsRepo extends JpaRepository<LeavedetailsEntity1, Integer> {
    //checking if exist:
    @Procedure(name = "CheckLeaveExist")
    public Integer CheckLeaveExist(@Param("idi") Integer id);
    //get leave by date and employee
    @Procedure(name = "SearchforleavesFromTo")
    public List<LeavedetailsEntity1> SearchforleavesFromTo(@Param("datefrom")Date datefrom , @Param("dateto") Date dateto , @Param("employee_id")  Integer employee_id);
    //max id
    @Procedure(name="getMaxleavedetailsID")
    public Integer getMaxleavedetailsID();
}
