package com.vehicle.vms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="documents")
public class DocumentsEntity {
    @Id
    @SequenceGenerator(name="documents_sequence",sequenceName = "documents_id_seq",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="documents_sequence")
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="pan_card")
    private String panCard;

    @Column(name="aadhar_no")
    private String aadharNo;

    @Column(name="dl")
    private String dl;


    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_by")
    private Integer createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_by")
    private Integer updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on")
    @CreationTimestamp
    private Date updatedOn;



}
