package com.admindesk.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LEAVE_RECORD")
public class LeaveRecord {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LEAVE_ID")
    private Long leaveId;


    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "LEAVE_EMAIL_TO")
    private String  leaveEmailTo;


    @Column(name = "LEAVE_SUBJECT")
    private String leaveSubject;

    @Column(name = "LEAVE_FROM_DATE")
    private Date leaveFromDate;

    @Column(name = "LEAVE_To_DATE")
    private Date leaveToDate;

    @Column(name = "LEAVE_STATUS")
    private String leaveStatus;

    @Column(name = "NO_OF_LEAVES")
    private int noOfLeaves;

    @Column(name = "LEAVE_REASON")
    private String leaveReason;


}
