package com.admindesk.controllers;

import com.admindesk.models.LeaveRecord;
import com.admindesk.security.services.ILeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/leave")
@RestController
public class LeaveRestController {


    @Autowired
    private ILeaveService iLeaveService;

    @GetMapping("/findAll")
    public List<LeaveRecord> findAll()
    {
        return this.iLeaveService.findAll();
    }

    @GetMapping("/checkForStatus")
    public List<LeaveRecord> checkForStatus(@RequestParam String  userId)
    {
        return  this.iLeaveService.checkForStatus((Long)Long.parseLong(userId));
    }

    @PostMapping("/create")
    public LeaveRecord create(@RequestBody LeaveRecord leaveRecord)
    {
        return this.iLeaveService.create(leaveRecord);
    }


    @DeleteMapping("/deleteLeaveRecordById")
    public void deleteLeaveRecordById(Long leaveId)
    {
        this.iLeaveService.deleteLeaveRecordById(leaveId);
    }

    @PostMapping("/applyForLeave")
    public LeaveRecord applyForLeave(@RequestParam  String userId,@RequestBody LeaveRecord leaveRecord)
    {

        return this.iLeaveService.applyForLeave(leaveRecord,(Long)Long.parseLong(userId));
    }

    @PostMapping("/updateLeaveStatus")
    public void updateLeaveStatus(@RequestParam Long leaveId,@RequestParam String leaveStatus)
    {
        this.iLeaveService.updateLeaveStatus(leaveId,leaveStatus);
    }

}
