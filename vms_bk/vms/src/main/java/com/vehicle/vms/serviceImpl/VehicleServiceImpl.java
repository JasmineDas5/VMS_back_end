package com.vehicle.vms.serviceImpl;

import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.repository.VehicleRepository;
import com.vehicle.vms.repositoryImpl.VehicleRepositoryImpl;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService
{
    @Autowired
    private VehicleRepositoryImpl vehicleRepositoryImpl;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public Integer createVehicleDetails(VehicleEntity insertVehicleDto) {
        return vehicleRepositoryImpl.createVehicleDetails(insertVehicleDto);
    }

    @Override
    public Integer updateVehicleDetails( Integer id,String vehicleName, String vehicleNo, String engineNo, String model, String rcNo, String chassisNo,Integer updatedBy) {
        return vehicleRepositoryImpl.updateVehicleDetails(id,vehicleName,vehicleNo,engineNo,model,rcNo,chassisNo,updatedBy);
    }

    @Override
    public List<VehicleDto> getAllVehicleDetails() {
        return vehicleRepositoryImpl.getAllVehicleDetails();
    }

    @Override
    public VehicleDto getVehicleDetails(Integer id) {

        return vehicleRepositoryImpl.getVehicleDetails(id);
    }

    @Override
    public VehicleEntity deleteVehicleDetails(Integer id) {
        return vehicleRepositoryImpl.deleteVehicleDetails(id);
    }

    @Override
    public List<VehicleEntity> checkVehNo(String vehicleNo){
    return vehicleRepositoryImpl.checkVehNo(vehicleNo);
    }

    @Override
    public List<VehicleEntity> checkVeh(String vehicleNo,Integer id){
        return vehicleRepositoryImpl.checkVeh(vehicleNo,id);
    }


    @Override
    public VehicleEntity mapVehicleDetails(Integer id, Integer driverId, Integer ownerId)
    {
        return vehicleRepositoryImpl.mapVehicleDetails(id,driverId,ownerId);
    }

    @Override
    public Integer getDriverAssigned(Integer id,Integer driverId)
    {
        return vehicleRepositoryImpl.getDriverAssigned(id,driverId);
    }

    @Override
    public Integer getDriverUnassigned(Integer id)
    {
        return vehicleRepositoryImpl.getDriverUnassigned(id);
    }

}
