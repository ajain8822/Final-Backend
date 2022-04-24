package com.admindesk.repository;

import com.admindesk.models.LeaveModel;
import com.admindesk.models.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveModel,Long> {

	  @Query(value = "SELECT * FROM LEAVES WHERE USER_ID = ?1", nativeQuery = true)
	  List<LeaveModel> getAllLeave(String id);
	
}
