//package com.admindesk.controllers;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
//import com.admindesk.models.ERole;
//import com.admindesk.models.Role;
//import com.admindesk.models.User;
//import com.admindesk.payload.request.LoginRequest;
//import com.admindesk.payload.request.SignupRequest;
//import com.admindesk.payload.response.JwtResponse;
//import com.admindesk.payload.response.MessageResponse;
//import com.admindesk.repository.RoleRepository;
//import com.admindesk.repository.IUserDao;
//import com.admindesk.security.jwt.JwtUtils;
//import com.admindesk.security.services.UserDetailsImpl;
//import com.admindesk.security.services.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//  @Autowired
//  AuthenticationManager authenticationManager;
//
//  @Autowired
//  IUserDao userRepository;
//
//  @Autowired
//  RoleRepository roleRepository;
//
//  @Autowired
//  PasswordEncoder encoder;
//
//  @Autowired
//  JwtUtils jwtUtils;
//
//  @Autowired
//  UserDetailsServiceImpl userDetailsService;
//  
//
//
//  @PostMapping("/signin")
//  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//    Authentication authentication = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//    SecurityContextHolder.getContext().setAuthentication(authentication);
//    String jwt = jwtUtils.generateJwtToken(authentication);
//    
//    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());
//
//    return ResponseEntity.ok(new JwtResponse(jwt,
//                         userDetails.getId(), 
//                         userDetails.getUsername(), 
//                         userDetails.getEmail(),
//                         roles));
//  }
//
//  @PostMapping("/signup")
//  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Username is already taken!"));
//    }
//
//    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//      return ResponseEntity
//          .badRequest()
//          .body(new MessageResponse("Error: Email is already in use!"));
//    }
//
//    // Create new user's account
//    User user = new User(signUpRequest.getFirstname(),
//    		signUpRequest.getLastname(),
//    		signUpRequest.getUsername(),
//    		signUpRequest.getEmail(),
//    		encoder.encode(signUpRequest.getPassword()),
//            signUpRequest.getEmployeeId(),
//            signUpRequest.getDesignation(),
//            signUpRequest.getContact(),
//            signUpRequest.getGender(),
//            signUpRequest.getDateOfJoining(),
//            signUpRequest.getAddress(),
//            signUpRequest.getCity(),
//            signUpRequest.getState(),
//            signUpRequest.getMstatus(),
//            signUpRequest.getBloodGroup());
//
//    Set<String> strRoles = signUpRequest.getRole();
//
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(adminRole);
//
//          break;
//        case "manager":
//          Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(managerRole);
//
//          break;
//        default:
//          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//          roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
//    userRepository.save(user);
//
//    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//  }
//
//    @GetMapping("/showusers")
//  public List<User> getAllEmployee(){
//    return  userRepository.findAll();
//
//
//  }
// @PostMapping("/update-user/{id}")
//  public ResponseEntity<User> updateEmployee(@PathVariable long id , @RequestBody User user) {
//  User employee = userDetailsService.updateEmployee(id,user);
//  userRepository.save(employee);
//  return ResponseEntity.ok(employee);
//
// }
// @PostMapping("/delete-user/{id}")
//  public ResponseEntity<User> deleteEmployee(@PathVariable long id ) {
//   User employee = userRepository.findById(id).get();
//   userRepository.delete(employee);
//   return ResponseEntity.ok(employee);
// }
//
// }
//
//
//
//
//
//
//
package com.admindesk.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.admindesk.models.ERole;
import com.admindesk.models.LeaveModel;
import com.admindesk.models.Role;
import com.admindesk.models.User;
import com.admindesk.payload.request.LoginRequest;
import com.admindesk.payload.request.SignupRequest;
import com.admindesk.payload.response.JwtResponse;
import com.admindesk.payload.response.MessageResponse;
import com.admindesk.repository.IUserDao;
import com.admindesk.repository.LeaveRepository;
import com.admindesk.repository.RoleRepository;
//import com.admindesk.repository.UserRepository;
import com.admindesk.security.jwt.JwtUtils;
import com.admindesk.security.services.UserDetailsImpl;
import com.admindesk.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

//  @Autowired
//  UserRepository userRepository;
  @Autowired
  IUserDao userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  LeaveRepository leaveRepository;
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getFirstname(),signUpRequest.getLastname(),signUpRequest.getUsername(),signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getEmployeeId(),signUpRequest.getDesignation(),signUpRequest.getContact(),
            signUpRequest.getGender(),signUpRequest.getDateOfJoining(),signUpRequest.getAddress(),
            signUpRequest.getCity(),signUpRequest.getState(),signUpRequest.getMstatus(),signUpRequest.getBloodGroup(),signUpRequest.getExtraLeave(),signUpRequest.getLeaveBalance(),signUpRequest.getLeaveTaken());

    Set<String> strRoles = signUpRequest.getRole();

    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "manager":
          Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(managerRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

    @GetMapping("/showusers")
  public List<User> getAllEmployee(){
    return  userRepository.findAll();


  }
 @PostMapping("/update-user/{id}")
  public ResponseEntity<User> updateEmployee(@PathVariable long id , @RequestBody User user) {
  User employee = userDetailsService.updateEmployee(id,user);
  userRepository.save(employee);
  return ResponseEntity.ok(employee);

 }
 
 
 @GetMapping("/show-leaves")
 public List<LeaveModel> leaveList(){
	 return leaveRepository.findAll();
 }
 
 @PostMapping("/delete-user/{id}")
  public ResponseEntity<User> deleteEmployee(@PathVariable long id ) {
   User employee = userRepository.findById(id).get();
   userRepository.delete(employee);
   return ResponseEntity.ok(employee);
 }

 @PostMapping("/change-status/{id}")
 public ResponseEntity<String> changeStatus(@PathVariable long id){
	 LeaveModel leave = leaveRepository.findById(id).get();
	 if(leave.getStatus().equals("Not Approved")) {
		 leave.setStatus("Approved");
	 }
	 else {
		 leave.setStatus("Not Approved");
	 }
	 
	leaveRepository.save(leave); 
	 return new ResponseEntity<String>("Status Changed", HttpStatus.OK);
 }
 }







