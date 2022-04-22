package com.admindesk.security.services;

import com.admindesk.models.LeaveRecord;

import java.util.List;

public interface ILeaveService {

    List<LeaveRecord> findAll();

    LeaveRecord create(LeaveRecord leaveRecord);

    void deleteLeaveRecordById(Long leaveId);

    LeaveRecord applyForLeave(LeaveRecord leaveRecord,Long userId);

    void updateLeaveStatus(Long parseLong,String leaveStatus);

    List<LeaveRecord> checkForStatus(Long userId);

}
