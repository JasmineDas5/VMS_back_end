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
@Table(name="users")
public class UserEntity {
    @Id
    @SequenceGenerator(name="user_sequence",sequenceName = "user_id_seq",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="users_sequence")
    @Column(name="id")
    private Integer id;

    @Column(name="email_id")
    private String emailId;

    @Column(name="role_id")
    private Integer roleId;

    @Column(name="full_name")
    private String fullName;

    @Column(name="role_name")
    private String roleName;

    @Column(name="phone_no")
    private BigInteger phoneNo;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

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

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_on")
//    @CreationTimestamp
    private Date updatedOn;

}
