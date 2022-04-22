package com.admindesk.repository;

import com.admindesk.models.LeaveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveModel,Long> {
}
