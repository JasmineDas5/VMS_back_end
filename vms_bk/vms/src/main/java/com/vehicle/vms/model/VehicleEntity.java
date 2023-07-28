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
@Table(name="vehicle")
public class VehicleEntity {
    @Id
    @SequenceGenerator(name="vehicle_sequence",sequenceName = "vehicle_id_seq",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="vehicle_sequence")
    @Column(name="id")
    private Integer id;

    @Column(name="vehicle_name")
    private String vehicleName;

    @Column(name="vehicle_no")
    private String vehicleNo;

    @Column(name="model")
    private String model;

    @Column(name="rc_no")
    private String rcNo;

    @Column(name="chassis_no")
    private String chassisNo;

    @Column(name="engine_no")
    private String engineNo;

    @Column(name="driver_id")
    private Integer driverId;

    @Column(name="owner_id")
    private Integer ownerId;

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
