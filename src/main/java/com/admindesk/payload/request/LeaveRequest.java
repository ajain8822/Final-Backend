package com.admindesk.payload.request;

import java.util.Date;

public class LeaveRequest {
    private String employeeId;
    private Date startDate;
    private Date endDate;
    private String leaveBalance;
    private String status;
    private String discription;

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


    }
