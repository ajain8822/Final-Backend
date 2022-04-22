package com.admindesk.repository;

import com.admindesk.models.LeaveRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILeaveDao extends JpaRepository<LeaveRecord,Long> {


    @Query(value="SELECT * from leave_record l WHERE l.user_user_id = :userId ORDER BY l.leave_id DESC", nativeQuery = true)
    List<LeaveRecord> checkForStatus(@Param("userId") Long userId);


}
