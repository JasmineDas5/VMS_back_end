package com.vehicle.vms.repository;

import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VehicleRepository extends JpaRepository<VehicleEntity,Integer> {
}
