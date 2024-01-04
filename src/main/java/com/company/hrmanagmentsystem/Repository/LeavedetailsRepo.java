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


//    @Query(nativeQuery = true , value = "Select * from managementsystem.leavedetails Where employee =  :empId  && leavetype = :leaveId")
//    public Page<LeavedetailsEntity1> findByEmpIdLeaveId(int empId , int leaveId , Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM managementsystem.leavedetails " +
            "WHERE (:empId = 0 OR employee = :empId) AND (:leaveId = 0 OR leavetype = :leaveId)")
    public Page<LeavedetailsEntity1> findByEmpIdLeaveId(int empId, int leaveId, Pageable pageable);

    @Query(nativeQuery = true , value ="  Select l.id , l.leavetype  , l.fromdate ,  l.Todate , l.number_of_days ,  l.note , l.employee  from managementsystem.leavedetails as l\n" +
            "join managementsystem.employee  as e On e.id = l.employee \n" +
            "join managementsystem.leavetype as t On t.id = l.leavetype Where lower(e.name) like CONCAT('%', lower(:text), '%') OR lower(t.name) like CONCAT('%', lower(:text), '%') ")
    public Page<LeavedetailsEntity1> pageableComponent(String text  , Pageable pageable);
}
