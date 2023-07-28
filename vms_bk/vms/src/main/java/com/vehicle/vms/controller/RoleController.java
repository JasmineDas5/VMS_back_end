package com.vehicle.vms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.vms.dto.VmsResponse;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.service.RoleService;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/vms/vehicle")
public class RoleController {
    @Autowired
    private RoleService service;

    @PostMapping("/createRoleDetails")
    public VmsResponse createRoleDetails(@RequestBody String data) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            RoleEntity insertRoleDto = mapper.readValue(data, RoleEntity.class);
            Integer insertRoleDetails = service.createRoleDetails(insertRoleDto);
            result.put("creatingDetails", insertRoleDetails);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("All details are inserted.");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/updateRoleDetails")
    public  VmsResponse updateRoleDetails(@RequestParam Integer id,
                                          @RequestParam String roleName,
                                          @RequestParam Integer updatedBy)
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer editRoleDetails = service.updateRoleDetails(id,roleName,updatedBy);
            result.put("after user details are edited", editRoleDetails);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("All details are displayed");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getAllRoleDetails")
    public VmsResponse getAllRoleDetails() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<RoleEntity> datas = service.getAllRoleDetails();
            result.put("AllDetails", datas);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("All user details");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getRoleDetails")
    public VmsResponse getRoleDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            RoleEntity someDetails = service.getRoleDetails(id);
            result.put("SomeDetails", someDetails);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("All selected details are here");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/deleteRoleDetails")
    public VmsResponse deleteRoleDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            RoleEntity deleteData = service.deleteRoleDetails(id);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Data Deleted Successfully!!!");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getRoleDropdown")
    public VmsResponse getRoleDropdown() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<RoleEntity> dto = service.getRoleDropdown();
            result.put("allRoleDetails", dto);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Role details");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }
}
