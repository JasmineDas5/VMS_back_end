package com.vehicle.vms.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Integer id;

    private String vehicleName;

    private String vehicleNo;

    private String model;

    private String rcNo;

    private String chassisNo;

    private String engineNo;

    private String fullName;

    private Integer driverId;

    private Integer ownerId;

    private Boolean isActive;

    private Integer createdBy;

    private Date createdOn;

    private Integer updatedBy;

    private Date updatedOn;


}
