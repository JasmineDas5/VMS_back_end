package com.vehicle.vms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.dto.VmsResponse;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/vms/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @PostMapping("/createVehicleDetails")
    public VmsResponse createVehicleDetails(@RequestBody String data) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            VehicleEntity insertVehicleDto = mapper.readValue(data, VehicleEntity.class);
            List<VehicleEntity> vehicleNoObj=service.checkVehNo(insertVehicleDto.getVehicleNo());
            if(vehicleNoObj.size()>0)
            {
                vmsResponse.setStatus(0);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("This vehicle number already Exist!!!");
            }
            else{
                Integer insertVehicleDetails = service.createVehicleDetails(insertVehicleDto);
                result.put("creatingVehicleDetails", insertVehicleDetails);
                vmsResponse.setData(result);
                vmsResponse.setStatus(1);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("All vehicle details are inserted.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/updateVehicleDetails")
    public  VmsResponse updateVehicleDetails(@RequestParam Integer id,
                                             @RequestParam(required = false) String vehicleName,
                                             @RequestParam(required = false) String vehicleNo,
                                             @RequestParam(required = false) String engineNo,
                                             @RequestParam(required = false) String model,
                                             @RequestParam(required = false) String rcNo,
                                             @RequestParam(required = false) String chassisNo,
                                             @RequestParam(required = false) Integer updatedBy)
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<VehicleEntity> vehicleNoObj=service.checkVeh(vehicleNo,id);
            if(vehicleNoObj.size()>0)
            {
                vmsResponse.setStatus(0);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("This vehicle number already Exist!!!");
            }
            else {
                Integer editVehicleDetails = service.updateVehicleDetails(id, vehicleName, vehicleNo, engineNo, model, rcNo, chassisNo, updatedBy);
                result.put("vehicleEdited", editVehicleDetails);
                vmsResponse.setData(result);
                vmsResponse.setStatus(1);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("All details are displayed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getAllVehicleDetails")
    public VmsResponse getAllVehicleDetails() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<VehicleDto> dataUser = service.getAllVehicleDetails();
            result.put("AllDetails", dataUser);
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
    @PostMapping("/getVehicleDetails")
    public VmsResponse getVehicleDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            VehicleDto someDetails = service.getVehicleDetails(id);
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

    @PostMapping("/deleteVehicleDetails")
    public VmsResponse deleteVehicleDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            VehicleEntity deleteData = service.deleteVehicleDetails(id);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Data Deleted Successfully!!!");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/mapVehicleDetails")
    public VmsResponse mapVehicleDetails(@RequestParam Integer id,
                                          @RequestParam(required = false) Integer driverId ,
                                          @RequestParam(required = false) Integer ownerId
                                          )
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            VehicleEntity mapVehicle = service.mapVehicleDetails(id,driverId,ownerId);
            result.put("mapped vehicle with id", mapVehicle);
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
    @PostMapping("/getDriverAssigned")
    public VmsResponse  getDriverAssigned(@RequestParam Integer id,
                                          @RequestParam Integer driverId) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer dto = service.getDriverAssigned(id,driverId);
            result.put("allDriverDetails", dto);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Driver details");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getDriverUnassigned")
    public VmsResponse  getDriverUnassigned(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer dtos = service.getDriverUnassigned(id);
            result.put("noDriverAssign", dtos);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("No driver assign");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }


//    @PostMapping("/getOwnerDropdown")
//    public VmsResponse getOwnerDropdown() {
//        VmsResponse vmsResponse = new VmsResponse();
//        Map<String, Object> result = new HashMap<>();
//        try {
//            List<VehicleDto> dto = service.getOwnerDropdown();
//            result.put("allOwnerDetails", dto);
//            vmsResponse.setData(result);
//            vmsResponse.setStatus(1);
//            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
//            vmsResponse.setMessage("Owner details");
//        } catch (Exception e) {
//            e.printStackTrace();
//            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
//        }
//        return vmsResponse;
//    }


}
