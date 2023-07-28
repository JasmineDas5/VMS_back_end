package com.vehicle.vms.service;

import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Service
public interface VehicleService {

    Integer createVehicleDetails(VehicleEntity insertVehicleDto);

    Integer updateVehicleDetails( Integer id,String vehicleName, String vehicleNo, String engineNo, String model, String rcNo, String chassisNo,Integer updatedBy);

    List<VehicleDto> getAllVehicleDetails();

    VehicleDto getVehicleDetails(Integer id);

    VehicleEntity deleteVehicleDetails(Integer id);

    List<VehicleEntity> checkVehNo(String vehicleNo);

    VehicleEntity mapVehicleDetails(Integer id, Integer driverId, Integer ownerId);

    Integer  getDriverAssigned(Integer id,Integer driverId);

    Integer  getDriverUnassigned(Integer id);

    List<VehicleEntity> checkVeh(String vehicleNo, Integer id);
}
