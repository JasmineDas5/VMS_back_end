package com.vehicle.vms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.vms.dto.VmsResponse;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.service.DocumentsService;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/vms/vehicle")
public class MainDocController
{
    @Autowired
    private DocumentsService service;

    @PostMapping("/createDetails")
    public VmsResponse createDetails(@RequestBody String data) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {

            ObjectMapper mapper = new ObjectMapper();
            DocumentsEntity insertDetailsDto = mapper.readValue(data,DocumentsEntity.class);

            List<DocumentsEntity> allUserDetails = service.userIdDetails(insertDetailsDto.getUserId());

            if (allUserDetails.size() >0)
            {
                vmsResponse.setStatus(0);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("Already this user id Exist!!!");
            } else
            {
                Integer insertDetails = service.createDetails(insertDetailsDto);
                if(insertDetails==1)
                {
                    result.put("creatingDetails", insertDetails);
                    vmsResponse.setData(result);
                    vmsResponse.setStatus(1);
                    vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                    vmsResponse.setMessage("All details are inserted.");

                }
                else {
                    vmsResponse.setStatus(0);
                    vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                    vmsResponse.setMessage("the details already exist");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/updateDocDetails")
    public  VmsResponse updateDocDetails(@RequestParam Integer id,
                                        @RequestParam Integer userId,
                                        @RequestParam String panCard,
                                        @RequestParam String aadharCard,
                                        @RequestParam String dl,
                                         @RequestParam  Integer updatedBy)
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer editDetails = service.updateDocDetails(id,userId,panCard,aadharCard,dl,updatedBy);
            result.put("after user details are edited", editDetails);
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

    @PostMapping("/getAllDetails")
    public VmsResponse getAllDetails() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<DocumentsEntity> data = service.getAllDetails();
            result.put("AllDetails", data);
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

    @PostMapping("/getDetails")
    public VmsResponse getDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            DocumentsEntity someDetails = service.getDetails(id);
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

        @PostMapping("/deleteDocDetails")
    public VmsResponse deleteDocDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            DocumentsEntity deleteData = service.deleteDocDetails(id);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Data Deleted Successfully!!!");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

}
