package com.admindesk.repository;

import java.util.List;
import java.util.Optional;

import com.admindesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  
  @Query(value = "SELECT * FROM USERS WHERE EMPLOYEE_ID = ?1", nativeQuery = true)
  List<User> getUserByEmpId(String empID);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
