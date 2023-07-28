package com.vehicle.vms.service;

import com.vehicle.vms.model.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    Integer createRoleDetails(RoleEntity inserRoleDto);

    List<RoleEntity> getAllRoleDetails();

    RoleEntity getRoleDetails(Integer id);

    Integer updateRoleDetails(Integer id, String roleName,Integer updatedBy);


    RoleEntity deleteRoleDetails(Integer id);

    List<RoleEntity> getRoleDropdown();
}
