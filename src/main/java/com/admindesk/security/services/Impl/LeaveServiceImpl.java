package com.admindesk.security.services.Impl;

import com.admindesk.models.LeaveRecord;
import com.admindesk.models.User;
import com.admindesk.repository.ILeaveDao;
import com.admindesk.repository.IUserDao;
import com.admindesk.security.services.ILeaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LeaveServiceImpl implements ILeaveService {


    @Autowired
    private ILeaveDao iLeaveDao;

    @Autowired
    private IUserDao iUserDao;


    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<LeaveRecord> findAll() {
        return this.iLeaveDao.findAll();
    }

    @Override
    public LeaveRecord create(LeaveRecord leaveRecord) {
        return this.iLeaveDao.save(leaveRecord);
    }


    @Override
    public void deleteLeaveRecordById(Long leaveId) {
        this.iLeaveDao.deleteById(leaveId);
    }

    @Override
    public LeaveRecord applyForLeave(LeaveRecord leaveRecord,Long userId) {

        User user=iUserDao.getById(userId);
        log.info("Apply for leave");
        leaveRecord.setLeaveStatus("Pending");
        leaveRecord.setUser(user);
        this.iLeaveDao.save(leaveRecord);

       // sendEmail(leaveRecord.getLeaveEmailTo(), leaveRecord.getLeaveSubject(),"Request For Leave");
        log.info("Email Sent Successfully");
        return leaveRecord;
    }

    @Override
    public void updateLeaveStatus(Long parseLong,String leaveStatus) {
        LeaveRecord leaveRecord=null;
        User user=null;
        try {
            leaveRecord=iLeaveDao.getById(parseLong);
            user=leaveRecord.getUser();
        }catch (Exception e)
        {
            log.error("getById not found data");
        }
        if(leaveStatus.equals("Accepted"))
        {
            leaveRecord.setLeaveStatus(leaveStatus);

            user.setLeaveTaken(user.getLeaveTaken()+ leaveRecord.getNoOfLeaves());

            int val= user.getLeaveTaken();
            if(val>12)
            {
                val= val-12;
            }else
            {
                val=0;
            }
            user.setExtraLeave(val);

            int leaveBalance=user.getLeaveBalance();
            leaveBalance=leaveBalance-leaveRecord.getNoOfLeaves();
            if(leaveBalance<0)
            {
                user.setExtraLeave(user.getExtraLeave()-(-leaveBalance));
                leaveBalance=0;
            }
            user.setLeaveBalance(leaveBalance);

            this.iLeaveDao.save(leaveRecord);
        }
        if(leaveStatus.equals("Rejected"))
        {
            leaveRecord.setLeaveStatus(leaveStatus);
            this.iLeaveDao.save(leaveRecord);
        }
    }

    @Override
    public List<LeaveRecord> checkForStatus(Long userId)   {
        User user=null;
        try {

            user = iUserDao.getById(userId);

        }catch (Exception e)
        {
            log.error("user id is incorrect");

        }

        return this.iLeaveDao.checkForStatus(user.getId());
    }


    public void sendEmail(String toEmail,String subject,String body)
    {
        System.out.println("SimpleEmail Start");

        SimpleMailMessage message=new SimpleMailMessage();

        message.setFrom(toEmail);
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);


        System.out.println("Mail Sended");

    }


}
