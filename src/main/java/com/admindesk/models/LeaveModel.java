package com.admindesk.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="leaves")
public class LeaveModel {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private long id ;

 @NotBlank
 @Size
 private  String employeeId;

 @Temporal(TemporalType.TIMESTAMP)
 private Date startDate;

 @Temporal(TemporalType.TIMESTAMP)
 private  Date endDate;
 @NotBlank
 @Size
 private String leaveBalance;

 private  String status;

 private String discription;


 public LeaveModel() {
 }

 public LeaveModel(String employeeId, Date startDate, Date endDate , String leaveBalance ,String status,String discription ) {
  this.employeeId = employeeId;
  this.startDate = startDate;
  this.endDate = endDate;
  this.leaveBalance=leaveBalance;
  this.status=status;
  this.discription=discription;
 }

 public String getEmployeeId() {
  return employeeId;
 }

 public void setEmployeeId(String employeeId) {
  this.employeeId = employeeId;
 }

 public Date getStartDate() {
  return startDate;
 }

 public void setStartDate(Date startDate) {
  this.startDate = startDate;
 }

 public Date getEndDate() {
  return endDate;
 }

 public void setEndDate(Date endDate) {
  this.endDate = endDate;
 }
 public String getLeaveBalance() {
  return leaveBalance;
 }

 public void setLeaveBalance(String leaveBalance) {
  this.leaveBalance = leaveBalance;
 }
 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public String getDiscription() {
  return discription;
 }

 public void setDiscription(String discription) {
  this.discription = discription;
 }


 @ManyToOne(cascade = CascadeType.ALL,optional = false)
 @JoinColumn(name="user_id", nullable = false)
 private User user;

 public User getUser() {
  return user;
 }

 public void setUser(User user) {
  this.user = user;
 }
}

