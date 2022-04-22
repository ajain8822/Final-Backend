//package com.admindesk.models;
//
//import java.sql.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "users", 
//    uniqueConstraints = { 
//      @UniqueConstraint(columnNames = "username"),
//      @UniqueConstraint(columnNames = "email") 
//    })
//public class User {
//	
//
//	  @Id
//	  @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  private Long userId;
//
//	  @NotBlank
//	  @Size(max = 20)
//	  private String username;
//
//	  @NotBlank
//	  @Size(max = 50)
//	  @Email
//	  private String email;
//
//	  @NotBlank
//	  @Size(max = 120)
//	  private String password;
//
//	  @NotBlank
//	  @Size(max = 120)
//	  private String city;
//	  @NotBlank
//	  @Size(max = 120)
//	  private String state;
//
//	  @NotBlank
//	  private String designation;
//	  @NotBlank
//	  private String contact;
//
//	  @NotBlank
//	  private String gender;
//
//	  @NotBlank
//	  private String address;
//
//	  @NotBlank
//	  private String employeeId;
//
//	  @Temporal(TemporalType.TIMESTAMP)
//	  private Date dateOfJoining;
//
//	  @NotBlank
//	  private String firstname;
//
//	  @NotBlank
//	  @Size
//	  private String lastname;
//
//	  private String mstatus;
//
//	  private String bloodGroup;
//
//	  @Column(name="EXTRA_LEAVE")
//	  private int extraLeave;
//
//	  @Column(name = "LEAVE_BALANCE")
//	  private int leaveBalance;
//
//
//	  @Column(name = "LEAVE_TAKEN")
//	  private int leaveTaken;
//
//
//
//	  @ManyToMany(fetch = FetchType.LAZY)
//	  @JoinTable(  name = "user_roles", 
//	        joinColumns = @JoinColumn(name = "user_id"), 
//	        inverseJoinColumns = @JoinColumn(name = "role_id"))
//	  private Set<Role> roles = new HashSet<>();
//
//
//
//	public User(Long userId,  String username ,String email,String password,  String city,String state, String designation,  String contact,String gender,  String address,String employeeId, Date dateOfJoining,
//			 String firstname,String lastname, String mstatus, String bloodGroup,int extraLeave, int leaveBalance, int leaveTaken, Set<Role> roles) {
//		super();
//		this.userId = userId;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.city = city;
//		this.state = state;
//		this.designation = designation;
//		this.contact = contact;
//		this.gender = gender;
//		this.address = address;
//		this.employeeId = employeeId;
//		this.dateOfJoining = dateOfJoining;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.mstatus = mstatus;
//		this.bloodGroup = bloodGroup;
//		this.extraLeave = extraLeave;
//		this.leaveBalance = leaveBalance;
//		this.leaveTaken = leaveTaken;
//		this.roles = roles;
//	}
//	  
//
//	  
//	
//
//
//}



package com.admindesk.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @NotBlank
  @Size(max = 120)
  private String city;
  @NotBlank
  @Size(max = 120)
  private String state;

  @NotBlank
  private String designation;
  @NotBlank
  private String contact;

  @NotBlank
  private String gender;

  @NotBlank
  private String address;

  @NotBlank
  private String employeeId;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOfJoining;

  @NotBlank
  private String firstname;

  @NotBlank
  @Size
  private String lastname;

  private String mstatus;

  private String bloodGroup;
  
   @Column(name="EXTRA_LEAVE")
	  private int extraLeave;

	  @Column(name = "LEAVE_BALANCE")
	  private int leaveBalance;


	  @Column(name = "LEAVE_TAKEN")
	  private int leaveTaken;



  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String firstname , String lastname , String username, String email, String password , String employeeId, String designation,   String contact ,  String gender , Date dateOfJoining, String address ,String city ,  String state , String mstatus, String bloodGroup,int extraLeave, int leaveBalance, int leaveTaken)  {
    this.username = username;
    this.email = email;
    this.password = password;
    this.city=city;
    this.designation=designation;
    this.contact=contact;
    this.gender=gender;
    this.address=address;
    this.state=state;
    this.employeeId=employeeId;
    this.dateOfJoining=dateOfJoining;
    this.firstname=firstname;
    this.lastname=lastname;
    this.mstatus=mstatus;
    this.bloodGroup=bloodGroup;
    this.extraLeave = extraLeave;
	this.leaveBalance = leaveBalance;
	this.leaveTaken = leaveTaken;
	//this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city =city;
  }

  public String  getState(){ return state ;}

  public  void setState(String state){ this.state=state;}

  public String getDesignation(){ return designation;}

  public void setDesignation(String designation){this.designation=designation;}

  public String  getContact(){ return contact ;}

  public  void setContact(String contact){ this.contact=contact;}

  public String  getGender(){ return gender ;}

  public  void setGender(String gender){ this.gender=gender;}

  public String  getAddress(){ return address ;}

  public  void setAddress(String address){ this.address=address;}

  public String  getEmployeeId(){ return employeeId ;}

  public  void setEmployeeId(String employeeId){ this.employeeId=employeeId;}

  public Date  getDateOfJoining(){ return dateOfJoining ;}

  public  void setDateOfJoining(Date dateOfJoining){ this.dateOfJoining=dateOfJoining;}

  public String  getFirstname(){ return firstname ;}

  public  void setFirstname(String firstname){ this.firstname=firstname;}

  public String  getLastname(){ return lastname;}

  public  void setLastname(String lastname){ this.lastname=lastname;}

  public String  getMstatus(){ return mstatus;}

  public  void setMstatus(String status){ this.mstatus=mstatus;}

  public String  getBloodGroup(){ return bloodGroup;}

  public  void setBloodGroup(String bloodGroup){ this.bloodGroup=bloodGroup;}
  
   public int  getExtraLeave(){ return extraLeave;}

  public  void setExtraLeave(int extraLeave){ this.extraLeave=extraLeave;}
  
  public int  getLeaveBalance(){ return leaveBalance;}

  public  void setLeaveBalance(int leaveBalance){ this.leaveBalance=leaveBalance;}
  
  public int  getLeaveTaken(){ return leaveTaken;}

  public  void setLeaveTaken(int leaveTaken){ this.leaveTaken=leaveTaken;}
  
  




  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }
}

