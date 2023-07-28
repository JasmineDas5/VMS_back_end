package com.vehicle.vms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.vms.dto.*;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.service.UserService;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;
import static java.util.regex.Pattern.matches;


@Slf4j
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/vms/vehicle")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String uploadDir = new ClassPathResource("C:/xampp/htdocs/VMS/uploads/").getPath();


    @PostMapping("/createUserDetails")
    public VmsResponse createUserDetails(@RequestBody String data) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserEntity insertUserDto = mapper.readValue(data, UserEntity.class);
            Integer insertUserDetails = service.createUserDetails(insertUserDto);
            result.put("creatingDetails", insertUserDetails);
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

    @PostMapping("/updateUserDetails")
    public  VmsResponse updateUserDetails(@RequestParam Integer id,
                                          @RequestParam(required = false) String fullName,
                                          @RequestParam(required = false)  String emailId,
                                          @RequestParam(required = false)  BigInteger phoneNo,
                                          @RequestParam(required = false)  String address,
                                          @RequestParam(required = false)  Integer updatedBy,
                                          @RequestParam(required = false)  Integer roleId)
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            Integer editUserDetails = service.updateUserDetails(id,fullName,emailId,phoneNo,address,updatedBy,roleId);
            result.put("userUpdated", editUserDetails);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("All details are displayed after editing");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getAllUserDetails")
    public VmsResponse getAllUserDetails() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<UserDto> dataUser = service.getAllUserDetails();
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

    @PostMapping("/getUserDetails")
    public VmsResponse getUserDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {

            DocumentsDto panImage = service.getImgByUserId(id);

                UserDto someDetails = service.getUserDetails(id);
                result.put("SomeDetails", someDetails);
                if(panImage != null && !panImage.toString().isEmpty()){
                    if(panImage.getPanCard()!=null) {
                        result.put("panImage",panImage.getPanCard());
                    }
                    if(panImage.getAadharNo() !=null) {
                        result.put("aadharImage",panImage.getAadharNo());
                    }
                    if(panImage.getDl()!=null){
                        result.put("dlImage",panImage.getDl());
                    }
                }
                vmsResponse.setData(result);
                vmsResponse.setStatus(1);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("All selected details are here");
//            }
//            else{
//                vmsResponse.setStatus(0);
//                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
//                vmsResponse.setMessage("The user details cannot be soon!!!");
//
//            }


        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/deleteUserDetails")
    public VmsResponse deleteUserDetails(@RequestParam Integer id) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            UserEntity deleteData = service.deleteUserDetails(id);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Data Deleted Successfully!!!");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/loginDetails")
    public VmsResponse loginDetails(@RequestBody String data) {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {

            ObjectMapper mapper = new ObjectMapper();
            UserEntity loginDetailsDto = mapper.readValue(data, UserEntity.class);
            UserEntity allEmailDetails = service.emailIdCheck(loginDetailsDto.getEmailId());
//            UserEntity allPasswordDetails = service.passwordCheck(loginDetailsDto.getPassword(),loginDetailsDto.getEmailId());
            Boolean encodedPassword= encoder.matches(loginDetailsDto.getPassword(),allEmailDetails.getPassword());
            if (encodedPassword )
            {
                result.put("allEmailDetails",allEmailDetails);
                vmsResponse.setData(result);
                vmsResponse.setStatus(1);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("Successfully signed in!!!");

            } else
            {
                vmsResponse.setStatus(0);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("This email id does not exist or the password is incorrect");

            }
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse ;   }


//    @PostMapping("/mappingUserDetails")
//    public VmsResponse mappingUserDetails(@RequestParam Integer id) {
//        VmsResponse vmsResponse = new VmsResponse();
//        Map<String, Object> result = new HashMap<>();
//        try {
//            VehicleDto vehicleId=VehicleDto.get
//
//            if(id==vehicleId)
//            {
//                UserEntity someDetails = service.mappingUserDetails(id);
//                result.put("User mapped", someDetails);
//                vmsResponse.setData(result);
//                vmsResponse.setStatus(1);
//                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
//                vmsResponse.setMessage("The driver is mapped to this vehicle");
//
//            }
//            else if(id==vehicleId)
//            {
//                UserEntity someDetails = service.mappingUserDetails(id);
//                result.put("User mapped", someDetails);
//                vmsResponse.setData(result);
//                vmsResponse.setStatus(1);
//                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
//                vmsResponse.setMessage("The owner is mapped to this vehicle");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
//        }
//        return vmsResponse;
//    }

    @PostMapping("/registerUserDetails")
    public VmsResponse registerUserDetails(@RequestParam String data,
                                           @RequestParam (name="panImage" ,required=false) MultipartFile panImage,
                                           @RequestParam (name="aadharImage", required=false) MultipartFile aadharImage,
                                           @RequestParam (name="dlImage" ,required=false) MultipartFile dlImage)  throws IOException
    {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserDto insertUserDto = mapper.readValue(data, UserDto.class);
            UserEntity insertUserDetails = new UserEntity();
            DocumentsEntity docModel = new DocumentsEntity();
            List<UserEntity> mailCheck=service.email(insertUserDto.getEmailId());
            if (mailCheck.size()>0)
            {
                vmsResponse.setStatus(0);
                vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                vmsResponse.setMessage("Already this email id Exist!!!");
            } else
            {
                if(insertUserDto.getPassword()!=null && !insertUserDto.getPassword().isEmpty())
                {
                    if(panImage != null || aadharImage != null || dlImage != null){
                        String encodedPw = encoder.encode(insertUserDto.getPassword());
                        insertUserDetails = service.saveRegisterUserDetails(insertUserDto, encodedPw);
                        docModel =service.registerDocumentsDetails(insertUserDetails.getId(),panImage,aadharImage,dlImage);
                        if (insertUserDetails != null || docModel != null){
                            vmsResponse.setStatus(1);
                            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                            vmsResponse.setMessage("All details are inserted.");
                        }else {
                            vmsResponse.setStatus(0);
                            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                            vmsResponse.setMessage("Data not saved.");
                        }
                    } else {
                        vmsResponse.setStatus(0);
                        vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                        vmsResponse.setMessage("Upload the files");
                    }

                }else{
                    vmsResponse.setStatus(0);
                    vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
                    vmsResponse.setMessage("Enter password.");
                }
//                result.put("creatingDetails", insertUserDetails);
//                result.put("imageDetails", docModel);

            }

        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }

    @PostMapping("/getDriverDropdown")
    public VmsResponse getDriverDropdown() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<UserDto> dto = service.getDriverDropdown();
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

    @PostMapping("/getOwnerDropdown")
    public VmsResponse getOwnerDropdown() {
        VmsResponse vmsResponse = new VmsResponse();
        Map<String, Object> result = new HashMap<>();
        try {
            List<UserDto> dto = service.getOwnerDropdown();
            result.put("allOwnerDetails", dto);
            vmsResponse.setData(result);
            vmsResponse.setStatus(1);
            vmsResponse.setStatusCode(new ResponseEntity<>(HttpStatus.OK));
            vmsResponse.setMessage("Owner details");
        } catch (Exception e) {
            e.printStackTrace();
            vmsResponse = new VmsResponse(0, new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage(), result);
        }
        return vmsResponse;
    }
}
