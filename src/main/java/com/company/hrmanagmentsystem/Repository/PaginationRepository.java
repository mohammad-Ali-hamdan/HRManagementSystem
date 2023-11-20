package com.company.hrmanagmentsystem.Repository;

import com.company.hrmanagmentsystem.Entity.LeavedetailsEntity1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaginationRepository extends JpaRepository<LeavedetailsEntity1 , Integer> {
}
