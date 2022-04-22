package com.admindesk.controllers;

import com.admindesk.models.LeaveModel;
import com.admindesk.models.User;
import com.admindesk.payload.request.LeaveRequest;
import com.admindesk.repository.LeaveRepository;
import com.admindesk.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LeaveController {

@Autowired
LeaveRepository leaveRepository;

@Autowired
IUserDao userRepository;

@PostMapping("/apply-leave/{id}")
public ResponseEntity<?> applyLeave(@PathVariable("id") long id, @RequestBody LeaveRequest leaveRequest) {
    LeaveModel leave = new LeaveModel(leaveRequest.getEmployeeId(), leaveRequest.getStartDate(), leaveRequest.getEndDate(), leaveRequest.getLeaveBalance(),leaveRequest.getStatus(),leaveRequest.getDiscription());
    User user = userRepository.findById(id).get();
    leave.setUser(user);
    leaveRepository.save(leave);
    return ResponseEntity.ok("Applied Successfully");
}

/*@GetMapping("/leave-list")
public ResponseEntity<List<LeaveModel>> getAllLeave(){
    return ResponseEntity.ok(leaveRepository.findAll());
}*/

    @GetMapping("/leave-list")
    public List<LeaveModel>getallLeave(){
      return leaveRepository.findAll();}

}








