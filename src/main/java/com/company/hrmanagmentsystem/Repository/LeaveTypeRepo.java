package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveTypeRepo extends JpaRepository<LeaveTypeEntity , Integer> {

    @Procedure(name = "leaveTypeMaxID")
    public Integer leaveTypeMaxID();
    @Procedure(name = "CheckTypeExist")
    public List<Integer> CheckTypeExist(@Param("idi") Integer id);
    @Query(nativeQuery = true , value = "SELECT name From managementsystem.leavetype Where id = :typename")
    public String findTypeById(@Param("typename") Integer id);
    @Procedure(name= "CheckLeaveTypeNameExist")
    public List<LeaveTypeEntity> CheckLeaveTypeNameExist(@Param("nameLeave") String name);
}
