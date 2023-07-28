package com.vehicle.vms.serviceImpl;

import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.repository.VehicleRepository;
import com.vehicle.vms.repositoryImpl.RoleRepositoryImpl;
import com.vehicle.vms.repositoryImpl.UserRepositoryImpl;
import com.vehicle.vms.service.DocumentsService;
import com.vehicle.vms.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepositoryImpl roleRepositoryImpl;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Integer createRoleDetails(RoleEntity insertRoleDto) {
        return roleRepositoryImpl.createRoleDetails(insertRoleDto);
    }

    @Override
    public List<RoleEntity> getAllRoleDetails() {
        return roleRepositoryImpl.getAllRoleDetails();
    }

    @Override
    public RoleEntity getRoleDetails(Integer id) {

        return roleRepositoryImpl.getRoleDetails(id);
    }

    @Override
    public Integer updateRoleDetails(Integer id,String roleName,Integer updatedBy) {
        return roleRepositoryImpl.updateRoleDetails(id,roleName,updatedBy);
    }
    @Override
    public RoleEntity deleteRoleDetails(Integer id) {
        return roleRepositoryImpl.deleteRoleDetails(id);
    }

    @Override
    public List<RoleEntity> getRoleDropdown()
    {
        return roleRepositoryImpl.getRoleDropdown();
    }


}
