package com.admindesk.controllers;

import com.admindesk.models.LeaveModel;
import com.admindesk.models.User;
import com.admindesk.payload.request.LeaveRequest;
import com.admindesk.payload.response.MessageResponse;
import com.admindesk.repository.LeaveRepository;
import com.admindesk.security.services.UserDetailsServiceImpl;
import com.admindesk.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LeaveController {

@Autowired
LeaveRepository leaveRepository;

@Autowired
IUserDao userRepository;

@Autowired
UserDetailsServiceImpl userServiceImpl;

@PostMapping("/apply-leave/{id}")
public ResponseEntity<?> applyLeave(@PathVariable("id") long id, @RequestBody LeaveRequest leaveRequest) {
    LeaveModel leave = new LeaveModel(leaveRequest.getEmployeeId(), leaveRequest.getStartDate(), leaveRequest.getEndDate(), leaveRequest.getLeaveBalance(),
    		leaveRequest.getStatus(),leaveRequest.getDiscription(),leaveRequest.getCount());
    User user = userRepository.findById(id).get();
//    user.setLeaveBalance(user.getLeaveBalance() - Integer.parseInt(leaveRequest.getCount()));
//    userRepository.save(user);
//    leave.setLeaveBalance(String.valueOf(leave.getLeaveBalance()));
    leave.setUser(user);
    leaveRepository.save(leave);
    System.out.println(leaveRequest.getEmailId());
    userServiceImpl.sendEmail(leaveRequest.getEmailId(),"Leave Applied Successfully","Dear " + user.getFirstname() + " You have applied leave"
    		+ "from " + leaveRequest.getStartDate() + " to " + leaveRequest.getEndDate());
    userServiceImpl.sendEmail("sakshisikarwar967@gmail.com",user.getFirstname() +" applied Leave", "Employee "+ user.getFirstname()
   + " Employee Id "+ user.getEmployeeId()+ " applied for leave");
    return ResponseEntity.ok(new MessageResponse("Leave applied successfully!"));
}

/*@GetMapping("/leave-list")
public ResponseEntity<List<LeaveModel>> getAllLeave(){
    return ResponseEntity.ok(leaveRepository.findAll());
}*/

    @GetMapping("/leave-list")
    public List<LeaveModel>getallLeave(){
      return leaveRepository.findAll();}
    
    @GetMapping("/get-leave/{id}")
    public List<LeaveModel> getUserLeave(@PathVariable("id") Long id){
    	return leaveRepository.getAllLeave(String.valueOf(id));
    }
    
    @GetMapping("get-user-by-leave/{empId}")
    public List<User> getUserByLeave(@PathVariable("empId") String empId) {
    	return userRepository.getUserByEmpId(empId);
    }
    

}








