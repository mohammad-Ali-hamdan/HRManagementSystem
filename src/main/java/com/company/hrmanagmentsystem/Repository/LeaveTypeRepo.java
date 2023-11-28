package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.LeaveTypeEntity;
import lombok.experimental.PackagePrivate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveTypeRepo extends JpaRepository<LeaveTypeEntity , Integer> {

    @Query(nativeQuery = true , value = "SELECT MAX(id) FROM managementsystem.leavetype")
    public Integer leaveTypeMaxID();
    @Query(nativeQuery = true , value = "SELECT id FROM managementsystem.leavetype Where id = :idi ")
    public List<Integer> CheckTypeExist(@Param("idi") Integer id);
    @Query(nativeQuery = true , value = "SELECT name From managementsystem.leavetype Where id = :typename")
    public String findTypeById(@Param("typename") Integer id);
    @Query(nativeQuery = true , value = "SELECT * FROM managementsystem.leavetype Where name = :nameLeave")
    public List<LeaveTypeEntity> CheckLeaveTypeNameExist(@Param("nameLeave") String name);
}
