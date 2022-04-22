package com.admindesk.security.services;


import com.admindesk.models.User;
import com.admindesk.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  IUserDao userRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));


    sendEmail("ajain8822@gmail.com","You Registered","user name: akshay");
    return UserDetailsImpl.build(user);
  }


    public User updateEmployee(long id ,User user) {
      User employee = userRepository.findById(id).get();
      employee.setEmployeeId(user.getEmployeeId());
      employee.setFirstname(user.getFirstname());
      employee.setLastname(user.getLastname());
      employee.setUsername(user.getUsername());
      employee.setAddress(user.getAddress());
      employee.setCity(user.getCity());
      employee.setState(user.getState());
      employee.setContact(user.getContact());
      employee.setRoles(user.getRoles());
      employee.setDateOfJoining(user.getDateOfJoining());
      employee.setDesignation(user.getDesignation());
      employee.setGender(user.getGender());
      employee.setPassword(user.getPassword());

      return employee;
    }
    





  public void sendEmail(String toEmail,String subject,String body)
  {

    System.out.println("started");
    SimpleMailMessage message=new SimpleMailMessage();

    message.setFrom("ankitrakuten2022@gmail.com");
    message.setTo(toEmail);
    message.setText(body);
    message.setSubject(subject);
    javaMailSender.send(message);

    System.out.println("done" +
            "");



  }
  }

