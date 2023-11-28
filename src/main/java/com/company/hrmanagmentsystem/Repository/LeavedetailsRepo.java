package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LeavedetailsRepo extends JpaRepository<LeavedetailsEntity1, Integer> {
    //checking if exist:
    @Query(nativeQuery = true , value = "SELECT id  FROM managementsystem.leavedetails Where id = :idi")
    public Integer CheckLeaveExist(@Param("idi") Integer id);
    //get leave by date and employee
    @Query(nativeQuery = true , value = "Select * From managementsystem.leavedetails Where (fromdate >= :datefrom && Todate<= :dateto && employee = :empId ) ")
    public List<LeavedetailsEntity1> SearchforleavesFromTo(@Param("datefrom")Date datefrom , @Param("dateto") Date dateto , @Param("empId")  Integer empId);
    //max id
    @Query(nativeQuery = true , value = "SELECT MAX(id) FROM managementsystem.leavedetails")
    public Integer getMaxleavedetailsID();

    @Query(nativeQuery = true , value = "Select * from managementsystem.leavedetails Where employee =  :empId  && leavetype = :leaveId")
    public List<LeavedetailsEntity1> getLeavedetailsbyEmp(@Param("empId") Integer empId , @Param("leaveId") Integer leaveId );


    @Query(nativeQuery = true , value = "Select * from managementsystem.leavedetails Where employee =  :empId  && leavetype = :leaveId")
    public Page<LeavedetailsEntity1> findByEmpIdLeaveId(int empId , int leaveId , Pageable pageable);


}
