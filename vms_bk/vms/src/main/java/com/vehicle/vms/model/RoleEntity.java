package com.vehicle.vms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="role")
public class RoleEntity {
    @Id
    @SequenceGenerator(name="role_sequence",sequenceName = "role_id_seq",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="role_sequence")
    @Column(name="id")
    private Integer id;

    @Column(name="role_name")
    private String roleName;

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
