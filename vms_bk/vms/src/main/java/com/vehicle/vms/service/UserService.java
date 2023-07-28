package com.vehicle.vms.service;

import com.vehicle.vms.dto.DocumentsDto;
import com.vehicle.vms.dto.UserDto;
import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public interface UserService {

    Integer createUserDetails(UserEntity insertRoleDto);

    List<UserDto> getAllUserDetails();

    UserDto getUserDetails(Integer id);

    Integer updateUserDetails(Integer id, String fullName, String emailId, BigInteger phoneNo,  String address, Integer updatedBy,Integer roleId);

    UserEntity deleteUserDetails(Integer id);

    UserEntity emailIdCheck(String emailId);

    UserEntity getUserData(Integer id);

    UserEntity saveRegisterUserDetails(UserDto insertUserDto, String encodedPw);

    DocumentsEntity registerDocumentsDetails(Integer id, MultipartFile panImage, MultipartFile aadharImage, MultipartFile dlImage) throws IOException;

    List<UserEntity> email(String emailId);

    DocumentsDto getImgByUserId(Integer id);

    List<UserDto> getDriverDropdown();

    List<UserDto> getOwnerDropdown();


}
